package ficheros;

public class ParteArchivo extends Cluster{
    public String contenido;

    public ParteArchivo(int numCluster, String contenido) {
        super(numCluster);
        this.contenido = contenido;
    }
}
