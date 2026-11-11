package Clases.ClasesObjetos;

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

}
