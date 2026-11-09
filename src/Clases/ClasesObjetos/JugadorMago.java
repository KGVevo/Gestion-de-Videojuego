package Clases.ClasesObjetos;

public class JugadorMago extends Jugador {

    private int mana;
    private ArmaMago arma;

    public JugadorMago(String nombreEntidad, int vida, int dañoBase, int nivel,String nombreJugador, int idUsuario, int expAcumuladaNivel) {
        super(nombreEntidad, vida, dañoBase, nivel, nombreJugador, idUsuario, expAcumuladaNivel);
        this.mana = 100;
        this.arma = arma;
    }

}
