package Clases.ClasesObjetos.SistemaDeJuego;

import Clases.ClasesObjetos.Entidades.Enemigo;
import Clases.ClasesObjetos.Entidades.Jugador;
import Clases.ClasesObjetos.ObjetosClass.Objeto;
import Enums.*;
import Interfaces.IObtenerID;

import java.util.ArrayList;

public class Mundo implements IObtenerID<Integer> {

    private Integer id;
    private String nombreMundo;
    private E_Fases fase;
    private E_Dificultad dificultad;
    private ArrayList<Enemigo> enemigosMundo;
    private ArrayList<Objeto> tiendaObjetos;
    private int cantidadObjetosTienda;

    /// Jugador que esta dentro del mundo. Solo tiene valor mientras el mundo esta EN_PARTIDA
    private Jugador jugadorActual;

    /// Estado del mundo
    private E_EstadoMundo estadoMundo;

    /// Motivo y detalle. Solo tienen valor mientras el mundo esta NO_DISPONIBLE
    private E_MotivoMundo motivoMundo;
    private String detalle;


    /// Constructor: un mundo nuevo nace DISPONIBLE y sin nadie adentro
    public Mundo(int id ,String nombreMundo, E_Dificultad dificultad) {
        this.id = id;

        this.nombreMundo = nombreMundo;
        this.fase = E_Fases.FACE_I;
        this.dificultad = dificultad;
        this.jugadorActual = null;
        this.tiendaObjetos = new ArrayList<>();
        this.enemigosMundo = new ArrayList<>();
        this.cantidadObjetosTienda = 5; // 5 objetos por tienda por defecto
        this.estadoMundo = E_EstadoMundo.DISPONIBLE;
        this.motivoMundo = null;
        this.detalle = null;
    }


    /// Getters
    @Override
    public Integer getID() {
        return this.id;
    }

    public String getNombreMundo() {
        return nombreMundo;
    }

    public E_Fases getFase() {
        return fase;
    }

    public E_Dificultad getDificultad() {
        return dificultad;
    }

    public Jugador getJugadorActual() {
        return jugadorActual;
    }

    public ArrayList<Enemigo> getEnemigosMundo() {
        return enemigosMundo;
    }

    public ArrayList<Objeto> getTiendaObjetos() {
        return tiendaObjetos;
    }

    public int getCantidadObjetosTienda() {
        return cantidadObjetosTienda;
    }

    public E_EstadoMundo getEstadoMundo() {
        return estadoMundo;
    }

    public E_MotivoMundo getMotivoMundo() {
        return motivoMundo;
    }

    public String getDetalle() {
        return detalle;
    }


    /// Metodos de estado ///

    /// Un jugador entra al mundo. Solo se puede si el mundo esta DISPONIBLE.
    public boolean entrar(Jugador jugador) {
        if (jugador == null || this.estadoMundo != E_EstadoMundo.DISPONIBLE) {
            return false;
        }

        this.jugadorActual = jugador;
        this.estadoMundo = E_EstadoMundo.EN_PARTIDA;
        return true;
    }

    /// El jugador sale del mundo.
    public boolean salir() {
        if (this.estadoMundo != E_EstadoMundo.EN_PARTIDA) {
            return false;
        }

        this.jugadorActual = null;
        this.estadoMundo = E_EstadoMundo.DISPONIBLE;
        return true;
    }

    /// Cierra el mundo indicando el motivo. No se puede cerrar mientras alguien esta jugando.
    public boolean marcarNoDisponible(E_MotivoMundo motivoMundo, String detalle) {
        if (this.estadoMundo == E_EstadoMundo.EN_PARTIDA) {
            return false;
        }

        this.estadoMundo = E_EstadoMundo.NO_DISPONIBLE;
        this.motivoMundo = motivoMundo;
        this.detalle = detalle;
        return true;
    }

    /// Vuelve a abrir un mundo que estaba NO_DISPONIBLE y borra el motivo.
    public boolean marcarDisponible() {
        if (this.estadoMundo != E_EstadoMundo.NO_DISPONIBLE) {
            return false;
        }

        this.estadoMundo = E_EstadoMundo.DISPONIBLE;
        this.motivoMundo = null;
        this.detalle = null;
        return true;
    }


    /// Metodos del juego ///

    /// Metodo para avanzar las fases. Devuelve false si ya esta en la fase final.
    public boolean avanzarFace() {
        switch (this.fase) {
            case FACE_I:
                this.fase = E_Fases.FACE_II;
                break;
            case FACE_II:
                this.fase = E_Fases.FACE_III;
                break;
            case FACE_III:
                this.fase = E_Fases.FACE_FINAL;
                break;
            default:
                return false;
        }

        return true;
    }

    /// Genera la calidad del Loot de enemigos
    public E_Calidad generarCalidadObjeto() {
        E_Calidad calidad = E_Calidad.COMUN;

        if (SistemaDeProbabilidad.generarProb(70)) {
            calidad = E_Calidad.RARO;
            if (SistemaDeProbabilidad.generarProb(45)) {
                calidad = E_Calidad.EPICO;
                if (SistemaDeProbabilidad.generarProb(20)) {
                    calidad = E_Calidad.LEGENDARIO;
                    if (SistemaDeProbabilidad.generarProb(5)) {
                        calidad = E_Calidad.MITICO;
                    }
                }
            }
        }

        return calidad;
    }



    /// Reemplaza los enemigosGlobal del mundo por una copia de la lista recibida
    public void modificarEnemigos(ArrayList<Enemigo> enemigosGlobal) {
        this.enemigosMundo = new ArrayList<>(enemigosGlobal);
    }

    /// Saca de la lista al enemigo vencido. Solo lo saca si es el primero de la lista
    public boolean eliminarEnemigoVencido(Enemigo enemigo) {
        if (enemigo == null || this.enemigosMundo.isEmpty()) {
            return false;
        }

        if (!this.enemigosMundo.get(0).getID().equals(enemigo.getID())) {
            return false;
        }

        this.enemigosMundo.remove(0);
        return true;
    }



    /// Reemplaza la tienda del mundo por una copia de la lista recibida
    public void modificarTienda(ArrayList<Objeto> tiendaGlobal) {
        this.tiendaObjetos = new ArrayList<>(tiendaGlobal);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Mundo [ID: ").append(this.id);
        sb.append(" | Nombre: ").append(this.nombreMundo);
        sb.append(" | Dificultad: ").append(this.dificultad);
        sb.append(" | Fase: ").append(this.fase.obtenerFaceActual());
        sb.append(" | Estado: ").append(this.estadoMundo);

        if (this.estadoMundo == E_EstadoMundo.NO_DISPONIBLE) {
            sb.append(" | Motivo: ").append(this.motivoMundo);
            sb.append(" (").append(this.detalle).append(")");
        }

        if (this.estadoMundo == E_EstadoMundo.EN_PARTIDA) {
            sb.append(" | Jugador: ").append(this.jugadorActual.getNombreEntidad());
        }

        sb.append("]\n");

        return sb.toString();
    }
}