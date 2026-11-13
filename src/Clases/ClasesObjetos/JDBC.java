package Clases.ClasesObjetos;

import Clases.ClasesObjetos.Entidades.*;
import Clases.ClasesObjetos.ObjetosClass.*;
import Enums.E_ClaseAsociada;
import Enums.E_TipoDeEnemigo;
import Exceptions.RangoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;

public class JDBC {

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///                                              CONEXION                                                       ///
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static String URL = "jdbc:sqlite:sistema.db";


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///                                              QUERYS                                                         ///
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /// QUARY INSERTAR ///

    //Para agregar un Usuario a la base de datos(Debere retonar un id que es el que usaremos para guardarlo en la clase)
    private static String INSERTAR_USUARIO = "INSERT INTO Usuario (nombre, gmail, password) VALUES (?, ?, ?)";

    //Para agregar un Administrador a la base de datos
    private static String INSERTAR_ADMIN = "INSERT INTO Administrador(cuil, idUsuario) VALUES (?, ?)";

    //Para agregar un Player de un Usuario
    private static String INSERTAR_JUGADOR = "INSERT INTO Player (id_usuario, nombrePlayer) VALUES (?, ?);";

    //Para agregar un Inventario(se debe crear al mismo tiempo que player y conectarlo con el id que retorne Player)
    private static String INSERTAR_INVENTARIO = "INSERT INTO Inventario (id_player, nombrePlayer) VALUES (?, ?)";

    //Para agregar Objetos a la base de datos
    private static String INSERTAR_OBJETOS = "INSERT INTO Objetos (nombre, nivel, descripcion) VALUES (?, ?, ?)";

    //Para agregar un Enemigo
    private static String INSERTAR_ENEMIGO = "INSERT INTO Enemigo (nombreEnemigo, vida, level, danio, tipo) VALUES (?, ?, ?, ?, ?)";


    /// QUARY MOSTRAR / OBTENER ///

    private static String MOSTRAR_USUARIOS = "SELECT * FROM Usuario";
    private static String MOSTRAR_ADMINISTRADORES = "SELECT * FROM Administrador";
    private static String MOSTRAR_JUGADORES = "SELECT * FROM Player";
    private static String MOSTRAR_INVENTARIO_POR_ID = "SELECT * FROM Inventario WHERE id_player = ?";
    private static String MOSTRAR_OBJETO_POR_ID = "SELECT * FROM Objetos WHERE id = ?";
    private static String MOSTRAR_ENEMIGOS = "SELECT * FROM Enemigo";

    private static String OBTENER_CONTRASEÑA = "SELECT * FROM Usuario WHERE gmail = ?";
    private static String OBTENER_PLAYERS_POR_ID = "SELECT * FROM Player WHERE id_Usuario = ?;";

    /// QUARY VERIFICAR ///

    private static String VERIFICAR_ADMIN = "SELECT * FROM Administrador WHERE idUsuario = ?;";

    /// QUARY ACTUALIZAR ///

    private static String ACTUALIZAR_ADMIN = "UPDATE Administrador SET permitido = ? WHERE id_usuario = ?";
    private static String ACTUALIZAR_LEVEL = "UPDATE Player SET level = ? WHERE id = ?";
    private static String ACTUALIZAR_VIDA = "UPDATE Player SET vida = ? WHERE id = ?";
    private static String ACTUALIZAR_EXP_ACUMULADA = "UPDATE Player SET expAcumulada = ? WHERE id = ?";
    private static String ACTUALIZAR_MONEDAS = "UPDATE Player SET monedas = ? WHERE id = ?";

    /// QUARY ELIMINAR ///

