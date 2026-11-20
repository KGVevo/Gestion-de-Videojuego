package Clases.ClasesObjetos.Entidades;

import Interfaces.IInterfazDeCombate;
import Interfaces.IObtenerID;

import java.util.Objects;

public abstract class Entidad implements IInterfazDeCombate, IObtenerID<Integer> {
    private Integer id;
    private String nombreEntidad;
    private int vida;
    private int danioBase;
    private int nivel;

    public Entidad(Integer id, String nombre, int vida, int danioBase, int nivel) {
        this.id = id;
        this.nombreEntidad = nombre;
        this.vida = vida;
        this.danioBase = danioBase;
        this.nivel = nivel;
    }


    public String getNombreEntidad() {
        return nombreEntidad;
    }

    public int getVida() {
        return vida;
    }

    public int getDanioBase() {
        return danioBase;
    }

    public int getNivel() {
        return nivel;
    }

    public Integer getID() {
        return this.id;
    }

    protected void setNivel(int nivel) {
        this.nivel = nivel;
    }


    /// Resta vida
    public int recibirDanio(int valor) {
        this.vida -= valor;

        if (this.vida < 0) {
            this.vida = 0;
        }

        return this.vida;
    }

    /// Suma vida
    public int curar(int valor) {
        this.vida += valor;

        if (this.vida > 100) {
            this.vida = 100;
        }

        return this.vida;
    }


    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }

        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        /// Comparamos los Enemigos por ID
        Entidad aux = (Entidad) obj;
        return Objects.equals(this.id, aux.getID());
    }


    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public String toString()
    {
        return "|ID: " + this.id + "|Nombre: "+this.nombreEntidad+"|Vida Actual: "+this.vida+"|Daño Base: "
                + this.danioBase + "|Nivel: " +this.nivel + " | ";
    }

}
