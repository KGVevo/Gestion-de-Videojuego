package Clases.ClasesObjetos;

import Enums.E_RolAdministrador;

public class Administrador extends Usuario {

    private E_RolAdministrador rol;
    private String cuit;

    public Administrador(Integer id, String userNombre, String gmail, E_RolAdministrador rol, String cuit)
    {
        super(id, userNombre, gmail);
        this.rol = rol;
        this.cuit = cuit;
    }

    public String obtenerCuitAdmin()
    {
        return this.cuit;
    }

    public String obtenerRolActual()
    {
        return this.rol.obtenerRolAdministrador();
    }

}
