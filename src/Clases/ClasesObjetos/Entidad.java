package Clases.ClasesObjetos;

import Interfaces.IInterfazDeCombate;

import java.util.Objects;

public abstract class Entidad implements IInterfazDeCombate {
    private String nombreEntidad;
    private int vida;
    private int dañoBase;
    private int nivel;

    public Entidad(String nombre, int vida, int dañoBase, int nivel) {
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



}
