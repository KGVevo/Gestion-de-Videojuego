package Clases.ClasesObjetos;


import Clases.ClasesObjetos.Entidades.*;
import Clases.ClasesObjetos.ObjetosClass.ArmaCuerpoACuerpo;
import Clases.ClasesObjetos.ObjetosClass.Armadura;
import Clases.ClasesObjetos.ObjetosClass.Objeto;
import Clases.ClasesObjetos.ObjetosClass.Pocion;
import Enums.*;
import Exceptions.ObjetoInvalidoException;
import Exceptions.RangoException;
import Exceptions.TarjetaInvalidaException;

import java.util.ArrayList;


public class Servidor {
    /// HashSets
    private ClaseGenericaHS<Usuario> usuarios;
    private ClaseGenericaHS<Tarjeta> tarjetas;
    /// ArrayLists
    private ClaseGenericaAL<Mundo> mundos;
    private ClaseGenericaAL<Objeto> objetos;
    private ClaseGenericaAL<Enemigo> enemigos;
    private ClaseGenericaAL<Jugador> jugadores;

    public Servidor() {
        this.usuarios = new ClaseGenericaHS<>();
        this.tarjetas = new ClaseGenericaHS<>();

        this.mundos = new ClaseGenericaAL<>();
        this.objetos = new ClaseGenericaAL<>();
        this.enemigos = new ClaseGenericaAL<>();
        this.jugadores = new ClaseGenericaAL<>();
    }





    ////////////////////////////////// AGREGAR ELEMETOS METODOS ///////////////////////////////////////////////////////

