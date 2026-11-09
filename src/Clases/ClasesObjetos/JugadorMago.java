package Clases.ClasesObjetos;

public class JugadorMago extends Jugador {

    private int mana;
    private ArmaMago arma;

    public JugadorMago(String nombreJugador, int vida, int dañoBase, int nivel, int idUsuario) {
        super(nombreJugador, vida, dañoBase, nivel, idUsuario);
        this.mana = 100;
        this.arma = null;
    }

}
