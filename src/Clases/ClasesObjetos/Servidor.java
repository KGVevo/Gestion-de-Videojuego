package Clases.ClasesObjetos;


import Clases.ClasesObjetos.Entidades.*;
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
    private ClaseGenericaAL<Jugador> jugadores;

    public Servidor() {
        this.usuarios = new ClaseGenericaHS<>();
        this.objetos = new ClaseGenericaAL<>();
        this.tarjetas = new ClaseGenericaHS<>();
        enemigos = new ClaseGenericaAL<>();
        jugadores = new ClaseGenericaAL<>();
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

    /// Agregar Objeto
    public boolean agregarObjeto(String cuitAdmin, String nombre, int nivel, E_Calidad calidad) throws NoAdminException, RangoException
    {
        verificacionUsuario(cuitAdmin);
        return objetos.agregarElemento(new Objeto(nombre, nivel, calidad)); /// Esta funcion crea objetos que no tienen ninguna funcionalidad (huesos, piedra, palo, etc)
    }

    /// Agregar Arma
    public boolean crearArma(String cuitAdmin, String nombre, int nivel, int dañoBase, E_Calidad calidad, int consumo, E_ClaseAsociada claseAsociada) throws NoAdminException, RangoException
    {
        verificacionUsuario(cuitAdmin); //Verifica que exista el administrador

        if(claseAsociada == E_ClaseAsociada.CUERPO_A_CUERPO)
        {
            return objetos.agregarElemento(new ArmaCuerpoACuerpo(nombre, nivel, dañoBase, calidad, consumo));
        }
        else if(claseAsociada == E_ClaseAsociada.DISTANCIA)
        {
            return objetos.agregarElemento(new ArmaCuerpoACuerpo(nombre, nivel, dañoBase, calidad, consumo));
        }
        else if(claseAsociada == E_ClaseAsociada.MAGO)
        {
            return objetos.agregarElemento(new ArmaCuerpoACuerpo(nombre, nivel, dañoBase, calidad, consumo));
        }

        return false;
    }

    /// Agregar Armadura
    public boolean crearArmadura(String cuitAdmin, String nombre, int nivel, E_Calidad calidad, double proteccion, E_ClaseAsociada claseAsociada, double efecto) throws NoAdminException, RangoException {
        verificacionUsuario(cuitAdmin);
        return objetos.agregarElemento(new Armadura(nombre, nivel, calidad ,proteccion, claseAsociada));
    }

    /// Agregar Pocion
    public boolean crearPocion(String cuitAdmin, String nombre, int nivel, double efecto, E_TipoPocion tipoPocion, E_Calidad calidad) throws NoAdminException, RangoException {
        verificacionUsuario(cuitAdmin);
        return objetos.agregarElemento(new Pocion(nombre, nivel, efecto, tipoPocion, calidad));
    }

    /// Eliminar Objeto
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

    /*==============================================================================================================*/

    // Metodos de Jugador
    private Jugador obtenerJugador(int idUsuario)
    {
        return jugadores.obtenerElemento(idUsuario);
    }

    private Jugador resultadoClase(int idUsuario)
    {
        Jugador jugadorActual = obtenerJugador(idUsuario);
        if(jugadorActual.getClaseAsociada() == E_ClaseAsociada.CUERPO_A_CUERPO)
        {
            jugadorActual = (JugadorCuerpoACuerpo) jugadorActual;
        }
        else if(jugadorActual.getClaseAsociada() == E_ClaseAsociada.DISTANCIA)
        {
            jugadorActual = (JugadorDistancia) jugadorActual;
        }
        else if(jugadorActual.getClaseAsociada() == E_ClaseAsociada.MAGO)
        {
            jugadorActual = (JugadorMago) jugadorActual;
        }
        return jugadorActual;
    }

    public boolean crearJugador(String nombreJugador, int vida, int danioBase, int idUsuario, int opcion)
    {
        //Envez de hacerlo con ENUM, me ahorro haciendolo con enteros.
        if(opcion == 1) //Solo para Cuerpo a Cuerpo
        {
            return jugadores.agregarElemento(new JugadorCuerpoACuerpo(nombreJugador, vida, danioBase, idUsuario));
        }
        else if(opcion == 2) //Solo para Distancia
        {
            return jugadores.agregarElemento(new JugadorDistancia(nombreJugador, vida, danioBase, idUsuario));
        }
        else if(opcion == 3) //Solo para Mago
        {
            return jugadores.agregarElemento(new JugadorMago(nombreJugador, vida, danioBase, idUsuario));
        }

        return false;
    }

    public boolean eliminarJugador(int idUsuario)
    {
        return jugadores.eliminarElemento(idUsuario);
    }

    public boolean eliminarJugador(Jugador j)
    {
        return jugadores.eliminarElemento(j);
    }

    public String mostrarJugadores(String cuitAdmin) throws NoAdminException
    {
        verificacionUsuario(cuitAdmin);
        return jugadores.obtenerElementoTodos();
    }

    public int modificadorDeExp(int idUsuario, int valor, int nivelActual)
    {
        return obtenerJugador(idUsuario).modificadorDeExp(valor, nivelActual);
    }

    public boolean jugadorDefenderse(int idUsuario) {
        return obtenerJugador(idUsuario).defenderse();
    }

    public boolean jugadorHuir(int idUsuario) {
        return obtenerJugador(idUsuario).huir();
    }

    public int jugadorGolpeCritico(int idUsuario) {
        return resultadoClase(idUsuario).golpeCritico();
    }

    public int jugadorAtaca(int idUsuario) {
        return resultadoClase(idUsuario).atacar();
    }


    public boolean jugadorRecargar(int idUsuario){

        if(resultadoClase(idUsuario) instanceof JugadorDistancia)
        {
            return ((JugadorDistancia) resultadoClase(idUsuario)).recargar();
        }
        return false;
    }

    public int jugadorHabilidadArma(int idUsuario) {
        return resultadoClase(idUsuario).habilidadArma();
    }

    /*==============================================================================================================*/

    // Entidades (Jugador y Enemigo)

    /*public int modificadorVidaJugador()
    {

    }

    public int modificadorVidaJugador()
    {

    }*/

    /*==============================================================================================================*/

}


