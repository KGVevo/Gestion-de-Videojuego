package Clases.ClasesObjetos.ObjetosClass;

import Enums.E_Calidad;
import Enums.E_ClaseAsociada;
import Exceptions.ClaseAsociadaException;
import Exceptions.RangoException;

public class ArmaCuerpoACuerpo extends Arma {

    private int consumoEnergia;

    public ArmaCuerpoACuerpo(String nombre, int nivel, int dañoBase, E_Calidad calidad, int consumoEnergia) throws RangoException
    {
        super(nombre, nivel, calidad, dañoBase, E_ClaseAsociada.CUERPO_A_CUERPO);
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
