package Clases.ClasesObjetos;


import Clases.ClasesObjetos.Entidades.Enemigo;
import Clases.ClasesObjetos.ObjetosClass.ArmaCuerpoACuerpo;
import Clases.ClasesObjetos.ObjetosClass.Armadura;
import Clases.ClasesObjetos.ObjetosClass.Objeto;
import Clases.ClasesObjetos.ObjetosClass.Pocion;
import Enums.E_Calidad;
import Enums.E_ClaseAsociada;
import Enums.E_TipoDeEnemigo;
import Enums.E_TipoPocion;
import Exceptions.NoAdminException;
import Exceptions.RangoException;
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

    public boolean crearArmaCuerpoACuerpo(String cuitAdmin, String nombre, int nivel, int dañoBase, E_Calidad calidad, int consumoEnergia) throws NoAdminException, RangoException
    {
        if(usuarios.existeElemento(cuitAdmin) == false)
        {
            throw new NoAdminException("ERROR: El administrador ingresado no existe.");
        }
        return objetos.agregarElemento(new ArmaCuerpoACuerpo(nombre, nivel, dañoBase, calidad, consumoEnergia));
    }

    public boolean crearArmaDistancia(String cuitAdmin, String nombre, int nivel, int dañoBase, E_Calidad calidad, int consumoMunicion) throws NoAdminException, RangoException
    {
        if(usuarios.existeElemento(cuitAdmin) == false)
        {
            throw new NoAdminException("ERROR: El administrador ingresado no existe.");
        }
        return objetos.agregarElemento(new ArmaCuerpoACuerpo(nombre, nivel, dañoBase, calidad, consumoMunicion));
    }

    public boolean crearArmaMago(String cuitAdmin, String nombre, int nivel, int dañoBase, E_Calidad calidad, int consumoMana) throws NoAdminException, RangoException
    {
        if(usuarios.existeElemento(cuitAdmin) == false)
        {
            throw new NoAdminException("ERROR: El administrador ingresado no existe.");
        }
        return objetos.agregarElemento(new ArmaCuerpoACuerpo(nombre, nivel, dañoBase, calidad, consumoMana));
    }

    public boolean crearArmadura(String cuitAdmin, String nombre, int nivel, double proteccion, E_ClaseAsociada claseAsociada, double efecto) throws NoAdminException, RangoException
    {
        if(usuarios.existeElemento(cuitAdmin) == false)
        {
            throw new NoAdminException("ERROR: El administrador ingresado no existe.");
        }
        return objetos.agregarElemento(new Armadura(nombre, nivel, proteccion, claseAsociada, efecto));
    }

    public boolean crearPocion(String cuitAdmin, String nombre, int nivel, double efecto, E_TipoPocion tipoPocion) throws NoAdminException, RangoException
    {
        if(usuarios.existeElemento(cuitAdmin) == false)
        {
            throw new NoAdminException("ERROR: El administrador ingresado no existe.");
        }
        return objetos.agregarElemento(new Pocion(nombre, nivel, efecto, tipoPocion));
    }

    public boolean eliminarObjeto(String cuitAdmin, int id) throws NoAdminException
    {
        if(usuarios.existeElemento(cuitAdmin) == false)
        {
            throw new NoAdminException("ERROR: El administrador ingresado no existe.");
        }
        return objetos.eliminarElemento(id);
    }

    public String verObjetoID(String cuitAdmin, int id) throws NoAdminException
    {
        if(usuarios.existeElemento(cuitAdmin) == false)
        {
            throw new NoAdminException("ERROR: El administrador ingresado no existe.");
        }
        return objetos.obtenerElemento(id).toString();
    }

    public String verObjetoTodos(String cuitAdmin) throws NoAdminException
    {
        if(usuarios.existeElemento(cuitAdmin) == false)
        {
            throw new NoAdminException("ERROR: El administrador ingresado no existe.");
        }
        return objetos.obtenerElementoTodos();
    }

}


