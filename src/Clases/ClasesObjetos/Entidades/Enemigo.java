package Clases.ClasesObjetos.Entidades;

import Clases.ClasesObjetos.SistemaDeJuego.SistemaDeProbabilidad;
import Enums.E_TipoDeEnemigo;

public class Enemigo extends Entidad {

    private E_TipoDeEnemigo ETipoDeEnemigo;


    public Enemigo(int id ,String nombreEnemigo, int vida, int danioBase, int nivel, E_TipoDeEnemigo ETipoDeEnemigo) {
        super(id ,nombreEnemigo, vida, danioBase, nivel);
        this.ETipoDeEnemigo = ETipoDeEnemigo;
    }

    public E_TipoDeEnemigo getTipoDeEnemigo() {
        return ETipoDeEnemigo;
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
        return "Enemigo [" + super.toString() + "|Tipo: "+ this.ETipoDeEnemigo.getTipoDeEnemigo()+"|.\n";
    }
}
