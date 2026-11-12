package Clases.ClasesObjetos.Entidades;

import Clases.ClasesObjetos.ObjetosClass.ArmaCuerpoACuerpo;
import Clases.ClasesObjetos.SistemaDeProbabilidad;
import Enums.E_ClaseAsociada;

public class JugadorCuerpoACuerpo extends Jugador {

    private int energia;
    private ArmaCuerpoACuerpo arma;

    public JugadorCuerpoACuerpo(String nombreJugador, int vida, int danioBase, int idUsuario) {
        super(nombreJugador, vida, danioBase, idUsuario, E_ClaseAsociada.CUERPO_A_CUERPO);
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

        /// 20% de hacer un golpe critico
        if(SistemaDeProbabilidad.generarProb(20)){
            return golpe * 2;
        }

        return golpe;
    }

    @Override
    public int atacar() {
        int golpeFinal = 0;

        /// 90% de no fallar el golpe
        if(SistemaDeProbabilidad.generarProb(90)){
            golpeFinal = golpeCritico();
        }
        return golpeFinal;
    }

    @Override
    public int habilidadArma() {

        if(arma.getConsumoEnergia() < this.energia){
            return arma.getDanioBase();
        }

        this.energia -= this.energia;
        return arma.getDanioBase() * 2;
    }

    public String toString()
    {
        return "Clase Cuerpo a Cuerpo ["+super.toString()+" | Energia: "+this.energia+"]\n"
                +super.getInventario().toString();
    }
}
