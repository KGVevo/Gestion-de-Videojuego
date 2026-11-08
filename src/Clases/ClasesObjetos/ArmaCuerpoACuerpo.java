package Clases.ClasesObjetos;

import Enums.E_Calidad;
import Exceptions.RangoException;

public class ArmaCuerpoACuerpo extends Arma{

    private int consumoEnergia;

    public ArmaCuerpoACuerpo(String nombre, int nivel, int espacios, int dañoBase,
                             E_Calidad calidad, int consumoEnergia) throws RangoException
    {
        super(nombre, nivel, espacios, dañoBase, calidad);
        this.consumoEnergia = consumoEnergia;
    }

    public int obtenerConsumoEnergia()
    {
        return this.consumoEnergia;
    }

}
