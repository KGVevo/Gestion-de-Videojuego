package Clases.ClasesObjetos.ObjetosClass;

import Enums.E_Calidad;
import Enums.E_ClaseAsociada;

public class ArmaDistancia extends Arma {

    private int consumoMunicion;

    //Constructor
    public ArmaDistancia(Integer id, String nombre, int nivel, int danioBase, E_Calidad calidad, int consumoMunicion)
    {
        super(id, nombre, nivel, calidad, danioBase, E_ClaseAsociada.DISTANCIA);
        this.consumoMunicion = consumoMunicion;
    }

    public ArmaDistancia(Objeto obj, int danioBase, int consumoMunicion)
    {
        super(obj.getID(), obj.getNombre(), obj.getNivel(), obj.getCalidad(), danioBase, E_ClaseAsociada.DISTANCIA);
        this.consumoMunicion = consumoMunicion;
    }

    //Metodos
    public int getConsumoMunicion()
    {
        return this.consumoMunicion;
    }

    public String toString()
    {
        return "Arma Distancia ["+super.toString()+" | Consumo Municion: "+this.consumoMunicion+"]";
    }

}
