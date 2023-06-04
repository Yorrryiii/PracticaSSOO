package ficheros;

import java.util.ArrayList;

public class SistemaFicheros {
    public int cantidadClusters;
    public ArrayList<EntradaFAT> entradas;
    public Directorio root;
    public ArrayList<Cluster> clusters;
    public ArrayList<Cluster> clustersLibres;

    public SistemaFicheros(int cantidadClusters) {
        this.cantidadClusters = cantidadClusters;
        this.entradas = new ArrayList<EntradaFAT>();
        for(int i = 0; i < cantidadClusters; i++) {
            entradas.add(new EntradaFAT());
        }
        this.root = new Directorio("root", -1); //Numero de cluster -1 porque es el root
        this.clusters = new ArrayList<Cluster>();
        this.clustersLibres = new ArrayList<Cluster>();
        //Finalmente inicializaremos los clusters
        for (int i = 0; i < cantidadClusters; i++) {
            clusters.add(new Cluster(i));
            clustersLibres.add(clusters.get(i));
        }
    }    

    public void formatear() {
        for (int i = 0; i < cantidadClusters; i++) {
            entradas.get(i).disponible = true; // Marcamos todas las entradas como disponibles
        }
        root = new Directorio("root", -1); // Numero de cluster -1 porque es el root
        
        this.clustersLibres = new ArrayList<Cluster>();
        for (int i = 0; i < cantidadClusters; i++) {
            clustersLibres.add(clusters.get(i)); //Añadimos todos los clusters al array de clusters libres
        }
        
    }

    public void crearArchivo(String nombreDelArchivo, int tamanoArchivoEnClusters, 
            String nombreDirectorioDondeGuardarArchivo) {
        ArrayList<Cluster> listaClustersLibres = buscarClustersLibres(tamanoArchivoEnClusters);

        escribirDatosArchivoEnClustersLibres(nombreDelArchivo, listaClustersLibres);

        escribirEntradasFAT(listaClustersLibres);

        Directorio  directorioDondeGuardarArchivo = buscarDirectorioPorNombre(nombreDirectorioDondeGuardarArchivo);

        crearEntradaDirectorioEnDirectorio(nombreDelArchivo, false, listaClustersLibres.get(0));

    }

    private void escribirDatosArchivoEnClustersLibres(String nombreDelArchivo, ArrayList<Cluster> listaClustersLibres) {
    }

    private ArrayList<Cluster> buscarClustersLibres(int tamanoArchivoEnClusters) {
        return null;
    }

    private void crearEntradaDirectorioEnDirectorio(String nombreDelArchivo, boolean esDirectorio, Cluster cluster) {
    }

    private Directorio buscarDirectorioPorNombre(String nombreDirectorioDondeGuardarArchivo) {
        return null;
    }

    private void escribirEntradasFAT(ArrayList<Cluster> listaClustersLibres) {
    }

}