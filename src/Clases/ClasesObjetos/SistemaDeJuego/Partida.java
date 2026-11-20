package Clases.ClasesObjetos.SistemaDeJuego;

import Clases.ClasesObjetos.Entidades.Jugador;
import Enums.E_ResultadoPartida;
import Interfaces.IObtenerID;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Partida implements IObtenerID<Integer> {
    private Integer id;

    private Jugador jugador;
    private Mundo mundo;

    private LocalDate inicio;
    private LocalDate fin;                      /// Es null mientras la partida sigue en curso
    private E_ResultadoPartida resultadoPartida;    /// Es null mientras la partida sigue en curso

    private int expGanada;
    private int monedasGanadas;


    /// Constructor 1: Para la JDBC
    public Partida(Integer id, Jugador jugador, Mundo mundo, LocalDate inicio, LocalDate fin,  E_ResultadoPartida resultadoPartida,  int expGanada, int monedasGanadas) {
        this.id = id;
        this.jugador = jugador;
        this.mundo = mundo;
        this.inicio = inicio;
        this.fin = fin;
        this.resultadoPartida = resultadoPartida;
        this.expGanada = expGanada;
        this.monedasGanadas = monedasGanadas;
    }



    /// Constructor: Para la creacion
    public Partida(Integer id, Jugador jugador, Mundo mundo) {
        this.id = id;
        this.jugador = jugador;
        this.mundo = mundo;
        this.inicio = LocalDate.now();
        this.fin = null;
        this.resultadoPartida = null;
        this.expGanada = 0;
        this.monedasGanadas = 0;
    }


    /////////////////////////////////////////////// Getters ////////////////////////////////////////////////////////////
    @Override
    public Integer getID() {
        return this.id;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public Mundo getMundo() {
        return mundo;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public LocalDate getFin() {
        return fin;
    }

    public E_ResultadoPartida getResultadoPartida() {
        return resultadoPartida;
    }

    public int getExpGanada() {
        return expGanada;
    }

    public int getMonedasGanadas() {
        return monedasGanadas;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    /// La partida esta en curso cuando la fecha fin esta en NULL
    public boolean partidaEnCurso() {
        return this.fin == null;
    }


    /// Suma lo ganado en una batalla mientras la partida esta en curso.
    public boolean sumarRecompensa(int exp, int monedas) {
        if (!this.partidaEnCurso()) {
            return false;
        }

        if (exp < 0 || monedas < 0) {
            return false;
        }

        this.expGanada += exp;
        this.monedasGanadas += monedas;
        return true;
    }


    /// Termina la partida y guarda los datos
    public boolean finalizarPartida(E_ResultadoPartida resultado, int expGanada, int monedasGanadas) {
        if (!this.partidaEnCurso()) {
            return false;
        }

        if (resultado == null || expGanada < 0 || monedasGanadas < 0) {
            return false;
        }

        this.fin = LocalDate.now();
        this.resultadoPartida = resultado;
        this.expGanada = expGanada;
        this.monedasGanadas = monedasGanadas;
        return true;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Partida [ID: ").append(this.id);
        sb.append(" | Jugador: ").append(this.jugador.getNombreEntidad());
        sb.append(" | Mundo: ").append(this.mundo.getNombreMundo());
        sb.append(" | Inicio: ").append(this.inicio);

        if (this.partidaEnCurso()) {
            sb.append(" | EN CURSO");
        } else {
            sb.append(" | Fin: ").append(this.fin);
            sb.append(" | Resultado: ").append(this.resultadoPartida);
            sb.append(" | Exp: ").append(this.expGanada);
            sb.append(" | Monedas: ").append(this.monedasGanadas);
        }

        sb.append("]\n");

        return sb.toString();
    }
}