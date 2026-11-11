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
        return this.elementos.add(elemento);
    }

    /// Eliminar por Elemento
    public boolean eliminarElemento(T elemento) {

        if(this.elementos.contains(elemento)){
            return this.elementos.remove(elemento);
        }

        return false;
    }

    /// Eliminar Elemento por Id
    public boolean eliminarElemento(Integer id){

        for(T elemento : this.elementos){
            if(elemento.getID() == id){
                return this.elementos.remove(elemento);
            }
        }

        return false;
    }

    /// Obtener elemento por Id
    public T obtenerElemento(Integer id){
        if(id < 1 || id > this.elementos.size()){
            return null;
        }

        for(T elemento : elementos){
            if (elemento.getID() == id){
                return elemento;
            }
        }

        return null;
    }


    public boolean existeElemento(Integer id){
        for(T elemento : this.elementos){
            if(elemento.getID().equals(id)){
                return true;
            }
        }
        return false;
    }


}
