package Clases.ClasesObjetos;

import java.util.Objects;

public abstract class Jugador extends Entidad {

    private int idUsuario;
    private int expAcumuladaNivel;
    private Inventario inventario;
    private int monedas;


    public Jugador(String nombreJugador, int vida, int danioBase, int idUsuario) {
        super(nombreJugador, vida, danioBase, 1); //Por defecto el nivel esta en 1
        this.idUsuario = idUsuario; //Deberiamos usar la Base de Datos para saber si existe el jugador, caso contrario lanzar un "UserException"
        this.expAcumuladaNivel = 0;
        this.inventario = new Inventario();
        this.monedas = 0;
    }

    //////////////// GETTERS ////////////////////

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getExpAcumuladaNivel() {
        return expAcumuladaNivel;
    }

    public void setExpAcumuladaNivel(int expAcumuladaNivel) {
        this.expAcumuladaNivel = expAcumuladaNivel;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public int getMonedas() {
        return monedas;
    }

    /////////////////////////////////////////////



    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Jugador jugador = (Jugador) o;
        return idUsuario == jugador.idUsuario;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idUsuario);
    }


}
