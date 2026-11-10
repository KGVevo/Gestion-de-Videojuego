package Enums;

public enum E_Fases {

    FACE_I(1, 7), FACE_II(2, 15), FACE_III(3, 20), FACE_FINAL(4, 30);

    private int numFace;
    private int numNivel;

    E_Fases(int numFace, int numNivel)
    {
        this.numFace = numFace;
        this.numNivel = numNivel;
    }

    public int obtenerNumeroFace()
    {
        return this.numFace;
    }

    public int obtenerNumeroNivel()
    {
        return this.numFace;
    }

}
