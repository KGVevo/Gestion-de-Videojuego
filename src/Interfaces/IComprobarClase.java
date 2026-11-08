package Interfaces;

import Enums.E_ClaseAsociada;
import Exceptions.ClaseAsociadaException;

public interface IComprobarClase {

    String equipar(E_ClaseAsociada claseActual) throws ClaseAsociadaException;

}
