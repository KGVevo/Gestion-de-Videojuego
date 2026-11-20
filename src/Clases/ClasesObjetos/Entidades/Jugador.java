package Clases.ClasesObjetos.Entidades;

import Clases.ClasesObjetos.Inventario;
import Clases.ClasesObjetos.ObjetosClass.Arma;
import Clases.ClasesObjetos.SistemaDeJuego.SistemaDeProbabilidad;
import Enums.E_ClaseAsociada;
import Interfaces.IPlayerInterface;

import java.util.Objects;


public abstract class Jugador extends Entidad implements IPlayerInterface {
    private final int idUsuario;
    private final E_ClaseAsociada claseAsociada;
    private int expAcumuladaNivel;
    private final Inventario inventario;
    private Arma arma;
    private int monedas;

    /// Bonus temporales que dan las pociones de DANIO y CRITICO. Duran hasta que termina la proxima batalla.
    private int bonusDanio;
    private int bonusCritico;


    /// Constructor 1: para cargar un jugador YA existente (por ejemplo, descargado de la base de datos)
    public Jugador(Integer id, String nombreJugador, int idUsuario, int danioBase, E_ClaseAsociada claseAsociada,
                   Arma arma, int vida, int nivel, int expAcumulada, int moneda) {
        super(id, nombreJugador, vida, danioBase, nivel);
        this.idUsuario = idUsuario;
        this.claseAsociada = claseAsociada;
        this.expAcumuladaNivel = expAcumulada;
        this.inventario = new Inventario();
        this.monedas = moneda;
        this.arma = arma;
    }

    /// Constructor 2: para crear un jugador NUEVO (100 de vida, nivel 1, sin exp ni monedas)
    public Jugador(Integer id, String nombreJugador, int idUsuario, int danioBase, int vida, E_ClaseAsociada claseAsociada, Arma arma) {
        super(id, nombreJugador, vida, danioBase, 1);
        this.idUsuario = idUsuario;
        this.claseAsociada = claseAsociada;
        this.expAcumuladaNivel = 0;
        this.inventario = new Inventario();
        this.monedas = 0;
        this.arma = arma;
    }

    //////////////// GETTERS //////////////////////////////////////////////
    public int getIdUsuario() {
        return idUsuario;
    }

    public E_ClaseAsociada getClaseAsociada() {
        return claseAsociada;
    }

    public int getExpAcumuladaNivel() {
        return expAcumuladaNivel;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public int getMonedas() {
        return monedas;
    }

    public Arma getArma() {
        return arma;
    }
    /////////////////////////////////////////////////////////////////////




    /// Dice si el jugador tiene un arma equipada (las clases hijas lo usan antes de atacar)
    public boolean tieneArma() {
        return this.arma != null;
    }

    /// Cada clase tiene su propia habilidad de arma
    public abstract int habilidadArma();


    /// Suma monedas al jugador (ignora valores negativos)
    public void sumarMonedas(int cantidad) {
        if (cantidad > 0) {
            this.monedas += cantidad;
        }
    }

    /// Resta monedas al jugador. Devuelve false si la cantidad no es valida o si no le alcanzan las monedas.
    public boolean gastarMonedas(int cantidad) {
        if (cantidad <= 0 || cantidad > this.monedas) {
            return false;
        }

        this.monedas -= cantidad;
        return true;
    }


    /// Bonus de pociones ///
    public int getBonusDanio() {
        return bonusDanio;
    }

    public int getBonusCritico() {
        return bonusCritico;
    }

    /// Suma danio fijo a cada golpe hasta que termine la proxima batalla (ignora valores negativos)
    public void aplicarBonusDanio(int valor) {
        if (valor > 0) {
            this.bonusDanio += valor;
        }
    }

    /// Suma puntos de probabilidad de golpe critico hasta que termine la proxima batalla (ignora valores negativos)
    public void aplicarBonusCritico(int valor) {
        if (valor > 0) {
            this.bonusCritico += valor;
        }
    }

    /// Saca los bonus de las pociones (se llama cuando termina una batalla)
    public void quitarBonus() {
        this.bonusDanio = 0;
        this.bonusCritico = 0;
    }

    /// Probabilidad de golpe critico: 20% base + el bonus de las pociones (maximo 100%)
    public int probabilidadCritico() {
        return Math.min(100, 20 + this.bonusCritico);
    }

    /// GOLPE CRITICO: igual para todas las clases.
    @Override
    public int golpeCritico() {
        int golpe = this.getDanioBase() + this.getBonusDanio();

        /// Sin arma equipada pega solo con su danio base
        if (this.tieneArma()) {
            golpe += this.getArma().getDanioFinal();
        }

        if (SistemaDeProbabilidad.generarProb(this.probabilidadCritico())) {
            return golpe * 2;
        }

        return golpe;
    }



    /// Lo usa la clase JugadorMago
    public boolean recuperarMana(int cantidad) {
        return false;
    }

    /// Lo usa la clase JugadorDistancia
    public boolean recuperarEnergia(int cantidad) {
        return false;
    }


    /// Metodos ///
    private boolean modificadorDeNivel(int valor) {
        this.setNivel(this.getNivel() + valor);
        return true;
    }


    /// Suma experiencia. Por cada nivel completado sube de nivel (cuesta 100 * nivel actual).
    public int modificadorDeExp(int valor) {
        this.expAcumuladaNivel += valor;
        int expMaxima = 100 * this.getNivel();

        while (this.expAcumuladaNivel >= expMaxima) {
            this.expAcumuladaNivel -= expMaxima;
            modificadorDeNivel(1);
            expMaxima = 100 * this.getNivel();
        }

        return this.expAcumuladaNivel;
    }


    /// 50% de probabilidad de cubrirse
    @Override
    public boolean defenderse() {
        return SistemaDeProbabilidad.generarProb(50);
    }

    /// 20% de probabilidad de huir
    @Override
    public boolean huir() {
        return SistemaDeProbabilidad.generarProb(20);
    }


    @Override
    public String verInfoArma() {
        if (this.arma == null) {
            return "No tiene arma equipada";
        }

        return this.arma.toString();
    }

    /// Equipar arma
    @Override
    public boolean equiparArma(Arma arma) {
        if (arma == null) {
            return false;
        }

        this.arma = arma;
        return true;
    }

    /// Dos jugadores son el mismo si tienen el mismo ID de jugador (un Usuario puede tener varios jugadores)
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Jugador jugador = (Jugador) o;
        return Objects.equals(getID(), jugador.getID());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getID());
    }

    @Override
    public String toString() {
        return super.toString() +
                " | ID Usuario: [" + this.idUsuario +
                " | Clase: " + this.claseAsociada.getClaseAsociada() +
                " | XP Acumulada: " + this.expAcumuladaNivel +
                " | Monedas: " + this.monedas + " |]";
    }

}