    private static String ELIMINAR_ENEMIGO = "DELETE FROM Enemigo WHERE id = ?";
    private static String ELIMINAR_USUARIO = "DELETE FROM Usuario WHERE id = ?";


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
    ///                                              AGREGAR                                                        ///
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /// Agrega un Usuario a la Base de Datos
    public static int agregarUsuario(String nombre, String gmail, String password){
        int idAgregado = -1;

        try(Connection connection = DriverManager.getConnection(URL);
            PreparedStatement ps = connection.prepareStatement(INSERTAR_USUARIO, Statement.RETURN_GENERATED_KEYS)){

            ps.setString(1, nombre);
            ps.setString(2, gmail);
            ps.setString(3, password);

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

    /// Agregar un Administrador a la Base de Datos
    public static int agregarAdministrador(String nombre, String gmail, String password, String cuil){
        int idAgregado = -1;

        try(Connection connection = DriverManager.getConnection(URL);
            PreparedStatement ps = connection.prepareStatement(INSERTAR_USUARIO, Statement.RETURN_GENERATED_KEYS)){

            ps.setString(1, nombre);
            ps.setString(2, gmail);
            ps.setString(3, password);

            ps.executeUpdate();

            try(ResultSet rs = ps.getGeneratedKeys()){
                if(rs.next()){
                    idAgregado = rs.getInt(1);
                }
            }

            try(PreparedStatement ps2 = connection.prepareStatement(INSERTAR_ADMIN)){
                ps2.setString(1, cuil);
                ps2.setInt(2, idAgregado);

                ps2.executeUpdate();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return idAgregado;

    }

    /// Agrega un Jugador a la Base de Datos
    public static int agregarJugador(int id_usuario, String nombrePlayer, int opcion){
        int idAgregado = -1;


        try(Connection connection = DriverManager.getConnection(URL);
            PreparedStatement ps = connection.prepareStatement(INSERTAR_JUGADOR, PreparedStatement.RETURN_GENERATED_KEYS)){

            ps.setInt(1, id_usuario);
            ps.setString(2, nombrePlayer);

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

    /// Agregar Inventario
    public static int agregarInventario(int idPlayer, String nombrePlayer){
        int idAgregado = -1;

        try(Connection connection = DriverManager.getConnection(URL);
            PreparedStatement ps = connection.prepareStatement(INSERTAR_INVENTARIO, PreparedStatement.RETURN_GENERATED_KEYS)){

            ps.setInt(1, idPlayer);
            ps.setString(2, nombrePlayer);

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

    /// Agregar Objeto
    public static int agregarObjeto(String nombre, int level, String descripcion){
        int idAgregado = -1;

        try(Connection connection = DriverManager.getConnection(URL);
            PreparedStatement ps = connection.prepareStatement(INSERTAR_OBJETOS, Statement.RETURN_GENERATED_KEYS)){

            ps.setString(1, nombre);
            ps.setInt(2, level);

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

    /// Agrega un enemigo a la Base de Datos
    public static int agregarEnemigo(String nombreEnemigo, int vida, int level, int danio, E_TipoDeEnemigo tipoDeEnemigo){
        int idAgregado = -1;

        try(Connection connection = DriverManager.getConnection(URL);
            PreparedStatement ps = connection.prepareStatement(INSERTAR_ENEMIGO)){

            ps.setString(1, nombreEnemigo);
            ps.setInt(2, vida);
            ps.setInt(3, level);
            ps.setInt(4, danio);
            ps.setString(5, tipoDeEnemigo.getTipoDeEnemigo());

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


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///                                              DESCARGAR                                                      ///
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /// Descargar Enemigos de la Base de Datos
    public static ClaseGenericaAL descargarEnemigos(){
        ClaseGenericaAL enemigos = new ClaseGenericaAL<>();
        Enemigo enemigo;
        try(Connection connection = DriverManager.getConnection(URL);
            PreparedStatement ps = connection.prepareStatement(MOSTRAR_ENEMIGOS)){

            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    String nombre = rs.getString("nombreEnemigo");
                    int vida  = rs.getInt("vida");
                    int danio = rs.getInt("danio");
                    int nivel = rs.getInt("level");
                    String tipo = rs.getString("tipo");
                    E_TipoDeEnemigo tipoE = null;
                    switch (tipo){

                        case "NORMAL":{
                            tipoE = E_TipoDeEnemigo.NORMAL;
                            break;
                        }
                        case "ELITE":{
                            tipoE = E_TipoDeEnemigo.ELITE;
                            break;
                        }
                        case "JEFE":{
                            tipoE = E_TipoDeEnemigo.JEFE;
                            break;
                        }
                    }

                    enemigo = new Enemigo(nombre, vida, danio, nivel, tipoE);
                    enemigos.agregarElemento(enemigo);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return enemigos;
    }

    /// Descargar Usuarios de la Base de Datos
    public static ClaseGenericaHS descargarUsuarios(){
        ClaseGenericaHS<Usuario> usuarios = new ClaseGenericaHS<>();
        Usuario usuario;

        try(Connection connection = DriverManager.getConnection(URL);
            PreparedStatement ps = connection.prepareStatement(MOSTRAR_USUARIOS)){

            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    int idUsuario = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    String gmail = rs.getString("gmail");

                    usuario = new Usuario(idUsuario, nombre, gmail);
                    usuarios.agregarElemento(usuario);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return usuarios;
    }

    /// Descargar Administradores de la Base de Datos
    public static ClaseGenericaHS descargarAdministradores(){
        ClaseGenericaHS<Administrador> administrador = new ClaseGenericaHS<>();
        Administrador admin;

        try(Connection connection = DriverManager.getConnection(URL);
            PreparedStatement ps = connection.prepareStatement(MOSTRAR_ADMINISTRADORES)){

            try(ResultSet rs = ps.executeQuery()){

                while(rs.next()){
                    int idAdministrador = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    String gmail = rs.getString("gmail");
                    String cuil = rs.getString("cuil");

                    admin = new Administrador(idAdministrador, nombre, gmail, cuil);
                    administrador.agregarElemento(admin);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return administrador;
    }

    /// Descargar Jugadores de la Base de Datos
    public static HashSet<Jugador> descargarJugadores(){
        HashSet<Jugador> jugadores = new HashSet<>();
        Jugador jugador;

        try(Connection connection = DriverManager.getConnection(URL);
            PreparedStatement ps = connection.prepareStatement(MOSTRAR_JUGADORES)){

            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    int idUsuario = rs.getInt("id_Usuario");
                    String nombreJugador = rs.getString("nombrePlayer");
                    int expAcumulada = rs.getInt("expAcumulada");
                    int monedas = rs.getInt("monedas");

                    String tipoJugador = rs.getString("tipoPlayer");


                    switch (tipoJugador.toUpperCase()) {
                        case "GUERRERO":
                            jugador = new JugadorCuerpoACuerpo(nombreJugador, idUsuario, monedas);
                            break;
                        case "MAGO":
                            jugador = new JugadorMago(nombreJugador, idUsuario, monedas);
                            break;
                        case "ARQUERO":
                            jugador = new JugadorDistancia(nombreJugador, idUsuario, monedas);
                            break;
                        default:

                            continue;
                    }

                    jugadores.add(jugador);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jugadores;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///                                              OBTENER / MOSTRAR                                              ///
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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

    /// Consigue el id de un Usuario a partir de su gmail
    public static int getUsuarioId(String gmail){
        int id = 0;

        try(Connection connection = DriverManager.getConnection(URL);
            PreparedStatement ps = connection.prepareStatement(OBTENER_CONTRASEÑA)){

            ps.setString(1, gmail);

            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    id = rs.getInt("id");
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return id;
    }

    /// Muestra los Jugadores de un Usuario
    public static String mostrarJugadoresPorUsuario(int id){
        Jugador jugador = null;
        StringBuilder sb = new StringBuilder();

        try(Connection connection = DriverManager.getConnection(URL);
            PreparedStatement ps = connection.prepareStatement(OBTENER_PLAYERS_POR_ID)){

            ps.setInt(1, id);

            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int idUsuario = rs.getInt("id_Usuario");
                    String nombreJugador = rs.getString("nombrePlayer");
                    int vida = rs.getInt("vida");
                    int expAcumulada = rs.getInt("expAcumulada");
                    int monedas = rs.getInt("monedas");
                    int nivel = rs.getInt("level");
                    String tipoJugador = rs.getString("tipoPlayer");


                    switch (tipoJugador.toUpperCase()) {
                        case "GUERRERO":
                            jugador = new JugadorCuerpoACuerpo(nombreJugador, idUsuario, vida, nivel, expAcumulada, monedas);
                            break;
                        case "MAGO":
                            jugador = new JugadorMago(nombreJugador, idUsuario, vida, nivel, expAcumulada, monedas);
                            break;
                        case "ARQUERO":
                            jugador = new JugadorDistancia(nombreJugador, idUsuario, vida, nivel, expAcumulada, monedas);
                            break;
                        default: {

                        }

                    }
                    sb.append(jugador.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return sb.toString();
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///                                              VERIFICAR                                                      ///
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static boolean verificarExistenciaAdmin(String gmail){
        boolean select = false;

        try(Connection connection = DriverManager.getConnection(URL);
            PreparedStatement ps = connection.prepareStatement("SELECT 1 FROM Administrador WHERE gmail = ?;")){

            ps.setString(1, gmail);

            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    select = true;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return select;
    }

    public static boolean verificarAdmin(int id_usuario){
        boolean select = false;

        try(Connection connection = DriverManager.getConnection(URL);
            PreparedStatement ps = connection.prepareStatement(VERIFICAR_ADMIN)){

            ps.setInt(1, id_usuario);

            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    select = true;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return select;
    }


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