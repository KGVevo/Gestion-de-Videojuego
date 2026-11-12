package Clases.ClasesObjetos.Entidades;

import Clases.ClasesObjetos.ObjetosClass.ArmaMago;
import Clases.ClasesObjetos.SistemaDeProbabilidad;

public class JugadorMago extends Jugador {

    private int mana;
    private ArmaMago arma;

    public JugadorMago(String nombreJugador, int vida, int danioBase, int idUsuario) {
        super(nombreJugador, vida, danioBase, idUsuario);
        this.mana = 100;
        this.arma = null;
    }

    public int getMana() {
        return mana;
    }

    public ArmaMago getArma() {
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
