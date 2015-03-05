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
        
        maestro.seek(maestro.length());
        indice.seek(indice.length());
        indice.writeInt(llave);
        indice.writeInt((int)(maestro.length()));
        
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
        //Llenar los arreglos segun correnda
        
    }
    
    void modificar(){
        
    }
    
    void eliminar(){
        
    }
    
    void mostrarMaestro(){
        
    }
    
    void mostrarIndice(){
        
    }
}