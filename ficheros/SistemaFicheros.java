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

    	ArrayList<Cluster> listaClustersLibres = buscarClustersLibres(1);
    	
        if(listaClustersLibres.size() == 0) {
            System.err.println("Error: no hay suficientes clusters libres para crear el directorio, APRENDE A CONTAR");
        }
        else {
            
            Directorio directorioDondeGuardarArchivo = buscarDirectorioPorNombre(nombreDirectorioDondeGuardarArchivo);
            
            //Si no existe la ruta, no crearemos el archivo
            if(directorioDondeGuardarArchivo != null) {
            	escribirDatosDirectorioEnClustersLibres(nombreDirectorio, listaClustersLibres);

                escribirEntradasFAT(listaClustersLibres);
                
            	System.out.println("Esta es tu carpeta mi REY: " + directorioDondeGuardarArchivo.nombre);
            	crearEntradaDirectorioEnDirectorio(nombreDirectorio, true,
                		directorioDondeGuardarArchivo, listaClustersLibres.get(0).numCluster);
            }
            else {System.err.println("Error: la ruta especificada no existe");}
            
        }
        
    }

    public void crearArchivo(String nombreDelArchivo, int tamanoArchivoEnClusters, 
            String nombreDirectorioDondeGuardarArchivo) {
                
        ArrayList<Cluster> listaClustersLibres = buscarClustersLibres(tamanoArchivoEnClusters);

        if(listaClustersLibres.size() < tamanoArchivoEnClusters) {
            System.err.println("Error: no hay suficientes clusters libres para crear el archivo, APRENDE A CONTAR");
        }
        else {
            
            Directorio directorioDondeGuardarArchivo = buscarDirectorioPorNombre(nombreDirectorioDondeGuardarArchivo);
            
            //Si no existe la ruta, no crearemos el archivo
            if(directorioDondeGuardarArchivo != null) {
            	escribirDatosArchivoEnClustersLibres(nombreDelArchivo, listaClustersLibres);

                escribirEntradasFAT(listaClustersLibres);
                
            	System.out.println("Esta es tu carpeta mi REY: " + directorioDondeGuardarArchivo.nombre);
            	crearEntradaDirectorioEnDirectorio(nombreDelArchivo, false,
                		directorioDondeGuardarArchivo, listaClustersLibres.get(0).numCluster);
            }
            else {System.err.println("Error: la ruta especificada no existe");}
            
        }

    }

    private void escribirDatosArchivoEnClustersLibres(String nombreDelArchivo, ArrayList<Cluster> listaClustersLibres) {
        for(int i = 0; i < listaClustersLibres.size(); i++) {
            for(int j = 0; j < cantidadClusters; j++) {
                if(listaClustersLibres.get(i).equals(clusters.get(j))) {
                    Cluster aux = clusters.get(j);
                    clusters.remove(aux);
                    clusters.add(j, new ParteArchivo(j, nombreDelArchivo + " " + (i+1) 
                    		+ "/" + listaClustersLibres.size())); //Restamos 1 ya que se esta cargando el cluster que habia
                }
            }
        }
    }
    
    private void escribirDatosDirectorioEnClustersLibres(String nombreDir, ArrayList<Cluster> listaClustersLibres) {
    	for(int i = 0; i < listaClustersLibres.size(); i++) {
            for(int j = 0; j < cantidadClusters; j++) {
                if(listaClustersLibres.get(i).equals(clusters.get(j))) {
                    Cluster aux = clusters.get(j);
                    clusters.remove(aux);
                    clusters.add(j, new Directorio(nombreDir, j)); //Restamos 1 ya que se esta cargando el cluster que habia
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

    private void crearEntradaDirectorioEnDirectorio(String nombreDelArchivo, boolean esDirectorio, Directorio dir, int clusterInicio) {
    	
    	dir.entradasDIR.add(new EntradaDir(nombreDelArchivo, esDirectorio, clusterInicio));
    	
    }

    private Directorio buscarDirectorioPorNombre(String nombreDirectorioDondeGuardarArchivo) {
        //Ruta donde buscar el directorio
    	String[] ruta = nombreDirectorioDondeGuardarArchivo.split("/");
        if(ruta[0].equals("root") && ruta.length == 1) {
            return this.root;
        }
        else if(ruta[0].equals("root") && ruta[1].equals("") && ruta.length == 2) {
        	return this.root;
        }
        else {
        	//Empieza en 1 porque el primero es root
            return buscarDirectorioRecursivo(this.root, ruta, 1);
        }
    }
    
    private Directorio buscarDirectorioRecursivo(Directorio current, 
    		String[] ruta, int currentPosition) {

        for(int i = 0; i < current.entradasDIR.size(); i++) {
            if(current.entradasDIR.get(i).esDirectorio) {
                if(current.entradasDIR.get(i).nombre.equals(ruta[currentPosition])
                		&& ruta.length == currentPosition + 1) {
                    return (Directorio) clusters.get(current.entradasDIR.get(i).clusterInicio);
                }
                else if(current.entradasDIR.get(i).nombre.equals(ruta[currentPosition])){
                	currentPosition = currentPosition + 1;
                    return buscarDirectorioRecursivo((Directorio) 
                                clusters.get(current.entradasDIR.get(i).clusterInicio), ruta
                                , currentPosition);
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
            	int indexOfNextCluster = listaClustersLibres.get(i+1).numCluster;
                current.fin = false;
                current.siguiente = indexOfNextCluster;
            }
        }

    }

	@Override
	public String toString() {
		return "SistemaFicheros [cantidadClusters=" + cantidadClusters + ", \nentradas=" + entradas + ", \nroot=" + root
				+ ", \nclusters=" + clusters + "]";
	}

}