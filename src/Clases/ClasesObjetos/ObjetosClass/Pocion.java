package Clases.ClasesObjetos.ObjetosClass;

import Clases.ClasesObjetos.Entidades.Jugador;
import Enums.E_Calidad;
import Enums.E_ClaseAsociada;
import Enums.E_TipoPocion;
import Exceptions.ClaseAsociadaException;
import Exceptions.ObjetoInvalidoException;
import Exceptions.RangoException;

public class Pocion extends Objeto {

    private double efecto;
    private E_TipoPocion tipoPocion;

    public Pocion(Integer id, String nombre, int nivel, double efecto, E_TipoPocion tipoPocion, E_Calidad calidad){
        super(id, nombre, nivel, calidad);
        this.efecto = efecto;
        this.tipoPocion = tipoPocion;
    }

    // Cuando crea el objeto
    public Pocion(Objeto obj, double efecto, E_TipoPocion tipoPocion) {
        super(obj.getID(), obj.getNombre(), obj.getNivel(), obj.getCalidad());
        this.efecto = efecto;
        this.tipoPocion = tipoPocion;
    }

    public double getEfecto() {
        return this.efecto;
    }

    public E_TipoPocion getTipoPocion() {
        return this.tipoPocion;
    }

    /// Aplica el efecto de la pocion sobre el jugador y devuelve un texto con lo que paso.
    public String usar(Jugador jugador) throws ClaseAsociadaException, ObjetoInvalidoException {
        int valor = (int) Math.round(this.efecto);

        switch (this.tipoPocion) {
            case CURACION:
                int vidaAntes = jugador.getVida();
                jugador.curar(valor);

                if (jugador.getVida() == vidaAntes) {
                    throw new ObjetoInvalidoException("|-> No tiene efecto: ya tenes la vida al maximo <-|");
                }
                return "Recuperaste " + (jugador.getVida() - vidaAntes) + " de vida\n";

            case MANA:
                if (jugador.getClaseAsociada() != E_ClaseAsociada.MAGO) {
                    throw new ClaseAsociadaException("|-> Solo los Magos usan mana <-|");
                }
                if (!jugador.recuperarMana(valor)) {
                    throw new ObjetoInvalidoException("|-> No tiene efecto: ya tenes el mana al maximo <-|");
                }
                return "Recuperaste mana\n";

            case ENERGIA:
                if (jugador.getClaseAsociada() != E_ClaseAsociada.CUERPO_A_CUERPO) {
                    throw new ClaseAsociadaException("|-> Solo los guerreros Cuerpo a Cuerpo usan energia <-|");
                }
                if (!jugador.recuperarEnergia(valor)) {
                    throw new ObjetoInvalidoException("|-> No tiene efecto: ya tenes la energia al maximo <-|");
                }
                return "Recuperaste energia\n";

            case DANIO:
                jugador.aplicarBonusDanio(valor);
                return "+" + valor + " de danio hasta que termine tu proxima batalla\n";

            case CRITICO:
                jugador.aplicarBonusCritico(valor);
                return "+" + valor + "% de probabilidad de golpe critico hasta que termine tu proxima batalla\n";

            default:
                throw new ObjetoInvalidoException("|-> Esta pocion no se puede usar <-|");
        }
    }

    @Override
    public String toString()
    {
        return "Pocion ["+super.toString()+" | Efecto: "+this.efecto+" | Tipo de Pocion: "+this.tipoPocion+"]";
    }

}
