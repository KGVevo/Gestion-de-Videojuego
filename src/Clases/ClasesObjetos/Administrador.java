package Clases.ClasesObjetos;

import Enums.E_Rol;


public class Administrador extends Usuario {

    private boolean permitido;

    public Administrador(Integer id, String userNombre, String gmail) {
        /// Los permisos del Administrador los asigna Usuario segun su rol
        super(id, userNombre, gmail, E_Rol.ADMINISTRADOR);
        this.permitido = false;
    }

    public boolean isPermitido() {
        return permitido;
    }

    public boolean aprobar(){
        if(this.permitido){
            return false;
        }

        this.permitido = true;
        return true;
    }

}
