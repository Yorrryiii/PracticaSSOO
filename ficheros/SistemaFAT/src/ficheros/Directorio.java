package ficheros;

import java.util.ArrayList;

public class Directorio extends Cluster {

    public String nombre;
    public ArrayList<EntradaDir> entradasDIR;

    public Directorio(String nombre, int numCluster) {
        super(numCluster);
        this.nombre = nombre;
        entradasDIR = new ArrayList<EntradaDir>();
    }

    // public void addEntrada(EntradaDir entrada) {
    //     entradasDIR.add(entrada);
    // }

    public ArrayList<EntradaDir> getEntradasDIR() {
        return entradasDIR;
    }

    public void setEntradasDIR(ArrayList<EntradaDir> entradasDIR) {
        this.entradasDIR = entradasDIR;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    private String showEntries() {
        String entries = new String();
        entries += "Entradas:\n";
        for(int i = 0; i < entradasDIR.size(); i++) {
            entries += entradasDIR.get(i) + "\n";
        }
        return entries;
    }

    @Override
    public String toString() {
        if(super.numCluster < 0) {
            return "Directorio: " + nombre + "\n" + showEntries();
        }
        else {
            return super.numCluster + ": Directorio: " + nombre + "\n" + showEntries();
        }
    }
}