package Clases.ClasesObjetos.Entidades;

import Clases.ClasesObjetos.ObjetosClass.ArmaCuerpoACuerpo;
import Clases.ClasesObjetos.SistemaDeProbabilidad;

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
            golpeFinal = golpeCritico();
        }
        return golpeFinal;
    }

    @Override
    public boolean habilidadArma() {
        return false;
    }
}
