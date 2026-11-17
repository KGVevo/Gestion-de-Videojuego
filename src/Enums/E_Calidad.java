package Enums;

public enum E_Calidad {

    COMUN(1.0, "COMUN"), RARO(1.2, "RARO"), EPICO(1.5, "EPICO"), LEGENDARIO(2, "LEGENDARIO"), MITICO(3, "MITICO");

    private double multiplicadorDeDanio;
    private String calidad;

    E_Calidad(double multiplicadorDeDaño,  String calidad)
    {
        this.multiplicadorDeDanio = multiplicadorDeDaño;
        this.calidad = calidad;
    }

    public double getMultiplicadorDeDanio() {
        return multiplicadorDeDanio;
    }

    public String getCalidad() {
        return calidad;
    }

}
