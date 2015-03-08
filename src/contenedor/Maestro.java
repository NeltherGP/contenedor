package contenedor;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author nelther
 */
public class Maestro {
    int llave;
    FileManager manager = new FileManager();
    RandomAccessFile maestro,indice;
    String URLArchivoMaestro="archivos/maestro";
    String URLArchivoIndice="archivos/indice";

    String antecedente  [] = new String [5];
    String operadoresAnt [] = new String [4];
    String consecuente [] = new String [5];
    String operadoresCons[]= new String [4];

    
    void insertar (int llave, String registro ) throws IOException{
        
        procesarRegistro(registro);
        //Considerar Eliminados        
        maestro=manager.Abrir(URLArchivoMaestro);
        indice=manager.Abrir(URLArchivoIndice);
        
        boolean eliminado=false;
        indice.seek(0);
        do {            
            if (indice.readInt()<0) {
                eliminado=true;
            }
        } while (indice.getFilePointer()<indice.length() | eliminado==true);
        
        if (eliminado) {
             indice.seek(indice.getFilePointer());
             maestro.seek(indice.readInt());
             indice.writeInt(llave);
             indice.writeInt((int)(maestro.getFilePointer()));
        }else{
             maestro.seek(maestro.length());
             indice.seek(indice.length());
             indice.writeInt(llave);
             indice.writeInt((int)(maestro.length()));
        }
        
        maestro.writeInt(llave);
        for (int i = 0; i < antecedente.length; i++) {
            manager.m_EscribirString(antecedente[i], 3, maestro);
            if(i<antecedente.length-1){
                manager.m_EscribirString(operadoresAnt[i], 1, maestro);
            }
            
        }
        
        for (int i = 0; i < consecuente.length; i++) {
            manager.m_EscribirString(consecuente[i], 3, maestro);
            if(i<consecuente.length-1){
                manager.m_EscribirString(operadoresCons[i], 1, maestro);
            }
            
        }
        
    }
    
    void procesarRegistro(String cadena){//SEPARAR LA CADENA 
        String campo="";
        int i=0, indice=0;
        do{
            if(cadena.charAt(i)!='&' | cadena.charAt(i)!='|' ){
                campo+=cadena.charAt(i);
            }else{
                     antecedente[indice]=campo;
                     operadoresAnt[indice]=String.valueOf(cadena.charAt(i));
                     campo="";
                     indice++;
            }
            i++;
        }while (cadena.charAt(i)!='-');
        antecedente[indice]=campo;
        campo="";
        indice=0;
        
        do{
            i++;
            if(cadena.charAt(i)!='&' | cadena.charAt(i)!='|' ){
                campo+=cadena.charAt(i);
            }else{
                     consecuente[indice]=campo;
                     operadoresCons[indice]=String.valueOf(cadena.charAt(i));
                     campo="";
                     indice++;
            }
        }while (i<cadena.length());
         consecuente[indice]=campo;
    }
    
    void modificar(){
        
    }
    
    void eliminar(int llave) throws IOException{
        indice=manager.Abrir(URLArchivoIndice);
        boolean eliminado=false;
        indice.seek(0);
        do {            
            if (indice.readInt()==llave) {
                indice.seek(indice.getFilePointer());
                indice.writeInt(((-1)*llave));
                eliminado=true;
            }
            indice.readInt();
        } while (indice.getFilePointer()<indice.length() | eliminado==true);
        
    }
    
    void mostrarMaestro(){
        
    }
    
    void mostrarIndice(){
        
    }
}
