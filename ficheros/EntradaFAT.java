package ficheros;

public class EntradaFAT {
    public boolean disponible;
    public int siguiente;
    public boolean end;

    public EntradaFAT(boolean disponible, int siguiente, boolean end) {
        this.disponible = disponible;
        this.siguiente = siguiente;
        this.end = end;
    }
}
