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
            entradas.add(new EntradaFAT());
        }
        this.root = new Directorio("root", -1); //Numero de cluster -1 porque es el root
        this.clusters = new ArrayList<Cluster>();
        for(int i = 0; i < cantidadClusters; i++) {
            clusters.add(new Cluster(i));
        }
    }    

    public void formatear() {
        for (int i = 0; i < cantidadClusters; i++) {
            entradas.get(i).disponible = true; // Marcamos todas las entradas como disponibles
        }
        root = new Directorio("root", -1); // Numero de cluster -1 porque es el root
    }

    public void crearDirectorio(String nombreDirectorio, String nombreDirectorioDondeGuardarArchivo) {

        ArrayList<Cluster> listaClustersLibres = buscarClustersLibres(1); //Un directorio solo ocupa un cluster
        escribirEntradasFAT(listaClustersLibres);
        Directorio  directorioDondeGuardarArchivo = buscarDirectorioPorNombre(nombreDirectorioDondeGuardarArchivo);
        crearEntradaDirectorioEnDirectorio(nombreDirectorio, true, listaClustersLibres.get(0));
        
    }

    public void crearArchivo(String nombreDelArchivo, int tamanoArchivoEnClusters, 
            String nombreDirectorioDondeGuardarArchivo) {
                
        ArrayList<Cluster> listaClustersLibres = buscarClustersLibres(tamanoArchivoEnClusters);

        if(listaClustersLibres.size() < tamanoArchivoEnClusters) {
            System.err.println("Error: no hay suficientes clusters libres para crear el archivo, APRENDE A CONTAR");
        }
        else {
            escribirDatosArchivoEnClustersLibres(nombreDelArchivo, listaClustersLibres);

            escribirEntradasFAT(listaClustersLibres);
            
            Directorio  directorioDondeGuardarArchivo = buscarDirectorioPorNombre(nombreDirectorioDondeGuardarArchivo);
            System.out.println("Esta es tu carpeta mi REY: " + directorioDondeGuardarArchivo.nombre);
            //crearEntradaDirectorioEnDirectorio(nombreDelArchivo, false, listaClustersLibres.get(0));
        }

    }

    private void escribirDatosArchivoEnClustersLibres(String nombreDelArchivo, ArrayList<Cluster> listaClustersLibres) {
        for(int i = 0; i < listaClustersLibres.size(); i++) {
            for(int j = 0; j < cantidadClusters; j++) {
                if(listaClustersLibres.get(i).equals(clusters.get(j))) {
                    Cluster aux = clusters.get(j);
                    clusters.remove(aux);
                    clusters.add(j, new ParteArchivo(j, nombreDelArchivo)); //Restamos 1 ya que se esta cargando el cluster que habia
                }
            }
        }
    }

    private ArrayList<Cluster> buscarClustersLibres(int tamanoArchivoEnClusters) {
        int counterClusters = 0;
        ArrayList<Cluster> clustersLibres = new ArrayList<Cluster>();
        for(int i = 0; i < cantidadClusters; i++) {
            if(counterClusters == tamanoArchivoEnClusters) {
                break;
            }
            if(entradas.get(i).disponible == true) {
                clustersLibres.add(clusters.get(i));
                counterClusters++;
            }
        }
        return clustersLibres;
    }

    private void crearEntradaDirectorioEnDirectorio(String nombreDelArchivo, boolean esDirectorio, Cluster cluster) {
    }

    private Directorio buscarDirectorioPorNombre(String nombreDirectorioDondeGuardarArchivo) {
        //Ruta donde buscar el directorio
        if(nombreDirectorioDondeGuardarArchivo.equals("root")) {
            return this.root;
        }else{
            return buscarDirectorioRecursivo(nombreDirectorioDondeGuardarArchivo, this.root);
        }
    }
    
    private Directorio buscarDirectorioRecursivo(String nombreDirectorioDondeGuardarArchivo, Directorio current) {

        for(int i = 0; i < current.entradasDIR.size(); i++) {
            if(current.entradasDIR.get(i).esDirectorio) {
                if(current.entradasDIR.get(i).nombre.equals(nombreDirectorioDondeGuardarArchivo)) {
                    System.out.println("Eres medio bobo y el directorio no existe");
                    return (Directorio) clusters.get(current.entradasDIR.get(i).clusterInicio);
                }
                else {
                    return buscarDirectorioRecursivo(nombreDirectorioDondeGuardarArchivo, (Directorio) 
                                clusters.get(current.entradasDIR.get(i).clusterInicio));
                }
            }
        }
        return null; // El directorio no fue encontrado
    }
        
        
   
    private void escribirEntradasFAT(ArrayList<Cluster> listaClustersLibres) {
        
        for(int i = 0; i < listaClustersLibres.size(); i++) {
            int indexOfCluster = listaClustersLibres.get(i).numCluster;
            EntradaFAT current = entradas.get(indexOfCluster);
            current.disponible = false;
            if(i == listaClustersLibres.size() - 1) {
                current.fin = true;
            }
            else {
                current.fin = false;
                current.siguiente = indexOfCluster + 1; // Ya que es la referencia a la siguiente entrada de la FAT
            }
        }

    }

}