package Clases.ClasesObjetos.ObjetosClass;

import Enums.E_Calidad;
import Interfaces.IObtenerID;

import java.util.Objects;

public class Objeto implements IObtenerID<Integer> {

    private Integer id;

    private String nombre;
    private int nivel;
    private E_Calidad calidad;
    private String descripcion;


    //Constructor
    /// El ID lo asigna la base de datos (JDBC), por eso llega por parametro
    public Objeto(Integer id, String nombre, int nivel, E_Calidad calidad){
        this.id = id;
        this.nombre = nombre;
        this.nivel = nivel;
        this.calidad = calidad;
        this.descripcion = "No tiene ninguna descripcion";
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

    public E_Calidad getCalidad() {
        return calidad;
    }


    /// Precio en la tienda: 10 monedas por nivel, multiplicado segun la calidad (COMUN x1, RARO x2, EPICO x3...)
    public int getPrecio() {
        int multiplicador = (this.calidad == null) ? 1 : this.calidad.ordinal() + 1;
        return this.nivel * 10 * multiplicador;
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
        return this.id.equals(aux.getID());

    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(this.id);
    }


    @Override
    public String toString() {
        return "ID: " + this.id + " | Nombre: " + this.nombre+" | Nivel: " + this.nivel + " | Calidad: " + (this.calidad == null ? "-" : this.calidad.getCalidad()) + " | Descripcion: " + this.descripcion + "";
    }
}
