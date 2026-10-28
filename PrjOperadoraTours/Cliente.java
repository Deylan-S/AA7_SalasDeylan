public class Cliente {
    public static final String NACIONAL = "nacional";
    public static final String EXTRANJERO = "extranjero";

    private String nombre;
    private String condicion;

    public Cliente(String pNombre, String pCondicion) {
        nombre = pNombre;
        condicion = pCondicion;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean esNacional() {
        return condicion.equalsIgnoreCase(NACIONAL);
    }

    public String toString() {
        return "Nombre= " + nombre + "\n" +
               "Condición= " + condicion;
    }
}