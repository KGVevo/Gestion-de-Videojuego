package Clases.ClasesObjetos;


import Clases.ClasesObjetos.ObjetosClass.Objeto;
import Exceptions.TarjetaInvalidaException;

public class Servidor {
    private ClaseGenericaHS<Usuario> usuarios;
    private ClaseGenericaHS<Objeto> objetos;
    private ClaseGenericaHS<Tarjeta> tarjetas;


    public Servidor() {
        this.usuarios = new ClaseGenericaHS<>();
        this.objetos = new ClaseGenericaHS<>();
        this.tarjetas = new ClaseGenericaHS<>();
    }

    /// Agrega tarjeta -> se usa para agregar un usuario con tarjeta
    public Tarjeta agregarTarjeta(String titular, int numeroDeTarjeta)throws TarjetaInvalidaException {
        Tarjeta tarjeta = new Tarjeta(titular, numeroDeTarjeta);

        if (!tarjetas.agregarElemento(tarjeta)) {
            throw new  TarjetaInvalidaException("| !!La tarjeta ya existe en el sistema!! |");
        }

        return tarjeta;
    }


    /// Agregar Usuario sin tarjeta
    public boolean agregarUsuario(String nombreUsuario, String gmail, String password) {

        int id = JDBC.agregarUsuario(nombreUsuario, gmail, password);

        if (id != -1) {
            Usuario usuario = new Usuario(id, nombreUsuario, gmail);
            return usuarios.agregarElemento(usuario);
        }

        return false;
    }


    /// Agregar Usuario con tarjeta
    public boolean agregarUsuario(String nombreUsuario, String gmail, String password, String titularTarjeta, int numeroDeTarjeta) throws TarjetaInvalidaException {
        Tarjeta tarjeta = agregarTarjeta(titularTarjeta, numeroDeTarjeta);

        Integer id = JDBC.agregarUsuario(nombreUsuario, gmail, password);
        Usuario usuario = new Usuario(id, nombreUsuario, gmail, tarjeta);

        return usuarios.agregarElemento(usuario);
    }
}


