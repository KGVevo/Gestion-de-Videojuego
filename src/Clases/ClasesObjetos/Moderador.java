package Clases.ClasesObjetos;

import Enums.E_Permisos;
import Enums.E_Rol;


public class Moderador extends Usuario {

    private boolean permitido;

    //Constructor 1: Cuando se crea un Moderador esta sin permisos por defecto, necesita que un Admin le de Permisos
    public Moderador(Integer id, String userNombre, String gmail){
        this(id, userNombre, gmail, false);
    }


    // Constructor 2 para cargarlo desde la base de datos
    public Moderador(Integer id, String userNombre, String gmail, boolean permitido){
        super(id, userNombre, gmail, E_Rol.MODERADOR);
        this.permitido = false;

        if (permitido) {
            this.aprobar();
        }
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
