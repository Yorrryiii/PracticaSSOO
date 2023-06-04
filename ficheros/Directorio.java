package ficheros;

import java.util.ArrayList;

public class Directorio extends Cluster {

    public String nombre;
    public ArrayList<EntradaDir> entradas;

    public Directorio(String nombre, int numCluster) {
        super(numCluster);
        this.nombre = nombre;
        entradas = new ArrayList<EntradaDir>();
    }
}