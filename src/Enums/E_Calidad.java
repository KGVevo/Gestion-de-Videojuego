package Enums;

public enum E_Calidad {

    COMUN(1.0, "COMUN"), RARO(1.2, "RARO"), EPICO(1.5, "EPICO"), LEGENDARIO(2, "LEGENDARIO"), MITICO(3, "MITICO");

    private double multiplicadorDeDaño;
    private String calidad;

    E_Calidad(double multiplicadorDeDaño,  String calidad)
    {
        this.multiplicadorDeDaño = multiplicadorDeDaño;
        this.calidad = calidad;
    }

    public double getMultiplicadorDeDaño() {
        return multiplicadorDeDaño;
    }

    public String getCalidad() {
        return calidad;
    }

}
