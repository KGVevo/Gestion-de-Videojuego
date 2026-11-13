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
    public int getDanioBase() {
        return dañoBase;
    }
    public int getNivel() {
        return nivel;
    }
    public Integer getID() {
        return this.id;
    }
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }


    /// Modificar de vida tanto para restar como para sumar
    public int modificadorDeVida(int valor, int opcion){
        if(opcion == -1){
            this.vida -= valor;

            if(this.vida < 0) {
                this.vida = 0;
            }

        } else if (opcion == 1){
            this.vida += valor;
            if(this.vida > 100){
                this.vida = 100;
            }
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
        return "|ID: " + this.id + "|Nombre: "+this.nombreEntidad+"|Vida Actual: "+this.vida+"|Daño Base: "
                + this.dañoBase + "|Nivel: " +this.nivel + "|";
    }

}
