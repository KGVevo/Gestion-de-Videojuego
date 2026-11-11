package Enums;

public enum E_TipoDeEnemigo {
    NORMAL("NORMAL"),
    ELITE("ELITE"),
    JEFE("JEFE");

    private String tipoDeEnemigo;

    E_TipoDeEnemigo(String tipoDeEnemigo) {
        this.tipoDeEnemigo = tipoDeEnemigo;
    }


    public String getTipoDeEnemigo() {
        return tipoDeEnemigo;
    }



}
