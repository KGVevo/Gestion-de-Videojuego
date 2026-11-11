package Clases.ClasesObjetos;

import Exceptions.InventarioException;
import Exceptions.ObjetoInvalidoException;

import java.util.ArrayList;

public class Inventario {
    private ArrayList<Objeto> objetos;
    private int capacidadMaxima;


    public Inventario() {
        this.objetos = new ArrayList<>();
        this.capacidadMaxima = 30;  /// Valor por defecto
    }

    public ArrayList<Objeto> getObjetos() {
        return objetos;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }



    //-------|METODOS|----------//

    /// Funcion para buscar un item y devolverlo  --> Moduralizacion
    public Objeto buscarItem(int id){

        for (Objeto obj : objetos) {
            if(obj.getIdentificador() == id){
                return obj;
            }
        }

        return null;
    }

    /// Ver inventario
    public String verInventario(){
        StringBuilder listado = new StringBuilder();

        for(Objeto o : objetos){
            listado.append(o.toString());
        }

        return listado.toString() + "\n|-> Espacio disponible: " + (capacidadMaxima - objetos.size()) + " <-|";
    }


    /// Mostrar Item
    public String verInfoObjeto(int id)throws ObjetoInvalidoException {
        Objeto obj = buscarItem(id);

        if(obj == null){
            throw new ObjetoInvalidoException("|-> El item no existe <-|");
        }

        return obj.toString();
    }


    /// Funcion para agregar un item al Inventario
    public boolean agregarObjetoInventario(Objeto objeto)throws ObjetoInvalidoException, InventarioException {

        if(objeto == null){
            throw new ObjetoInvalidoException("|-> El item no existe <-|");
        }else{

            if(objetos.size() < capacidadMaxima){
                if(objetos.contains(objeto)){
                    throw new ObjetoInvalidoException("|-> El item ya existe en el inventario <-|");
                }else {
                    objetos.add(objeto);
                }
            }else{
                throw new InventarioException("|-> No hay más espacio en el inventario <-|");
            }

        }

        return true;
    }

    /// Funcion para Borrar un item del Inventario
    public boolean borrarObjetoInventario(int id)throws ObjetoInvalidoException {
        Objeto aux = buscarItem(id);

        if(aux == null){
            throw new ObjetoInvalidoException("|-> El item no existe <-|");
        }{
            return objetos.remove(aux);
        }

    }







}
