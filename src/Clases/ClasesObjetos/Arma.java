package Clases.ClasesObjetos;

import Enums.E_Calidad;
import Enums.E_ClaseAsociada;
import Exceptions.ClaseAsociadaException;
import Exceptions.RangoException;
import Interfaces.I_comprobarClase;

public abstract class Arma extends Objeto implements I_comprobarClase {

    private final int dañoBase;
    private final E_ClaseAsociada claseAsociada;
    private E_Calidad calidad;

    public Arma(String nombre, int nivel, int espacios, int dañoBase, E_ClaseAsociada claseAsociada, E_Calidad calidad) throws RangoException
    {
        super(nombre, nivel, espacios);
        this.dañoBase = dañoBase;
        this.claseAsociada = claseAsociada;
        this.calidad = calidad;
    }

    public int obtenerDañoBase()
    {
        return this.dañoBase;
    }

    public E_ClaseAsociada obtenerClaseAsociada()
    {
        return this.claseAsociada;
    }

    public E_Calidad obtenerCalidad()
    {
        return this.calidad;
    }

    //Cabe aclarar, que entre los parametros debe ir la Clase Player para saber que tipo de Clase es.
    public String equipar(E_ClaseAsociada claseActual) throws ClaseAsociadaException
    {
        //Un jugador solamente puede equiparse armaduras compatibles con su clase.
        String mensaje = "Arma equipada.";

        //Los jugadores crean automaticamente su clase, entonces agregamos un atributo claseAsociada para identificar
        //mas rapido el tipo de clase que tiene el Personaje.
        if(claseActual != this.claseAsociada)
        {
            throw new ClaseAsociadaException("No se puede equipar esta arma "+super.obtenerNombre()+" porque tu clase no es "+this.claseAsociada.obtenerClaseAsociada()+".");
        }

        return mensaje;
    }

}
