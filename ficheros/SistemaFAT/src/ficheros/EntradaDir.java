package ficheros;

public class EntradaDir {
    public String nombre;
    public boolean esDirectorio;
    public int clusterInicio;

    public EntradaDir(String nombre, boolean esDirectorio, int clusterInicio) {
        this.nombre = nombre;
        this.esDirectorio = esDirectorio;
        this.clusterInicio = clusterInicio;
    }

    // public String toString(){
    // return nombre + " " + esDirectorio + " " + clusterInicio;
    // }

    public String getNombre(){
    return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public boolean getEsDirectorio(){
        return esDirectorio;
    }

    public void setEsDirectorio(boolean esDirectorio){
        this.esDirectorio = esDirectorio;
    }

    public int getClusterInicio(){
        return clusterInicio;
    }

    public void setClusterInicio(int clusterInicio){
        this.clusterInicio = clusterInicio;
    }

    @Override
	public String toString() {
		return "\nEntradaDir [nombre=" + nombre + ", esDirectorio=" + esDirectorio + ", clusterInicio=" + clusterInicio
				+ "]";
	}

}