package Clases.ClasesObjetos.SistemaDeJuego;

import Clases.ClasesObjetos.Entidades.Enemigo;
import Clases.ClasesObjetos.Entidades.Jugador;
import Enums.E_Accion;
import Enums.E_ResultadoPartida;
import Enums.E_TipoDeEnemigo;

import java.util.Objects;

public class Batalla {
    private final Jugador jugador;
    private final Enemigo enemigo;
    private boolean terminada;

    /// Es null mientras la batalla sigue
    private E_ResultadoPartida resultado;

    /// Pasa a true cuando el Servidor ya aplico las consecuencias del resultado (recompensa, revivir, etc.)
    private boolean cerrada;


    /// Una batalla siempre necesita un jugador y un enemigo
    public Batalla(Jugador jugador, Enemigo enemigo) {
        this.jugador = jugador;
        this.enemigo = enemigo;
        this.terminada = false;
        this.resultado = null;
        this.cerrada = false;
    }


    /// Getters  //////////////////////////////////////
    public Jugador getJugador() {
        return jugador;
    }

    public Enemigo getEnemigo() {
        return enemigo;
    }

    public boolean getTerminada() {
        return terminada;
    }

    public E_ResultadoPartida getResultado() {
        return resultado;
    }
    ////////////////////////////////////////////////


    /// Muestra la vida de los dos para que el menu la imprima antes de cada turno
    public String mostrarEstadisticas() {


        return "[" + this.jugador.getNombreEntidad() + ": " + this.jugador.getVida() + " HP] <->"
                + " [" + this.enemigo.getNombreEntidad() + ": " + this.enemigo.getVida() + " HP]\n";
    }

   /// Termina la batalla
    public boolean cerrarBatalla() {
        if (!this.terminada || this.cerrada) {
            return false;
        }

        this.cerrada = true;
        return true;
    }



    /// El jugador ataca. Si el enemigo se cubre, el golpe baja a la mitad
    private String ataqueDelJugador() {
        StringBuilder sb = new StringBuilder();
        int danio = this.jugador.atacar();

        if (danio == 0) {
            sb.append("|>>> Fallaste el golpe <<<|\n");
        } else {
            if (this.enemigo.defenderse()) {
                danio = Math.max(1, danio / 2);
                sb.append("|>>> El enemigo se cubre <<<|\n");
            }

            this.enemigo.recibirDanio(danio);
            sb.append("|>>> Le hiciste " + danio + " de daño <<<|\n");
        }

        return sb.toString();
    }


    /// El jugador intenta huir. No se puede huir de un JEFE.
    private String intentarHuir() {
        if (this.enemigo.getTipoDeEnemigo() == E_TipoDeEnemigo.JEFE) {
            return "|===========================|\n"+
                   "|No se puede huir de un JEFE|\n"+
                   "|===========================|";
        }

        if (this.jugador.huir()) {
            this.terminada = true;
            this.resultado = E_ResultadoPartida.ABANDONO;
            return "|>>> Lograste huir <<<|\n";
        }

        return "|>>> Intentaste huir pero no pudiste <<<|\n";
    }


    /// El enemigo ataca, salvo que el jugador se haya cubierto con exito.
    private String ataqueDelEnemigo(boolean jugadorSeCubre) {
        if (jugadorSeCubre) {
            return "|>>> " + this.enemigo.getNombreEntidad() + " ataca pero bloqueas el golpe <<<|\n";
        }

        int danio = this.enemigo.atacar();
        this.jugador.recibirDanio(danio);

        return "|>>> " + this.enemigo.getNombreEntidad() + " te hace " + danio + " de daño <<<|\n";
    }



    /// Combate
    public String turno(E_Accion accion) {
        if (this.terminada) {
            return "|=====================|\n"+
                   "|La batalla ya termino|\n"+
                   "|=====================|\n";
        }

        if (accion == null) {
            return "|Accion invalida|\n";
        }

        StringBuilder sb = new StringBuilder();
        boolean jugadorSeCubre = false;

        /// Turno del jugador
        switch (accion) {
            case ATACAR:
                sb.append(ataqueDelJugador());
                break;
            case DEFENDER:
                jugadorSeCubre = this.jugador.defenderse();
                if (jugadorSeCubre) {
                    sb.append("|>>> Te cubris: bloqueas el proximo golpe <<<|\n");
                } else {
                    sb.append("|>>> Intentaste cubrirte y fallaste <<<|\n");
                }
                break;
            case HUIR:
                sb.append(intentarHuir());
                break;
        }

        /// Si el jugador logro huir, la batalla termino
        if (this.terminada) {
            return sb.toString();
        }

        /// Confirmamos si el enemigo se murio
        if (this.enemigo.getVida() <= 0) {
            sb.append("|>>> Venciste a " + this.enemigo.getNombreEntidad() + "! <<<\n");
            this.terminada = true;
            this.resultado = E_ResultadoPartida.VICTORIA;
            return sb.toString();
        }

        /// -Turno del enemigo
        sb.append(ataqueDelEnemigo(jugadorSeCubre));

        /// Confirmamos si sigue con vida el Jugador
        if (this.jugador.getVida() <= 0) {
            sb.append("|===========================|\n"+
                      "|>>> Fuiste derrotado... <<<|\n"+
                      "|===========================|\n");
            this.terminada = true;
            this.resultado = E_ResultadoPartida.DERROTA;
        }

        return sb.toString();
    }




}