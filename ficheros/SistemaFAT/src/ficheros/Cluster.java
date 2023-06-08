package ficheros;

public class Cluster {
    public int numCluster;

    public Cluster(int numCluster) {
        this.numCluster = numCluster;
    }

    @Override
    public String toString() {
        return "\n" +numCluster + ": Empty";
    }
    
}