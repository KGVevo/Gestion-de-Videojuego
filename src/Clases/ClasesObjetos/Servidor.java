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
    private ClaseGenericaHS<Tarjeta> tarjetas;

    private ClaseGenericaAL<Objeto> objetos;
    private ClaseGenericaAL<Enemigo> enemigos;

    public Servidor() {
        this.usuarios = new ClaseGenericaHS<>();
        this.objetos = new ClaseGenericaAL<>();
        this.tarjetas = new ClaseGenericaHS<>();
    }

    private void verificacionUsuario(String cuit) throws NoAdminException {
        if(!usuarios.existeElemento(cuit)){
            throw new NoAdminException("|-> ERROR: El administrador ingresado no existe <-|");
        }
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

    public boolean crearArmaCuerpoACuerpo(String cuitAdmin, String nombre, int nivel, int dañoBase, E_Calidad calidad, int consumoEnergia) throws NoAdminException, RangoException {
        verificacionUsuario(cuitAdmin);
        return objetos.agregarElemento(new ArmaCuerpoACuerpo(nombre, nivel, dañoBase, calidad, consumoEnergia));
    }

    public boolean crearArmaDistancia(String cuitAdmin, String nombre, int nivel, int dañoBase, E_Calidad calidad, int consumoMunicion) throws NoAdminException, RangoException {
        verificacionUsuario(cuitAdmin);
        return objetos.agregarElemento(new ArmaCuerpoACuerpo(nombre, nivel, dañoBase, calidad, consumoMunicion));
    }

    public boolean crearArmaMago(String cuitAdmin, String nombre, int nivel, int dañoBase, E_Calidad calidad, int consumoMana) throws NoAdminException, RangoException {
        verificacionUsuario(cuitAdmin);
        return objetos.agregarElemento(new ArmaCuerpoACuerpo(nombre, nivel, dañoBase, calidad, consumoMana));
    }

    public boolean crearArmadura(String cuitAdmin, String nombre, int nivel, double proteccion, E_ClaseAsociada claseAsociada, double efecto) throws NoAdminException, RangoException {
        verificacionUsuario(cuitAdmin);
        return objetos.agregarElemento(new Armadura(nombre, nivel, proteccion, claseAsociada));
    }

    public boolean crearPocion(String cuitAdmin, String nombre, int nivel, double efecto, E_TipoPocion tipoPocion) throws NoAdminException, RangoException {
        verificacionUsuario(cuitAdmin);
        return objetos.agregarElemento(new Pocion(nombre, nivel, efecto, tipoPocion));
    }

    public boolean eliminarObjeto(String cuitAdmin, int id) throws NoAdminException {
        verificacionUsuario(cuitAdmin);
        return objetos.eliminarElemento(id);
    }

    public String verObjetoID(String cuitAdmin, int id) throws NoAdminException {
        verificacionUsuario(cuitAdmin);
        return objetos.obtenerElemento(id).toString();
    }

    public String verObjetoTodos(String cuitAdmin) throws NoAdminException {
        verificacionUsuario(cuitAdmin);
        return objetos.obtenerElementoTodos();
    }

    public boolean agregarEnemigo(String cuitAdmin, String nombreEnemigo, int vida, int danioBase, int nivel, E_TipoDeEnemigo ETipoDeEnemigo) throws NoAdminException {
        verificacionUsuario(cuitAdmin);
        return enemigos.agregarElemento(new Enemigo(nombreEnemigo, vida, danioBase, nivel, ETipoDeEnemigo));
    }

    public boolean eliminarEnemigo(String cuitAdmin, int id) throws NoAdminException {
        verificacionUsuario(cuitAdmin);
        return enemigos.eliminarElemento(id);
    }

    public String verEnemigoID(String cuitAdmin, int id) throws NoAdminException {
        verificacionUsuario(cuitAdmin);
        return enemigos.obtenerElemento(id).toString();
    }

    public String verEnemigoTodos(String cuitAdmin) throws NoAdminException {
        verificacionUsuario(cuitAdmin);
        return enemigos.obtenerElementoTodos();
    }

}


