package Enums;

public enum E_TipoPocion {

    CURACION("CURACION"), MANA("MANA"), ENERGIA("ENERGIA"),
    DANIO("DANIO"), CRITICO("CRITICO");

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
