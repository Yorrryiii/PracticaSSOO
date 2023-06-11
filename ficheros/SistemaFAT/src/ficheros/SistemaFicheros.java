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
            System.err.println("Error: no hay suficientes clusters libres para crear el directorio");
        }
        else {
            
            Directorio directorioDondeGuardarArchivo = buscarDirectorioPorNombre(nombreDirectorioDondeGuardarArchivo);
            
            //Si no existe la ruta, no crearemos el archivo
            if(directorioDondeGuardarArchivo != null) {
            	escribirDatosDirectorioEnClustersLibres(nombreDirectorio, listaClustersLibres);

                escribirEntradasFAT(listaClustersLibres);
                
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
            System.err.println("Error: no hay suficientes clusters libres para crear el archivo");
        }
        else {
            
            Directorio directorioDondeGuardarArchivo = buscarDirectorioPorNombre(nombreDirectorioDondeGuardarArchivo);
            
            //Si no existe la ruta, no crearemos el archivo
            if(directorioDondeGuardarArchivo != null) {
            	escribirDatosArchivoEnClustersLibres(nombreDelArchivo, listaClustersLibres);

                escribirEntradasFAT(listaClustersLibres);
                
            	crearEntradaDirectorioEnDirectorio(nombreDelArchivo, false,
                		directorioDondeGuardarArchivo, listaClustersLibres.get(0).numCluster);
            }
            else {System.err.println("Error: la ruta de creacion especificada no existe");}
            
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
                		&& (ruta.length == currentPosition + 1 || (ruta.length == currentPosition + 2 && 
                        ruta[ruta.length-1].equals("")))) {
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

    private String obtenerRutaPadre(String rutaCompleta) {

        String[] rutaArchivo = rutaCompleta.split("/");
        //Creamos una String para volcar dentro de esta la ruta del padre
    	String rutaPadre = new String();
    	
    	//La ruta padre del archivo siempre sera de uno menos que la del hijo
    	for(int i = 0; i < rutaArchivo.length - 1; i++) {
    		
    		//Si es la ultima iteracion, no añadimos la barra a la ruta
    		if(i == rutaArchivo.length - 2) {
    			rutaPadre += rutaArchivo[i];
    		}
    		else {
    			rutaPadre += rutaArchivo[i] + "/";
    		}
    		
    	}

        return rutaPadre;

    }

    private void borrarEntradasFAT(int numCluster) {

        entradas.get(numCluster).disponible = true;
        while(!entradas.get(numCluster).fin) {
		    //Si no indica fin cierto, nos moveremos para poner todo a disponible
        	numCluster = entradas.get(numCluster).siguiente;
        	entradas.get(numCluster).disponible = true;
        }

    }

    public void borrarDirectorio(String rutaCompleta) {

        String rutaPadre = obtenerRutaPadre(rutaCompleta);
    	
    	Directorio dir = buscarDirectorioPorNombre(rutaCompleta);
        Directorio dondeEstaDir = buscarDirectorioPorNombre(rutaPadre);
    	
        //Si no existe la ruta del directorio, no hacemos nada
    	if(dir == null) {
    		System.err.println("Error: el directorio especificado no existe");
    		return;
    	}
    	else if(dir.equals(root)) {
    		formatear();
    	}
    	else {
    		for(int i = 0; i < dir.getEntradasDIR().size(); i++) {
    			//Obtenemos el numero del cluster en el que se encuentra lo que haya en la entrada
    			int numCluster = dir.getEntradasDIR().get(i).clusterInicio;
    			borrarEntradasFAT(numCluster);
    		}
    		//Al final, ponemos el disponible a cierto de la entrada donde esta la carpeta
    		entradas.get(dir.numCluster).disponible = true;
            //Borramos la entrada de dicho directorio de donde se haye
            for(int i = 0; i < dondeEstaDir.getEntradasDIR().size(); i++) {
                if(dondeEstaDir.getEntradasDIR().get(i).nombre.equals(dir.nombre)) {
                    dondeEstaDir.getEntradasDIR().remove(i);
                }
            }

    	}
    	
    }

    public void borrarArchivo(String rutaCompleta) {
    	
    	String[] rutaArchivo = rutaCompleta.split("/");
    	//Rescatamos el nombre del archivo, que estara en la ultima posicion del array
    	String nombreArchivo = rutaArchivo[rutaArchivo.length-1];
        //Creamos una String y llamamos a la funcion obtenerRutaPadre
    	String rutaPadre = obtenerRutaPadre(rutaCompleta);
    	
    	//Rescatamos el directorio en el que esta el archivo
    	Directorio dir = buscarDirectorioPorNombre(rutaPadre);
    	//Si es null, es porque la ruta no existe, por lo que pondremos un mensaje de error
    	if(dir == null) {
    		System.err.println("Error: la ruta especificada no existe");
    		return;
    	}
    	else {
    		for(int i = 0; i < dir.getEntradasDIR().size(); i++) {
    			
    			if(dir.getEntradasDIR().get(i).nombre.equals(nombreArchivo)) {
    				int numCluster = dir.getEntradasDIR().get(i).clusterInicio;
        			//Ponemos a cierto (true) el disponible para indicar que esta libre y se puede escribir
        			borrarEntradasFAT(numCluster);
        			return; //Si lo encuentra dejamos de comprobar el resto
    			}
    			
    		}
    		//Si lo ha encontrado no llegara hasta aqui, por tanto podemos imprimir un mensaje de error
    		System.err.println("Error: no se ha podido encontrar el archivo " + nombreArchivo +
    				" en la carpeta " + rutaPadre);
    	}
    	
    }

    public void mover(String archivo, String carpetaNueva) {

        //Obtenemos la ruta padre del archivo
        String rutaPadre = obtenerRutaPadre(archivo);

        String[] ruta = archivo.split("/");
    	//Rescatamos el nombre del archivo, que estara en la ultima posicion del array
    	String nombre = ruta[ruta.length-1];
        
        //Obtenemos los directorios en el que esta actualmente el archivo y al que lo queremos mover
        Directorio dondeEsta = buscarDirectorioPorNombre(rutaPadre);
        Directorio dondeMovemos = buscarDirectorioPorNombre(carpetaNueva);

        //Comprobamos si existen los directorios que nos ha pedido el usuario
        if(dondeEsta == null) {
            System.err.println("Error: el archivo o ruta especificada no existe");
            return;
        }
        if(dondeMovemos == null) {
            System.err.println("Error: la ruta de destino no ha sido encontrada");
            return;
        }

        //Si los directorios existen, pasamos a mover el archivo
        for(int i = 0; i < dondeEsta.getEntradasDIR().size(); i++) {
            if(dondeEsta.getEntradasDIR().get(i).nombre.equals(nombre)) {
                //Movemos la entrada a la nueva carpeta y la borramos de la entrada vieja
                dondeMovemos.getEntradasDIR().add(dondeEsta.getEntradasDIR().get(i));
                dondeEsta.getEntradasDIR().remove(i);
                //Si lo ha encontrado, dejamos de buscar
                return;
            }
        }
        //Si no lo ha encontrado, imprimimos un error, ya que quiere decir que el archivo no existe
        System.err.println("Error, el archivo o carpeta " + nombre + " no se ha encontrado en la ruta " + rutaPadre);

    }

    public void copiarArchivo(String archivo, String carpetaDestino) {

        //Obtenemos la ruta padre del archivo
        String rutaPadre = obtenerRutaPadre(archivo);

        String[] ruta = archivo.split("/");
    	//Rescatamos el nombre del archivo, que estara en la ultima posicion del array
    	String nombre = ruta[ruta.length-1];
        
        //Obtenemos el directorio en el que esta actualmente el archivo
        Directorio dondeEsta = buscarDirectorioPorNombre(rutaPadre);

        //Comprobamos que el direcotorio donde esta existe
        if(dondeEsta == null) {
            System.err.println("Error: el archivo especificado no existe");
            return;
        }
        
        //Si existe, contamos el numero de clusters que tiene el archivo que buscamos. Buscamos el archivo
        int numeroDeClustersArchivo = 0;
        for(int i = 0; i < dondeEsta.getEntradasDIR().size(); i++) {

            if(!dondeEsta.getEntradasDIR().get(i).esDirectorio) {
                if(dondeEsta.getEntradasDIR().get(i).nombre.equals(nombre)) {
                    int currentCluster = dondeEsta.getEntradasDIR().get(i).clusterInicio;
                    numeroDeClustersArchivo++;
                    while(!entradas.get(currentCluster).fin) {
		                //Si no indica fin cierto, nos moveremos para poner todo a disponible
        	            currentCluster = entradas.get(currentCluster).siguiente;
        	            numeroDeClustersArchivo++;
                    }
                    //Como lo ha encontrado, creamos un archivo nuevo con ese numero de clusters y con el mismo nombre que
                    crearArchivo(nombre, numeroDeClustersArchivo, carpetaDestino);
                    //Cuando lo cree, nos saldremos de la funcion
                    return;
                }
            }

        }
        //Si se sale del bucle, quiere decir que no ha encontrado el archivo, por lo que mostraremos mensaje de error
        System.err.println("Error: el archivo " + nombre + " no se encuentra en la ruta " + rutaPadre);

    }

	@Override
	public String toString() {
		return "SistemaFicheros [cantidadClusters=" + cantidadClusters + ", \nentradas=" + entradas + ", \nroot=" + root
				+ ", \nclusters=" + clusters + "]";
	}

}