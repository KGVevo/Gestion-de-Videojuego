package Clases.ClasesObjetos;

import Enums.E_Calidad;
import Exceptions.RangoException;

public class ArmaDistancia extends Arma{

    private int consumoMunicion;

    public ArmaDistancia(String nombre, int nivel, int espacios, int dañoBase,
                         E_Calidad calidad, int consumoMunicion) throws RangoException
    {
        super(nombre, nivel, espacios, dañoBase, calidad);
        this.consumoMunicion = consumoMunicion;
    }

    private int obtenerConsumoMunicion()
    {
        return this.consumoMunicion;
    }

}
