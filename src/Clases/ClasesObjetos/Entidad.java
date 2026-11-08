package Clases.ClasesObjetos;

import java.util.Objects;

public abstract class Entidad {
    private String nombre;
    private int vida;
    private int dañoBase;
    private int nivel;

    public Entidad(String nombre, int vida, int dañoBase, int nivel) {
        this.nombre = nombre;
        this.vida = vida;
        this.dañoBase = dañoBase;
        this.nivel = nivel;
    }


    public String getNombre() {
        return nombre;
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
        return this.nombre.equals(aux.getNombre());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.nombre);
    }



}
