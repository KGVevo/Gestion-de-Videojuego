package Enums;

public enum E_Fases {

    FACE_I("FASE 1", 1), FACE_II("FASE 2", 2), FACE_III("FASE 3", 3), FACE_FINAL("FASE FINAL", 4);

    private String faceActual;
    private int numEnemigos;

    E_Fases(String numFace, int numEnemigos)
    {
        this.faceActual = numFace;
        this.numEnemigos = numEnemigos;
    }

    public String obtenerFaceActual()
    {
        return this.faceActual;
    }

    public int obtenerNumeroEnemigos()
    {
        return this.numEnemigos;
    }

}
