package Clases.ClasesObjetos;

import Enums.E_EstadoCuenta;
import Enums.E_Permisos;
import Enums.E_Rol;


public class Moderador extends Usuario {

    private boolean permitido;

    //Constructor 1: Cuando se crea un Moderador esta sin permisos por defecto, necesita que un Admin le de Permisos
    public Moderador(Integer id, String userNombre, String gmail){
        super(id, userNombre, gmail, E_Rol.MODERADOR);
        this.permitido = false;
    }


    // Constructor 2 para cargarlo desde la base de datos
    public Moderador(Integer id, String userNombre, String gmail, boolean permitido, E_EstadoCuenta estadoCuenta){
        super(id, userNombre, gmail, E_Rol.MODERADOR, estadoCuenta);
        this.permitido = false;

        if (permitido) {
            this.aprobar();
        }
    }

    public Integer getID() {
        return super.getID();
    }
    public boolean getPermitido() {
        return permitido;
    }

    /// Aprueba al Moderador con sus permisos
    public boolean aprobar() {
        if (this.permitido) {
            return false;
        }

        this.permitido = true;
        darPermiso(E_Permisos.GESTIONAR_MUNDOS);
        darPermiso(E_Permisos.SUSPENDER_CUENTAS);
        return true;
    }

}
