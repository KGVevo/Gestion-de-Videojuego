package Clases.ClasesObjetos;

import Exceptions.TarjetaInvalidaException;
import Interfaces.IObtenerID;

import java.util.Objects;

public class Tarjeta implements IObtenerID {
    private String titular;
    private Integer numeroDeTarjeta;
    private double saldoDisponible;

    public Tarjeta(String titular, Integer numeroDeTarjeta) {
        this.titular = titular;
        this.numeroDeTarjeta = numeroDeTarjeta;
        this.saldoDisponible = 0;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldoDisponible() {
        return saldoDisponible;
    }


    /// Carga de saldo limitada a 150.000
    public boolean cargarSaldo(double saldo) throws TarjetaInvalidaException {
        if(saldo < 0 || saldo > 150000){
            throw new TarjetaInvalidaException("|Numeros de carga Invalidos|");
        }
        this.saldoDisponible += saldo;
        return true;
    }

    /// Funcion para restar Saldo a la Tarjeta
    public boolean restarSaldo(double saldo) throws TarjetaInvalidaException {
        if(saldo < 0 || this.saldoDisponible < saldo){
            throw new TarjetaInvalidaException("|Saldo insuficiente|");
        }

        this.saldoDisponible -= saldo;
        return true;
    }


    @Override
    public Integer getID() {
        return this.numeroDeTarjeta;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(obj == null || this.getClass() != obj.getClass()){
            return false;
        }
        Tarjeta aux = (Tarjeta) obj;

        return this.numeroDeTarjeta == aux.numeroDeTarjeta;

    }

    @Override
    public int hashCode() {
        return Objects.hash(this.numeroDeTarjeta);
    }

}
