public class CasetaPeaje {
    private int numeroCaseta;
    private String nombreAutopista;
    private double totalCobrado;
    private static int cantidadCasetas;

    public CasetaPeaje(String pNombreAutopista) {
        cantidadCasetas++;
        numeroCaseta = cantidadCasetas;
        nombreAutopista = pNombreAutopista;
    }

    public void cobrarPeaje(Vehiculo obj) {
        switch (obj.getTipo().toLowerCase()) {
            case "particular":
                totalCobrado += 100;
                break;
            case "carga liviana":
                totalCobrado += 200;
                break;
            case "carga":
                totalCobrado += 500;
                break;
            case "moto":
                totalCobrado += 75;
                break;
        }
    }

    public double getTotalCobrado() {
        return totalCobrado;
    }

    public static int getCantidadDeCasetasPeaje() {
        return cantidadCasetas;
    }

    public String toString() {
        return "Caseta de Peaje número: " + numeroCaseta + "\n" +
               "Autopista= " + nombreAutopista + "\n" +
               "Total Cobrado= " + totalCobrado + "\n";
    }
}