package Clases.ClasesObjetos;

import Enums.E_Rol;


public class Administrador extends Usuario {


    public Administrador(Integer id, String userNombre, String gmail) {
        /// Los permisos del Administrador los asigna Usuario segun su rol
        super(id, userNombre, gmail, E_Rol.ADMINISTRADOR);
    }

}
