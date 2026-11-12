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
