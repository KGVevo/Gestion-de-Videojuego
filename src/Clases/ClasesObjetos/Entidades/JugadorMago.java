package Clases.ClasesObjetos.Entidades;

import Clases.ClasesObjetos.ObjetosClass.Arma;
import Clases.ClasesObjetos.ObjetosClass.ArmaMago;
import Clases.ClasesObjetos.SistemaDeJuego.SistemaDeProbabilidad;
import Enums.E_ClaseAsociada;

public class JugadorMago extends Jugador {

    private int mana;


    // CONSTRUCTOR 1: PARA RELLENAR DE LA JDBC A JAVA (misma estructura en las tres clases de Jugador)
    public JugadorMago(Integer id, String nombreJugador, int idUsuario, int danioBase, ArmaMago arma, int vida, int nivel, int expAcumulada, int monedas) {
        super(id, nombreJugador, idUsuario, danioBase, E_ClaseAsociada.MAGO, arma , vida, nivel, expAcumulada, monedas);
        this.mana = 100; /// Mana default de cada Jugador
    }

    // CONSTRUCTOR 2: PARA CREAR LOS JUGADORES
    public JugadorMago(Integer id ,String nombreJugador, int idUsuario, ArmaMago arma) {
        super(id, nombreJugador,  idUsuario, 10, 100, E_ClaseAsociada.MAGO, arma);
        this.mana = 100; /// Mana default de cada Jugador
    }


    /// GETTERS
    public int getMana() {
        return mana;
    }






    //***************************** METODOS ******************************************//

    /// Obtenemos el arma de su clase
    @Override
    public ArmaMago getArma() {
        return (ArmaMago) super.getArma();
    }


    /// Solo armas de su clase
    @Override
    public boolean equiparArma(Arma arma) {
        if(!(arma instanceof  ArmaMago)) {
            return false;
        }

        return super.equiparArma(arma);
    }


    /// METODO DE ATAQUE
    @Override
    public int atacar() {
        if (!this.tieneArma()) {
            return 0;
        }

        int consumo = this.getArma().getConsumoMana();
        int golpeFinal = 0;

        /// >= para poder usar el ultimo mana disponible
        if (this.mana >= consumo) {
            if (SistemaDeProbabilidad.generarProb(90)) {
                golpeFinal = this.golpeCritico();
                this.mana -= consumo;
            }
        } else {
            /// Igual que recargar() en el jugador a Distancia
            this.recuperarMana();
        }

        return golpeFinal;
    }

    /// Recupera el mana
    public boolean recuperarMana() {
        return this.recuperarMana(100);
    }

    /// Recupera una cantidad de mana maxima, o sea, 100
    @Override
    public boolean recuperarMana(int cantidad) {
        if (cantidad <= 0 || this.mana >= 100) {
            return false;
        }

        this.mana = Math.min(100, this.mana + cantidad);
        return true;
    }

    /// Habilidad del Arma
    @Override
    public int habilidadArma() {
        // Sin arma equipada no hay habilidad
        if (!this.tieneArma()) {
            return 0;
        }

        // Gasta mana y se cura vida al mismo tiempo
        if(this.mana > (this.getArma().getConsumoMana() * 2)){
            this.mana -= this.getArma().getConsumoMana() * 2;
            this.curar(15);

            return this.getArma().getDanioFinal() * 2;
        }

        return 0;
    }




    @Override
    public String toString() {
        return "Clase Mago ["+super.toString()+" | Mana: "+this.mana+"]\n"
                +super.getInventario().toString();
    }

}
