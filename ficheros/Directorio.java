package ficheros;

import java.util.ArrayList;

public class Directorio extends Cluster {

    public String nombre;
    public ArrayList<EntradaDir> entradasDIR;

    public Directorio(String nombre, int numCluster) {
        super(numCluster);
        this.nombre = nombre;
        entradasDIR = new ArrayList<EntradaDir>();
    }

    // public void addEntrada(EntradaDir entrada) {
    //     entradasDIR.add(entrada);
    // }

    public ArrayList<EntradaDir> getEntradasDIR() {
        return entradasDIR;
    }

    public void setEntradasDIR(ArrayList<EntradaDir> entradasDIR) {
        this.entradasDIR = entradasDIR;
    }
    
    @Override
    public String toString() {
        return "Directorio [nombre=" + nombre + ", entradasDIR=" + entradasDIR + "]";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}