package ficheros;

public class Main {
    
    public static void main(String[] args) {
        
        SistemaFicheros sistemaFicheros = new SistemaFicheros(10);
        //System.out.println(sistemaFicheros);
        sistemaFicheros.crearArchivo("Buenas tardes", 1, "root");

    }

}
