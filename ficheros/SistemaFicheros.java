package ficheros;

import java.util.ArrayList;

public class SistemaFicheros {
    public int cantidadClusters;
    public ArrayList<EntradaFAT> entradas;
    public Directorio root;
    public ArrayList<Cluster> clusters;

    public SistemaFicheros(int cantidadClusters) {
        this.cantidadClusters = cantidadClusters;
        this.entradas = new ArrayList<EntradaFAT>();
        for(int i = 0; i < cantidadClusters; i++) {

        }
        this.root = new Directorio(0);
        this.clusters = new ArrayList<Cluster>();
    }    

    public void formatear (){
        for (int i = 0; i < cantidadClusters; i++) {
            entradas.add(new EntradaFAT(true, 0, false));
        }
        entradas.get(0).disponible = false;
        entradas.get(0).end = true;
        root = new Directorio(0);
        clusters = new ArrayList<Cluster>();
        clusters.add(root);
        
    }
}