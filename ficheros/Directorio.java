package ficheros;

import java.util.ArrayList;

public class Directorio extends Cluster{
    ArrayList<EntradaDir> entradas;
    public Directorio(int numCluster) {
        super(numCluster);
        entradas = new ArrayList<EntradaDir>();
    }
}