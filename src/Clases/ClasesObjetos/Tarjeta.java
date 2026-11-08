package Clases.ClasesObjetos;

import java.util.Objects;

public class Tarjeta {
    private String titular;
    private long numeroDeTarjeta;
    private double saldoDisponible;

    public Tarjeta(String titular, long numeroDeTarjeta, double saldoDisponible) {
        this.titular = titular;
        this.numeroDeTarjeta = numeroDeTarjeta;
        this.saldoDisponible = saldoDisponible;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldoDisponible() {
        return saldoDisponible;
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

        return this.titular.equals(aux.titular) || this.numeroDeTarjeta == aux.numeroDeTarjeta;

    }

    @Override
    public int hashCode() {
        return Objects.hash(this.titular, this.numeroDeTarjeta);
    }

}
