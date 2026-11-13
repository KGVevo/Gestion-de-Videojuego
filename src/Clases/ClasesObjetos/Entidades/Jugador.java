package Clases.ClasesObjetos.Entidades;

import Clases.ClasesObjetos.Inventario;
import Clases.ClasesObjetos.SistemaDeProbabilidad;
import Enums.E_ClaseAsociada;
import Interfaces.IInterfazDeCombate;

import java.util.Objects;

public abstract class Jugador extends Entidad {

    private int idUsuario;
    private E_ClaseAsociada claseAsociada;
    private int expAcumuladaNivel;
    private Inventario inventario;
    private int monedas;


    public Jugador(String nombreJugador, int danioBase, int idUsuario, E_ClaseAsociada claseAsociada, int vida, int nivel,int expAcumulada , int moneda) {
        super(nombreJugador, vida, danioBase, nivel);
        this.idUsuario = idUsuario; //Deberiamos usar la Base de Datos para saber si existe el jugador, caso contrario lanzar un "UserException"
        this.claseAsociada = null;
        this.expAcumuladaNivel = expAcumulada;
        this.inventario = new Inventario();
        this.monedas = moneda;
    }


    public Jugador(String nombreJugador, int danioBase, int idUsuario, E_ClaseAsociada claseAsociada) {
        super(nombreJugador, 100, danioBase, 1); //Por defecto el nivel esta en 1
        this.idUsuario = idUsuario; //Deberiamos usar la Base de Datos para saber si existe el jugador, caso contrario lanzar un "UserException"
        this.claseAsociada = null;
        this.expAcumuladaNivel = 0;
        this.inventario = new Inventario();
        this.monedas = 0;
    }

    //////////////// GETTERS ////////////////////

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


    public abstract int habilidadArma();


    /// Metodos ///
    private boolean modificadorDeNivel(int valor) {
        this.setNivel(this.getNivel() + valor);
        return true;
    }

    /// Verifica si tiene la suficiente exp Para subir de nivel
    public int modificadorDeExp(int valor, int nivelActual) {
        int flag = 1;

        while(flag == 1){
            int expMaxima = 100 * nivelActual;
            this.expAcumuladaNivel += valor;

            if(this.expAcumuladaNivel > expMaxima){
                this.expAcumuladaNivel = (valor - expMaxima);
                modificadorDeNivel(1);
            }else{
                flag = 0;
            }
        }


        return this.expAcumuladaNivel;
    }




    @Override
    public boolean defenderse() {
        if(SistemaDeProbabilidad.generarProb(50)){
            return true;
        }

        return false;
    }

    @Override
    public boolean huir() {

        if(SistemaDeProbabilidad.generarProb(20)){
            return true;
        }

        return false;
    }





    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Jugador jugador = (Jugador) o;
        return idUsuario == jugador.idUsuario;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idUsuario);
    }

    @Override
    public String toString()
    {
        return "ID Usuario: "+this.idUsuario+" | Clase: "+this.claseAsociada.getClaseAsociada()+" | XP Acumulada"+
                this.expAcumuladaNivel+" | Monedas: "+this.monedas+"";
    }


}
