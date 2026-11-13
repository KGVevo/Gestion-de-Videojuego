package Clases.ClasesObjetos.Entidades;

import Clases.ClasesObjetos.ObjetosClass.ArmaMago;
import Clases.ClasesObjetos.SistemaDeProbabilidad;
import Enums.E_ClaseAsociada;

public class JugadorMago extends Jugador {

    private int mana;
    private ArmaMago arma;

    public JugadorMago(String nombreJugador, int idUsuario, int vida, int nivel, int expAcumulada, int monedas) {
        super(nombreJugador,6, idUsuario, E_ClaseAsociada.MAGO, vida,  nivel, expAcumulada, monedas);
        this.mana = 100; /// Mana default de cada Jugador
        this.arma = null;
    }


    public JugadorMago(String nombreJugador, int danioBase, int idUsuario) {
        super(nombreJugador,  danioBase, idUsuario, E_ClaseAsociada.MAGO);
        this.mana = 100; /// Mana default de cada Jugador
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

        if(this.mana > this.arma.getConsumoMana()){
            if(SistemaDeProbabilidad.generarProb(90)){
                golpeFinal = this.golpeCritico();
                this.mana -= this.arma.getConsumoMana();
            }
        }


        return golpeFinal;
    }


    @Override
    public int habilidadArma() {
        /// Gasta mana y se cura vida al mismo tiempo
        if(this.mana > (this.arma.getConsumoMana() * 2)){
            this.mana -= this.arma.getConsumoMana() * 2;
            this.modificadorDeVida(15, 1);

            return arma.getDanioBase() * 2;
        }

        return 0;
    }

    public String toString()
    {
        return "Clase Cuerpo a Cuerpo ["+super.toString()+" | Mana: "+this.mana+"]\n"
                +super.getInventario().toString();
    }

}
