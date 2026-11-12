package Clases.ClasesObjetos;

import Clases.ClasesObjetos.Entidades.Enemigo;
import Clases.ClasesObjetos.Entidades.Jugador;
import Enums.E_Dificultad;
import Enums.E_Fases;
import Exceptions.RangoException;

import java.util.ArrayList;

public class Mundo {

    private String nombreMundo;
    private E_Fases fase;
    private Jugador jugadorActual;
    private E_Dificultad dificultad;
    private ArrayList<Enemigo> enemigosMundo;
    private Tienda tienda;

    public Mundo(String nombreMundo, Jugador jugadorActual, Tienda tienda, E_Dificultad dificultad) {
        this.nombreMundo = nombreMundo;
        this.fase = E_Fases.FACE_I;
        this.dificultad = dificultad;
        this.jugadorActual = jugadorActual;
        this.tienda = tienda;
        this.enemigosMundo = new ArrayList<>();
    }

    public Tienda obtenerTienda() {
        return this.tienda;
    }


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

    public boolean generarEnemigosMundo(ArrayList<Enemigo> enemigosGlobal, E_Dificultad  dificultad, int nivelPlayer)throws RangoException {
        ArrayList<Enemigo> auxiliar = new ArrayList<>();


        /// Filtra a los enemigos por el nivel del jugador y por la dificultadad del mundo
        for (int i = 0; i < this.enemigosMundo.size(); i++){
            if(dificultad.obtenerCantidadEnemigos() <= enemigosGlobal.size()){
                if(enemigosGlobal.get(i).getNivel() <= nivelPlayer){
                    auxiliar.add(enemigosGlobal.get(i));
                }
            }else{
                throw new RangoException("| !! No hay suficientes enemigos para jugar en esta Dificultad !! |");
            }
        }



        /// Elegi un enemigo random del arraylist auxiliar
        for (int i = 0; i < dificultad.obtenerCantidadEnemigos(); i++){
            enemigosMundo.add(auxiliar.get(SistemaDeProbabilidad.generadorInt(auxiliar.size())));
        }

        return true;
    }
    








}
