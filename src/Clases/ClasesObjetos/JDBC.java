package Clases.ClasesObjetos;

import Enums.E_EstadoCuenta;
import Enums.E_Rol;

import java.sql.*;

public class JDBC {

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///                                              CONEXION                                                       ///
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///
    private static String URL = "jdbc:sqlite:registro.db";

    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------- QUERY USUARIO --------------------------------------------------------*/

    /// Para agregar un Usuario a la base de datos(Debere retonar un id que es el que usaremos para guardarlo en la clase)
    private static String INSERTAR_USUARIO = "INSERT INTO Usuario (nombre, gmail, password, rol) VALUES (?, ?, ?, ?)";

    /// Obtiene todos los Usuarios
    private static String OBTENER_USUARIOS = "SELECT * FROM Usuario";

    /// Para obtener la contraseña de un Usuario en base a su gmail
    private static String OBTENER_CONTRASEÑA = "SELECT * FROM Usuario WHERE gmail = ?";

    ///  Para cuando le damos el permitido a un Moderador
    private static String ACTUALIZAR_MODERADOR = "UPDATE Usuario SET permitido = ? WHERE idUsuario = ?";

    /// Obtener el Usuario por Gmail, crucial para no perder la verificacion despues de iniciar sesion
    private static String OBTENER_USUARIO_POR_GMAIL = "SELECT * FROM Usuario WHERE gmail = ?";

    /// Eliminar Usuario por id
    private static String ELIMINAR_USUARIO = "DELETE FROM Usuario WHERE id = ?";

    /// Servira para cuando cambiemos el estado de Usuario a BENEADO
    private static String CAMBIAR_ESTADO_USUARIO = "UPDATE Usuario SET estado = ? WHERE id = ?";

    private static String OBTENER_USUARIO_POR_ID = "SELECT * FROM Usuario WHERE id = ?";

    /*----------------------------------------- QUERY USUARIO --------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------- QUERY JUGADOR --------------------------------------------------------*/

    ///Para agregar un Player de un Usuario
    private static String INSERTAR_JUGADOR = "INSERT INTO Player (idUsuario, nombrePlayer, tipoPlayer) VALUES (?, ?, ?);";
    //El "tipoPlayer" es un ENUM en la base de datos que contiene lo siguiente:[GUERRERO, ARQUERO, MAGO]

    /// Obtenemos todos los players
    private static String OBTENER_JUGADORES = "SELECT * FROM Player";
    /*
    *   Debe retornar los siguiente de la base de datos:
    *   int id | int idUsuario | String nombrePlayer | tipoPlayer | int vida | int danioBase | nivel | expAcumulada | monedas |
    *
    *   el "tipoPlayer" va a definir en que clase se guarda(JugadorCuerpoACuerpo, JugadorDistancia, JugadorMago)
    */

    /// Obtenemos todos los Players de un Jugador en especifico
    private static String OBTENER_JUGADORES_USUARIO = "SELECT * FROM Player WHERE idUsuario = ?";


    /// Borra un Player en especifico
    private static String ELIMINAR_JUGADOR = "DELETE FROM Player WHERE idPlayer = ?";


    /// Actualiza estadisticas de jugador
    private static String ACTUALIZAR_VIDA = "UPDATE Player SET vida = ? WHERE id = ?";
    private static String ACTUALIZAR_NIVEL = "UPDATE Player SET nivel = ? WHERE id = ?";
    private static String ACTUALIZAR_EXP_ACUMULADA = "UPDATE Player SET expAcumulada = ? WHERE id = ?";
    private static String ACTUALIZAR_DANIO = "UPDATE Player SET danioBase = ? WHERE id = ?";
    private static String ACTUALIZAR_MONEDAS = "UPDATE Player SET monedas = ? WHERE id = ?";

    /*----------------------------------------- QUERY JUGADOR --------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------- QUERY ENEMIGO --------------------------------------------------------*/

    /// Agrega un Enemigo a la base de datos
    private static String INSERTAR_ENEMIGO = "INSERT INTO Enemigo (nombre, vida, danio, nivel, tipoEnemigo) VALUES (?, ?, ?, ?, ?)";
    // Contiene ENUM en la base de datos [NORMAL, ELITE, JEFE]

    /// Obtiene todos los Enemigos
    private static String OBTENER_ENEMIGOS = "SELECT * FROM Enemigo";

    /*
     *   Debe retornar los siguiente de la base de datos:
     *   int id | String nombre | int vida | int danio | nivel | tipoEnemigo |
     *
     *   el tipoEnemigo contiene si es [NORMAL, ELITE, JEFE]
     */

    /// Borra un Enemigo de la BDD
    private static String ELIMINAR_ENEMIGO = "DELETE FROM Enemigo WHERE id = ?";

    /*----------------------------------------- QUERY ENEMIGO --------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------- QUERY MUNDOS ---------------------------------------------------------*/

    /// Parar agregar un mundo a la BDD
    private static String INSERTAR_MUNDO = "INSERT INTO Mundo (nombre, dificultad, estado, motivo, detalle)  VALUES (?, ?, ?, ?, ?)";
    /*PARAMETROS:
    *   String nombre
    *   ENUM   dificultad  -> FACIL , NORMAL , DIFICIL
    *   ENUM   estado -> DISPONIBLE, EN_PARTIDA, RESERVADO, NO_DISPONIBLE
    *   ENUM   motivo -> MANTENIMIENTO, ERROR_TECNICO, OTRO
    *   String detalle
    * */

    /// Obtener Mundos
    private static String OBTENER_MUNDOS = "SELECT * FROM Mundo";

    /// Borrar Mundo
    private static String ELIMINAR_MUNDO = "DELETE FROM Mundo WHERE id = ?";

