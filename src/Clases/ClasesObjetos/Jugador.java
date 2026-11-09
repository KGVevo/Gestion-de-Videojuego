package Clases.ClasesObjetos;

import java.util.Objects;

public abstract class Jugador extends Entidad {

    private int idUsuario;
    private int expAcumuladaNivel;
    private Inventario inventario;
    private int monedas;


    public Jugador(String nombreJugador, int vida, int danioBase, int nivel, int idUsuario) {
        super(nombreJugador, vida, danioBase, nivel);
        this.idUsuario = idUsuario;
        this.expAcumuladaNivel = 0;
        this.inventario = new Inventario();
        this.monedas = 0;
    }

    //////////////// GETTERS Y SETTERS ////////////////////

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

    public void setMonedas(int monedas) {
        this.monedas = monedas;
    }

    //////////////////////////////////////////////////////////////



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
