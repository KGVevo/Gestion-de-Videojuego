package Clases.ClasesObjetos;

import java.util.Objects;

public class Usuario {
    private String userNombre;
    private String gmail;
    private Tarjeta tarjeta;
    private boolean premium;


    public Usuario(String userNombre, String gmail, Tarjeta tarjeta, boolean premium) {
        this.userNombre = userNombre;
        this.gmail = gmail;
        this.tarjeta = tarjeta;
        this.premium = premium;
    }

    public String getUserNombre() {
        return userNombre;
    }

    public String getGmail() {
        return gmail;
    }

    public boolean getPremium() {
        return premium;
    }



    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }

        if(obj == null || this.getClass() != obj.getClass()){
            return false;
        }

        Usuario aux = (Usuario) obj;
        return this.userNombre.equals(aux.userNombre) || this.gmail.equals(aux.gmail);

    }


    @Override
    public int hashCode() {
        return Objects.hash(this.userNombre, this.gmail);
    }


}
