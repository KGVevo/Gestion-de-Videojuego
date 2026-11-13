package Clases.ClasesObjetos;

import Clases.ClasesObjetos.Entidades.Enemigo;
import Clases.ClasesObjetos.Entidades.Jugador;
import Clases.ClasesObjetos.ObjetosClass.Objeto;
import Enums.E_Calidad;
import Enums.E_Dificultad;
import Enums.E_Fases;
import Exceptions.ObjetoInvalidoException;
import Exceptions.RangoException;
import Interfaces.IObtenerID;

import java.util.ArrayList;

public class Mundo implements IObtenerID {

    private String nombreMundo;
    private E_Fases fase;
    private Jugador jugadorActual;
    private E_Dificultad dificultad;
    private ArrayList<Enemigo> enemigosMundo;
    private ArrayList<Objeto> tiendaObjetos;
    private int cantidadObjetosTienda;



    /// Constructor
    public Mundo(String nombreMundo, Jugador jugadorActual, E_Dificultad dificultad) {
        this.nombreMundo = nombreMundo;
        this.fase = E_Fases.FACE_I;
        this.dificultad = dificultad;
        this.jugadorActual = jugadorActual;
        this.tiendaObjetos = new ArrayList<>();
        this.enemigosMundo = new ArrayList<>();
        this.cantidadObjetosTienda = 5; // 5 objetos por tienda por defecto
    }

    /// Getters
    public ArrayList<Objeto> getTienda() {
        return tiendaObjetos;
    }

    public String getNombreMundo() {
        return nombreMundo;
    }

    public E_Fases getFase() {
        return fase;
    }

    public Jugador getJugadorActual() {
        return jugadorActual;
    }

    public E_Dificultad getDificultad() {
        return dificultad;
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

    @Override
    public Jugador getID() {
        return this.jugadorActual;
    }



    /// Metodos ///

    /// Metodo para avanzar las fases
    public boolean avanzarFace(){
        switch (this.fase){
            case FACE_I -> this.fase = E_Fases.FACE_II;
            case FACE_II -> this.fase = E_Fases.FACE_III;
            case FACE_III -> this.fase = E_Fases.FACE_FINAL;
            case FACE_FINAL -> {
                return false;
            }
        }

        return true;
    }

    /// Genera la calidad del Loot de enemigos
    public E_Calidad generarCalidadObjeto(){
        E_Calidad calidad = E_Calidad.COMUN;

        if(SistemaDeProbabilidad.generarProb(70)){
            calidad = E_Calidad.RARO;
            if(SistemaDeProbabilidad.generarProb(45)){
                calidad = E_Calidad.EPICO;
                if(SistemaDeProbabilidad.generarProb(20)){
                    calidad = E_Calidad.LEGENDARIO;
                    if(SistemaDeProbabilidad.generarProb(5)){
                        calidad = E_Calidad.MITICO;
                    }
                }
            }
        }



        return calidad;
    }

    public void modificarEnemigos(ArrayList<Enemigo> enemigos){
        this.enemigosMundo.clear();
        this.enemigosMundo = enemigos;
    }

    public void modificarTienda(ArrayList<Objeto> tiendas){
        this.tiendaObjetos.clear();
        this.tiendaObjetos = tiendas;
    }


}
