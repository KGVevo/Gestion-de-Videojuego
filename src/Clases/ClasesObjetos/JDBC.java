package Clases.ClasesObjetos;

import Clases.ClasesObjetos.Entidades.*;
import Enums.E_Rol;
import Enums.E_TipoDeEnemigo;
import Exceptions.UserInvalidException;

import java.sql.*;
import java.util.HashSet;

public class JDBC {

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///                                              CONEXION                                                       ///
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///
    private static String URL = "jdbc:sqlite:registro.db";



    ///Para agregar un Usuario a la base de datos(Debere retonar un id que es el que usaremos para guardarlo en la clase)
    private static String INSERTAR_USUARIO = "INSERT INTO Usuario (nombre, gmail, password, rol) VALUES (?, ?, ?, ?)";
    /*  Atributos:  String nombre, gmail, password, rol[USUARIO, MODERADOR , ADMINISTRADOR] -> Pasar a texto para la BDD
    *   Para saber que ROL de Usuario se va a agregar se va a requerir que reciba un -> "int select" por parametros
    *                                                                                     1 = USUARIO, 2 = MODERADOR 3 = ADMINISTRADOR
    */

    private static String OBTENER_USUARIOS = "SELECT * FROM Usuario";
    /*Debe retornar Usuario lo siguiente:
     *  int id,
     *  String nombre,
     *  String gmail,
     *  E_Rol rol[USUARIO, MODERADOR, ADMINISTRADOR],
     *  boolean permitido[En la base de datos está como 0 o 1 por lo tanto para saber deberias verificar si es 1 = true o 0 = false]
     *  E_EstadoCuenta estado[ACTIVA, BANEADA]
     *
     *  -> Recorda que los ENUMs en la base de datos son TEXT, los tenes que pasar de TEXT a ENUM
     * **/

    ///Para obtener la contraseña de un Usuario en base a su gmail
    private static String OBTENER_CONTRASEÑA = "SELECT * FROM Usuario WHERE gmail = ?";

    ///Para cuando le damos el permitido a un Moderador
    private static String ACTUALIZAR_MODERADOR = "UPDATE Usuario SET permitido = ? WHERE idUsuario = ?";
    /*Como debe funcionar:
    *   Buscamos al Usuario con el idUsuario y a ese Usuario le colocamos el 1 al permitido
    *   permitido = ? -> 1 = true
    *
    *       IMPORTANTE: el metodo debe permitir tanto darle el permitido = 1(true) o permitido = 0(false)
    *                       esto se logra teniendo en cuenta que va a llegar un boolean por parametros
    *  */

    /// Obtener el Usuario por Gmail, crucial para no perder la verificacion despues de iniciar sesion
    private static String OBTENER_USUARIO_POR_GMAIL = "SELECT * FROM Usuario WHERE gmail = ?";

    /// Eliminar Usuario por id
    private static String ELIMINAR_USUARIO = "DELETE FROM Usuario WHERE id = ?";

    /// Servira para cuando cambiemos el estado de Usuario a BENEADO
    private static String CAMBIAR_ESTADO_USUARIO = "UPDATE Usuario SET estado = ? WHERE id = ?";








    /// ESTOS NO LOS TOQUES TODAVIA,  ANDA HACIENDO LO DE USUARIO
    //Para agregar un Player de un Usuario
    private static String INSERTAR_JUGADOR = "INSERT INTO Player (idUsuario, nombrePlayer, tipoPlayer) VALUES (?, ?, ?);";
    //Para agregar un Inventario(se debe crear al mismo tiempo que player y conectarlo con el id que retorne Player)
    private static String INSERTAR_INVENTARIO = "INSERT INTO Inventario (idPlayer, idObjeto, nombreObjeto) VALUES (?, ?, ?)";
    //Para agregar Cualquier tipo de Objetos a la base de datos
    private static String INSERTAR_OBJETOS = "INSERT INTO Objetos (tipoObjeto ,nombreObjeto, nivel, calidad, descripcion, danioBase, consumo) VALUES (?, ?, ?, ?, ?, ?, ?)";
    //Para agregar un Enemigo
    private static String INSERTAR_ENEMIGO = "INSERT INTO Enemigo (nombre, vida, danio, nivel, tipoEnemigo) VALUES (?, ?, ?, ?, ?)";
    //Parar agregar un mundo a la BDD
    private static String INSERTAR_MUNDO = "INSERT INTO Mundo (nombre, dificultad, estado, motivo, detalle)  VALUES (?, ?, ?, ?, ?)";
    //Para crear Reservas en la BDD
    private static String INSERTAR_RESERVA = "INSERT INTO Reserva (idPlayer, idMundo, desde, hasta, estado) VALUES (?, ?, ?, ?, ?)";
    //Para guardar las partidas en la BDD
    private static String INSERTAR_PARTIDA = "INSERT INTO Partida (idPlayer, idMundo, inicio, fin, resultado, expGanada, monedasGanadas) VALUES (?, ?, ?, ?, ?, ?, ?)";


