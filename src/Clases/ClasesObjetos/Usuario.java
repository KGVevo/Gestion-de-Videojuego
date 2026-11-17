package Clases.ClasesObjetos;

import Enums.E_EstadoCuenta;
import Enums.E_Permisos;
import Enums.E_Rol;
import Interfaces.IObtenerID;
import Interfaces.IPermisos;

import java.util.HashSet;
import java.util.Objects;


public class Usuario implements IObtenerID<String>, IPermisos {
    private Integer id;
    private String userNombre;
    private String gmail;
    private E_Rol rol;
    private HashSet<E_Permisos> permisos;
    private E_EstadoCuenta estadoCuenta;



    /// Constructor para un usuario nuevo: la cuenta nace ACTIVA
    public Usuario(Integer id,String userNombre, String gmail, E_Rol rol) {
        this(id, userNombre, gmail, rol, E_EstadoCuenta.ACTIVA);
    }

    /// Constructor para cargar un usuario de la base de datos (con su estado de cuenta)
    public Usuario(Integer id,String userNombre, String gmail, E_Rol rol, E_EstadoCuenta estadoCuenta) {
        this.id = id;
        this.userNombre = userNombre;
        this.gmail = gmail;
        this.rol = rol;

        // usar el signo "?" es como usar un IF  y los ":" seria como el ELSE
        // Osea que significa que si estadoCuenta es igual a NULL lo deja en ACTIVA y si no es NULL le aplica lo que le pasaron por parametros
        this.estadoCuenta = (estadoCuenta == null) ? E_EstadoCuenta.ACTIVA : estadoCuenta;
        this.permisos = new HashSet<>();


        /// El Administrador tiene todos los permisos por su rol, asi no los pierde si se carga de la base de datos.
        if (rol == E_Rol.ADMINISTRADOR) {
            for (E_Permisos p : E_Permisos.values()) {
                this.permisos.add(p);
            }
        }
    }



    public Integer getIdUsuario() {
        return id;
    }

    public String getUserNombre() {
        return userNombre;
    }

    public String getGmail() {
        return gmail;
    }

    public E_Rol getRol() {
        return this.rol;
    }

    public E_EstadoCuenta getEstadoCuenta() {
        return this.estadoCuenta;
    }

    /// Dice si la cuenta esta baneada
    public boolean estaBaneada() {
        return this.estadoCuenta == E_EstadoCuenta.BANEADA;
    }

    /// Banea la cuenta. Devuelve false si ya estaba baneada.
    public boolean banear() {
        if (this.estaBaneada()) {
            return false;
        }

        this.estadoCuenta = E_EstadoCuenta.BANEADA;
        return true;
    }

    /// Le saca el ban a la cuenta. Devuelve false si no estaba baneada.
    public boolean desbanear() {
        if (!this.estaBaneada()) {
            return false;
        }

        this.estadoCuenta = E_EstadoCuenta.ACTIVA;
        return true;
    }



    /// Dar permisos
    public void darPermiso(E_Permisos permiso){
        this.permisos.add(permiso);
    }

    public void quitarPermiso(E_Permisos permiso){
        this.permisos.remove(permiso);
    }




    /// Metodo para revisar si tiene permisos
    @Override
    public boolean tienePermiso(E_Permisos permiso) {
        return this.permisos.contains(permiso);
    }

    @Override
    public String getID() {
        return this.gmail;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }

        if(obj == null || this.getClass() != obj.getClass()){
            return false;
        }

        Usuario aux = (Usuario) obj;
        return this.gmail.equals(aux.getGmail());

    }

    @Override
    public int hashCode() {
        return Objects.hash(this.gmail);
    }

    @Override
    public String toString() {
        return "Usuario [ID: "+this.id+" | Nombre Usuario: "+this.userNombre+" | Gmail: "+this.gmail
                + "| Rol: " + this.rol.getRol() + " | Cuenta: " + this.estadoCuenta + "]";
    }

}
