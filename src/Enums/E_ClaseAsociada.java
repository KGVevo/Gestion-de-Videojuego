package Enums;

public enum E_ClaseAsociada {

    CUERPO_A_CUERPO("CUERPO_A_CUERPO"), MAGO("MAGO"), DISTANCIA("DISTANCIA");

    private String opcionClase;

    E_ClaseAsociada(String opcionClase)
    {
        this.opcionClase = opcionClase;
    }

    public String obtenerClaseAsociada()
    {
        return this.opcionClase;
    }

}
