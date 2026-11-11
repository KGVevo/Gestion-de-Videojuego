package Clases.ClasesObjetos;

import java.sql.*;

public class JDBC {

    private static String URL = "jdbc:sqlite:sistema_jdbc.db";

    ///Para agregar un Usuario a la base de datos(Debere retonar un id que es el que usaremos para guardarlo en la clase)
    private static String INSERTAR_USUARIO = "INSERT INTO Usuario (nombre, gmail, password) VALUES (?, ?, ?);";

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

    private static String MOSTRAR_INVENTARIO = "SELECT * FROM Inventario;";

    private static String MOSTRAR_OBJETOS = "SELECT * FROM Objetos;";

    private static String MOSTRAR_ENEMIGOS = "SELECT * FROM Enemigo;";

    public static String probarConexcion(){
        String conexion = "|----> Error <----|";

        try(Connection connection = DriverManager.getConnection(URL)){
            conexion =  "|----> Conectado con Exito <----|";

        }catch (SQLException e){
            e.printStackTrace();
        }


        return conexion;
    }

    public static int agregarUsuario(String nombre, String gmail, String password){
        int idAgregado = -1;

        try(Connection connection = DriverManager.getConnection(URL);
                PreparedStatement ps = connection.prepareStatement(INSERTAR_USUARIO)){

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
    
}
