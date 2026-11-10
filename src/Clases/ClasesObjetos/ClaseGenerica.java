package Clases.ClasesObjetos;

import java.util.ArrayList;

public class ClaseGenerica <T> {
    private ArrayList<T> lista;

    public ClaseGenerica() {
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

    /// Eliminar Elemento por Indice
    public boolean eliminarElemento(int indice){
        if(this.lista.contains(indice)){
            this.lista.remove(indice);
            return true;
        }

        return false;
    }

    /// Obtener elemento por Indice
    public T obtenerElemento(int indice){
        if(indice < 0 || indice >= this.lista.size()){
            return null;
        }

        return this.lista.get(indice);
    }


}
