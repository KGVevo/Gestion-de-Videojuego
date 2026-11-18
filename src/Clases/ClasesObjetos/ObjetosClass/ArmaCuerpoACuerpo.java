package Clases.ClasesObjetos.ObjetosClass;

import Enums.E_Calidad;
import Enums.E_ClaseAsociada;
import Exceptions.RangoException;

public class ArmaCuerpoACuerpo extends Arma {

    private int consumoEnergia;

    public ArmaCuerpoACuerpo(Integer id, String nombre, int nivel, int danioBase, E_Calidad calidad, int consumoEnergia) throws RangoException
    {
        super(id, nombre, nivel, calidad, danioBase, E_ClaseAsociada.CUERPO_A_CUERPO);
        this.consumoEnergia = consumoEnergia;
    }

    public ArmaCuerpoACuerpo(Objeto obj, int danioBase, int consumoEnergia) throws RangoException
    {
        super(obj.getID(), obj.getNombre(), obj.getNivel(), obj.getCalidad(), danioBase, E_ClaseAsociada.CUERPO_A_CUERPO);
        this.consumoEnergia = consumoEnergia;
    }

    public int getConsumoEnergia()
    {
        return this.consumoEnergia;
    }

    public String toString()
    {
        return "Arma Cuerpo a Cuerpo ["+super.toString()+" | Consumo Energia: "+this.consumoEnergia+"]";
    }

}
