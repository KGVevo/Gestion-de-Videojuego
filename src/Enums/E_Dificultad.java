package Enums;

public enum E_Dificultad {

    FACIL(1), NORMAL(1.7), DIFICIL(2.4);

    private double numDificultad;

    E_Dificultad(double numDificultad)
    {
        this.numDificultad = numDificultad;
    }

    public double obtenerDificultad()
    {
        return this.numDificultad;
    }

}
