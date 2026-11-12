package Enums;

public enum E_Dificultad {

    /// La dificultadad y la cantidad de enemigos por fase
    FACIL(3, 5), NORMAL(5, 10), DIFICIL(8, 20);

    private double cantidadEnemigos;
    private int nivelDeEnemigos;

    E_Dificultad(double cantidadEnemigos, int  nivelDeEnemigos)
    {
        this.cantidadEnemigos = cantidadEnemigos;
    }

    public double obtenerCantidadEnemigos()
    {
        return this.cantidadEnemigos;
    }

    public int obtenerNivel(){
        return this.nivelDeEnemigos;
    }

}
