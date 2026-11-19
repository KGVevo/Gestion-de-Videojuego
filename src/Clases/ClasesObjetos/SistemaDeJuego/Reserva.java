package Clases.ClasesObjetos.SistemaDeJuego;

import Clases.ClasesObjetos.Entidades.Jugador;
import Enums.E_EstadoReserva;
import Interfaces.IObtenerID;

import java.time.LocalDate;

public class Reserva implements IObtenerID<Integer> {
    private Integer id;

    private Jugador jugador;
    private Mundo mundo;
    private LocalDate desde;
    private LocalDate hasta;
    private E_EstadoReserva estadoReserva;


    /// Constructor: una reserva nueva siempre nace ACTIVA. El ID lo asigna la base de datos
    public Reserva(Integer id, Jugador jugador, Mundo mundo, LocalDate desde, LocalDate hasta) {
        this.id = id;
        this.jugador = jugador;
        this.mundo = mundo;
        this.desde = desde;
        this.hasta = hasta;
        this.estadoReserva = E_EstadoReserva.ACTIVA;
    }


    /// Getters
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

    public LocalDate getDesde() {
        return desde;
    }

    public LocalDate getHasta() {
        return hasta;
    }

    public E_EstadoReserva getEstadoReserva() {
        return estadoReserva;
    }



    /// Verificamos si las fechas tienen sentido
    public boolean comprobarFechas(LocalDate desde, LocalDate hasta) {
        return desde.isBefore(this.hasta) && hasta.isAfter(this.desde);
    }

    /// Comprueba si la reserva todavia esta disponible
    public boolean comprobarReserva() {
        if (this.estadoReserva != E_EstadoReserva.ACTIVA) {
            return false;
        }

        /// Comprueba si la fecha de ahora es antes que hasta (A < B)
        return LocalDate.now().isBefore(this.hasta);
    }


    /// Cancela la reserva. Solo se puede cancelar una reserva ACTIVA.
    public boolean cancelarReserva() {
        if (this.estadoReserva != E_EstadoReserva.ACTIVA) {
            return false;
        }

        this.estadoReserva = E_EstadoReserva.CANCELADA;
        return true;
    }


    /// Marca la reserva como cumplida, solamente si esta ACTIVA
    public boolean cumplirReserva() {
        if (!this.comprobarReserva()) {
            return false;
        }

        this.estadoReserva = E_EstadoReserva.CUMPLIDA;
        return true;
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Reserva [ID: ").append(this.id);
        sb.append(" | Jugador: ").append(this.jugador.getNombreEntidad());
        sb.append(" | Mundo: ").append(this.mundo.getNombreMundo());
        sb.append(" | Desde: ").append(this.desde);
        sb.append(" | Hasta: ").append(this.hasta);
        sb.append(" | Estado: ").append(this.estadoReserva);
        sb.append("]\n");

        return sb.toString();
    }
}