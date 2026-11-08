package Clases.ClasesObjetos;

import Enums.E_Calidad;
import Exceptions.RangoException;

public class ArmaMago extends Arma{

    private int consumoMana;

    public ArmaMago(String nombre, int nivel, int espacios, int dañoBase,
                    E_Calidad calidad, int consumoMana) throws RangoException
    {
        super(nombre, nivel, espacios, dañoBase, calidad);
        this.consumoMana = consumoMana;
    }

    public int obtenerConsumoMana()
    {
        return this.consumoMana;
    }

}
