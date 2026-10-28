import java.util.ArrayList;

public abstract class Tour {
    public static final String PREFIJO_CODIGO = "TOUR-";
    public static final double PORCENTAJE_DESCUENTO_NACIONAL = 0.20;

    private String codigo;
    private String nombre;
    protected double precioBase;
    private int cupoMaximo;
    private Guia guia;
    protected ArrayList<Atractivo> recorrido;
    private ArrayList<Reserva> reservas;
    private static int cantidadTours;

    public Tour(String pNombre, double pPrecioBase, int pCupoMaximo, Guia pGuia) {
        cantidadTours++;
        codigo = PREFIJO_CODIGO + cantidadTours;
        nombre = pNombre;
        precioBase = pPrecioBase;
        cupoMaximo = pCupoMaximo;
        guia = pGuia;
        recorrido = new ArrayList<>();
        reservas = new ArrayList<>();
    }

    public abstract double calcularPrecioPorPersona();

    public final double calcularIngresoTotal() {
        double total = 0;
        for (Reserva r : reservas) {
            total += r.getMonto();
        }
        return total;
    }

    public final int calcularCuposDisponibles() {
        return cupoMaximo - calcularPersonasReservadas();
    }

    public final void registrarReserva(Cliente pCliente, int pCantidadPersonas) {
        if (pCantidadPersonas > 0 && pCantidadPersonas <= calcularCuposDisponibles()) {
            int numero = reservas.size() + 1;
            double monto = calcularMontoReserva(pCliente, pCantidadPersonas);
            reservas.add(new Reserva(numero, pCliente.getNombre(), pCantidadPersonas, monto));
        }
    }

    public final void registrarReserva(Cliente pCliente) {
        registrarReserva(pCliente, 1);
    }

    public void agregarAtractivo(Atractivo pAtractivo) {
        if (!recorrido.contains(pAtractivo)) {
            recorrido.add(pAtractivo);
        }
    }

    public void cambiarGuia(Guia pGuia) {
        guia = pGuia;
    }

    public String consultarNombreGuia() {
        return guia.getNombreCompleto();
    }

    public String consultarNombresAtractivos() {
        String s = "";
        for (int i = 0; i < recorrido.size(); i++) {
            if (i > 0) {
                s = s + ", ";
            }
            s = s + recorrido.get(i).getNombre();
        }
        return s;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public static int getCantidadTours() {
        return cantidadTours;
    }

    private int calcularPersonasReservadas() {
        int total = 0;
        for (Reserva r : reservas) {
            total += r.getCantidadPersonas();
        }
        return total;
    }

    private double calcularMontoReserva(Cliente pCliente, int pCantidadPersonas) {
        double monto = calcularPrecioPorPersona() * pCantidadPersonas;
        if (pCliente.esNacional()) {
            monto = monto - (monto * PORCENTAJE_DESCUENTO_NACIONAL);
        }
        return monto;
    }

    public String toString() {
        String s = "Código: " + codigo +
                   "\nNombre: " + nombre +
                   "\nGuía: " + guia.getNombreCompleto() +
                   "\nPrecio base: " + precioBase +
                   "\nPrecio por persona: " + calcularPrecioPorPersona() +
                   "\nCupo máximo: " + cupoMaximo +
                   "\nCupos disponibles: " + calcularCuposDisponibles() +
                   "\nAtractivos: " + consultarNombresAtractivos() +
                   "\nReservas (" + reservas.size() + "):\n";
        for (Reserva r : reservas) {
            s = s + "- " + r + "\n";
        }
        s = s + "Ingreso total: " + calcularIngresoTotal() + "\n";
        return s;
    }
}