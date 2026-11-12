package Clases.ClasesObjetos.Entidades;

import Clases.ClasesObjetos.ObjetosClass.ArmaDistancia;
import Clases.ClasesObjetos.SistemaDeProbabilidad;

public class JugadorDistancia extends Jugador {

    private int municion;
    private ArmaDistancia arma;

    public JugadorDistancia(String nombreJugador, int vida, int dañoBase, int idUsuario){
        super(nombreJugador, vida, dañoBase, idUsuario);
        this.municion = 50;
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

        if(SistemaDeProbabilidad.generarProb(90)){
            golpeFinal = this.golpeCritico();
        }
        return golpeFinal;
    }


    @Override
    public boolean habilidadArma() {
        return false;
    }
}
