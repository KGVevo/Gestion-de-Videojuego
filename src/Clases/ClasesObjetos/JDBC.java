package Clases.ClasesObjetos;

import Clases.ClasesObjetos.Entidades.*;
import Clases.ClasesObjetos.ObjetosClass.*;
import Clases.ClasesObjetos.SistemaDeJuego.Mundo;
import Clases.ClasesObjetos.SistemaDeJuego.Partida;
import Clases.ClasesObjetos.SistemaDeJuego.Reserva;
import Enums.*;
import Exceptions.InventarioException;
import Exceptions.ObjetoInvalidoException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class JDBC {

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///                                              CONEXION                                                       ///
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///
    private static final String URL = "jdbc:sqlite:registro.db";

    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------- QUERY USUARIO --------------------------------------------------------*/

    /// Para agregar un Usuario a la base de datos(Debere retonar un id que es el que usaremos para guardarlo en la clase)
    private static final String INSERTAR_USUARIO = "INSERT INTO Usuario (nombre, gmail, password, rol) VALUES (?, ?, ?, ?)";

    /// Obtiene todos los Usuarios
    private static final String OBTENER_USUARIOS = "SELECT * FROM Usuario";

    /// Para obtener la contraseña de un Usuario en base a su gmail
    private static final String OBTENER_CONTRASEÑA = "SELECT * FROM Usuario WHERE gmail = ?";

    ///  Para cuando le damos el permitido a un Moderador
    private static final String ACTUALIZAR_MODERADOR = "UPDATE Usuario SET permitido = ? WHERE idUsuario = ?";

    /// Obtener el Usuario por Gmail, crucial para no perder la verificacion despues de iniciar sesion
    private static final String OBTENER_USUARIO_POR_GMAIL = "SELECT * FROM Usuario WHERE gmail = ?";

    /// Eliminar Usuario por id
    private static final String ELIMINAR_USUARIO = "DELETE FROM Usuario WHERE id = ?";

    /// Servira para cuando cambiemos el estado de Usuario a BENEADO
    private static final String CAMBIAR_ESTADO_USUARIO = "UPDATE Usuario SET estado = ? WHERE id = ?";

    private static final String OBTENER_USUARIO_POR_ID = "SELECT * FROM Usuario WHERE id = ?";

    /*----------------------------------------- QUERY USUARIO --------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------- QUERY JUGADOR --------------------------------------------------------*/

    ///Para agregar un Player de un Usuario
    private static final String INSERTAR_JUGADOR = "INSERT INTO Player (idUsuario, nombrePlayer, tipoPlayer) VALUES (?, ?, ?);";
    //El "tipoPlayer" es un ENUM en la base de datos que contiene lo siguiente:[GUERRERO, ARQUERO, MAGO]

    /// Obtenemos todos los players
    private static final String OBTENER_JUGADORES = "SELECT * FROM Player;";
    /*
    *   Debe retornar los siguiente de la base de datos:
    *   int id | int idUsuario | String nombrePlayer | tipoPlayer | int vida | int danioBase | nivel | expAcumulada | monedas |
    *
    *   el "tipoPlayer" va a definir en que clase se guarda(JugadorCuerpoACuerpo, JugadorDistancia, JugadorMago)
    */

    /// Obtenemos todos los Players de un Jugador en especifico
    private static final String OBTENER_JUGADORES_USUARIO = "SELECT * FROM Player WHERE idUsuario = ?;";

    private static final String OBTENER_JUGADOR_POR_ID = "SELECT * FROM Player WHERE id = ?;";


    /// Borra un Player en especifico
    private static final String ELIMINAR_JUGADOR = "DELETE FROM Player WHERE id = ?;";


    /// Actualiza estadisticas de jugador///
    private static final String ACTUALIZAR_ARMA_EQUIPADA = "UPDATE Player SET idArma = ? WHERE id = ?;";
    private static final String ACTUALIZAR_VIDA = "UPDATE Player SET vida = ? WHERE id = ?;";
    private static final String ACTUALIZAR_NIVEL = "UPDATE Player SET nivel = ? WHERE id = ?;";
    private static final String ACTUALIZAR_EXP_ACUMULADA = "UPDATE Player SET expAcumulada = ? WHERE id = ?;";
    private static final String ACTUALIZAR_DANIO = "UPDATE Player SET danioBase = ? WHERE id = ?;";
    private static final String ACTUALIZAR_MONEDAS = "UPDATE Player SET monedas = ? WHERE id = ?;";

    /*----------------------------------------- QUERY JUGADOR --------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------- QUERY ENEMIGO --------------------------------------------------------*/

    /// Agrega un Enemigo a la base de datos
    private static final String INSERTAR_ENEMIGO = "INSERT INTO Enemigo (nombre, vida, danio, nivel, tipoEnemigo) VALUES (?, ?, ?, ?, ?);";
    // Contiene ENUM en la base de datos [NORMAL, ELITE, JEFE]

    /// Obtiene todos los Enemigos
    private static final String OBTENER_ENEMIGOS = "SELECT * FROM Enemigo;";

    /*
     *   Debe retornar los siguiente de la base de datos:
     *   int id | String nombre | int vida | int danio | nivel | tipoEnemigo |
     *
     *   el tipoEnemigo contiene si es [NORMAL, ELITE, JEFE]
     */

    /// Borra un Enemigo de la BDD
    private static final String ELIMINAR_ENEMIGO = "DELETE FROM Enemigo WHERE id = ?;";

    /*----------------------------------------- QUERY ENEMIGO --------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------- QUERY MUNDOS ---------------------------------------------------------*/

    /// Parar agregar un mundo a la BDD
    private static final String INSERTAR_MUNDO = "INSERT INTO Mundo (nombre, dificultad, estado, motivo, detalle)  VALUES (?, ?, ?, ?, ?);";
    /*PARAMETROS:
    *   String nombre
    *   ENUM   dificultad  -> FACIL , NORMAL , DIFICIL
    *   ENUM   estado -> DISPONIBLE, EN_PARTIDA, RESERVADO, NO_DISPONIBLE
    *   ENUM   motivo -> MANTENIMIENTO, ERROR_TECNICO, OTRO
    *   String detalle
    * */

    /// Obtener Mundos
    private static final String OBTENER_MUNDOS = "SELECT * FROM Mundo;";

    private static final String OBTENER_MUNDO_POR_ID = "SELECT * FROM Mundo WHERE id = ?;";

    /// Borrar Mundo
    private static final String ELIMINAR_MUNDO = "DELETE FROM Mundo WHERE id = ?;";

    /// Actualiza el estado del mundo
    private static final String ACTUALIZAR_ESTADO_MUNDO = "UPDATE Mundo SET estado = ? WHERE id = ?;";
    // Por parametros recibira el ENUM con el estado al que cambiara

    /// Modificar motivo del Mundo
    private static final String ACTUALIZAR_MOTIVO_MUNDO = "UPDATE Mundo SET motivo = ?, detalle = ? WHERE id = ?;";
    // Por parametros recibira un ENUM del motivo y un String sobre los detalles

    /*----------------------------------------- QUERY MUNDOS ---------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------- QUERY RESERVAS -------------------------------------------------------*/
    /// Ingresa una Reserva a la Base de datos
    private static final String INSERTAR_RESERVA = "INSERT INTO Reserva (idPlayer, idMundo, desde, hasta, estado) VALUES (?, ?, ?, ?, ?);";
    /*Parametros:
    *   int idPlayer
    *   int idMundo
    *   LocalDate desde -> Recibira una fecha, deberas buscar la forma de pasarla a un String para la base de datos
    *   LocalDate hasta -> Recibira una fecha, deberas buscar la forma de pasarla a un String para la base de datos
    *   E_EstadoReserva estado -> ACTIVA, CANCELADA, CUMPLIDA
    * */

    /// Obtiene todas las reservas
    private static final String OBTENER_RESERVAS  = "SELECT * FROM Reserva;";

    /// Obtiene las Reservas de un Player en especifico
    private static final String OBTENER_RESERVAS_USUARIO = "SELECT * FROM Reserva WHERE idPlayer = ?;";

    /// Actualiza el estado de la reserva
    private static final String ACTUALIZAR_RESERVA_ESTADO = "UPDATE Reserva SET estado = ? WHERE id = ?;";
    //estado -> ACTIVA, CANCELADA, CUMPLIDA

    /// Borra una reserva en base a la id del Mundo
    private static final String ELIMINAR_RESERVAS = "DELETE FROM Reserva WHERE idMundo = ?;";

    /*----------------------------------------- QUERY RESERVAS -------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------- QUERY PARTIDAS -------------------------------------------------------*/

    /// Inserta un partida
    private static final String INSERTAR_PARTIDA = "INSERT INTO Partida (idPlayer, idMundo, inicio, fin, resultado, expGanada, monedasGanadas) VALUES (?, ?, ?, ?, ?, ?, ?);";
    /*Parametros
    *   int idPlayer
    *   int idMundo
    *   LocalDate inicio
    *   LocalDate fin
    *   E_ResultadoPartida -> VICTORIA, DERROTA, ABANDONO
    *   int expGanada
    *   int monedasGanadas
    * */

    /// Obtiene todas las partidas
    private static final String OBTENER_PARTIDAS = "SELECT * FROM Partida;";

    /// Obtiene las partidas de un Player en especifico
    private static final String OBTENER_PARTIDAS_PLAYER = "SELECT * FROM Partida WHERE idPlayer = ?;";

    /// Actualiza resultado de la partida
    private static final String ACTUALIZAR_RESULTADO_PARTIDA = "UPDATE Partida SET resultado = ? WHERE idPlayer = ?;";
    // resultado -> VICTORIA, DERROTA, ABANDONO

    /*----------------------------------------- QUERY PARTIDAS -------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------- QUERY INVENTARIO -----------------------------------------------------*/

    /// Para agregar un Inventario a un Player
    private static final String INSERTAR_INVENTARIO = "INSERT INTO Inventario (idPlayer, slot, idObjeto, nombreObjeto) VALUES (?, ?, NULL, NULL);";
    /*Parametro:
    *   (int idPlayer)
    *
    *   OJO: Tenes que lograr que cuando se crea un Inventario se creen los 10 SLOTS de una con un FOR
    *   ejemplo:
    *   for(int slot = 1; slot <= 10; slot++){
    *       ps.Int(1, idPlayer)
    *       ps.Int(2, slot)
    *       ps.executeUpdate()
    *   }
    *
    *
    * */


    /// Obtener el inventario de Player
    private static final String OBTENER_INVENTARIO_PLAYER  = "SELECT * FROM Inventario WHERE id = ?;";

    /// Actualiza el slot de un inventario de un player en base al id
    private static final String ACTUALIZAR_INVENTARIO_PLAYER = "UPDATE Inventario SET idObjeto = ?, nombreObjeto WHERE slot = ? AND idPlayer = ?;";

    /// Borra un inventario de un Player
    private static final String ELIMINAR_INVENTARIO = "DELETE FROM Inventario WHERE idPlayer = ?;";


    /*----------------------------------------- QUERY INVENTARIO -----------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------- QUERY OBJETOS --------------------------------------------------------*/

    /// ESTOS LOS HAGO YO PORQUE SON UN EMBOLE
    private static final String INSERTAR_OBJETOS = "INSERT INTO Objetos (tipoObjeto ,nombreObjeto, nivel, calidad, descripcion, danioBase, consumo, tipoPocion) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String OBTENER_OBJETO_POR_ID = "SELECT * FROM Objeto WHERE id = ?;";
    private static final String ELIMINAR_OBJETO = "DELETE FROM Objeto WHERE id = ?;";

    /*----------------------------------------- QUERY OBJETOS --------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///                                         PROBAR CONEXION                                                     ///
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static String probarConexcion(){
        String conexion = "|----> Error <----|";

        try(Connection connection = DriverManager.getConnection(URL)){
            conexion =  "|----> Conectado con Exito <----|";

        }catch (SQLException e){
            e.printStackTrace();
        }

        return conexion;
    }
    //-///////////////////////////////////////////////////////////////////////////////////////////////////////////////-//
    //-///////////////////////////////////////////////////////////////////////////////////////////////////////////////-//


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///                                         QUERY USUARIO                                                       ///
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /// Agrega un Usuario a la Base de Datos
    public static int agregarUsuario(String nombre, String gmail, String password, int rol){
        int idAgregado = -1;

        try(Connection connection = DriverManager.getConnection(URL);
            PreparedStatement ps = connection.prepareStatement(INSERTAR_USUARIO, Statement.RETURN_GENERATED_KEYS)){

            ps.setString(1, nombre);
            ps.setString(2, gmail);
            ps.setString(3, password);

            if (rol == 1){
                ps.setString(4, String.valueOf(E_Rol.USUARIO));
            }else if (rol == 2){
                ps.setString(4, String.valueOf(E_Rol.MODERADOR));
            }else if (rol == 3){
                ps.setString(4, String.valueOf(E_Rol.ADMINISTRADOR));
            }


            ps.executeUpdate();
            try(ResultSet rs = ps.getGeneratedKeys()){
                if(rs.next()){
                    idAgregado = rs.getInt(1);
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return idAgregado;
    }

    /// Obtiene todos los Usuarios
    public static ClaseGenericaHS<Usuario> obtenerUsuarios(){
        ClaseGenericaHS<Usuario> usuarios = new ClaseGenericaHS<>();
        boolean flag;


        try(Connection connection = DriverManager.getConnection(URL);
                PreparedStatement ps = connection.prepareStatement(OBTENER_USUARIOS)){

            try(ResultSet rs = ps.executeQuery()){
               while(rs.next()){
                    int idUsuario = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    String gmail = rs.getString("gmail");
                    E_Rol rol = E_Rol.valueOf(rs.getString("rol"));
                    E_EstadoCuenta estadoCuenta = E_EstadoCuenta.valueOf(rs.getString("estado"));

                    if (rol == E_Rol.MODERADOR){
                        int permitido = rs.getInt("permitido");
                        if(permitido == 0){
                            flag = false;
                        }else{
                            flag = true;
                        }

                        Moderador mod = new Moderador(idUsuario, nombre, gmail, flag, estadoCuenta);
                        usuarios.agregarElemento(mod);
                    }
                    if (rol == E_Rol.ADMINISTRADOR){
                        Administrador admin = new Administrador(idUsuario, nombre, gmail);
                    }if (rol == E_Rol.USUARIO){
                        Usuario usuario = new Usuario(idUsuario, nombre, gmail, rol, estadoCuenta);
                   }

               }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return usuarios;
    }


    /// Consigue la password de un Usuario para iniciar sesion
    public static String getUsuarioPassword(String gmail){
        String password = null;

        try(Connection connection = DriverManager.getConnection(URL);
            PreparedStatement ps = connection.prepareStatement(OBTENER_CONTRASEÑA)){

            ps.setString(1, gmail);

            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    password = rs.getString("password");
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return password;
    }

    /// Cambio el estado del Moderador
    public static void actualizarModerador(boolean b, int idUsuario){
        int permitido;
        if (b == false){
            permitido = 0;
        }else {
            permitido = 1;
        }
        try(Connection connection = DriverManager.getConnection(URL);
                PreparedStatement ps = connection.prepareStatement(ACTUALIZAR_MODERADOR)){

            ps.setInt(1, permitido);
            ps.setInt(2, idUsuario);

            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /// Obtiene el Usuario en base al Gmail
    public static Usuario obtenerUsuario(String gmail){

        Usuario usuario = null;

        boolean flag;

        try(Connection connection = DriverManager.getConnection(URL);
                PreparedStatement ps = connection.prepareStatement(OBTENER_USUARIO_POR_GMAIL)){

            ps.setString(1, gmail);

            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    int idUsuario = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    E_Rol rol = E_Rol.valueOf(rs.getString("rol"));
                    E_EstadoCuenta estadoCuenta = E_EstadoCuenta.valueOf(rs.getString("estado"));

                    if (rol == E_Rol.MODERADOR){
                        int permitido = rs.getInt("permitido");
                        if(permitido == 0){
                            flag = false;
                        }else{
                            flag = true;
                        }
                        usuario = new Moderador(idUsuario, nombre, gmail, flag, estadoCuenta);
                    }
                    if (rol == E_Rol.ADMINISTRADOR){
                        usuario = new Administrador(idUsuario, nombre, gmail);
                    }if (rol == E_Rol.USUARIO){
                        usuario = new Usuario(idUsuario, nombre, gmail, rol, estadoCuenta);
                    }
                }
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return usuario;
    }

    /// Obtiene el Usuario por ID
    public static Usuario obtenerUsuarioPorId(int idUsuario){

        Usuario usuario = null;

        boolean flag;

        try(Connection connection = DriverManager.getConnection(URL);
            PreparedStatement ps = connection.prepareStatement(OBTENER_USUARIO_POR_ID)){

            ps.setInt(1, idUsuario);

            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    String gmail = rs.getString("gmail");
                    String nombre = rs.getString("nombre");
                    E_Rol rol = E_Rol.valueOf(rs.getString("rol"));
                    E_EstadoCuenta estadoCuenta = E_EstadoCuenta.valueOf(rs.getString("estado"));

                    if (rol == E_Rol.MODERADOR){
                        int permitido = rs.getInt("permitido");
                        if(permitido == 0){
                            flag = false;
                        }else{
                            flag = true;
                        }
                        usuario = new Moderador(idUsuario, nombre, gmail, flag, estadoCuenta);
                    }
                    if (rol == E_Rol.ADMINISTRADOR){
                        usuario = new Administrador(idUsuario, nombre, gmail);
                    }if (rol == E_Rol.USUARIO){
                        usuario = new Usuario(idUsuario, nombre, gmail, rol, estadoCuenta);
                    }
                }
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return usuario;
    }

    /// Cambia el estado del Usuario ACTIVA/BANEADO
    public static void cambiarEstadoUsuario(int idUsuario){
        Usuario usuario = obtenerUsuarioPorId(idUsuario);

        try(Connection connection = DriverManager.getConnection(URL);
            PreparedStatement ps = connection.prepareStatement(CAMBIAR_ESTADO_USUARIO)){
            
            if (usuario.getEstadoCuenta() == E_EstadoCuenta.ACTIVA){
                ps.setString(1, String.valueOf(E_EstadoCuenta.BANEADA));
            }else if (usuario.getEstadoCuenta() == E_EstadoCuenta.BANEADA){
                ps.setString(1, String.valueOf(E_EstadoCuenta.ACTIVA));
            }

            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    /// ELIMINA UN USUARIO
    public static int eliminarUsuario(int id){
        int afectado = -1;

        try(Connection connection = DriverManager.getConnection(URL);
            PreparedStatement ps = connection.prepareStatement(ELIMINAR_USUARIO)){

            ps.setInt(1, id);

            afectado = ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

        return  afectado;
    }

    //-///////////////////////////////////////////////////////////////////////////////////////////////////////////////-//
    //-///////////////////////////////////////////////////////////////////////////////////////////////////////////////-//


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///                                         QUERY JUGADOR                                                       ///
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static int agregarJugador (int idUsuario, String nombrePlayer, E_ClaseAsociada tipo){
        int idAgregado = -1;
        String tipoPlayer = null;

        try(Connection connection = DriverManager.getConnection(URL);
            PreparedStatement ps = connection.prepareStatement(INSERTAR_JUGADOR, Statement.RETURN_GENERATED_KEYS)){

            switch (tipo){
                case CUERPO_A_CUERPO: tipoPlayer = "GUERRERO"; break;
                case DISTANCIA: tipoPlayer = "ARQUERO"; break;
                case MAGO: tipoPlayer = "MAGO"; break;
            }



            ps.setInt(1, idUsuario);
            ps.setString(2, nombrePlayer);
            ps.setString(3, tipoPlayer);


            ps.executeUpdate();

            try(ResultSet rs = ps.getGeneratedKeys()){
                if(rs.next()){
                    idAgregado = rs.getInt(1);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  idAgregado;
    }

    public static Jugador obtenerJugador(int idPlayer){
        Jugador jugador = null;

        try(Connection connection = DriverManager.getConnection(URL);
                PreparedStatement ps = connection.prepareStatement(OBTENER_JUGADOR_POR_ID)){
            ps.setInt(1, idPlayer);

            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    int idUsuario = rs.getInt("idUsuario");
                    String nombre = rs.getString("nombrePlayer");
                    E_ClaseAsociada tipo = E_ClaseAsociada.valueOf(rs.getString("tipoPlayer"));
                    int vida = rs.getInt("vida");
                    int danioBase = rs.getInt("danioBase");
                    int nivel = rs.getInt("nivel");
                    int expAcumulada = rs.getInt("expAcumulada");
                    int monedas = rs.getInt("monedas");

                    if (tipo == E_ClaseAsociada.CUERPO_A_CUERPO){
                        jugador = new JugadorCuerpoACuerpo(idPlayer, nombre, idUsuario, danioBase, (ArmaCuerpoACuerpo) obtenerObjeto(1), vida, nivel, expAcumulada, monedas);
                    }else if (tipo == E_ClaseAsociada.MAGO){
                        jugador = new JugadorMago(idPlayer, nombre, idUsuario, danioBase, (ArmaMago) obtenerObjeto(3),vida, nivel, expAcumulada, monedas);
                    }else if (tipo == E_ClaseAsociada.DISTANCIA){
                        jugador = new JugadorDistancia(idPlayer, nombre, idUsuario, danioBase, (ArmaDistancia) obtenerObjeto(2),vida, nivel, expAcumulada, monedas);
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return jugador;
    }

    public static ClaseGenericaHS<Jugador> obtenerJugadores(){
        ClaseGenericaHS<Jugador> jugadores = new ClaseGenericaHS<>();
        Jugador jugador = null;

        try(Connection connection = DriverManager.getConnection(URL);
                PreparedStatement ps = connection.prepareStatement(OBTENER_JUGADORES)){

            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    int id = rs.getInt("id");
                    int idUsuario = rs.getInt("idUsuario");
                    String nombre = rs.getString("nombrePlayer");
                    E_ClaseAsociada tipo = E_ClaseAsociada.valueOf(rs.getString("tipoPlayer"));
                    int vida = rs.getInt("vida");
                    int danioBase = rs.getInt("danioBase");
                    int nivel = rs.getInt("nivel");
                    int expAcumulada = rs.getInt("expAcumulada");
                    int monedas = rs.getInt("monedas");

                    if (tipo == E_ClaseAsociada.CUERPO_A_CUERPO){
                        jugador = new JugadorCuerpoACuerpo(id, nombre, idUsuario, danioBase, (ArmaCuerpoACuerpo) obtenerObjeto(1), vida, nivel, expAcumulada, monedas);
                    }else if (tipo == E_ClaseAsociada.MAGO){
                        jugador = new JugadorMago(id, nombre, idUsuario, danioBase, (ArmaMago) obtenerObjeto(3),vida, nivel, expAcumulada, monedas);
                    }else if (tipo == E_ClaseAsociada.DISTANCIA){
                        jugador = new JugadorDistancia(id, nombre, idUsuario, danioBase, (ArmaDistancia) obtenerObjeto(2),vida, nivel, expAcumulada, monedas);
                    }

                    jugadores.agregarElemento(jugador);

                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return jugadores;
    }


    public static ClaseGenericaHS<Jugador> obtenerJugadoresUsuario(int idUsuario){
        ClaseGenericaHS<Jugador> jugadores = new ClaseGenericaHS<>();
        Jugador jugador = null;

        try(Connection connection = DriverManager.getConnection(URL);
            PreparedStatement ps = connection.prepareStatement(OBTENER_JUGADORES_USUARIO)){

            ps.setInt(1, idUsuario);

            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombrePlayer");
                    E_ClaseAsociada tipo = E_ClaseAsociada.valueOf(rs.getString("tipoPlayer"));
                    int vida = rs.getInt("vida");
                    int danioBase = rs.getInt("danioBase");
                    int nivel = rs.getInt("nivel");
                    int expAcumulada = rs.getInt("expAcumulada");
                    int monedas = rs.getInt("monedas");

                    if (tipo == E_ClaseAsociada.CUERPO_A_CUERPO){
                        jugador = new JugadorCuerpoACuerpo(id, nombre, idUsuario, danioBase, (ArmaCuerpoACuerpo) obtenerObjeto(1),vida, nivel, expAcumulada, monedas);
                    }else if (tipo == E_ClaseAsociada.MAGO){
                        jugador = new JugadorMago(id, nombre, idUsuario, danioBase, (ArmaMago) obtenerObjeto(3),vida, nivel, expAcumulada, monedas);
                    }else if (tipo == E_ClaseAsociada.DISTANCIA){
                        jugador = new JugadorDistancia(id, nombre, idUsuario, danioBase, (ArmaDistancia) obtenerObjeto(2),vida, nivel, expAcumulada, monedas);
                    }

                    jugadores.agregarElemento(jugador);

                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return jugadores;
    }


    public static int eliminarJugador(int idPlayer){
        int filasAfectadas = -1;
        try(Connection connection = DriverManager.getConnection(URL);
                PreparedStatement ps = connection.prepareStatement(ELIMINAR_JUGADOR)){
            ps.setInt(1, idPlayer);

            filasAfectadas = ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return filasAfectadas;
    }

    public static void actualizarArmaJugador(int idArma, int idJugador){

        try(Connection connection = DriverManager.getConnection(URL);
            PreparedStatement ps = connection.prepareStatement(ACTUALIZAR_ARMA_EQUIPADA)){

            ps.setInt(1, idArma);
            ps.setInt(2, idJugador);

            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    /// Actualiza el nivel del Jugador
    public static void actualizarNivelJugador(int nuevoLevelJugador, int idJugador){

        try(Connection connection = DriverManager.getConnection(URL);
            PreparedStatement ps = connection.prepareStatement(ACTUALIZAR_NIVEL)){

            ps.setInt(1, nuevoLevelJugador);
            ps.setInt(2, idJugador);

            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /// Actualiza el vida del Jugador
    public static void actualizarVidaJugador(int nuevaVidaJugador, int idJugador){

        try(Connection connection = DriverManager.getConnection(URL);
            PreparedStatement ps = connection.prepareStatement(ACTUALIZAR_VIDA)){

            ps.setInt(1, nuevaVidaJugador);
            ps.setInt(2, idJugador);

            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /// Actualiza el danio del Jugador
    public static void actualizarDanioJugador(int nuevoDanioJugador, int idJugador){

        try(Connection connection = DriverManager.getConnection(URL);
            PreparedStatement ps = connection.prepareStatement(ACTUALIZAR_DANIO)){

            ps.setInt(1, nuevoDanioJugador);
            ps.setInt(2, idJugador);

            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /// Actualiza la experiencia del Jugador
    public static void actualizarXPJugador(int expAcumulada, int idJugador){

        try(Connection connection = DriverManager.getConnection(URL);
            PreparedStatement ps = connection.prepareStatement(ACTUALIZAR_EXP_ACUMULADA)){

            ps.setInt(1, expAcumulada);
            ps.setInt(2, idJugador);

            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /// Actualiza las monedas del Jugador
    public static void actualizarMonedasJugador(int nuevasMonedas, int idJugador){

        try(Connection connection = DriverManager.getConnection(URL);
            PreparedStatement ps = connection.prepareStatement(ACTUALIZAR_MONEDAS)){

            ps.setInt(1, nuevasMonedas);
            ps.setInt(2, idJugador);

            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }








    //-///////////////////////////////////////////////////////////////////////////////////////////////////////////////-//
    //-///////////////////////////////////////////////////////////////////////////////////////////////////////////////-//


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///                                              QUERY ENEMIGOS                                                 ///
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public static int agregarEnemigos(String nombre , int vida, int danio, int nivel, E_TipoDeEnemigo tipo){
        int idAgregado = -1;

        try(Connection connection = DriverManager.getConnection(URL);
                PreparedStatement ps = connection.prepareStatement(INSERTAR_ENEMIGO)){

            ps.setString(1, nombre);
            ps.setInt(2, vida);
            ps.setInt(3, danio);
            ps.setInt(4, nivel);
            ps.setString(5, String.valueOf(tipo));

            ps.executeUpdate();

            try(ResultSet rs = ps.getGeneratedKeys()){
                if (rs.next()){
                    idAgregado = rs.getInt(1);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return idAgregado;
    }

    public static ClaseGenericaAL<Enemigo> obtenerEnemigos(){
        ClaseGenericaAL<Enemigo> enemigos = new ClaseGenericaAL<>();
        Enemigo enemigo = null;

        try(Connection connection = DriverManager.getConnection(URL);
                PreparedStatement ps = connection.prepareStatement(OBTENER_ENEMIGOS)){

            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    int vida = rs.getInt("vida");
                    int danio = rs.getInt("danio");
                    int nivel = rs.getInt("nivel");
                    E_TipoDeEnemigo tipo = E_TipoDeEnemigo.valueOf(rs.getString("tipoEnemigo"));

                    enemigo = new Enemigo(id, nombre, vida, danio, nivel, tipo);
                    enemigos.agregarElemento(enemigo);
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return enemigos;
    }

    public static int eliminarEnemigo(int id){
        int afectado = -1;

        try(Connection connection = DriverManager.getConnection(URL);
                PreparedStatement ps = connection.prepareStatement(ELIMINAR_ENEMIGO)){
            ps.setInt(1, id);

            afectado = ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

        return afectado;
    }




    //-///////////////////////////////////////////////////////////////////////////////////////////////////////////////-//
    //-///////////////////////////////////////////////////////////////////////////////////////////////////////////////-//


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///                                              QUERY MUNDO                                                   ///
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public static int agregarMundo(String nombre, E_Dificultad dificultad, E_EstadoMundo estado, E_MotivoMundo motivo, String detalles){
        int idAgregado = -1;

        try(Connection connection = DriverManager.getConnection(URL);
                PreparedStatement ps = connection.prepareStatement(INSERTAR_MUNDO)){
            ps.setString(1,nombre);
            ps.setString(2, String.valueOf(dificultad));
            ps.setString(3, String.valueOf(estado));
            ps.setString(4, String.valueOf(motivo));
            ps.setString(5, detalles);

            ps.executeUpdate();

            try(ResultSet rs = ps.getGeneratedKeys()){
                if (rs.next()){
                    idAgregado = rs.getInt(1);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return idAgregado;
    }

    public static Mundo obtenerMundo(int idMundo){
        Mundo mundo = null;

        try(Connection connection = DriverManager.getConnection(URL);
                PreparedStatement ps = connection.prepareStatement(OBTENER_MUNDO_POR_ID)){
            ps.setInt(1, idMundo);

            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    String nombre = rs.getString("nombre");
                    E_Dificultad dificultad = E_Dificultad.valueOf(rs.getString("dificultad"));
                    mundo = new Mundo(idMundo, nombre, dificultad);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return mundo;
    }

    public static ClaseGenericaAL<Mundo> obtenerMundos (){
        ClaseGenericaAL<Mundo> mundos = new ClaseGenericaAL<>();
        Mundo mundo = null;

        try(Connection connection = DriverManager.getConnection(URL);
                PreparedStatement ps = connection.prepareStatement(OBTENER_MUNDOS)){

            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    E_Dificultad dificultad = E_Dificultad.valueOf(rs.getString("dificultad"));

                    mundo = new Mundo(id, nombre, dificultad);
                    mundos.agregarElemento(mundo);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return mundos;
    }

    public static int eliminarMundo(int id){
        int filasAfectadas = -1;
        try(Connection connection = DriverManager.getConnection(URL);
                PreparedStatement ps = connection.prepareStatement(ELIMINAR_MUNDO)){
            ps.setInt(1, id);

            filasAfectadas = ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return filasAfectadas;
    }

    public static void actualizarEstadoMundo(E_EstadoMundo estado, int id){

        try(Connection connection = DriverManager.getConnection(URL);
                PreparedStatement ps = connection.prepareStatement(ACTUALIZAR_ESTADO_MUNDO)){
            ps.setString(1, String.valueOf(estado));
            ps.setInt(2, id);

            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void actualizarMotivoMundo(E_MotivoMundo motivo, String detalles, int id){

        try(Connection connection = DriverManager.getConnection(URL);
                PreparedStatement ps = connection.prepareStatement(ACTUALIZAR_MOTIVO_MUNDO)){
            ps.setString(1, String.valueOf(motivo));
            ps.setString(2, detalles);
            ps.setInt(3, id);

            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //-///////////////////////////////////////////////////////////////////////////////////////////////////////////////-//
    //-///////////////////////////////////////////////////////////////////////////////////////////////////////////////-//


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///                                              QUERY RESERVAS                                                 ///
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static int agregarReserva(int idPlayer, int idMundo, LocalDate desde, LocalDate hasta, E_EstadoReserva estado){
        int idAgregado = -1;

        try(Connection connection = DriverManager.getConnection(URL);
                PreparedStatement ps = connection.prepareStatement(INSERTAR_RESERVA)){
            ps.setInt(1, idPlayer);
            ps.setInt(2, idMundo);
            ps.setString(3, String.valueOf(desde));
            ps.setString(4, String.valueOf(hasta));
            ps.setString(5, String.valueOf(estado));

            ps.executeUpdate();

            try(ResultSet rs = ps.getGeneratedKeys()){
                if (rs.next()){
                    idAgregado = rs.getInt(1);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return idAgregado;
    }

    public static ClaseGenericaHS<Reserva> obtenerReservas(){
        ClaseGenericaHS<Reserva> reservas = new ClaseGenericaHS<>();
        Reserva reserva = null;

        try(Connection connection = DriverManager.getConnection(URL);
                PreparedStatement ps = connection.prepareStatement(OBTENER_RESERVAS)){

            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    int id = rs.getInt("id");
                    int idPlayer = rs.getInt("idPlayer");
                    int idMundo = rs.getInt("idMundo");
                    LocalDate desde = LocalDate.parse(rs.getString("desde"));
                    LocalDate hasta = LocalDate.parse(rs.getString("hasta"));
                    E_EstadoReserva estado = E_EstadoReserva.valueOf(rs.getString("estado"));

                    reserva = new Reserva(id, obtenerJugador(idPlayer), obtenerMundo(idMundo) , desde, hasta, estado);
                    reservas.agregarElemento(reserva);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return reservas;
    }


    public static ClaseGenericaHS<Reserva> obtenerReservasUsuario(int idPlayer){
        ClaseGenericaHS<Reserva> reservas = new ClaseGenericaHS<>();
        Reserva reserva = null;

        try(Connection connection = DriverManager.getConnection(URL);
            PreparedStatement ps = connection.prepareStatement(OBTENER_RESERVAS_USUARIO)){

            ps.setInt(1, idPlayer);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    int id = rs.getInt("id");
                    int idMundo = rs.getInt("idMundo");
                    LocalDate desde = LocalDate.parse(rs.getString("desde"));
                    LocalDate hasta = LocalDate.parse(rs.getString("hasta"));
                    E_EstadoReserva estado = E_EstadoReserva.valueOf(rs.getString("estado"));

                    reserva = new Reserva(id, obtenerJugador(idPlayer), obtenerMundo(idMundo) , desde, hasta, estado);
                    reservas.agregarElemento(reserva);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return reservas;
    }

    public static void actualizarEstadoReserva(E_EstadoReserva estado, int idPlayer){

        try(Connection connection = DriverManager.getConnection(URL);
                PreparedStatement ps = connection.prepareStatement(ACTUALIZAR_RESERVA_ESTADO)){
            ps.setString(1, String.valueOf(estado));
            ps.setInt(2, idPlayer);

            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static int eliminarReserva(int idMundo){
        int filasAfectadas = -1;
        try(Connection connection = DriverManager.getConnection(URL);
                PreparedStatement ps = connection.prepareStatement(ELIMINAR_RESERVAS)){
            ps.setInt(1, idMundo);

            filasAfectadas = ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return filasAfectadas;
    }

    //-///////////////////////////////////////////////////////////////////////////////////////////////////////////////-//
    //-///////////////////////////////////////////////////////////////////////////////////////////////////////////////-//


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///                                              QUERY PARTIDAS                                                 ///
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static int agregarPartida(int idPlayer, int idMundo, LocalDate inicio, LocalDate fin, E_ResultadoPartida resultado, int expGanada, int monedasGanadas){
        int idAgregado = -1;

        try(Connection connection = DriverManager.getConnection(URL);
                PreparedStatement ps = connection.prepareStatement(INSERTAR_PARTIDA)){
            ps.setInt(1, idPlayer);
            ps.setInt(2, idMundo);
            ps.setString(3, String.valueOf(inicio));
            ps.setString(4, String.valueOf(fin));
            ps.setString(5, String.valueOf(resultado));
            ps.setInt(6, expGanada);
            ps.setInt(7, monedasGanadas);

            ps.executeUpdate();

            try(ResultSet rs = ps.getGeneratedKeys()){
                if (rs.next()){
                    idAgregado = rs.getInt(1);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return idAgregado;
    }

    public static ClaseGenericaAL<Partida> obtenerPartidas(){
        ClaseGenericaAL<Partida> partidas = new ClaseGenericaAL<>();
        Partida partida = null;

        try(Connection connection = DriverManager.getConnection(URL);
                PreparedStatement ps = connection.prepareStatement(OBTENER_PARTIDAS)){

            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    int id = rs.getInt("id");
                    int idPlayer = rs.getInt("idPlayer");
                    int idMundo = rs.getInt("idMundo");
                    LocalDate inicio = LocalDate.parse(rs.getString("inicio"));
                    LocalDate fin = LocalDate.parse(rs.getString("fin"));
                    E_ResultadoPartida resultado = E_ResultadoPartida.valueOf(rs.getString("resultado"));
                    int expGanada = rs.getInt("expGanada");
                    int monedasGanadas = rs.getInt("monedasGanadas");

                    partida = new Partida(id, obtenerJugador(idPlayer), obtenerMundo(id) , inicio, fin, resultado, expGanada, monedasGanadas);
                    partidas.agregarElemento(partida);
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return partidas;
    }

    public static ClaseGenericaAL<Partida> obtenerPartidasJugador(int idPlayer){
        ClaseGenericaAL<Partida> partidas = new ClaseGenericaAL<>();
        Partida partida = null;

        try(Connection connection = DriverManager.getConnection(URL);
            PreparedStatement ps = connection.prepareStatement(OBTENER_PARTIDAS_PLAYER)){

            ps.setInt(1, idPlayer);

            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    int id = rs.getInt("id");
                    int idMundo = rs.getInt("idMundo");
                    LocalDate inicio = LocalDate.parse(rs.getString("inicio"));
                    LocalDate fin = LocalDate.parse(rs.getString("fin"));
                    E_ResultadoPartida resultado = E_ResultadoPartida.valueOf(rs.getString("resultado"));
                    int expGanada = rs.getInt("expGanada");
                    int monedasGanadas = rs.getInt("monedasGanadas");

                    partida = new Partida(id, obtenerJugador(idPlayer) , obtenerMundo(idMundo) , inicio, fin, resultado, expGanada, monedasGanadas);
                    partidas.agregarElemento(partida);
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return partidas;
    }

    public static void actualizarResultadoPartida(E_ResultadoPartida resultado, int idPlayer){

        try(Connection connection = DriverManager.getConnection(URL);
                PreparedStatement ps = connection.prepareStatement(ACTUALIZAR_RESULTADO_PARTIDA)){
            ps.setString(1, String.valueOf(resultado));
            ps.setInt(2, idPlayer);

            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //-///////////////////////////////////////////////////////////////////////////////////////////////////////////////-//
    //-///////////////////////////////////////////////////////////////////////////////////////////////////////////////-//

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///                                              QUERY INVENTARIO                                               ///
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void agregarInventario(int idPlayer){

        try(Connection connection = DriverManager.getConnection(URL);
                PreparedStatement ps = connection.prepareStatement(INSERTAR_INVENTARIO)){
            for (int i = 1; i <= 10; i++){
                ps.setInt(1, idPlayer);
                ps.setInt(2, i);
                ps.executeUpdate();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static Inventario obtenerInventario(int idPlayer) throws ObjetoInvalidoException , InventarioException {
        Inventario inventario = new Inventario();
        Objeto objeto = null;

        try(Connection connection = DriverManager.getConnection(URL);
                PreparedStatement ps = connection.prepareStatement(OBTENER_INVENTARIO_PLAYER)){

            ps.setInt(1, idPlayer);

            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    int idObjeto = rs.getInt("idObjeto");

                    objeto = obtenerObjeto(idObjeto);
                    inventario.agregarObjetoInventario(objeto);
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return inventario;
    }

    public static void actualizarInventario(int idObjeto, int slot, int idPlayer){

        try(Connection connection = DriverManager.getConnection(URL);
                PreparedStatement ps = connection.prepareStatement(ACTUALIZAR_INVENTARIO_PLAYER)){

            ps.setInt(1, idObjeto);
            ps.setInt(2, slot);
            ps.setInt(3, idPlayer);

            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static int eliminarInventario(int idPlayer){
        int filasAfectadas = -1;
        try(Connection connection = DriverManager.getConnection(URL);
            PreparedStatement ps = connection.prepareStatement(ELIMINAR_INVENTARIO)){
            ps.setInt(1, idPlayer);

            filasAfectadas = ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return filasAfectadas;
    }

    //-///////////////////////////////////////////////////////////////////////////////////////////////////////////////-//
    //-///////////////////////////////////////////////////////////////////////////////////////////////////////////////-//


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///                                              QUERY OBJETOS                                                  ///
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public static int agregarObjeto(int tipoObjeto, String nombreObjeto, int nivel, E_Calidad calidad, String descripcion, int danioBase, int consumo, int tipoPocion){
        int idAgregado = -1;

        try(Connection connection = DriverManager.getConnection(URL);
                PreparedStatement ps = connection.prepareStatement(INSERTAR_OBJETOS)){
            if(tipoObjeto == 1){
                ps.setString(1, "OBJETO");
            }else if(tipoObjeto == 2){
                ps.setString(1, "ARMA_GUERRERO");
            }else if(tipoObjeto == 3){
                ps.setString(1, "ARMA_ARQUERO");
            }else if(tipoObjeto == 4){
                ps.setString(1, "ARMA_MAGO");
            }else if(tipoObjeto == 5){
                ps.setString(1, "POCION");

                if(tipoPocion == 1){
                    ps.setString(8,String.valueOf(E_TipoPocion.CURACION));
                }else if(tipoPocion == 2){
                    ps.setString(8,String.valueOf(E_TipoPocion.MANA));
                }else if(tipoPocion == 3){
                    ps.setString(8,String.valueOf(E_TipoPocion.ENERGIA));
                }else if(tipoPocion == 4){
                    ps.setString(8,String.valueOf(E_TipoPocion.DANIO));
                }else if(tipoPocion == 5){
                    ps.setString(8,String.valueOf(E_TipoPocion.CRITICO));
                }
            }

            ps.setString(2, nombreObjeto);
            ps.setInt(3, nivel);
            ps.setString(4, String.valueOf(calidad));
            ps.setString(5, descripcion);
            ps.setInt(6, danioBase);
            ps.setInt(7, consumo);


            ps.executeUpdate();

            try(ResultSet rs = ps.getGeneratedKeys()){
                if (rs.next()){
                    idAgregado = rs.getInt(1);
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return idAgregado;
    }

    public static Objeto obtenerObjeto(int idObjeto){
        Objeto objeto = null;

        try(Connection connection = DriverManager.getConnection(URL);
                PreparedStatement ps = connection.prepareStatement(OBTENER_OBJETO_POR_ID)){
            ps.setInt(1, idObjeto);

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    String tipo = rs.getString("tipoObjeto");
                    String nombre = rs.getString("nombreObjeto");
                    int nivel = rs.getInt("nivel");
                    E_Calidad calidad = E_Calidad.valueOf(rs.getString("calidad"));
                    String descripcion = rs.getString("descripcion");
                    int danioBase = rs.getInt("danioBase");
                    int consumo = rs.getInt("consumo");
                    E_TipoPocion tipoPocion = E_TipoPocion.valueOf(rs.getString("tipoPocion"));

                    if (tipo.equals("OBJETO")){
                        objeto = new Objeto(idObjeto, nombre, nivel, calidad);
                    }else if(tipo.equals("ARMA_GUERRERO")){
                        objeto = new ArmaCuerpoACuerpo(idObjeto, nombre, nivel, danioBase, calidad, consumo);
                    }else if(tipo.equals("ARMA_ARQUERO")){
                        objeto = new ArmaDistancia(idObjeto, nombre, nivel, danioBase, calidad, consumo);
                    }else if(tipo.equals("ARMA_MAGO")){
                        objeto = new ArmaMago(idObjeto, nombre, nivel, danioBase, calidad, consumo);
                    }else if(tipo.equals("POCION")){
                        objeto = new Pocion(idObjeto, nombre, nivel, consumo, tipoPocion,calidad);
                    }
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return objeto;
    }

    public static int eliminarObjeto(int idObjeto){
        int filasAfectadas = -1;
        try(Connection connection = DriverManager.getConnection(URL);
                PreparedStatement ps = connection.prepareStatement(ELIMINAR_OBJETO)){
            ps.setInt(1, idObjeto);

            filasAfectadas = ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return filasAfectadas;
    }





    //-///////////////////////////////////////////////////////////////////////////////////////////////////////////////-//
    //-///////////////////////////////////////////////////////////////////////////////////////////////////////////////-//
}
