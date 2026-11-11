package Clases.ClasesObjetos.ObjetosClass;

import Enums.E_ClaseAsociada;
import Exceptions.ClaseAsociadaException;
import Exceptions.RangoException;
import Interfaces.IComprobarClase;


public class Armadura extends Objeto implements IComprobarClase {

    private double proteccion;
    private final E_ClaseAsociada claseAsociada;
    private double efecto;

    public Armadura(String nombre, int nivel, double proteccion, E_ClaseAsociada claseAsociada, double efecto) throws RangoException
    {

        if(proteccion > 100.00 || proteccion < 0.00)
        {
            throw new RangoException("ERROR: La proteccion no puede ser Mayor a 100 o Menor a 0");
        }

        super(nombre, nivel);
        this.proteccion = proteccion;
        this.claseAsociada = claseAsociada;
        this.efecto = efecto;
    }

    public double getProteccion()
    {
        return this.proteccion;
    }

    public E_ClaseAsociada getClaseAsociada()
    {
        return this.claseAsociada;
    }

    public double getEfecto()
    {
        return this.efecto;
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

}
