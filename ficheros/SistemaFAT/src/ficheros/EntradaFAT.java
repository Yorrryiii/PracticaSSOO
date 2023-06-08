package ficheros;

public class EntradaFAT {

    public boolean disponible;
    public int siguiente;
    public boolean fin;

    public EntradaFAT(boolean disponible, int siguiente, boolean fin) {
        this.disponible = disponible;
        this.siguiente = siguiente;
        this.fin = fin;
    }

    public EntradaFAT() {
        this.disponible = true;
        this.siguiente = -1;
        this.fin = false;
    }

    public int getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(int siguiente) {
        this.siguiente = siguiente;
    }

    public boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(boolean Disponible) {
        this.disponible = Disponible;
    }

    public boolean getFin() {
        return fin;
    }

    public void setFin(boolean Fin) {
        this.fin = Fin;
    }

    @Override
    public String toString() {
        return "\nDisponible: " + disponible + " Siguiente: " + siguiente + " Fin: " + fin;
    }
}
