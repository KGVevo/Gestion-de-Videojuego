package Clases.ClasesObjetos;
import Interfaces.IObtenerID;

import java.util.HashSet;

public class ClaseGenericaHS<T extends IObtenerID> {
    private HashSet<T> elementos;

    public ClaseGenericaHS() {
        this.elementos = new HashSet<>();
    }



    /// Agregar elemento
    public boolean agregarElemento(T elemento) {
        if(this.elementos.contains(elemento)){
            return false;
        }
        this.elementos.add(elemento);
        return true;
    }

    /// Eliminar por Elemento
    public boolean eliminarElemento(T elemento) {

        if(this.elementos.contains(elemento)){
            this.elementos.remove(elemento);
            return true;
        }

        return false;
    }

    /// Eliminar Elemento por Id
    public boolean eliminarElemento(int id){

        for(T elemento : this.elementos){
            if(elemento.getID() == id){
                return this.elementos.remove(elemento);
            }
        }

        return false;
    }


    /// Obtener elemento por Indice
    public T obtenerElemento(int id){
        if(id < 1){
            return null;
        }

        for(T elemento : elementos){
            if (elemento.getID() == id){
                return elemento;
            }
        }

        return null;
    }


}
