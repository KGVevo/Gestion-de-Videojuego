package Clases.ClasesObjetos.ObjetosClass;

import Enums.E_Calidad;
import Enums.E_TipoPocion;

public class Pocion extends Objeto {

    private double efecto;
    private E_TipoPocion tipoPocion;

    public Pocion(Integer id, String nombre, int nivel, double efecto, E_TipoPocion tipoPocion, E_Calidad calidad)
    {
        super(id, nombre, nivel, calidad);
        this.efecto = efecto;
        this.tipoPocion = tipoPocion;
    }

    // Cuando crea el objeto
    public Pocion(Objeto obj, double efecto, E_TipoPocion tipoPocion)
    {
        super(obj.getID(), obj.getNombre(), obj.getNivel(), obj.getCalidad());
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
