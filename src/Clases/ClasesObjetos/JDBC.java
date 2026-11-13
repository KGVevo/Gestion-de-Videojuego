package Clases.ClasesObjetos;

import Clases.ClasesObjetos.Entidades.Jugador;
import Clases.ClasesObjetos.Entidades.JugadorCuerpoACuerpo;
import Clases.ClasesObjetos.Entidades.JugadorDistancia;
import Clases.ClasesObjetos.Entidades.JugadorMago;
import Clases.ClasesObjetos.ObjetosClass.*;
import Exceptions.RangoException;

import java.sql.*;
import java.util.HashSet;

public class JDBC {

    private static String URL = "jdbc:sqlite:sistema_jdbc.db";

    ///Para agregar un Usuario a la base de datos(Debere retonar un id que es el que usaremos para guardarlo en la clase)
    private static String INSERTAR_USUARIO = "INSERT INTO Usuario (nombre, gmail, password) VALUES (?, ?, ?)";

    ///Para agregar un Administrador a la base de datos
    private static String INSERTAR_ADMIN = "INSERT INTO Administrador(cuil, idUsuario) VALUES (?, ?)";

    ///Para agregar un Player de un Usuario
    private static String INSERTAR_JUGADOR = "INSERT INTO Player (id_usuario, nombrePlayer, vida, level, expacumulada, monedas) VALUES (?, ?, ?, ?, ?, ?);";

    ///Para agregar un Inventario(se debe crear al mismo tiempo que player y conectarlo con el id que retorne Player)
    private static String INSERTAR_INVENTARIO = "INSERT INTO Inventario (id_player, nombrePlayer) VALUES (?, ?);";

    ///Para agregar Objetos a la base de datos
    private static String INSERTAR_OBJETOS = "INSERT INTO Objetos (nombre, nivel, descripcion) VALUES (?, ?, ?);";

    ///Para agregar un Enemigo
    private static String INSERTAR_ENEMIGO = "INSERT INTO Enemigo (nombreEnemigo, vida, level) VALUES (?, ?, ?);";




    private static String MOSTRAR_USUARIOS = "SELECT * FROM Usuario;";

    private static String MOSTRAR_JUGADORES = "SELECT * FROM Player;";

    private static String MOSTRAR_INVENTARIO_POR_ID = "SELECT * FROM Inventario WHERE id_player = ?;";

    private static String MOSTRAR_OBJETO_POR_ID = "SELECT * FROM Objetos WHERE id = ?;";

    private static String MOSTRAR_ENEMIGOS = "SELECT * FROM Enemigo;";

    private static String ACTUALIZAR_ADMIN = "UPDATE Administrador SET permitido = ? WHERE id_usuario = ?;";

    private static String ACTUALIZAR_LEVEL = "UPDATE Player SET level = ? WHERE id = ?;";

    private static String ACTUALIZAR_VIDA = "UPDATE Player SET vida = ? WHERE id = ?;";

    private static String ACTUALIZAR_EXP_ACUMULADA = "UPDATE Player SET expAcumulada = ? WHERE id = ?;";

    private static String ACTUALIZAR_MONEDAS = "UPDATE Player SET monedas = ? WHERE id = ?;";




    public static String probarConexcion(){
        String conexion = "|----> Error <----|";

        try(Connection connection = DriverManager.getConnection(URL)){
            conexion =  "|----> Conectado con Exito <----|";

        }catch (SQLException e){
            e.printStackTrace();
        }


        return conexion;
    }


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




    public static int agregarJugador(int id_usuario, String nombrePlayer, int vida, int level, int expacumulada, int monedas){
        int idAgregado = -1;

        try(Connection connection = DriverManager.getConnection(URL);
                PreparedStatement ps = connection.prepareStatement(INSERTAR_JUGADOR)){

            ps.setInt(1, id_usuario);
            ps.setString(2, nombrePlayer);
            ps.setInt(3, vida);
            ps.setInt(4, level);
            ps.setInt(5, expacumulada);
            ps.setInt(6, monedas);

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

    public static int agregarInventario(int idPlayer, String nombrePlayer){
        int idAgregado = -1;

        try(Connection connection = DriverManager.getConnection(URL);
                PreparedStatement ps = connection.prepareStatement(INSERTAR_INVENTARIO)){

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

    public static int agregarObjeto(String nombre, int level, String descripcion){
        int idAgregado = -1;

        try(Connection connection = DriverManager.getConnection(URL);
                PreparedStatement ps = connection.prepareStatement(INSERTAR_OBJETOS)){

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

    public static int agregarEnemigo(String nombreEnemigo, int vida, int level){
        int idAgregado = -1;

        try(Connection connection = DriverManager.getConnection(URL);
                PreparedStatement ps = connection.prepareStatement(INSERTAR_ENEMIGO)){

            ps.setString(1, nombreEnemigo);
            ps.setInt(2, vida);
            ps.setInt(3, level);

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

    public static HashSet<Usuario> descargarUsuarios(){
        HashSet<Usuario> usuarios = new HashSet<>();
        Usuario usuario;

        try(Connection connection = DriverManager.getConnection(URL);
                PreparedStatement ps = connection.prepareStatement(MOSTRAR_USUARIOS)){

            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    int idUsuario = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    String gmail = rs.getString("gmail");

                    usuario = new Usuario(idUsuario, nombre, gmail);
                    usuarios.add(usuario);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return usuarios;
    }


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
                            jugador = new JugadorCuerpoACuerpo(nombreJugador, idUsuario, expAcumulada, monedas);
                            break;
                        case "MAGO":
                            jugador = new JugadorMago(nombreJugador, idUsuario, expAcumulada, monedas);
                            break;
                        case "ARQUERO":
                            jugador = new JugadorDistancia(nombreJugador, idUsuario, expAcumulada, monedas);
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
