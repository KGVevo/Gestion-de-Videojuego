package Clases.ClasesObjetos;


import Clases.ClasesObjetos.Entidades.*;
import Clases.ClasesObjetos.ObjetosClass.*;
import Clases.ClasesObjetos.SistemaDeJuego.Mundo;
import Enums.*;
import Exceptions.*;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class Servidor {
    /// HashSets
    private ClaseGenericaHS<Usuario> usuarios;
    /// ArrayLists
    private ClaseGenericaAL<Mundo> mundos;
    private ClaseGenericaAL<Objeto> objetos;
    private ClaseGenericaAL<Enemigo> enemigos;
    private ClaseGenericaAL<Jugador> jugadores;

    public Servidor() {
        this.usuarios = new ClaseGenericaHS<>();


        this.mundos = new ClaseGenericaAL<>();
        this.objetos = new ClaseGenericaAL<>();
        this.enemigos = new ClaseGenericaAL<>();
        this.jugadores = new ClaseGenericaAL<>();
    }

//*=====================================================================================================================================================================*//
    // ============= METODOS DE USUARIO ============= //
//*=====================================================================================================================================================================*//

    //=== Metodos Publicos ===//

    // Crea un Usuario sin Tarjeta
    public String crearUsuario(int id, String userNombre, String gmail, String password, E_Rol rol)
            throws RangoException, TextoContenidoException, ElementoExisteException,
            ContraseñaException
    {

        if (userNombre.length() < 8) {
            throw new RangoException("ERROR: Solo se permite un nombre de 8 o mas caracteres.");
        }

        if (!gmail.contains("@") || !gmail.contains(".com")){ //asiodaadsjioasdjiodasjdoasjasid
            throw new TextoContenidoException("ERROR: El correo debe contener '@' y '.com'.");
        }

        if (password.length() < 8) {
            throw new ContraseñaException("ERROR: La contraseña debe ser como minimo 8 caracteres");
        }

        String mensaje = "=== Se creo un Usuario ===";
        this.usuarios.agregarElemento(new Usuario(id, userNombre, gmail, rol));
        return mensaje;
    }

    // Elimina un Usuario
    public String eliminarUsuario(Integer id)
    {
        String mensaje = "=== Se elimino el Usuario ===";
        if (!this.usuarios.eliminarElemento(id)) {
            mensaje = "=== Este usuario no existe ===";
        }
        return mensaje;
    }

    // Busca y muestra un Usuario especifico
    public String verUsuarioID(Integer id) {
        String mensaje = "[=] " + this.usuarios.obtenerElemento(id).toString();
        if (mensaje.isBlank()) {
            mensaje = "=== No hay usuarios disponibles ===";
        }
        return mensaje;
    }

    // Muestra todos los Usuarios
    public String verTodosUsuario() {
        String mensaje = this.usuarios.verTodosElemento();
        if (mensaje.isBlank()) {
            mensaje = "=== No hay usuarios disponibles ===";
        }
        return mensaje;
    }

//*=====================================================================================================================================================================*//
    // ============= METODOS DE JUGADOR ============= //
//*=====================================================================================================================================================================*//

    public String crearJugador(Integer id, Integer idUsuario, String nombreJugador, E_ClaseAsociada claseAsociada)
            throws RangoException, ElementoExisteException, UserInvalidException
    {

        if (jugadores.verificarElemento(id)) {
            throw new ElementoExisteException("ERROR: Este Jugador ya existe");
        }

        if(!usuarios.verificarElemento(idUsuario))
        {
            throw new UserInvalidException("ERROR: Este Usuario no existe");
        }

        if (nombreJugador.length() < 8) {
            throw new RangoException("ERROR: Solo se permite un nombre de 8 o mas caracteres.");
        }

        if(claseAsociada == E_ClaseAsociada.CUERPO_A_CUERPO)
        {
            // Hay un problema con Arma cuerpo a cuerpo y es la ID. De ultima puede generarse sin ID pero eso conlleva a que Arma tenga otro constructor.
            ArmaCuerpoACuerpo melee = new ArmaCuerpoACuerpo(0, "Espada", 1, 12, E_Calidad.COMUN, 5); //Resolver problema
            this.jugadores.agregarElemento(new JugadorCuerpoACuerpo(id, nombreJugador, idUsuario, melee));
        }
        else if(claseAsociada == E_ClaseAsociada.DISTANCIA)
        {
            // Hay un problema con Arma Distancia y es la ID. De ultima puede generarse sin ID pero eso conlleva a que Arma tenga otro constructor.
            ArmaDistancia ranger = new ArmaDistancia(0, "Arco", 1, 7, E_Calidad.COMUN, 1); //Resolver problema
            this.jugadores.agregarElemento(new JugadorDistancia(id, nombreJugador, idUsuario, ranger));
        }
        else if(claseAsociada == E_ClaseAsociada.MAGO)
        {
            // Hay un problema con Arma Mago y es la ID. De ultima puede generarse sin ID pero eso conlleva a que Arma tenga otro constructor.
            ArmaMago mage = new ArmaMago(0, "Varita", 1, 10, E_Calidad.COMUN, 10); //Resolver problema
            this.jugadores.agregarElemento(new JugadorMago(id, nombreJugador, idUsuario, mage));
        }

        return "=== Se creo un Jugador ===";
    }

    public String eliminarJugador(Integer id)
    {
        String mensaje = "=== Se elimino el Jugador ===";
        if (!this.usuarios.eliminarElemento(id)) {
            mensaje = "=== Este Jugador no existe ===";
        }
        this.jugadores.eliminarElemento(id);
        return mensaje;
    }

    public String verJugadorID(Integer id, Integer idUsuario)
    {
        String mensaje = "[=] ";
        Jugador j = this.jugadores.obtenerElemento(idUsuario);
        if(j == null)
        {
            mensaje = "=== Este jugador no existe ===";
        }



        mensaje += j.toString();
        return mensaje;
    }

    //Este metodo no funciona
    public String verTodosJugadorEspecifico(Integer id) throws UserInvalidException
    {
        ///Aca deberia aver una funcion para que solo devuelva todos los jugadores de un Usuario por medio de la BDD
        return "nada";
    }

    public String verTodosJugador()
    {
        return this.jugadores.verElementoTodos();
    }

//*=====================================================================================================================================================================*//
    // ============= METODOS DE ENEMIGO ============= //
//*=====================================================================================================================================================================*//

    public String crearEnemigo(Integer id, String nombreEnemigo, int vida, int danioBase, int nivel,
                               E_TipoDeEnemigo ETipoDeEnemigo)
            throws NombreElementoException, RangoException, ElementoExisteException
    {

        if (enemigos.obtenerElemento(id) != null) {
            throw new ElementoExisteException("ERROR: Este Enemigo ya existe");
        }

        if (nombreEnemigo.length() < 4 || !nombreEnemigo.matches("^[\\p{L}\\s]+$")) {
            throw new NombreElementoException("ERROR: Solo se permite un nombre de 8 o mas caracteres.");
        }

        if(nivel < 1)
        {
            throw new RangoException("ERROR: Se permite como minimo Nivel 1");
        }

        if(vida < 10)
        {
            throw new RangoException("ERROR: Se permite como minimo de Vida 10");
        }

        this.enemigos.agregarElemento(new Enemigo(id, nombreEnemigo, vida, danioBase, nivel, ETipoDeEnemigo));

        return "=== Se creo un Enemigo ===";
    }

    public String eliminarEnemigo(Integer id)
    {
        String mensaje = "=== Se elimino el Enemigo ===";
        if (!this.enemigos.eliminarElemento(id)) {
            mensaje = "=== Este Enemigo no existe ===";
        }
        this.enemigos.eliminarElemento(id);
        return mensaje;
    }

    public String verEnemigoID(Integer id)
    {
        String mensaje = "[=] ";
        Enemigo e = this.enemigos.obtenerElemento(id);
        if(e == null)
        {
            mensaje = "=== Este Enemigo no existe ===";
        }
        mensaje += e.toString();
        return mensaje;
    }

    public String verTodosEnemigo()
    {
        return this.enemigos.verElementoTodos();
    }

//*=====================================================================================================================================================================*//
    // ============= METODOS DE OBJETOS ============= //
//*=====================================================================================================================================================================*//

    private Objeto crearObjetoAuxiliar(Integer id, String nombre, int nivel, E_Calidad calidad)
            throws RangoException, NombreElementoException, ElementoExisteException
    {
        Objeto obj = this.objetos.obtenerElemento(id);

        //En orden primero va el Objeto, luego si el objeto no esta vacio ahi lanza la excepcion.
        if (obj != null && obj.getNombre().equals(nombre)) {
            throw new ElementoExisteException("ERROR: Este Objeto ya existe");
        }

        if(nivel < 1)
        {
            throw new RangoException("ERROR: El nivel debe ser mayor o igual a 1");
        }

        if (nombre.length() < 1 || !nombre.matches("^[\\p{L}\\s]+$")) {
            throw new NombreElementoException("ERROR: Solo se permite un nombre de 8 o mas caracteres.");
        }

        return new Objeto(id, nombre, nivel, calidad);
    }

    public String crearObjeto(Integer id, String nombre, int nivel, E_Calidad calidad)
            throws RangoException, NombreElementoException, ElementoExisteException
    {

        objetos.agregarElemento(crearObjetoAuxiliar(id, nombre, nivel, calidad));

        return "=== Se creo un Objeto ===";
    }

    public String crearPocion(Integer id, String nombre, int nivel, E_TipoPocion tipoPocion, E_Calidad calidad)
            throws RangoException, NombreElementoException, ElementoExisteException
    {
        double efecto = 0;
        Objeto obj = crearObjetoAuxiliar(id, nombre, nivel, calidad);

        if(tipoPocion == E_TipoPocion.CRITICO)
        {
            efecto = 2;
        }
        else if(tipoPocion == E_TipoPocion.CURACION)
        {
            efecto = 15;
        }
        else if(tipoPocion == E_TipoPocion.DANIO)
        {
            efecto = 5;
        }
        else if(tipoPocion == E_TipoPocion.ENERGIA)
        {
            efecto = 10;
        }
        else if(tipoPocion == E_TipoPocion.MANA)
        {
            efecto = 12;
        }

        objetos.agregarElemento(new Pocion(obj, efecto, tipoPocion));

        return "=== Se creo una Pocion ===";
    }

    public String crearArma(Integer id, String nombre, int nivel, E_Calidad calidad , int danioBase, E_ClaseAsociada claseAsociada, int consumo)
            throws RangoException, NombreElementoException, ElementoExisteException
    {
        Objeto obj = crearObjetoAuxiliar(id, nombre, nivel, calidad);
        String mensaje = "=== Se creo un Arma ";

        if(claseAsociada == E_ClaseAsociada.CUERPO_A_CUERPO)
        {
            mensaje += "Cuerpo a Cuerpo ===";
            objetos.agregarElemento(new ArmaCuerpoACuerpo(obj, danioBase, consumo));
        }
        else if(claseAsociada == E_ClaseAsociada.DISTANCIA)
        {
            mensaje += "a Distancia ===";
            objetos.agregarElemento(new ArmaDistancia(obj, danioBase, consumo));
        }
        else if(claseAsociada == E_ClaseAsociada.MAGO)
        {
            mensaje += "de Mago ===";
            objetos.agregarElemento(new ArmaMago(obj, danioBase, consumo));
        }

        return mensaje;
    }

    public String eliminarObjeto(Integer id)
    {
        String mensaje = "";
        if(objetos.obtenerElemento(id) instanceof ArmaCuerpoACuerpo)
        {
            objetos.eliminarElemento(id);
            mensaje = "=== Se elimino un Arma Cuerpo a Cuerpo ===";
        }
        else if(objetos.obtenerElemento(id) instanceof ArmaDistancia)
        {
            objetos.eliminarElemento(id);
            mensaje = "=== Se elimino un Arma a Distancia ===";
        }
        else if(objetos.obtenerElemento(id) instanceof ArmaMago)
        {
            objetos.eliminarElemento(id);
            mensaje = "=== Se elimino un Arma un Mago ===";
        }
        else if(objetos.obtenerElemento(id) instanceof Pocion)
        {
            objetos.eliminarElemento(id);
            mensaje = "=== Se elimino una Pocion ===";
        }
        else if(objetos.obtenerElemento(id) instanceof Objeto)
        {
            objetos.eliminarElemento(id);
            mensaje = "=== Se elimino un Objeto ===";
        }
        return mensaje;
    }

    public String verObjetoID(Integer id)
    {
        String mensaje = "[=] ";
        Objeto o = this.objetos.obtenerElemento(id);
        if(o == null)
        {
            mensaje = "=== Este jugador no existe ===";
        }
        else
        {
            mensaje += o.toString();
        }
        return mensaje;
    }

    public String verTodosObjeto()
    {
        return this.objetos.verElementoTodos();
    }

//*=====================================================================================================================================================================*//
    // ============= METODOS DE OBJETOS ============= //
//*=====================================================================================================================================================================*//

}


