package Clases.ClasesObjetos.Entidades;

import Clases.ClasesObjetos.SistemaDeProbabilidad;
import Enums.E_TipoDeEnemigo;

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


    public void cambiarEstado(){
        if(this.estado){
            this.estado = false;
        }else {
            this.estado = true;
        }
    }





    @Override
    public int golpeCritico() {
        if(SistemaDeProbabilidad.generarProb(20)){
            return this.getDanioBase() * 2;
        }
        return this.getDanioBase();
    }

    @Override
    public int atacar() {
        return this.golpeCritico();
    }

    @Override
    public boolean defenderse() {
        return SistemaDeProbabilidad.generarProb(35);
    }

    @Override
    public boolean huir() {
        return SistemaDeProbabilidad.generarProb(10);
    }

    @Override
    public String toString()
    {
        return "Enemigo [" + super.toString() + "|Tipo: "+ this.ETipoDeEnemigo.getTipoDeEnemigo()+
                "|Estado: "+ this.estado +"]";
    }
}
