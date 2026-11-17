package Enums;

public enum E_Rol {
    USUARIO("USUARIO"),
    MODERADOR("MODERADOR"),
    ADMINISTRADOR("ADMINISTRADOR");

    private String rol;
    E_Rol(String rol) {
        this.rol = rol;
    }


    public String getRol() {
        return this.rol;
    }
}
