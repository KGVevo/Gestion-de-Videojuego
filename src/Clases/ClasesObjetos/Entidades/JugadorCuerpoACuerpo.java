package Clases.ClasesObjetos.Entidades;

import Clases.ClasesObjetos.ObjetosClass.ArmaCuerpoACuerpo;

public class JugadorCuerpoACuerpo extends Jugador {

    private int energia;
    private ArmaCuerpoACuerpo arma;

    public JugadorCuerpoACuerpo(String nombreJugador, int vida, int danioBase, int idUsuario) {
        super(nombreJugador, vida, danioBase, idUsuario);
        this.energia = 100;
        this.arma = null;
    }

    public ArmaCuerpoACuerpo getArma() {
        return arma;
    }
    public int getEnergia() {
        return energia;
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
