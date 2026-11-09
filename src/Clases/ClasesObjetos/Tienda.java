package Clases.ClasesObjetos;

import Exceptions.ObjetoInvalidoException;
import java.util.ArrayList;


public class Tienda {

    private ArrayList<Objeto> tiendaObjetos;
    private int cantidadMaxima;


    public Tienda(int cantidadMaxima) {
        this.tiendaObjetos = new ArrayList<>();
        this.cantidadMaxima = cantidadMaxima;
    }



    /// Funcion para generar una tienda randomizada en base al Arraylist de Objetos global
    public boolean generarTienda(ArrayList<Objeto> objetos, int cantidadObjetos)throws ObjetoInvalidoException {
        this.tiendaObjetos.clear();

        if(cantidadObjetos < this.cantidadMaxima){
            throw new ObjetoInvalidoException("|No hay suficientes objetos para rellenar la Tienda|");
        }

        for(int i = 0; i < this.cantidadMaxima; i++){
            tiendaObjetos.add(objetos.get(SistemaDeProbabilidad.generadorInt(cantidadObjetos)));
        }

        return true;
    }




}
