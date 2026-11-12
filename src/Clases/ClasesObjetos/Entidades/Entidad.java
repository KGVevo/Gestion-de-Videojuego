package Clases.ClasesObjetos.Entidades;

import Interfaces.IInterfazDeCombate;
import Interfaces.IObtenerID;

import java.util.Objects;

public abstract class Entidad implements IInterfazDeCombate, IObtenerID {

    private static int contador = 1;
    private Integer id;
    private String nombreEntidad;
    private int vida;
    private int dañoBase;
    private int nivel;

    public Entidad(String nombre, int vida, int dañoBase, int nivel) {
        this.id = contador++;
        this.nombreEntidad = nombre;
        this.vida = vida;
        this.dañoBase = dañoBase;
        this.nivel = nivel;
    }


    public String getNombreEntidad() {
        return nombreEntidad;
    }

    public int getVida() {
        return vida;
    }

    public int getDañoBase() {
        return dañoBase;
    }

    public int getNivel() {
        return nivel;
    }

    public Integer getID()
    {
        return this.id;
    }


    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }

        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        Entidad aux = (Entidad) obj;
        return this.nombreEntidad.equals(aux.getNombreEntidad());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.nombreEntidad);
    }

    @Override
    public String toString()
    {
        return "ID: "+this.id+" | Nombre: "+this.nombreEntidad+" | Vida Actual: "+this.vida+" | Daño Base: "
                +this.dañoBase+" | Nivel: "+this.nivel+"";
    }

}
