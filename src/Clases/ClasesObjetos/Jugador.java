package Clases.ClasesObjetos;

import java.util.Objects;

public abstract class Jugador extends Entidad {

    private String nombreJugador;
    private int idUsuario;
    private int expAcumuladaNivel;
    private Inventario inventario;
    private int monedas;


    public Jugador(String nombreEntidad, int vida, int dañoBase, int nivel, String nombreJugador, int idUsuario, int expAcumuladaNivel) {
        super(nombreEntidad, vida, dañoBase, nivel);
        this.nombreJugador = nombreJugador;
        this.idUsuario = idUsuario;
        this.expAcumuladaNivel = expAcumuladaNivel;
        this.inventario = new Inventario();
        this.monedas = 0;
    }

    //////////////// GETTERS Y SETTERS ////////////////////
    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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
