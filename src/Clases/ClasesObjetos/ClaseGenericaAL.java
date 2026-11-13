package Clases.ClasesObjetos;

import Interfaces.IObtenerID;

import java.util.ArrayList;
import java.util.Iterator;


public class ClaseGenericaAL<T extends IObtenerID> {
    private ArrayList<T> lista;

    public ClaseGenericaAL() {
        this.lista = new ArrayList<>();
    }



    /// Agregar elemento
    public boolean agregarElemento(T elemento) {
        if(this.lista.contains(elemento)){
            return false;
        }
        this.lista.add(elemento);
        return true;
    }

    /// Eliminar por Elemento
    public boolean eliminarElemento(T elemento) {

        if(this.lista.contains(elemento)){
            this.lista.remove(elemento);
            return true;
        }

        return false;
    }

    /// Eliminar Elemento por Id
    public boolean eliminarElemento(Integer id){

        for(T elemento : this.lista){
            if(elemento.getID().equals(id)){
                return this.lista.remove(elemento);
            }
        }

        return false;
    }

    /// Obtener elemento por Indice
    public T obtenerElemento(Integer id){
        if(id < 1){
            return null;
        }

        for(T elemento : lista){
            if (elemento.getID().equals(id)){
                return elemento;
            }
        }

        return null;
    }

    /// Obtener todos los elementos
    public String obtenerElementoTodos(){
        StringBuilder sb = new StringBuilder();

        for(T elemento : this.lista){
            sb.append(elemento.toString());
        }

        return sb.toString();
    }

    /// Obtener la Cantidad de Elementos
    public int cantidadElementos(){
        return this.lista.size();
    }
}
