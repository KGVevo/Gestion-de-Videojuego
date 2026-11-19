package Clases.ClasesObjetos.ObjetosClass;

import Clases.ClasesObjetos.Entidades.Jugador;
import Enums.E_Calidad;
import Enums.E_ClaseAsociada;
import Exceptions.ClaseAsociadaException;
import Interfaces.IComprobarClase;


public abstract class Arma extends Objeto implements IComprobarClase {

    private final int danioBase;
    private final E_ClaseAsociada claseAsociada;

    public Arma(Integer id, String nombre, int nivel, E_Calidad calidad , int danioBase, E_ClaseAsociada claseAsociada) {
        super(id, nombre, nivel, calidad);
        this.danioBase = danioBase;
        this.claseAsociada = claseAsociada;
    }

    public int getDanioBase() {
        return this.danioBase;
    }

    /// Danio real del arma: el danio base multiplicado por el multiplicador de su calidad
    public int getDanioFinal() {
        double multiplicador = (super.getCalidad() == null) ? 1.0 : super.getCalidad().getMultiplicadorDeDanio();
        return (int) Math.round(this.danioBase * multiplicador);
    }

    public E_ClaseAsociada getClaseAsociada() {
        return this.claseAsociada;
    }




    //Cabe aclarar, que entre los parametros debe ir la Clase Player para saber que tipo de Clase es.
    public boolean equipar(Jugador jugador) throws ClaseAsociadaException {

        if(jugador.getClaseAsociada() != this.claseAsociada) {
            throw new ClaseAsociadaException("|No es posible equipar el Arma: "+ super.getNombre() +
                    " porque tu clase no es " + this.claseAsociada.getClaseAsociada()+"|.");
        }


        /// equiparArma puede rechazar el arma (por ejemplo, si no es del tipo concreto de la clase)
        return jugador.equiparArma(this);
    }

    public String toString() {
        return "Daño Base: "+ this.danioBase+" | Daño Final: " + this.getDanioFinal() + " | Calidad: " + (super.getCalidad() == null ? "-" : super.getCalidad().getCalidad())
                + " | Clase: "+ this.claseAsociada.getClaseAsociada()+"|";
    }

}
