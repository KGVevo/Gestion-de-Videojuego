package Clases.ClasesObjetos.ObjetosClass;

import Enums.E_Calidad;
import Enums.E_ClaseAsociada;
import Exceptions.ClaseAsociadaException;
import Exceptions.RangoException;
import Interfaces.IComprobarClase;


public class Armadura extends Objeto implements IComprobarClase {

    private double proteccion;
    private final E_ClaseAsociada claseAsociada;

    public Armadura(String nombre, int nivel, E_Calidad calidad, double proteccion, E_ClaseAsociada claseAsociada) throws RangoException
    {
        super(nombre, nivel, calidad);

        if(proteccion > 100.00 || proteccion < 0.00)
        {
            throw new RangoException("ERROR: La proteccion no puede ser Mayor a 100 o Menor a 0");
        }


        this.proteccion = proteccion;
        this.claseAsociada = claseAsociada;
    }


    public double getProteccion()
    {
        return this.proteccion;
    }

    public E_ClaseAsociada getClaseAsociada()
    {
        return this.claseAsociada;
    }


    //Cabe aclarar, que entre los parametros debe ir la Clase Player para saber que tipo de Clase es.
    public String equipar(E_ClaseAsociada claseActual) throws ClaseAsociadaException
    {

        //Un jugador solamente puede equiparse armaduras compatibles con su clase.
        String mensaje = "Armadura equipada.";

        //Los jugadores crean automaticamente su clase, entonces agregamos un atributo claseAsociada para identificar
        //mas rapido el tipo de clase que tiene el Personaje.
        if(claseActual != this.claseAsociada)
        {
            throw new ClaseAsociadaException("No se puede equipar la armadura |" + super.getNombre()+ "| porque tu clase no es |" + this.claseAsociada.getClaseAsociada() + "|.");
        }

        return mensaje;
    }

    public String toString()
    {
        return "|Armadura [Proteccion: "+this.proteccion+"% | Clase: " + this.claseAsociada.getClaseAsociada() +
                "|.";
    }

}
