package Clases.ClasesObjetos;
import Interfaces.IObtenerID;

import java.util.HashSet;
import java.util.Iterator;

public class ClaseGenericaHS<T extends IObtenerID> {
    private HashSet<T> elementos;

    public ClaseGenericaHS() {
        this.elementos = new HashSet<>();
    }

    /// Agregar elemento
    public boolean agregarElemento(T elemento){
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

        Iterator<T> it = elementos.iterator();
        while (it.hasNext())
        {
            T devuelve = it.next();
            if(devuelve.getID().equals(id)){
                return this.elementos.remove(devuelve);
            }
        }

        return false;
    }

    /// Obtener elemento por Id
    public T obtenerElemento(Integer id){
        Iterator<T> it = elementos.iterator();
        while (it.hasNext())
        {
            T devuelve = it.next();
            if (devuelve.getID().equals(id)){
                return devuelve;
            }
        }

        return null;
    }


    /// Mostrar todos los elementos
    public String verTodosElemento(){
        StringBuilder sb = new StringBuilder();

        for(T elemento : elementos){
            sb.append(elemento.toString() + "\n");
        }

        return sb.toString();
    }

    public boolean verificarElemento(Integer id)
    {
        if(id < 1){
            return false;
        }

        for(T elemento : elementos){
            if (elemento.getID().equals(id)){
                return true;
            }
        }

        return false;
    }

    public boolean verificarElemento(String id)
    {
        for(T elemento : elementos){
            if (elemento.getID().equals(id)){
                return true;
            }
        }
        return false;
    }



}
