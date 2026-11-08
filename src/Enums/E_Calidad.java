package Enums;

public enum E_Calidad {

    COMUN(1.0), RARO(1.2), EPICO(1.5), LEGENDARIO(2), MITICO(3);

    private double opcionCalidad;

    E_Calidad(double opcionCalidad)
    {
        this.opcionCalidad = opcionCalidad;
    }

    public double getCalidad()
    {
        return this.opcionCalidad;
    }

}
