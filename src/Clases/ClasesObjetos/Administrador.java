package Clases.ClasesObjetos;

import java.util.Objects;

public class Administrador extends Usuario {

    private String cuil;
    private boolean permitido;

    public Administrador(Integer id, String userNombre, String gmail, String cuil) {
        super(id, userNombre, gmail);
        this.cuil = cuil;
        this.permitido = false;
    }

    public String getCuil() {
        return this.cuil;
    }


    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }

        if(obj == null || this.getClass() != obj.getClass()){
            return false;
        }

        Administrador aux = (Administrador) obj;
        return this.cuil.equals(aux.getCuil());

    }

    @Override
    public int hashCode() {
        return Objects.hash(this.cuil);
    }

}
