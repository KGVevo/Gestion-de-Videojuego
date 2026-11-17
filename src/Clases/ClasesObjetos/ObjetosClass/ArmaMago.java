package Clases.ClasesObjetos.ObjetosClass;

import Enums.E_Calidad;
import Enums.E_ClaseAsociada;
import Exceptions.RangoException;

public class ArmaMago extends Arma {

    private int consumoMana;

    public ArmaMago(Integer id, String nombre, int nivel, int danioBase, E_Calidad calidad, int consumoMana) throws RangoException
    {
        super(id, nombre, nivel, calidad, danioBase, E_ClaseAsociada.MAGO);
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
