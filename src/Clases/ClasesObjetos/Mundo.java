package Clases.ClasesObjetos;

import Enums.E_Fases;
import Exceptions.RangoException;

import java.util.ArrayList;

public class Mundo {

    private String nombreMundo;
    private E_Fases fase;
    private Jugador jugadorActual;
    private ArrayList<Enemigo> enemigos;
    private Tienda tienda;

    public Mundo(String nombreMundo, E_Fases fase, Jugador jugadorActual,
                 ArrayList<Enemigo> enemigos, int cantidadEnemigos,
                 Tienda tienda) throws RangoException
    {

        if(fase == E_Fases.FACE_I && jugadorActual.getNivel() > 7)
        {
            throw new RangoException("Solo se permite hasta nivel 10 en la fase I");
        }
        else if(fase == E_Fases.FACE_II && jugadorActual.getNivel() > 15)
        {
            throw new RangoException("Solo se permite hasta nivel 10 en la fase II");
        }
        else if(fase == E_Fases.FACE_III && jugadorActual.getNivel() > 20)
        {
            throw new RangoException("Solo se permite hasta nivel 10 en la fase III");
        }
        else if(fase == E_Fases.FACE_FINAL && jugadorActual.getNivel() > 30)
        {
            throw new RangoException("Solo se permite hasta nivel 10 en la fase final");
        }

        this.nombreMundo = nombreMundo;
        this.fase = fase;
        this.jugadorActual = jugadorActual;

        generarEnemigos(enemigos, cantidadEnemigos, fase.obtenerNumeroNivel());

        this.tienda = tienda;
    }

    private void generarEnemigos(ArrayList<Enemigo> enemigosAuxiliares, int cantidadEnemigos, int fase)
    {
        for (int i = 0; i < cantidadEnemigos; i++) {

                if(enemigosAuxiliares.get(i).getNivel() <= this.fase.obtenerNumeroNivel() &&
                        this.fase.obtenerNumeroFace() == fase)
                {
                    this.enemigos.add(enemigosAuxiliares.get(i));
                }
        }
    }

    public String obtenerFaceYNivel()
    {
        return "La fase de tu mundo es "+this.fase.obtenerNumeroFace()+". Solo se permite nivel "+this.fase.obtenerNumeroNivel()+".";
    }

}
