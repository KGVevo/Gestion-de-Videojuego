package Clases.ClasesObjetos;

import Enums.E_TipoPocion;
import Exceptions.RangoException;

public class Pocion extends Objeto{

    private double efecto;
    private E_TipoPocion tipoPocion;

    public Pocion(String nombre, int nivel, int espacios, double efecto, E_TipoPocion tipoPocion) throws RangoException
    {
        super(nombre, nivel, espacios);
        this.efecto = efecto;
        this.tipoPocion = tipoPocion;
    }

    public double obtenerEfecto()
    {
        return this.efecto;
    }

    public E_TipoPocion obtenerTipoPocion()
    {
        return this.tipoPocion;
    }

}
