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

        Iterator<T> it = elementos.iterator();
        while (it.hasNext())
        {
            T devuelve = it.next();
            if(devuelve.getID() == id){
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
            if (devuelve.getID() == id){
                return devuelve;
            }
        }

        return null;
    }

    /// Obtener todos los elementos
    public String obtenerElementoTodos(){
        String devuelve = "";

        Iterator<T> it = elementos.iterator();
        while (it.hasNext())
        {
            devuelve += it.toString() + "\n";
        }

        return devuelve;
    }

    public boolean existeElemento(Integer id){

        Iterator<T> it = elementos.iterator();
        while (it.hasNext())
        {
            if(it.next().getID().equals(id))
            {
                return true;
            }
        }

        return false;
    }

    public boolean existeElemento(String id){
        boolean devuelve = false;

        Iterator<T> it = elementos.iterator();
        while (it.hasNext())
        {
            if(it.next().getID().equals(id))
            {
                devuelve = true;
            }
        }

        return devuelve;
    }


}
