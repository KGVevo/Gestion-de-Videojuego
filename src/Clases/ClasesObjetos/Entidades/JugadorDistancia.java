package Clases.ClasesObjetos.Entidades;

import Clases.ClasesObjetos.ObjetosClass.ArmaDistancia;
import Clases.ClasesObjetos.SistemaDeProbabilidad;
import Enums.E_ClaseAsociada;

public class JugadorDistancia extends Jugador {

    private int municion;
    private ArmaDistancia arma;

    public JugadorDistancia(String nombreJugador, int vida, int dañoBase, int idUsuario){
        super(nombreJugador, vida, dañoBase, idUsuario, E_ClaseAsociada.DISTANCIA);
        this.municion = 25; /// Municion por defecto
        this.arma = null;
    }

    public int getMunicion() {
        return municion;
    }
    public ArmaDistancia getArma() {
        return arma;
    }



    @Override
    public int golpeCritico() {
        int golpe = this.getDanioBase() + arma.getDanioBase();
        if(SistemaDeProbabilidad.generarProb(20)){
            return golpe * 2;
        }

        return golpe;
    }

    @Override
    public int atacar() {
        int golpeFinal = 0;

        if(this.municion > this.arma.getConsumoMunicion()){
            if(SistemaDeProbabilidad.generarProb(90)){
                golpeFinal = this.golpeCritico();
                this.municion -= this.arma.getConsumoMunicion();

            }
        }else{
            this.recargar();
        }

        return golpeFinal;
    }


    public boolean recargar(){
        if(this.municion == 25){
            return false;
        }else{
            this.municion = 25;
            return true;
        }

    }

    public int habilidadArma() {
        if(this.municion == 25){
            this.municion = 0;
            return arma.getDanioBase() * 3;
        }

        return 0;
    }

    public String toString()
    {
        return "Clase Distancia ["+super.toString()+" | Municion: "+this.municion+"]\n"
                +super.getInventario().toString();
    }
}
