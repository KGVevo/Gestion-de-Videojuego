package Clases.ClasesObjetos.Entidades;

import Enums.E_TipoDeEnemigo;
import Interfaces.IObtenerID;

public class Enemigo extends Entidad {

    private E_TipoDeEnemigo ETipoDeEnemigo;
    private boolean estado; /// Estado de vivo o muerto


    public Enemigo(String nombreEnemigo, int vida, int danioBase, int nivel, E_TipoDeEnemigo ETipoDeEnemigo) {
        super(nombreEnemigo, vida, danioBase, nivel);
        this.ETipoDeEnemigo = ETipoDeEnemigo;
        this.estado = true;
    }

    public E_TipoDeEnemigo getTipoDeEnemigo() {
        return ETipoDeEnemigo;
    }

    public Integer obtenerID()
    {
        return super.getID();
    }


    @Override
    public String toString()
    {
        String estadoActual = "Muerto";
        if(this.estado == true)
        {
            estadoActual = "Vivo";
        }

        return "Enemigo ["+super.toString()+" | Tipo: "+this.ETipoDeEnemigo.getTipoDeEnemigo()+
                " | Estado: "+estadoActual+"]";
    }




    @Override
    public int golpeCritico() {
        return 0;
    }

    @Override
    public int atacar() {
        return 0;
    }

    @Override
    public boolean habilidadArma() {
        return false;
    }

    @Override
    public boolean defenderse() {
        return false;
    }

    @Override
    public boolean huir() {
        return false;
    }

}
