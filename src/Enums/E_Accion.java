package Enums;

public enum E_Accion {
    ATACAR(1),
    DEFENDER(2),
    HUIR(3);

    private final int opcion;

    E_Accion(int opcion) {
        this.opcion = opcion;
    }

    public int getOpcion() {
        return opcion;
    }

}
