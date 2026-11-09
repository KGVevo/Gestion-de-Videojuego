package Clases.ClasesObjetos;

public class JugadorCuerpoACuerpo extends Jugador {

    private int energia;
    private ArmaCuerpoACuerpo arma;

    public JugadorCuerpoACuerpo(String nombreEntidad, int vida, int dañoBase, int nivel, String nombreJugador, int idUsuario, int expAcumuladaNivel) {
        super(nombreEntidad, vida, dañoBase, nivel, nombreJugador, idUsuario, expAcumuladaNivel);
        this.energia = 100;
        this.arma = arma;
    }


}
