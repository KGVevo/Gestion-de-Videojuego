import Clases.ClasesObjetos.JDBC;
import Clases.ClasesObjetos.Servidor;
import Enums.*;
import Exceptions.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Servidor servidor = new Servidor();

        try {
            System.out.println(servidor.crearUsuario(1, "Juanceto009", "Juanceto009@gmail.com", "manuelita123", E_Rol.USUARIO));
            System.out.println(servidor.crearUsuario(2, "Manuelita43", "manuelita34@gmail.com", "manuelita1235", E_Rol.ADMINISTRADOR));
            System.out.println(servidor.crearUsuario(3, "Manuel4325", "manuel325@gmail.com", "manuel352", E_Rol.MODERADOR));
        }
        catch (RangoException r)
        {
            System.out.println(r.getMessage());
        }
        catch (TextoContenidoException t)
        {
            System.out.println(t.getMessage());
        }
        catch (ElementoExisteException ee)
        {
            System.out.println(ee.getMessage());
        }
        catch (ContraseñaException c)
        {
            System.out.println(c.getMessage());
        }

        System.out.println(servidor.verTodosUsuario());

        System.out.println(servidor.eliminarUsuario(1));

        System.out.println(servidor.verTodosUsuario());

        System.out.println(servidor.verUsuarioID(3));

        try {
            System.out.println(servidor.crearObjeto(2, "Hueso", 1, E_Calidad.COMUN));
            System.out.println(servidor.crearPocion(4, "Maracuya", 3, E_TipoPocion.CURACION, E_Calidad.COMUN));
            System.out.println(servidor.crearArma(1, "Espada", 1, E_Calidad.LEGENDARIO, 45, E_ClaseAsociada.CUERPO_A_CUERPO, 23));
            System.out.println(servidor.crearArma(3, "Arco", 1, E_Calidad.LEGENDARIO, 35, E_ClaseAsociada.DISTANCIA, 3));
            System.out.println(servidor.crearArma(5, "Varita", 1, E_Calidad.LEGENDARIO, 47, E_ClaseAsociada.MAGO, 25));
        }
        catch (ElementoExisteException ee) {
            System.out.println(ee.getMessage());
        }
        catch (RangoException r) {
            System.out.println(r.getMessage());
        } catch (NombreElementoException ee) {
            System.out.println(ee.getMessage());
        }

        try {
            System.out.println(servidor.crearJugador(1, 2, "JugadorPrueba1", E_ClaseAsociada.CUERPO_A_CUERPO));
            System.out.println(servidor.crearJugador(2, 2, "JugadorPrueba2", E_ClaseAsociada.DISTANCIA));
            System.out.println(servidor.crearJugador(3, 2, "JugadorPrueba3", E_ClaseAsociada.MAGO));
        }
        catch (ElementoExisteException e)
        {
            System.out.println(e.getMessage());
        }
        catch (UserInvalidException u)
        {
            System.out.println(u.getMessage());
        }
        catch (RangoException r)
        {
            System.out.println(r.getMessage());
        }

        System.out.println(servidor.verTodosJugador());

        System.out.println(servidor.eliminarJugador(3));

        System.out.println(servidor.verTodosJugador());

        try {
            System.out.println(servidor.crearEnemigo(3, "Javier", 100, 15, 1, E_TipoDeEnemigo.NORMAL));
            System.out.println(servidor.crearEnemigo(1, "Milicon", 175, 23, 4, E_TipoDeEnemigo.ELITE));
            System.out.println(servidor.crearEnemigo(6, "Sr Maocoli", 500, 35, 10, E_TipoDeEnemigo.JEFE));
        }
        catch (NombreElementoException ne)
        {
            System.out.println(ne.getMessage());
        }
        catch (RangoException r)
        {
            System.out.println(r.getMessage());
        }
        catch (ElementoExisteException ee)
        {
            System.out.println(ee.getMessage());
        }

        System.out.println(servidor.verTodosEnemigo());

        System.out.println(servidor.eliminarEnemigo(1));

        System.out.println(servidor.verTodosEnemigo());

        System.out.println(servidor.verEnemigoID(6));

    }
}