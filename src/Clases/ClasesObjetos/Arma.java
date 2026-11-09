package Clases.ClasesObjetos;

import Enums.E_Calidad;
import Enums.E_ClaseAsociada;
import Exceptions.ClaseAsociadaException;
import Exceptions.RangoException;
import Interfaces.IComprobarClase;



public abstract class Arma extends Objeto implements IComprobarClase {

    private final int danioBase;
    private final E_ClaseAsociada claseAsociada;
    private final E_Calidad calidad;

    public Arma(String nombre, int nivel, int espacios, int danioBase, E_ClaseAsociada claseAsociada, E_Calidad calidad) throws RangoException
    {
        super(nombre, nivel, espacios);
        this.danioBase = danioBase;
        this.claseAsociada = claseAsociada;
        this.calidad = calidad;
    }

    public int getDanioBase()
    {
        return this.danioBase;
    }

    public E_ClaseAsociada getClaseAsociada()
    {
        return this.claseAsociada;
    }

    public E_Calidad getCalidad()
    {
        return this.calidad;
    }


    //Cabe aclarar, que entre los parametros debe ir la Clase Player para saber que tipo de Clase es.
    public String equipar(E_ClaseAsociada claseActual) throws ClaseAsociadaException
    {
        /* IMPORTANTE: NO OLVIDAR COMPROBACION PLAYER */
        /* IMPORTANTE: NO OLVIDAR COMPROBACION PLAYER*/
        /* IMPORTANTE: NO OLVIDAR COMPROBACION PLAYER*/

        //Un jugador solamente puede equiparse armaduras compatibles con su clase.
        String mensaje = "Arma equipada.";

        //Los jugadores crean automaticamente su clase, entonces agregamos un atributo claseAsociada para identificar
        //mas rapido el tipo de clase que tiene el Personaje.

        if(claseActual != this.claseAsociada)
        {
            throw new ClaseAsociadaException("No se puede equipar esta arma |"+ super.getNombre() +
                    "| porque tu clase no es |" + this.claseAsociada.getClaseAsociada()+"|.");
        }

        return mensaje;
    }


}
