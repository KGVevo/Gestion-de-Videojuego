package Clases.ClasesObjetos.Entidades;

import Enums.E_TipoDeEnemigo;

public class Enemigo extends Entidad {

    private E_TipoDeEnemigo ETipoDeEnemigo;


    public Enemigo(String nombreEnemigo, int vida, int danioBase, int nivel, E_TipoDeEnemigo ETipoDeEnemigo) {
        super(nombreEnemigo, vida, danioBase, nivel);
        this.ETipoDeEnemigo = ETipoDeEnemigo;
    }

    public E_TipoDeEnemigo getTipoDeEnemigo() {
        return ETipoDeEnemigo;
    }


    @Override
    public String atacar() {
        return "";
    }

    @Override
    public String defenderse() {
        return "";
    }

    @Override
    public String huir() {
        return "";
    }


}
