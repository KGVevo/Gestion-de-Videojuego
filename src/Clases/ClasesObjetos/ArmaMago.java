package Clases.ClasesObjetos;

import Enums.E_Calidad;
import Enums.E_ClaseAsociada;
import Exceptions.RangoException;

public class ArmaMago extends Arma{

    private int consumoMana;

    public ArmaMago(String nombre, int nivel, int espacios, int dañoBase, E_ClaseAsociada claseAsociada, E_Calidad calidad, int consumoMana) throws RangoException
    {
        super(nombre, nivel, espacios, dañoBase, claseAsociada ,calidad);
        this.consumoMana = consumoMana;
    }

    public int getConsumoMana()
    {
        return this.consumoMana;
    }

}
