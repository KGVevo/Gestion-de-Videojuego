package Clases.ClasesObjetos;

public class JugadorDistancia extends Jugador {

    private int municion;
    private ArmaDistancia arma;

    public JugadorDistancia(String nombreJugador, int vida, int dañoBase, int idUsuario){
        super(nombreJugador, vida, dañoBase, idUsuario);
        this.municion = 50;
        this.arma = null;
    }

    public int getMunicion() {
        return municion;
    }
    public ArmaDistancia getArma() {
        return arma;
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
