package Enums;

public enum E_Fases {

    FACE_I(1, 1), FACE_II(2, 1.5), FACE_III(3, 2.2), FACE_FINAL(4, 2.7);

    private int numFace;
    private double numEnemigos;

    E_Fases(int numFace, double numEnemigos)
    {
        this.numFace = numFace;
        this.numEnemigos = numEnemigos;
    }

    public int obtenerNumeroFace()
    {
        return this.numFace;
    }

    public double obtenerNumeroEnemigos()
    {
        return this.numEnemigos;
    }

}
