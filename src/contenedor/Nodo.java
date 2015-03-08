package contenedor;

/**
 *
 * @author nelther
 */
public class Nodo {
int llave,pos;

Nodo izq;
Nodo der;

    public Nodo (int llave, int pos){
        this.llave=llave;
        this.pos = pos;
        izq=null;
        der=null;
    }

    public void set_izq(Nodo newIzq){
        izq=newIzq;
    }

    public void set_der(Nodo newDer){
        der=newDer;
    }
    
    public int getLlave(){
        return llave;
    }
    
    public int getPos(){
        return pos;
    }
}
