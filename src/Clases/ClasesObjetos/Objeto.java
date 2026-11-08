package Clases.ClasesObjetos;

import Exceptions.RangoException;

import java.util.Objects;

public abstract class Objeto {

    private static int contador = 1;
    private int id;
    private String nombre;
    private int nivel;
    private int espacios;
    private String descripcion;

    public Objeto(String nombre, int nivel, int espacios) throws RangoException
    {
        if(nivel < 0)
        {
            throw new RangoException("ERROR: El nivel debe ser mayor o igual a 0");
        }

        if(nivel > 21)
        {
            throw new RangoException("ERROR: El nivel debe ser como maximo 20");
        }

        this.id = contador++;
        this.nombre = nombre;
        this.nivel = nivel;
        this.espacios = espacios;
        this.descripcion = "No tiene ninguna descripcion";
    }

    public int obtenerIdentificador()
    {
        return this.id;
    }

    public String obtenerNombre()
    {
        return this.nombre;
    }

    public int obtenerNivel()
    {
        return this.nivel;
    }

    public int obtenerEspacios()
    {
        return this.espacios;
    }

    public String obtenerDescripcion()
    {
        return this.descripcion;
    }

    @Override
    public boolean equals(Object obj)
    {
        boolean verificacion = false;
        if(obj != null && obj instanceof Objeto)
        {
            Objeto aux = (Objeto) obj;
            if(aux.obtenerIdentificador() == this.id)
            {
                verificacion = true;
            }
        }
        return verificacion;
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(this.id);
    }

}
