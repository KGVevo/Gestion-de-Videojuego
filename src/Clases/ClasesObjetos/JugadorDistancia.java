package Clases.ClasesObjetos;

public class JugadorDistancia extends Jugador {

    private int municion;
    private ArmaDistancia arma;

    public JugadorDistancia(String nombre, int vida, int dañoBase, int nivel, String nombreJugador, int idUsuario, int expAcumuladaNivel) {
        super(nombre, vida, dañoBase, nivel, nombreJugador, idUsuario, expAcumuladaNivel);
        this.municion = 50;
        this.arma = arma;
    }
}