    /// Agrega tarjeta -> se usa para agregar un usuario con tarjeta
    private Tarjeta agregarTarjeta(String titular, int numeroDeTarjeta)throws TarjetaInvalidaException {
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

    /// Agrega un Administrador
    public boolean agregarAdministrador(String nombreUsuario, String gmail, String password,String cuil) {
        int id = JDBC.agregarAdministrador(nombreUsuario, gmail, password, cuil);

        if (id != -1) {
            Administrador admin = new Administrador(id, nombreUsuario, gmail, cuil);
            return usuarios.agregarElemento(admin);
        }

        return false;
    }

    /// Agregar un Jugador
    public boolean agregarJugador(String nombreJugador, int danioBase, int idUsuario, int opcion) {
        /// En vez de hacerlo con ENUM, me ahorro haciendolo con enteros.
        if(opcion == 1) {
            /// Solo para Cuerpo a Cuerpo
            return jugadores.agregarElemento(new JugadorCuerpoACuerpo(nombreJugador, danioBase, idUsuario));
        }
        else if(opcion == 2) {
            /// Solo para Distancia
            return jugadores.agregarElemento(new JugadorDistancia(nombreJugador, danioBase, idUsuario));
        }
        else if(opcion == 3) {
            /// Solo para Mago
            return jugadores.agregarElemento(new JugadorMago(nombreJugador, danioBase, idUsuario));
        }

        return false;
    }


    /// Agrega un Enemigo
    public boolean agregarEnemigo(String nombreEnemigo, int vida, int danioBase, int nivel, E_TipoDeEnemigo ETipoDeEnemigo) {
        if(enemigos.agregarElemento(new Enemigo(nombreEnemigo, vida, danioBase, nivel, ETipoDeEnemigo))){
            JDBC.agregarEnemigo(nombreEnemigo,vida, nivel);
            return true;
        }
        return false;
    }


    /// Agregar Objeto
    public boolean agregarObjeto(String nombre, int nivel, E_Calidad calidad) throws  RangoException {
        return objetos.agregarElemento(new Objeto(nombre, nivel, calidad)); /// Esta funcion crea objetos que no tienen ninguna funcionalidad (huesos, piedra, palo, etc)
    }


    /// Agregar Arma
    public boolean agregarArma(String nombre, int nivel, int dañoBase, E_Calidad calidad, int consumo, E_ClaseAsociada claseAsociada) throws RangoException {

        if(claseAsociada == E_ClaseAsociada.CUERPO_A_CUERPO) {
            return objetos.agregarElemento(new ArmaCuerpoACuerpo(nombre, nivel, dañoBase, calidad, consumo));
        }
        else if(claseAsociada == E_ClaseAsociada.DISTANCIA) {
            return objetos.agregarElemento(new ArmaCuerpoACuerpo(nombre, nivel, dañoBase, calidad, consumo));
        }
        else if(claseAsociada == E_ClaseAsociada.MAGO) {
            return objetos.agregarElemento(new ArmaCuerpoACuerpo(nombre, nivel, dañoBase, calidad, consumo));
        }

        return false;
    }

    /// Agregar Armadura
    public boolean agregarArmadura(String nombre, int nivel, E_Calidad calidad, int proteccion, E_ClaseAsociada claseAsociada) throws  RangoException {

        return objetos.agregarElemento(new Armadura(nombre, nivel, calidad, proteccion, claseAsociada));
    }

    /// Agregar Pocion
    public boolean agregarPocion( String nombre, int nivel, double efecto, E_TipoPocion tipoPocion, E_Calidad calidad) throws RangoException {
        return objetos.agregarElemento(new Pocion(nombre, nivel, efecto, tipoPocion, calidad));
    }



    ///////////////////////////// ELEMINAR ELEMETOS METODOS ////////////////////////////////////////////////////////////

    /// Eliminar Objeto
    public boolean eliminarObjeto(int id){
        return objetos.eliminarElemento(id);
    }

    public boolean eliminarEnemigo( int id){
        return enemigos.eliminarElemento(id);
    }

    /// Elimina un Jugador por id de Usuario
    public boolean eliminarJugador(int idUsuario) {
        return jugadores.eliminarElemento(idUsuario);
    }

    /// Elimina un Jugador por Elemento Jugador
    public boolean eliminarJugador(Jugador j) {
        return jugadores.eliminarElemento(j);
    }




    public String verObjetoID(int id) {
        return objetos.obtenerElemento(id).toString();
    }

    public String verObjetoTodos() {
        return objetos.obtenerElementoTodos();
    }


    public String verEnemigoID(int id) {
        return enemigos.obtenerElemento(id).toString();
    }

    public String verEnemigoTodos() {
        return enemigos.obtenerElementoTodos();
    }

    /////////////////// OBTENER ELEMENTOS METODOS //////////////////////////////////////////////////////////////////////

    /// Metodos de Jugador
    public Jugador obtenerJugador(int idUsuario) {
        return jugadores.obtenerElemento(idUsuario);
    }


    private Jugador resultadoClase(int idUsuario) {
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


    /// Mostrar Todos los Jugadores -> solo visible para admin
    public String mostrarJugadores() {
        return jugadores.obtenerElementoTodos();
    }




    ////////////////////////// METODOS PARA EL MUNDO ///////////////////////////////////////////////////////////////////


    /// Generador de tienda para Mundo
    private ArrayList generarTienda(Mundo mundoCreado)throws ObjetoInvalidoException {
        ArrayList<Objeto> aux = new ArrayList<>();

        if(objetos.cantidadElementos() < mundoCreado.getCantidadObjetosTienda()){
            throw new ObjetoInvalidoException("|No hay suficientes objetos para rellenar la Tienda|");
        }


        for(int i = 0; i < mundoCreado.getCantidadObjetosTienda(); i++){
            aux.add(objetos.obtenerElemento(SistemaDeProbabilidad.generadorInt(objetos.cantidadElementos())));
        }

        return aux;
    }

    /// Metodo para generar enemigos para el mundo
    private ArrayList generarEnemigosMundo(Mundo mundoCreado, int nivelPlayer)throws RangoException {
        // Creamos un Arraylist auxiliar para guardar los enemigos filtrados
        ArrayList<Enemigo> auxiliar = new ArrayList<>();
        int i = 0;
        // Calculamos la cantidad de enemigos totales
        int cantidadEnemigos = mundoCreado.getDificultad().obtenerCantidadEnemigos() + mundoCreado.getFase().obtenerNumeroEnemigos();

        // Filtra a los enemigos por el nivel del jugador y por la dificultadad del mundo
        while(auxiliar.size() <= cantidadEnemigos && i < enemigos.cantidadElementos()){

            if(cantidadEnemigos <= enemigos.cantidadElementos()){
                if(enemigos.obtenerElemento(i).getNivel() <= nivelPlayer){
                    auxiliar.add(enemigos.obtenerElemento(i));
                }
            }else{
                throw new RangoException("| !! No hay suficientes enemigos para jugar en esta Dificultad !! |");
            }
        }

        return auxiliar;
    }


    /// Crear un Mundo
    public Mundo generarMundo(Jugador jugador,String nombrMundo, E_Dificultad dificultad) throws RangoException, ObjetoInvalidoException {
        Mundo mundo = new Mundo(nombrMundo, jugador, dificultad);

        // Se generan los Enemigos
        mundo.modificarEnemigos(generarEnemigosMundo(mundo, jugador.getNivel()));
        // Se genera la tienda
        mundo.modificarTienda(generarTienda(mundo));

        return mundo;
    }

    public boolean comenzarFase(Mundo mundo, Jugador jugador){
        return true;
    }




    public String sistemaCombateEnemigo(){


        return "";
    }


    public int sistemaCombateJugador(int accion, Enemigo objetivo, Jugador jugador){
        if(accion == 1){
            return objetivo.modificadorDeVida(jugador.atacar(),-1);
        }else if(accion == 2){

        }else if(accion == 3){

        }else if(accion == 4){

        }

        return 1;
    }







    public int modificadorDeExp(int idUsuario, int valor, int nivelActual) {
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


