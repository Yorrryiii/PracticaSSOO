package ficheros;

public class ParteArchivo extends Cluster {
    public String contenido;

    public ParteArchivo(int numCluster, String contenido) {
        super(numCluster);
        this.contenido = contenido;
    }

    public String contenido(){
        return contenido;
    }

    public void setContenido(String contenido){
        this.contenido = contenido;
    }

    @Override
    public String toString(){
    	return super.numCluster + ": " + contenido;
    }
}
