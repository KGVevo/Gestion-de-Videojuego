package Clases.ClasesObjetos;

public class JugadorCuerpoACuerpo extends Jugador {

    private int energia;
    private ArmaCuerpoACuerpo arma;

    public JugadorCuerpoACuerpo(String nombreJugador, int vida, int danioBase, int nivel, int idUsuario) {
        super(nombreJugador, vida, danioBase, nivel, idUsuario);
        this.energia = 100;
        this.arma = null;
    }


}
