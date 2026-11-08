package Clases.ClasesObjetos;

import Exceptions.TarjetaInvalidaException;
import Interfaces.ISuscripcion;

import java.util.Objects;

public class Usuario implements ISuscripcion {
    private String userNombre;
    private String gmail;
    private Tarjeta tarjeta;
    private boolean premium;

    /// Constructor 1: En caso de que el Usuario no quiera meter su Tarjeta
    public Usuario(String userNombre, String gmail) {
        this.userNombre = userNombre;
        this.gmail = gmail;
        this.tarjeta = null;
        this.premium = false;
    }


    /// Constructor 2: En caso de que el Usuario quiera meter la tarjeta de una
    public Usuario(String userNombre, String gmail, Tarjeta tarjeta) {
        this.userNombre = userNombre;
        this.gmail = gmail;
        this.tarjeta = tarjeta;
        this.premium = false;
    }


    public String getUserNombre() {
        return userNombre;
    }

    public String getGmail() {
        return gmail;
    }

    public boolean getPremium() {
        return premium;
    }

    /// Funcion para pagar Cuenta Premium
    public boolean pagarSuscripcion()throws TarjetaInvalidaException {

        if(this.tarjeta != null) {
            this.tarjeta.restarSaldo(precioPremium);
        }else{
            throw new TarjetaInvalidaException("|No tiene tarjeta registrada|");
        }

        return true;
    }
    
    /// Funcion para Registrar la Tarjeta en caso de que no lo haya hecho antes
    public boolean registrarTarjeta(String titular, long numeroDeTarjeta)throws TarjetaInvalidaException {
        if(this.tarjeta != null) {
            throw new TarjetaInvalidaException("|Ya tienes una tarjeta registrada|");
        }

        Tarjeta aux = new Tarjeta(titular, numeroDeTarjeta);
        this.tarjeta = aux;

        return true;
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
        return this.userNombre.equals(aux.userNombre) || this.gmail.equals(aux.gmail);

    }


    @Override
    public int hashCode() {
        return Objects.hash(this.userNombre, this.gmail);
    }


}