    /** Estos se mantienen igual **/
    private static String ACTUALIZAR_LEVEL = "UPDATE Player SET nivel = ? WHERE id = ?";
    private static String ACTUALIZAR_VIDA = "UPDATE Player SET vida = ? WHERE id = ?";
    private static String ACTUALIZAR_EXP_ACUMULADA = "UPDATE Player SET expAcumulada = ? WHERE id = ?";
    private static String ACTUALIZAR_MONEDAS = "UPDATE Player SET monedas = ? WHERE id = ?";


    /// NO TOCAR
    private static String OBTENER_JUGADORES = "SELECT * FROM Player";
    private static String OBTENER_PLAYERS_POR_ID = "SELECT * FROM Player WHERE idUsuario = ?";
    /// NO TOCAR
    private static String OBTENER_ENEMIGOS = "SELECT * FROM Enemigo";
    private static String ELIMINAR_ENEMIGO = "DELETE FROM Enemigo WHERE id = ?";
    /// NO TOCAR
    private static String OBTENER_INVENTARIO_POR_ID = "SELECT * FROM Inventario WHERE idPlayer = ?";
    private static String OBTENER_OBJETO_POR_ID = "SELECT * FROM Objeto WHERE id = ?";



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

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///                                         QUARY USUARIO                                                       ///
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /// Agrega un Usuario a la Base de Datos
    public static int agregarUsuario(String nombre, String gmail, String password, int rol){
        int idAgregado = -1;

        try(Connection connection = DriverManager.getConnection(URL);
            PreparedStatement ps = connection.prepareStatement(INSERTAR_USUARIO)){

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



//    /// Agrega un Jugador a la Base de Datos
//    public static int agregarJugador(int id_usuario, String nombrePlayer, int opcion){
//        int idAgregado = -1;
//
//
//        try(Connection connection = DriverManager.getConnection(URL);
//            PreparedStatement ps = connection.prepareStatement(INSERTAR_JUGADOR, PreparedStatement.RETURN_GENERATED_KEYS)){
//
//            ps.setInt(1, id_usuario);
//            ps.setString(2, nombrePlayer);
//
//            ps.executeUpdate();
//
//            try(ResultSet rs = ps.getGeneratedKeys()){
//                if (rs.next()){
//                    idAgregado = rs.getInt(1);
//                }
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//        return idAgregado;
//    }
//
//    /// Agregar Inventario
//    public static int agregarInventario(int idPlayer, String nombrePlayer){
//        int idAgregado = -1;
//
//        try(Connection connection = DriverManager.getConnection(URL);
//            PreparedStatement ps = connection.prepareStatement(INSERTAR_INVENTARIO, PreparedStatement.RETURN_GENERATED_KEYS)){
//
//            ps.setInt(1, idPlayer);
//            ps.setString(2, nombrePlayer);
//
//            ps.executeUpdate();
//
//            try(ResultSet rs = ps.getGeneratedKeys()){
//                if (rs.next()){
//                    idAgregado = rs.getInt(1);
//                }
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//        return idAgregado;
//    }
//
//    /// Agregar Objeto
//    public static int agregarObjeto(String nombre, int level, String descripcion){
//        int idAgregado = -1;
//
//        try(Connection connection = DriverManager.getConnection(URL);
//            PreparedStatement ps = connection.prepareStatement(INSERTAR_OBJETOS, Statement.RETURN_GENERATED_KEYS)){
//
//            ps.setString(1, nombre);
//            ps.setInt(2, level);
//
//            ps.executeUpdate();
//
//            try(ResultSet rs = ps.getGeneratedKeys()){
//                if (rs.next()){
//                    idAgregado = rs.getInt(1);
//                }
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//        return idAgregado;
//    }
//
//    /// Agrega un enemigo a la Base de Datos
//    public static int agregarEnemigo(String nombreEnemigo, int vida, int level, int danio, E_TipoDeEnemigo tipoDeEnemigo){
//        int idAgregado = -1;
//
//        try(Connection connection = DriverManager.getConnection(URL);
//            PreparedStatement ps = connection.prepareStatement(INSERTAR_ENEMIGO)){
//
//            ps.setString(1, nombreEnemigo);
//            ps.setInt(2, vida);
//            ps.setInt(3, level);
//            ps.setInt(4, danio);
//            ps.setString(5, tipoDeEnemigo.getTipoDeEnemigo());
//
//            ps.executeUpdate();
//
//            try(ResultSet rs = ps.getGeneratedKeys()){
//                if (rs.next()){
//                    idAgregado = rs.getInt(1);
//                }
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//        return idAgregado;
//    }



//    /// Descargar Enemigos de la Base de Datos
//    public static ClaseGenericaAL descargarEnemigos(){
//        ClaseGenericaAL enemigos = new ClaseGenericaAL<>();
//        Enemigo enemigo;
//        try(Connection connection = DriverManager.getConnection(URL);
//            PreparedStatement ps = connection.prepareStatement(MOSTRAR_ENEMIGOS)){
//
//            try(ResultSet rs = ps.executeQuery()){
//                while(rs.next()){
//                    String nombre = rs.getString("nombreEnemigo");
//                    int vida  = rs.getInt("vida");
//                    int danio = rs.getInt("danio");
//                    int nivel = rs.getInt("level");
//                    String tipo = rs.getString("tipo");
//                    E_TipoDeEnemigo tipoE = null;
//                    switch (tipo){
//
//                        case "NORMAL":{
//                            tipoE = E_TipoDeEnemigo.NORMAL;
//                            break;
//                        }
//                        case "ELITE":{
//                            tipoE = E_TipoDeEnemigo.ELITE;
//                            break;
//                        }
//                        case "JEFE":{
//                            tipoE = E_TipoDeEnemigo.JEFE;
//                            break;
//                        }
//                    }
//
//                    enemigo = new Enemigo(nombre, vida, danio, nivel, tipoE);
//                    enemigos.agregarElemento(enemigo);
//                }
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//        return enemigos;
//    }



//    /// Descargar Usuarios de la Base de Datos
//    public static ClaseGenericaHS descargarUsuarios(){
//        ClaseGenericaHS<Usuario> usuarios = new ClaseGenericaHS<>();
//        Usuario usuario;
//
//        try(Connection connection = DriverManager.getConnection(URL);
//            PreparedStatement ps = connection.prepareStatement(OBTENER_USUARIOS)){
//
//            try(ResultSet rs = ps.executeQuery()){
//                while(rs.next()){
//                    int idUsuario = rs.getInt("id");
//                    String nombre = rs.getString("nombre");
//                    String gmail = rs.getString("gmail");
//
//                    usuario = new Usuario(idUsuario, nombre, gmail);
//                    usuarios.agregarElemento(usuario);
//                }
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//        return usuarios;
//    }



//    /// Descargar Jugadores de la Base de Datos
//    public static HashSet<Jugador> descargarJugadores(){
//        HashSet<Jugador> jugadores = new HashSet<>();
//        Jugador jugador;
//
//        try(Connection connection = DriverManager.getConnection(URL);
//            PreparedStatement ps = connection.prepareStatement(MOSTRAR_JUGADORES)){
//
//            try(ResultSet rs = ps.executeQuery()){
//                while (rs.next()){
//                    int idUsuario = rs.getInt("id_Usuario");
//                    String nombreJugador = rs.getString("nombrePlayer");
//                    int expAcumulada = rs.getInt("expAcumulada");
//                    int monedas = rs.getInt("monedas");
//
//                    String tipoJugador = rs.getString("tipoPlayer");
//
//
//                    switch (tipoJugador.toUpperCase()) {
//                        case "GUERRERO":
//                            jugador = new JugadorCuerpoACuerpo(nombreJugador, idUsuario, monedas);
//                            break;
//                        case "MAGO":
//                            jugador = new JugadorMago(nombreJugador, idUsuario, monedas);
//                            break;
//                        case "ARQUERO":
//                            jugador = new JugadorDistancia(nombreJugador, idUsuario, monedas);
//                            break;
//                        default:
//
//                            continue;
//                    }
//
//                    jugadores.add(jugador);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return jugadores;
//    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///                                              OBTENER / MOSTRAR                                              ///
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////


//    /// Muestra los Jugadores de un Usuario
//    public static String mostrarJugadoresPorUsuario(int id){
//        Jugador jugador = null;
//        StringBuilder sb = new StringBuilder();
//
//        try(Connection connection = DriverManager.getConnection(URL);
//            PreparedStatement ps = connection.prepareStatement(OBTENER_PLAYERS_POR_ID)){
//
//            ps.setInt(1, id);
//
//            try(ResultSet rs = ps.executeQuery()) {
//                while (rs.next()) {
//                    int idUsuario = rs.getInt("id_Usuario");
//                    String nombreJugador = rs.getString("nombrePlayer");
//                    int vida = rs.getInt("vida");
//                    int expAcumulada = rs.getInt("expAcumulada");
//                    int monedas = rs.getInt("monedas");
//                    int nivel = rs.getInt("level");
//                    String tipoJugador = rs.getString("tipoPlayer");
//
//
//                    switch (tipoJugador.toUpperCase()) {
//                        case "GUERRERO":
//                            jugador = new JugadorCuerpoACuerpo(nombreJugador, idUsuario, vida, nivel, expAcumulada, monedas);
//                            break;
//                        case "MAGO":
//                            jugador = new JugadorMago(nombreJugador, idUsuario, vida, nivel, expAcumulada, monedas);
//                            break;
//                        case "ARQUERO":
//                            jugador = new JugadorDistancia(nombreJugador, idUsuario, vida, nivel, expAcumulada, monedas);
//                            break;
//                        default: {
//
//                        }
//
//                    }
//                    sb.append(jugador.toString());
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        return sb.toString();
//    }



    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///                                              ACTUALIZAR                                                     ///
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void actualizarLevelJugador(int nuevoLevelJugador, int idJugador){

        try(Connection connection = DriverManager.getConnection(URL);
            PreparedStatement ps = connection.prepareStatement(ACTUALIZAR_LEVEL)){

            ps.setInt(1, nuevoLevelJugador);
            ps.setInt(2, idJugador);

            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

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


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///                                              ELIMINAR                                                       ///
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static int eliminarEnemigo(int id){
        int afectado = -1;

        try(Connection connection = DriverManager.getConnection(URL);
            PreparedStatement ps = connection.prepareStatement(ELIMINAR_ENEMIGO)){

            ps.setInt(1, id);

            afectado = ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

        return  afectado;
    }





    /*public static Objeto obtenerObjetoPorId(int idObjeto) throws RangoException{
        Objeto objeto = null;

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement ps = connection.prepareStatement(MOSTRAR_OBJETO_POR_ID)) {

            ps.setInt(1, idObjeto);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String nombre = rs.getString("nombre");
                    int nivel = rs.getInt("nivel");
                    String descripcion = rs.getString("descripcion");

                    String tipoObjeto = rs.getString("tipoObjeto");

                    switch (tipoObjeto.toUpperCase()) {
                        case "ARMA_GUERRO":
                            objeto = new ArmaCuerpoACuerpo(nombre, nivel);
                            break;
                        case "ARMA_ARQUERO":
                            objeto = new ArmaDistancia(nombre, nivel);
                            break;
                        case "ARMA_MAGO":
                            objeto = new ArmaMago(nombre, nivel);
                            break;
                        case "ARMADURA":
                            objeto = new Armadura(nombre, nivel);
                            break;
                        case "POCION":
                            objeto = new Pocion(nombre, nivel);
                            break;
                        default:
                            continue;
                    }
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return objeto;
    }

    public static Inventario obtenerInventarioPorIdJugador(int idJugador){
        Inventario inventario;

        try(Connection connection = DriverManager.getConnection(URL);
                PreparedStatement ps = connection.prepareStatement(MOSTRAR_INVENTARIO_POR_ID)){

            ps.setInt(1, idJugador);

            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    int idObjeto = rs.getInt("id_objeta");
                }
            }
        }

    }
    */

}
