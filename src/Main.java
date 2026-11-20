import Clases.ClasesObjetos.*;
import Clases.ClasesObjetos.Entidades.Jugador;
import Clases.ClasesObjetos.Entidades.JugadorMago;
import Clases.ClasesObjetos.SistemaDeJuego.Mundo;
import Enums.*;
import Exceptions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;




public class Main {
    public static void main(String[] args) throws RangoException {
        int select = 0;
        int flag = 1;


        Servidor servidor = new Servidor();



        System.out.println(JDBC.probarConexcion());

        servidor.descargarUsuarios();
        System.out.println(servidor.mostrarUsuarios());


        /// WHILE DE REGISTER / INICIAR SESION
        while(flag == 1){

            try {

                System.out.println(Menu.mostrarInformacionRegister());

                select = Consola.pedirInt(">>> ");

                switch (select) {
                    /// Case 1: Registrar Usuario
                    case 1:{
                        System.out.println(Menu.elegirTipoDeUsuario());
                        select = Consola.pedirInt(">>> ");

                        ///Switch de Verificacion
                        switch (select) {
                            case 1:{
                                try {
                                    //Menu Register
                                    System.out.println(Menu.registrarUsuario());

                                    String nombre = Consola.pedirString("1.");
                                    String gmail = Consola.pedirString("2.");
                                    Servidor.verificarFormatoGmail(gmail);
                                    String password = Consola.pedirString("3.");

                                    servidor.agregarUsuario(nombre, gmail, password, select);

                                }catch (GmailInvalidoException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }catch (UserInvalidException e){
                                    System.out.println(e.getMessage());
                                    break;
                                }

                                System.out.println(Menu.registroExistoso());

                            }break;
                            case 2:{
                                try {
                                    System.out.println(Menu.registrarUsuario());
                                    String nombre = Consola.pedirString("1.");
                                    String gmail = Consola.pedirString("2.");
                                    Servidor.verificarFormatoGmail(gmail);
                                    String password = Consola.pedirString("3.");

                                    servidor.agregarUsuario(nombre, gmail, password, select);
                                }catch (GmailInvalidoException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }catch (UserInvalidException e){
                                    System.out.println(e.getMessage());
                                    break;
                                }

                                System.out.println(Menu.registroExistoso());

                            }break;
                            case 3:{
                                try {
                                    System.out.println(Menu.registrarUsuario());
                                    String nombre = Consola.pedirString("1.");
                                    String gmail = Consola.pedirString("2.");
                                    Servidor.verificarFormatoGmail(gmail);
                                    String password = Consola.pedirString("3.");

                                    servidor.agregarUsuario(nombre, gmail, password, select);
                                }catch (GmailInvalidoException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }catch (UserInvalidException e){
                                    System.out.println(e.getMessage());
                                    break;
                                }

                                System.out.println(Menu.registroExistoso());

                            }break;
                            case 4:{


                            }break;
                        }
                    }break;

                    /// Case 2: Iniciar Sesion
                    case 2:{
                        try {
                            Usuario user = null;
                            System.out.println(Menu.iniciarSesion());
                            String gmail =  Consola.pedirString("1.");
                            String password = Consola.pedirString("2.");

                            user = servidor.confirmarExistenciaCuenta(gmail, password);

                            /// Definimos el Menu al que vamos a acceder
                            if(user.getRol() == E_Rol.USUARIO){
                                System.out.println(Menu.inicioExitosoUsuario());
                                select = 1;
                            }else if (user.getRol() == E_Rol.MODERADOR){
                                System.out.println(Menu.inicioExitosoModerador());
                                select = 2;
                            }else if (user.getRol() == E_Rol.ADMINISTRADOR) {
                                System.out.println(Menu.inicioAdministrador());
                                select = 3;
                            }

                            /// WHILE DE MENUS
                            while(flag == 1 || user != null){

                                if((user instanceof Moderador && ((Moderador) user).getPermitido() == false)){
                                    System.out.println(Menu.sinPermisos());
                                    user = null;
                                    break;
                                }

                                if (user instanceof Administrador){
                                    if (servidor.verificacionExistenciaAdmin()){
                                        user = null;
                                        System.out.println(Menu.sinPermisos());
                                        break;
                                    }
                                }

                                try {
                                    switch (select){
                                        /// CASE 1: Menu Usuarios
                                        case 1:{
                                            int menuUser;
                                            System.out.println(Menu.menuUsuario());

                                            menuUser = Consola.pedirInt(">>> ");

                                            switch (menuUser){
                                                // Case 1: Agregar Personaje:
                                                case 1:{
                                                    System.out.println(Menu.menuSeleccionDeClase());
                                                    menuUser = Consola.pedirInt(">>> ");

                                                    if (menuUser == 1) {
                                                        Menu.menuRegisterPersonaje();
                                                        String nombrePlayer = Consola.pedirString(">>> ");
                                                        servidor.agregarJugador(nombrePlayer, user.getIdUsuario(), menuUser);
                                                        System.out.println(Menu.JugadorRegistrado());
                                                        break;
                                                    }else if (menuUser == 2){
                                                        Menu.menuRegisterPersonaje();
                                                        String nombrePlayer = Consola.pedirString(">>> ");
                                                        servidor.agregarJugador(nombrePlayer, user.getIdUsuario(), menuUser);
                                                        System.out.println(Menu.JugadorRegistrado());
                                                        break;
                                                    }else if (menuUser == 3){
                                                        Menu.menuRegisterPersonaje();
                                                        String nombrePlayer = Consola.pedirString(">>> ");
                                                        servidor.agregarJugador(nombrePlayer, user.getIdUsuario(), menuUser);
                                                        System.out.println(Menu.JugadorRegistrado());
                                                        break;

                                                    }

                                                }
                                                break;
                                                // Case 2: Mostrar Jugadores de Usuario
                                                case 2:{
                                                    System.out.println(servidor.mostrarJugadoresDeUsuario(user.getIdUsuario()));

                                                }
                                                break;
                                                // Case 3: Mostrar Mundos Disponibles
                                                case 3:{
                                                    System.out.println(servidor.listarMundosDisponibles());
                                                }
                                                break;
                                                // Case 4: Reservar Mundo
                                                case 4:{
                                                    try {
                                                        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                                        System.out.println(Menu.seleccionMundoReserva());
                                                        int idJugador = Consola.pedirInt("1->>> ");
                                                        int idMundo = Consola.pedirInt("2->>> ");
                                                        String inicio = Consola.pedirString("3->>> ");
                                                        String fin = Consola.pedirString("4->>> ");

                                                        LocalDate inicioF = LocalDate.parse(inicio, formato);
                                                        LocalDate finF = LocalDate.parse(fin, formato);

                                                        servidor.mostrarJugadoresDeUsuario(user.getIdUsuario());
                                                        System.out.println("\n");

                                                        Jugador player = servidor.obtenerJugador(idJugador);
                                                        Mundo mundo = servidor.obtenerMundo(idMundo);

                                                        servidor.reservarMundo(player, mundo , inicioF, finF);



                                                    }catch (DateTimeParseException e){
                                                        System.out.println("| !! FORMATO DE FECHA INCORRECTO !! |");
                                                    }catch (ReservaInvalidaException e){
                                                        System.out.println(e.getMessage());
                                                    }



                                                }
                                                break;
                                                //  Case 5: Ver mis Reservas
                                                case 5:{
                                                    System.out.println(servidor.mostrarJugadoresDeUsuario(user.getIdUsuario()));

                                                    System.out.println(Menu.verReservasJugador());
                                                    int idPlayer = Consola.pedirInt("1->>> ");

                                                    Jugador jugador = servidor.obtenerJugador(idPlayer);

                                                    System.out.println(servidor.listarReservasDeJugador(jugador));

                                                }
                                                break;
                                                // Case 6: Ver mis Partidas
                                                case 6:{
                                                    System.out.println(servidor.mostrarJugadoresDeUsuario(user.getIdUsuario()));
                                                    System.out.println(Menu.partidasJugador());
                                                    int idPlayer  = Consola.pedirInt("1->>> ");

                                                    Jugador jugador = servidor.obtenerJugador(idPlayer);

                                                    System.out.println(servidor.listarPartidasDeJugador(jugador));
                                                }
                                                break;
                                                // Case 7: Cerrar Sesion
                                                case 7:{
                                                    user = null;
                                                    System.out.println("Cerrando sesion...");
                                                }
                                                break;


                                            }





                                        }break;
                                        /// CASE 2: Menu Moderador
                                        case 2:{

                                        }break;
                                        /// CASE 3: Menu Administrador
                                        case 3:{
                                            while(flag == 1 || user != null){
                                                int menuAdmin;
                                                System.out.println(Menu.menuAdministrador());

                                                // Seleccion del Menu
                                                menuAdmin = Consola.pedirInt(">>> ");

                                                /// Switch del Menu del Administrador Seleccion
                                                switch (menuAdmin) {
                                                    // Case 1: Peticiones de Moderador
                                                    case 1:{
                                                        System.out.println(servidor.mostrarPeticionesAdministracion());
                                                        System.out.println(Menu.menuPeticionesModerador());
                                                        menuAdmin = Consola.pedirInt(">>> ");
                                                        System.out.println(Menu.menuPermitirUsuario());


                                                        if(menuAdmin == 1){
                                                            String gmailVerificacion = Consola.pedirString(">>> ");
                                                            Servidor.verificarFormatoGmail(gmailVerificacion);

                                                            if(servidor.verificarModerador(user, gmailVerificacion)){
                                                                System.out.println(Menu.permisosOtorgados());
                                                            }

                                                        }else{
                                                            break;
                                                        }

                                                    }
                                                    break;
                                                    // Case 2: Banear o Desbanear Usuarios
                                                    case 2:{
                                                        System.out.println(Menu.menuAdministrarUsuarios());
                                                        menuAdmin = Consola.pedirInt(">>> ");
                                                        
                                                        if (menuAdmin == 1){
                                                            System.out.println(servidor.mostrarUsuarios());
                                                        }else if(menuAdmin == 2){
                                                            System.out.println(Menu.pedirIdUser());

                                                            int idUserBan =  Consola.pedirInt(">>> ");
                                                            Usuario baneado = servidor.obtenerUsuario(idUserBan);

                                                            if(baneado != null){
                                                                System.out.println(Menu.confirmacionBan(baneado));
                                                                menuAdmin = Consola.pedirInt(">>> ");

                                                                if (menuAdmin == 1){
                                                                    if (servidor.banearUsuario(user, baneado.getIdUsuario())){
                                                                        System.out.println(Menu.operacionExitosa());
                                                                        break;
                                                                    }
                                                                }
                                                            }

                                                        }else if (menuAdmin == 3){
                                                            System.out.println(Menu.pedirIdUser());

                                                            int idUserDesban =  Consola.pedirInt(">>> ");
                                                            Usuario desbaneado = servidor.obtenerUsuario(idUserDesban);

                                                            if(desbaneado != null){
                                                                System.out.println(Menu.confirmacionDesban(desbaneado));
                                                                menuAdmin = Consola.pedirInt(">>> ");

                                                                if (menuAdmin == 1){
                                                                    if (servidor.desbanearUsuario(user, desbaneado.getIdUsuario())){
                                                                        System.out.println(Menu.operacionExitosa());
                                                                        break;
                                                                    }
                                                                }

                                                            }
                                                        }else if (menuAdmin == 4){
                                                            break;
                                                        }

                                                    }
                                                    break;
                                                    // Case 3: Ver todos los Jugadores
                                                    case 3:{
                                                        System.out.println(servidor.mostrarJugadores());
                                                    }
                                                    break;
                                                    // Case 4: Mostrar todas las Reservas
                                                    case 4:{
                                                        System.out.println(servidor.mostrarReservas());
                                                    }
                                                    break;
                                                    // Case 5: Gestionar Mundos
                                                    case 5:{
                                                        int gestionMundo;
                                                        System.out.println(Menu.gestionMundo());
                                                        gestionMundo = Consola.pedirInt(">>> ");

                                                        /// SWITCH Sobre la gestion del Mundo
                                                        switch (gestionMundo){
                                                            // Case 1: Creador de Objetos
                                                            case 1:{
                                                                System.out.println(Menu.gestionMundoObjetos());
                                                                gestionMundo = Consola.pedirInt(">>> ");

                                                                if (gestionMundo == 1){
                                                                    System.out.println(Menu.creacionArma());
                                                                    String nombre = Consola.pedirString("1-> ");
                                                                    int nivel = Consola.pedirInt("2-> ");
                                                                    int danio = Consola.pedirInt("3-> ");
                                                                    int calidad = Consola.pedirInt("4-> ");
                                                                    E_Calidad eCalidad = null;
                                                                    int consumo = Consola.pedirInt("5-> ");

                                                                    if(calidad > 5 || calidad < 1){
                                                                        System.out.println("| !! ERROR: Calidad incorrecta !! |");
                                                                        break;
                                                                    }

                                                                    switch (calidad){
                                                                        case 1: eCalidad = E_Calidad.COMUN; break;
                                                                        case 2: eCalidad = E_Calidad.RARO; break;
                                                                        case 3: eCalidad = E_Calidad.EPICO; break;
                                                                        case 4: eCalidad = E_Calidad.LEGENDARIO; break;
                                                                        case 5: eCalidad = E_Calidad.MITICO; break;
                                                                    }

                                                                    servidor.agregarArma(nombre,nivel,danio, eCalidad,consumo, E_ClaseAsociada.CUERPO_A_CUERPO);

                                                                } else if (gestionMundo == 2) {
                                                                    System.out.println(Menu.creacionArma());
                                                                    String nombre = Consola.pedirString("1-> ");
                                                                    int nivel = Consola.pedirInt("2-> ");
                                                                    int danio = Consola.pedirInt("3-> ");
                                                                    int calidad = Consola.pedirInt("4-> ");
                                                                    E_Calidad eCalidad = null;
                                                                    int consumo = Consola.pedirInt("5-> ");

                                                                    if(calidad > 5 || calidad < 1){
                                                                        System.out.println("| !! ERROR: Calidad incorrecta !! |");
                                                                        break;
                                                                    }

                                                                    switch (calidad){
                                                                        case 1: eCalidad = E_Calidad.COMUN; break;
                                                                        case 2: eCalidad = E_Calidad.RARO; break;
                                                                        case 3: eCalidad = E_Calidad.EPICO; break;
                                                                        case 4: eCalidad = E_Calidad.LEGENDARIO; break;
                                                                        case 5: eCalidad = E_Calidad.MITICO; break;
                                                                    }

                                                                    servidor.agregarArma(nombre,nivel,danio, eCalidad,consumo, E_ClaseAsociada.DISTANCIA);

                                                                }else if(gestionMundo == 3){
                                                                    System.out.println(Menu.creacionArma());
                                                                    String nombre = Consola.pedirString("1-> ");
                                                                    int nivel = Consola.pedirInt("2-> ");
                                                                    int danio = Consola.pedirInt("3-> ");
                                                                    int calidad = Consola.pedirInt("4-> ");
                                                                    E_Calidad eCalidad = null;
                                                                    int consumo = Consola.pedirInt("5-> ");

                                                                    if(calidad > 5 || calidad < 1){
                                                                        System.out.println("| !! ERROR: Calidad incorrecta !! |");
                                                                        break;
                                                                    }

                                                                    switch (calidad){
                                                                        case 1: eCalidad = E_Calidad.COMUN; break;
                                                                        case 2: eCalidad = E_Calidad.RARO; break;
                                                                        case 3: eCalidad = E_Calidad.EPICO; break;
                                                                        case 4: eCalidad = E_Calidad.LEGENDARIO; break;
                                                                        case 5: eCalidad = E_Calidad.MITICO; break;
                                                                    }

                                                                    servidor.agregarArma(nombre,nivel,danio, eCalidad,consumo, E_ClaseAsociada.MAGO);


                                                                }else if(gestionMundo == 4){
                                                                    System.out.println(Menu.creacionDePocion());
                                                                    String nombre = Consola.pedirString("1-> ");
                                                                    int nivel = Consola.pedirInt("2-> ");
                                                                    int efecto = Consola.pedirInt("3-> ");
                                                                    int tipoPocion = Consola.pedirInt("4-> ");
                                                                    int calidad =  Consola.pedirInt("5-> ");

                                                                    E_TipoPocion eTipoPocion = null;
                                                                    E_Calidad eCalidad = null;

                                                                    if(calidad > 5 || calidad < 1 || tipoPocion < 1 || tipoPocion > 5){
                                                                        System.out.println("| !! ERROR: Calidad/Pocion incorrecta !! |");
                                                                        break;
                                                                    }

                                                                    switch (tipoPocion){
                                                                        case 1: eTipoPocion = E_TipoPocion.CURACION; break;
                                                                        case 2: eTipoPocion = E_TipoPocion.MANA; break;
                                                                        case 3: eTipoPocion = E_TipoPocion.ENERGIA; break;
                                                                        case 4: eTipoPocion = E_TipoPocion.DANIO; break;
                                                                        case 5: eTipoPocion = E_TipoPocion.CRITICO; break;
                                                                    }


                                                                    switch (calidad){
                                                                        case 1: eCalidad = E_Calidad.COMUN; break;
                                                                        case 2: eCalidad = E_Calidad.RARO; break;
                                                                        case 3: eCalidad = E_Calidad.EPICO; break;
                                                                        case 4: eCalidad = E_Calidad.LEGENDARIO; break;
                                                                        case 5: eCalidad = E_Calidad.MITICO; break;
                                                                    }


                                                                }else if(gestionMundo == 5){
                                                                    System.out.println(servidor.mostrarObjetoTodos());

                                                                }else if(gestionMundo == 6){
                                                                    System.out.println("|Volviendo...|");
                                                                    break;
                                                                }

                                                            }
                                                            break;
                                                            // Case 2: Gestion de Enemigos
                                                            case 2:{
                                                                System.out.println(Menu.gestionMundoEnemigos());
                                                                gestionMundo = Consola.pedirInt("1-> ");

                                                                if (gestionMundo == 1){
                                                                    System.out.println(Menu.agregarEnemigo());
                                                                    String nombre = Consola.pedirString("1-> ");
                                                                    int vida = Consola.pedirInt("2-> ");
                                                                    int danio = Consola.pedirInt("3-> ");
                                                                    int nivel = Consola.pedirInt("4-> ");
                                                                    int tipoEnemigo = Consola.pedirInt("5-> ");

                                                                    if(tipoEnemigo < 1 || tipoEnemigo > 3){
                                                                        System.out.println("| !! ERROR: Tipo de enemigo incorrecta !! |");
                                                                        break;
                                                                    }

                                                                    servidor.agregarEnemigo(nombre,vida,danio,nivel,tipoEnemigo);

                                                                }else if(gestionMundo == 2){

                                                                    System.out.println(Menu.eliminarEnemigo());
                                                                    int id = Consola.pedirInt(">>> ");

                                                                    if(servidor.eliminarEnemigo(id)){
                                                                        System.out.println(Menu.creadoConExito());
                                                                    }


                                                                }else if(gestionMundo == 3){
                                                                    System.out.println(servidor.mostrarEnemigos());

                                                                }else if(gestionMundo == 4){
                                                                    System.out.println("|Regresando...|");
                                                                    break;
                                                                }


                                                            }
                                                            break;
                                                            // Case 3: Gestion de Mundos
                                                            case 3:{
                                                                System.out.println(Menu.menuMundo());
                                                                menuAdmin = Consola.pedirInt(">>> ");

                                                                if (menuAdmin == 1){
                                                                    System.out.println(Menu.agregarMundo());

                                                                    String nombre = Consola.pedirString("1-> ");
                                                                    int dificultad = Consola.pedirInt("2-> ");

                                                                    if(dificultad < 1 || dificultad > 3){
                                                                        System.out.println("|>>> !! Dificultad desconocida !! <<<|");
                                                                        break;
                                                                    }

                                                                    if(servidor.agregarMundo(user, nombre,  dificultad)){
                                                                        System.out.println(Menu.creadoConExito());
                                                                        break;
                                                                    }

                                                                }else if(menuAdmin == 2){
                                                                    System.out.println(Menu.modificarMundo());
                                                                    int modificarMundo = Consola.pedirInt(">>> ");


                                                                    if(modificarMundo == 1){
                                                                        System.out.println(Menu.modificarEstadoMundo());
                                                                        int idMundo = Consola.pedirInt("1-> ");
                                                                        int motivo = Consola.pedirInt("2-> ");
                                                                        String detalle =  Consola.pedirString("3-> ");

                                                                        E_MotivoMundo eMotivoMundo = null;

                                                                        if(motivo < 1 || motivo > 3){
                                                                            System.out.println(" !! ERROR: El motivo no existe !! ");
                                                                            break;
                                                                        }

                                                                        switch (motivo){
                                                                            case 1: eMotivoMundo = E_MotivoMundo.MANTENIMIENTO; break;
                                                                            case 2: eMotivoMundo = E_MotivoMundo.ERROR_TECNICO; break;
                                                                            case 3: eMotivoMundo = E_MotivoMundo.MANTENIMIENTO; break;
                                                                        }

                                                                        servidor.marcarMundoNoDisponible(user, idMundo, eMotivoMundo, detalle);
                                                                    } else if (modificarMundo == 2) {
                                                                        System.out.println(Menu.modificarEstadoMundo());
                                                                        int idMundo = Consola.pedirInt(">>> ");

                                                                        if(servidor.marcarMundoDisponible(user, idMundo)){
                                                                            System.out.println("| !! Se ha cambiado el estado del mundo !! |");
                                                                            break;
                                                                        }
                                                                    }else{
                                                                        System.out.println("|Saliendo...");
                                                                        break;
                                                                    }


                                                                }else if(menuAdmin == 3){
                                                                    System.out.println(servidor.listarMundos());

                                                                }else if(menuAdmin == 4){
                                                                    System.out.println("|Volviendo...");
                                                                    break;
                                                                }


                                                            }
                                                            break;
                                                            // Case 4: Volver al inicio
                                                            case 4:{
                                                                System.out.println("Saliendo...");

                                                            }
                                                            break;
                                                            default:{
                                                                System.out.println(" >>>| !! Opcion incorrecta !!<<< |");
                                                            }
                                                        }


                                                    }
                                                    break;
                                                    // Case 6: Cerrar Sesion
                                                    case 6:{
                                                        System.out.println(Menu.cerrarSesion());
                                                        user = null;
                                                    }
                                                    break;
                                                    default:{
                                                        System.out.println("|>>> !! OPCION INCORRECTA !! <<<|\n\n");
                                                    }


                                                }

                                            }




                                        }break;



                                    }
                                }catch (UserInvalidException e){
                                    System.out.println(e.getMessage());
                                }catch (GmailInvalidoException e){
                                    System.out.println(e.getMessage());
                                }


                            }


                        }catch (UserInvalidException e){
                            System.out.println(e.getMessage());
                        }catch (GmailInvalidoException e){
                            System.out.println(e.getMessage());
                        }

                    }break;

                    /// Case 3: Salir del Sistema
                    case 3:{
                        System.out.println(Menu.salirDelSistema());
                        flag = 0;
                    }break;

                    default:{
                        System.out.println("ERROR: No existe esa opcion...\n");
                        break;
                    }

                }

            }catch (InputMismatchException e){

                System.out.println("|=======================================|\n" +
                                   "|ERROR: !! Ingrese una opcion valida !! |\n" +
                                   "|=======================================|\n");
            }

        }









    }
}