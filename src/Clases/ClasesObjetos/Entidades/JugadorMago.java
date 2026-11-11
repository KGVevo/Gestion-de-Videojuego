package Clases.ClasesObjetos.Entidades;

import Clases.ClasesObjetos.ObjetosClass.ArmaMago;

public class JugadorMago extends Jugador {

    private int mana;
    private ArmaMago arma;

    public JugadorMago(String nombreJugador, int vida, int dañoBase, int idUsuario) {
        super(nombreJugador, vida, dañoBase, idUsuario);
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
    public String atacar() {
        return "";
    }

    @Override
    public String defenderse() {
        return "";
    }

    @Override
    public String huir() {
        return "";
    }

}
