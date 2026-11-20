package Clases.ClasesObjetos.Entidades;

import Clases.ClasesObjetos.ObjetosClass.Arma;
import Clases.ClasesObjetos.ObjetosClass.ArmaCuerpoACuerpo;
import Clases.ClasesObjetos.SistemaDeJuego.SistemaDeProbabilidad;
import Enums.E_ClaseAsociada;

public class JugadorCuerpoACuerpo extends Jugador {


    private int energia;

    // CONSTRUCTOR 1: PARA RELLENAR DE LA JDBC A JAVA
    public JugadorCuerpoACuerpo(Integer id, String nombreJugador, int idUsuario, int danio, ArmaCuerpoACuerpo arma, int vida, int nivel, int expAcumulada, int moneda) {
        super(id, nombreJugador, idUsuario, danio, E_ClaseAsociada.CUERPO_A_CUERPO, arma, vida, nivel, expAcumulada, moneda);
        this.energia = 100;
    }

    // CONSTRUCTOR 2: PARA CREAR LOS JUGADORES
    public JugadorCuerpoACuerpo(Integer id, String nombreJugador, int idUsuario, ArmaCuerpoACuerpo arma) {
        super(id, nombreJugador, idUsuario, 5, 100, E_ClaseAsociada.CUERPO_A_CUERPO, arma);
        this.energia = 100;
    }

    //GETTERS
    public int getEnergia() {
        return energia;
    }


    //***************************** METODOS ******************************************//


    /// obtenemos el arma de su clase
    @Override
    public ArmaCuerpoACuerpo getArma() {
        return (ArmaCuerpoACuerpo) super.getArma();
    }


    /// Equipa armas solamente de su clase
    @Override
    public boolean equiparArma(Arma arma) {
        if (!(arma instanceof ArmaCuerpoACuerpo)) {
            return false;
        }

        return super.equiparArma(arma);
    }


    /// METODO DE ATAQUE
    @Override
    public int atacar() {

        /// Sin arma equipada no puede atacar
        if (!this.tieneArma()) {
            return 0;
        }

        int golpeFinal = 0;

        //90% probabilidad de no fallar el golpe
        if (SistemaDeProbabilidad.generarProb(90)) {
            golpeFinal = golpeCritico();
        }

        return golpeFinal;
    }

    /// Gasta energia y pega el doble del daño del arma. Sin arma o sin energia suficiente devuelve 0.
    @Override
    public int habilidadArma() {
        if (!this.tieneArma()) {
            return 0;
        }

        ArmaCuerpoACuerpo arma = this.getArma();

        if (this.energia >= arma.getConsumoEnergia()) {
            this.energia -= arma.getConsumoEnergia();
            return arma.getDanioFinal() * 2;
        }

        return 0;
    }

    /// Recupera una cantidad de energia maxima, o sea, 100
    @Override
    public boolean recuperarEnergia(int cantidad) {
        if (cantidad <= 0 || this.energia >= 100) {
            return false;
        }

        this.energia = Math.min(100, this.energia + cantidad);
        return true;
    }

    @Override
    public String toString() {
        return "|Clase Cuerpo a Cuerpo [" + super.toString() + " | Energia: " + this.energia + "]\n"
                + super.getInventario().toString();
    }
}