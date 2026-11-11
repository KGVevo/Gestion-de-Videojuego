package Enums;

public enum E_RolAdministrador {

    CREADOR("CREADOR"), BACKEND("BACKEND"), SUPERIOR("SUPERIOR");

    private String rolActual;

    E_RolAdministrador(String rolActual)
    {
        this.rolActual = rolActual;
    }

    public String obtenerRolAdministrador()
    {
        return this.rolActual;
    }

}
