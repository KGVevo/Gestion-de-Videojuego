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


    public ArrayList<Objeto> getTienda() {
        return tiendaObjetos;
    }

    @Override
    public Jugador getID() {
        return this.jugadorActual;
    }

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

    /// Metodo para generar enemigos para el mundo
    public boolean generarEnemigosMundo(ArrayList<Enemigo> enemigosGlobal, int nivelPlayer)throws RangoException {
        /// Creamos un Arraylist auxiliar para guardar los enemigos filtrados
        ArrayList<Enemigo> auxiliar = new ArrayList<>();
        int cantidadEnemigos = this.dificultad.obtenerCantidadEnemigos() + this.fase.obtenerNumeroEnemigos();

        /// Filtra a los enemigos por el nivel del jugador y por la dificultadad del mundo
        for (int i = 0; i < this.enemigosMundo.size(); i++){
            if(cantidadEnemigos <= enemigosGlobal.size()){
                if(enemigosGlobal.get(i).getNivel() <= nivelPlayer){
                    auxiliar.add(enemigosGlobal.get(i));
                }
            }else{
                throw new RangoException("| !! No hay suficientes enemigos para jugar en esta Dificultad !! |");
            }
        }



        /// Elegi un enemigo random del arraylist auxiliar
        for (int i = 0; i < cantidadEnemigos; i++){
            this.enemigosMundo.add(auxiliar.get(SistemaDeProbabilidad.generadorInt(auxiliar.size())));
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


    /// Funcion para generar una tienda randomizada en base al Arraylist de Objetos global
    public boolean generarTienda(ArrayList<Objeto> objetos)throws ObjetoInvalidoException {


        if(objetos.size() < this.cantidadObjetosTienda){
            throw new ObjetoInvalidoException("|No hay suficientes objetos para rellenar la Tienda|");
        }

        for(int i = 0; i < this.cantidadObjetosTienda; i++){
            tiendaObjetos.add(objetos.get(SistemaDeProbabilidad.generadorInt(objetos.size())));
        }

        return true;
    }
}
