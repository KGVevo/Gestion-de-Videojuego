package Clases.ClasesObjetos;

import Enums.E_Dificultad;
import Enums.E_Fases;
import Exceptions.RangoException;

import java.util.ArrayList;

public class Mundo {

    private String nombreMundo;
    private E_Fases fase;
    private Jugador jugadorActual;
    private E_Dificultad dificultad;
    private ArrayList<Enemigo> enemigos;
    private Tienda tienda;

    public Mundo(String nombreMundo, Jugador jugadorActual, Tienda tienda)
    {
        this.nombreMundo = nombreMundo;
        this.fase = E_Fases.FACE_I;
        this.jugadorActual = jugadorActual;
        this.tienda = tienda;
    }

    public void generarMundo(ArrayList<Enemigo> enemigosAuxiliares, int limiteNivel)
    {
        int cantidadEnemigos = (int) (10 * this.dificultad.obtenerDificultad());

        if(this.fase == E_Fases.FACE_II)
        {
            cantidadEnemigos = (int) (cantidadEnemigos * this.fase.obtenerNumeroEnemigos());
        }
        else if(this.fase == E_Fases.FACE_III)
        {
            cantidadEnemigos = (int) (cantidadEnemigos * this.fase.obtenerNumeroEnemigos());
        }
        else if(this.fase == E_Fases.FACE_FINAL)
        {
            cantidadEnemigos = (int) (cantidadEnemigos * this.fase.obtenerNumeroEnemigos());
        }

        for (int i = 0; i < cantidadEnemigos; i++)
        {
            int numeroRandom = SistemaDeProbabilidad.generadorInt(enemigosAuxiliares.size());
            Enemigo enemigoAux = enemigosAuxiliares.get(numeroRandom);
            if(enemigoAux.getNivel() <= limiteNivel)
            {
                this.enemigos.add(enemigoAux);
            }
        }
    }

    public String cambiarFace() {

        String verificacion = "La Fase Maxima solo es hasta la Fase Final";

        if(this.fase == E_Fases.FACE_I)
        {
            this.fase = E_Fases.FACE_II;
            verificacion = "Se cambio a Fase II";
        }
        else if(this.fase == E_Fases.FACE_II)
        {
            this.fase = E_Fases.FACE_III;
            verificacion = "Se cambio a Fase III";
        }
        else if(this.fase == E_Fases.FACE_III)
        {
            this.fase = E_Fases.FACE_FINAL;
            verificacion = "Se cambio a Fase Final";
        }

        return verificacion;
    }

    public Tienda obtenerTienda() {
        return this.tienda;
    }
}
