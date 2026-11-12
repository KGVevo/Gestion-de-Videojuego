package Clases.ClasesObjetos.ObjetosClass;

import Enums.E_Calidad;
import Enums.E_TipoPocion;
import Exceptions.RangoException;

public class Pocion extends Objeto {

    private double efecto;
    private E_TipoPocion tipoPocion;

    public Pocion(String nombre, int nivel, double efecto, E_TipoPocion tipoPocion, E_Calidad calidad) throws RangoException
    {
        super(nombre, nivel, calidad);
        this.efecto = efecto;
        this.tipoPocion = tipoPocion;
    }

    public double getEfecto()
    {
        return this.efecto;
    }

    public E_TipoPocion getTipoPocion()
    {
        return this.tipoPocion;
    }

    @Override
    public String toString()
    {
        return "Pocion ["+super.toString()+" | Efecto: "+this.efecto+" | Tipo de Pocion: "+this.tipoPocion+"]";
    }

}
