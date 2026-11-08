package Enums;

public enum E_TipoPocion {

    CURACION("CURACION"), MANA("MANA"), ENERGIA("ENERGIA"),
    DAÑO("DAÑO"), CRITICO("CRITICO");

    private String opcionPocion;

    E_TipoPocion(String opcionPocion)
    {
        this.opcionPocion = opcionPocion;
    }

    public String getTipoPocion()
    {
        return this.opcionPocion;
    }

}
