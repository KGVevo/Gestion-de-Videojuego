package Clases.ClasesObjetos.Entidades;

import Clases.ClasesObjetos.ObjetosClass.Arma;
import Clases.ClasesObjetos.ObjetosClass.ArmaDistancia;
import Clases.ClasesObjetos.SistemaDeJuego.SistemaDeProbabilidad;
import Enums.E_ClaseAsociada;

public class JugadorDistancia extends Jugador {

    private int municion;



    // CONSTRUCTOR 1: PARA RELLENAR DE LA JDBC A JAVA (misma estructura en las tres clases de Jugador)
    public JugadorDistancia(Integer id, String nombreJugador, int idUsuario, int danioBase, ArmaDistancia arma, int vida, int nivel, int expAcumulada, int monedas) {
        super(id, nombreJugador, idUsuario, danioBase, E_ClaseAsociada.DISTANCIA, arma, vida, nivel, expAcumulada, monedas);
        this.municion = 25; /// Municion por defecto
    }


    // CONSTRUCTOR 2: PARA CREAR LOS JUGADORES
    public JugadorDistancia(Integer id,String nombreJugador, int idUsuario, ArmaDistancia arma) {
        super(id, nombreJugador, idUsuario, 8, 100,  E_ClaseAsociada.DISTANCIA, arma);
        this.municion = 25; /// Municion por defecto
    }





    /// GETTERS
    public int getMunicion() {
        return municion;
    }






    //***************************** METODOS ******************************************//

    /// obtenemos el arma de su clase
    @Override
    public ArmaDistancia getArma() {
        return (ArmaDistancia) super.getArma();
    }

    /// Solo se puede equipar Armas de su clase
    @Override
    public boolean equiparArma(Arma arma) {
        if(!(arma instanceof ArmaDistancia)){
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

        int consumo = this.getArma().getConsumoMunicion();
        int golpeFinal = 0;


        if (this.municion >= consumo) {
            if (SistemaDeProbabilidad.generarProb(90)) {
                golpeFinal = this.golpeCritico();
                this.municion -= consumo;
            }
        } else {
            this.recargar();
        }

        return golpeFinal;
    }


    public boolean recargar(){
        if(this.municion == 25){
            return false;
        }else{
            this.municion = 25;
            return true;
        }

    }

    public int habilidadArma() {
        /// Sin arma equipada no hay habilidad
        if (!this.tieneArma()) {
            return 0;
        }

        if(this.municion == 25){
            this.municion = 0;
            return this.getArma().getDanioFinal() * 3;
        }

        return 0;
    }

    @Override
    public String toString()
    {
        return "Clase Distancia ["+super.toString()+" | Municion: "+this.municion+"]\n"
                +super.getInventario().toString();
    }
}
