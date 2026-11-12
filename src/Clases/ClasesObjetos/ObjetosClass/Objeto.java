package Clases.ClasesObjetos.ObjetosClass;

import Exceptions.RangoException;
import Interfaces.IObtenerID;

import java.util.Objects;

public abstract class Objeto implements IObtenerID {

    private static int contador = 1;
    private Integer id;
    private String nombre;
    private int nivel;

    private String descripcion;


    //Constructor
    public Objeto(String nombre, int nivel) throws RangoException
    {
        if(nivel < 1)
        {
            throw new RangoException("ERROR: El nivel debe ser mayor o igual a 1");
        }

        if(nivel > 20)
        {
            throw new RangoException("ERROR: El nivel debe ser como maximo 20");
        }

        this.id = contador++;
        this.nombre = nombre;
        this.nivel = nivel;
        this.descripcion = "No tiene ninguna descripcion";
    }


    public int getIdentificador()
    {
        return this.id;
    }

    public String getNombre()
    {
        return this.nombre;
    }

    public int getNivel()
    {
        return this.nivel;
    }

    public String getDescripcion()
    {
        return this.descripcion;
    }


    @Override
    public Integer getID() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj){
            return true;
        }

        if(obj == null || this.getClass() != obj.getClass()){
            return false;
        }

        Objeto aux = (Objeto) obj;
        return this.id.equals(aux.getIdentificador());

    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(this.id);
    }


    @Override
    public String toString() {
        return "ID: " + this.id + " | Nombre: " + this.nombre+" | Nivel: " + this.nivel +  " | Descripcion: " + this.descripcion + "";
    }
}
