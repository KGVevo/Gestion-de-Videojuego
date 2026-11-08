package Interfaces;

import Enums.E_ClaseAsociada;
import Exceptions.ClaseAsociadaException;

public interface I_comprobarClase {

    String equipar(E_ClaseAsociada claseActual) throws ClaseAsociadaException;

}
