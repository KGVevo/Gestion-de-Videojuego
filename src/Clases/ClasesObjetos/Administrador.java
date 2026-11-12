package Clases.ClasesObjetos;

import java.util.Objects;

public class Administrador extends Usuario {

    private String cuit;

    public Administrador(Integer id, String userNombre, String gmail, String cuit)
    {
        super(id, userNombre, gmail);
        this.cuit = cuit;
    }

    public String obtenerCuitAdmin()
    {
        return this.cuit;
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
        return this.cuit.equals(aux.obtenerCuitAdmin());

    }

    @Override
    public int hashCode() {
        return Objects.hash(this.cuit);
    }

}
