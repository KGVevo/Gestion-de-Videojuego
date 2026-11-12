package Clases.ClasesObjetos.ObjetosClass;

import Enums.E_Calidad;
import Enums.E_ClaseAsociada;
import Exceptions.RangoException;

public class ArmaMago extends Arma {

    private int consumoMana;

    public ArmaMago(String nombre, int nivel, int dañoBase, E_Calidad calidad, int consumoMana) throws RangoException
    {
        super(nombre, nivel, dañoBase, E_ClaseAsociada.MAGO, calidad);
        this.consumoMana = consumoMana;
    }

    public int getConsumoMana()
    {
        return this.consumoMana;
    }

    public String toString()
    {
        return "Arma Mago ["+super.toString()+" | Consumo Mana: "+this.consumoMana+"]";
    }

}
