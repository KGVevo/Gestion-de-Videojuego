package Clases.ClasesObjetos.ObjetosClass;

import Enums.E_Calidad;
import Enums.E_ClaseAsociada;

public class ArmaMago extends Arma {

    private int consumoMana;

    public ArmaMago(Integer id, String nombre, int nivel, int danioBase, E_Calidad calidad, int consumoMana)
    {
        super(id, nombre, nivel, calidad, danioBase, E_ClaseAsociada.MAGO);
        this.consumoMana = consumoMana;
    }

    public ArmaMago(Objeto obj, int danioBase, int consumoMana)
    {
        super(obj.getID(), obj.getNombre(), obj.getNivel(), obj.getCalidad(), danioBase, E_ClaseAsociada.MAGO);
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