    /// Actualiza el estado del mundo
    private static String ACTUALIZAR_ESTADO_MUNDO = "UPDATE Mundo SET estado = ? WHERE id = ?";
    // Por parametros recibira el ENUM con el estado al que cambiara

    /// Modificar motivo del Mundo
    private static String ACTUALIZAR_MOTIVO_MUNDO = "UPDATE Mundo SET motivo = ?, detalle = ? WHERE id = ?";
    // Por parametros recibira un ENUM del motivo y un String sobre los detalles

    /*----------------------------------------- QUERY MUNDOS ---------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------- QUERY RESERVAS -------------------------------------------------------*/
    /// Ingresa una Reserva a la Base de datos
    private static String INSERTAR_RESERVA = "INSERT INTO Reserva (idPlayer, idMundo, desde, hasta, estado) VALUES (?, ?, ?, ?, ?)";
    /*Parametros:
    *   int idPlayer
    *   int idMundo
    *   LocalDate desde -> Recibira una fecha, deberas buscar la forma de pasarla a un String para la base de datos
    *   LocalDate hasta -> Recibira una fecha, deberas buscar la forma de pasarla a un String para la base de datos
    *   E_EstadoReserva estado -> ACTIVA, CANCELADA, CUMPLIDA
    * */

    /// Obtiene todas las reservas
    private static String OBTENER_RESERVAS  = "SELECT * FROM Reserva WHERE id = ?";

    /// Obtiene las Reservas de un Player en especifico
    private static String OBTENER_RESERVAS_USUARIO = "SELECT * FROM Reserva WHERE idPlayer = ?";

    /// Actualiza el estado de la reserva
    private static String ACTUALIZAR_RESERVA_ESTADO = "UPDATE Reserva SET estado = ? WHERE id = ?";
    //estado -> ACTIVA, CANCELADA, CUMPLIDA

    /// Borra una reserva en base a la id del Mundo
    private static String ELIMINAR_RESERVAS = "DELETE FROM Reserva WHERE idMundo = ?";

    /*----------------------------------------- QUERY RESERVAS -------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------- QUERY PARTIDAS -------------------------------------------------------*/

    /// Inserta un partida
    private static String INSERTAR_PARTIDA = "INSERT INTO Partida (idPlayer, idMundo, inicio, fin, resultado, expGanada, monedasGanadas) VALUES (?, ?, ?, ?, ?, ?, ?)";
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
    private static String OBTENER_PARTIDAS = "SELECT * FROM Partida ";

    /// Obtiene las partidas de un Player en especifico
    private static String OBTENER_PARTIDAS_PLAYER = "SELECT * FROM Partida WHERE idPlayer = ?";

    /// Actualiza resultado de la partida
    private static String ACTUALIZAR_RESULTADO_PARTIDA = "UPDATE Partida SET resultado = ? WHERE idPlayer = ?";
    // resultado -> VICTORIA, DERROTA, ABANDONO

    /*----------------------------------------- QUERY PARTIDAS -------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------- QUERY INVENTARIO -----------------------------------------------------*/

    /// Para agregar un Inventario a un Player
    private static String INSERTAR_INVENTARIO = "INSERT INTO Inventario (idPlayer, slot, idObjeto, nombreObjeto) VALUES (?, ?, NULL, NULL)";
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
    private static String OBTENER_INVENTARIO_PLAYER  = "SELECT * FROM Inventario WHERE id = ?";

    /// Actualiza el slot de un inventario de un player en base al id
    private static String ACTUALIZAR_INVENTARIO_PLAYER = "UPDATE Inventario SET idObjeto = ?, nombreObjeto WHERE slot = ? AND idPlayer = ?";

    /// Borra un inventario de un Player
    private static String ELIMINAR_INVENTARIO = "DELETE FROM Inventario WHERE idPlayer = ?";


    /*----------------------------------------- QUERY INVENTARIO -----------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------- QUERY OBJETOS --------------------------------------------------------*/

    /// ESTOS LOS HAGO YO PORQUE SON UN EMBOLE
    private static String INSERTAR_OBJETOS = "INSERT INTO Objetos (tipoObjeto ,nombreObjeto, nivel, calidad, descripcion, danioBase, consumo) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static String OBTENER_OBJETO_POR_ID = "SELECT * FROM Objeto WHERE id = ?";
    private static String ELIMINAR_OBJETO = "DELETE FROM Objeto WHERE id = ?";

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




    //-///////////////////////////////////////////////////////////////////////////////////////////////////////////////-//
    //-///////////////////////////////////////////////////////////////////////////////////////////////////////////////-//


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///                                              QUERY MUNDO                                                   ///
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////







    //-///////////////////////////////////////////////////////////////////////////////////////////////////////////////-//
    //-///////////////////////////////////////////////////////////////////////////////////////////////////////////////-//


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///                                              QUERY RESERVAS                                                 ///
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////




    //-///////////////////////////////////////////////////////////////////////////////////////////////////////////////-//
    //-///////////////////////////////////////////////////////////////////////////////////////////////////////////////-//


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///                                              QUERY PARTIDAS                                                 ///
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////




    //-///////////////////////////////////////////////////////////////////////////////////////////////////////////////-//
    //-///////////////////////////////////////////////////////////////////////////////////////////////////////////////-//

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///                                              QUERY INVENTARIO                                               ///
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////




    //-///////////////////////////////////////////////////////////////////////////////////////////////////////////////-//
    //-///////////////////////////////////////////////////////////////////////////////////////////////////////////////-//


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///                                              QUERY OBJETOS                                                  ///
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////





    //-///////////////////////////////////////////////////////////////////////////////////////////////////////////////-//
    //-///////////////////////////////////////////////////////////////////////////////////////////////////////////////-//
}
