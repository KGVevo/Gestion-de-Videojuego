package Interfaces;

import Clases.ClasesObjetos.Entidades.Jugador;
import Exceptions.ClaseAsociadaException;

public interface IComprobarClase{
    boolean equipar(Jugador jugador) throws ClaseAsociadaException;
}
