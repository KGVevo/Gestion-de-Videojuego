package Clases.ClasesObjetos.ObjetosClass;

import Enums.E_Calidad;
import Enums.E_ClaseAsociada;
import Exceptions.RangoException;

public class ArmaDistancia extends Arma {

    private int consumoMunicion;

    //Constructor
    public ArmaDistancia(String nombre, int nivel, int dañoBase, E_Calidad calidad, int consumoMunicion) throws RangoException
    {
        super(nombre, nivel, dañoBase, E_ClaseAsociada.DISTANCIA, calidad);
        this.consumoMunicion = consumoMunicion;
    }


    //Metodos
    private int getConsumoMunicion()
    {
        return this.consumoMunicion;
    }

    public String toString()
    {
        return "Arma Distancia ["+super.toString()+" | Consumo Municion: "+this.consumoMunicion+"]";
    }

}
