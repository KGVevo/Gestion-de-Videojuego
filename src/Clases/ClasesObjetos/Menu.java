package Clases.ClasesObjetos;

import java.awt.*;

public class Menu {

    public static String mostrarInformacionRegister(){
        StringBuilder sb = new StringBuilder();

        sb.append("|==============================|\n");
        sb.append("|          Bienvenido          |\n");
        sb.append("|==============================|\n");
        sb.append("|-> 1.|Registrar               |\n");
        sb.append("|-> 2.|Iniciar Sesion          |\n");
        sb.append("|-> 3.|Salir del sistema       |\n");
        sb.append("|==============================|\n");

        sb.append("\n");
        return sb.toString();
    }


    public static String elegirTipoDeUsuario(){
        StringBuilder sb = new StringBuilder();

        sb.append("|================================|\n");
        sb.append("|        Tipo de Usuario         |\n");
        sb.append("|================================|\n");
        sb.append("|-> 1.|Registrar Usuario         |\n");
        sb.append("|-> 2.|Registrar Moderador       |\n");
        sb.append("|-> 3.|Registrar Administrador   |\n");
        sb.append("|-> 4.|Volver al Menu            |\n");
        sb.append("|================================|\n");
        sb.append("\n");


        return sb.toString();
    }


    public static String registrarUsuario(){
        StringBuilder sb = new StringBuilder();

        sb.append("|================================|\n");
        sb.append("|           Registrar            |\n");
        sb.append("|================================|\n");
        sb.append("|Ingrese los siguientes Datos:   |\n");
        sb.append("|-> 1.|Nombre de Usuario         |\n");
        sb.append("|-> 2.|Gmail                     |\n");
        sb.append("|-> 3.|Contraseña                |\n");
        sb.append("|================================|\n");

        sb.append("\n");

        return sb.toString();
    }


    public static String iniciarSesion(){
        StringBuilder sb = new StringBuilder();

        sb.append("|=============================|\n");
        sb.append("|        Iniciar Sesion       |\n");
        sb.append("|=============================|\n");
        sb.append("|Ingrese los datos:           |\n");
        sb.append("|-> 1.|Gmail                  |\n");
        sb.append("|-> 2.|Contraseña             |\n");
        sb.append("|=============================|\n");

        sb.append("\n");

        return sb.toString();
    }








    public static String mostrarInformacionUsuario(String gmailUser){
        StringBuilder sb = new StringBuilder();
        return  sb.toString();
    }


    public static String registroExistoso(){
        StringBuilder sb = new StringBuilder();
        sb.append("|=========================|\n");
        sb.append("|  Registrado con Exito   |\n");
        sb.append("|=========================|\n");

        return sb.toString();
    }

    public static String inicioExitosoUsuario(){
        StringBuilder sb = new StringBuilder();
        sb.append("|===================================|\n");
        sb.append("|  Has Iniciado Sesion como Usuario |\n");
        sb.append("|===================================|\n");

        return sb.toString();
    }

    public static String inicioExitosoModerador(){
        StringBuilder sb = new StringBuilder();
        sb.append("|=====================================|\n");
        sb.append("|  Has Iniciado Sesion como Moderador |\n");
        sb.append("|=====================================|\n");

        return sb.toString();
    }

    public static String inicioAdministrador(){
        StringBuilder sb = new StringBuilder();
        sb.append("|=========================================|\n");
        sb.append("|  Has Iniciado Sesion como Administrador |\n");
        sb.append("|=========================================|\n");

        return sb.toString();
    }

    //*****************************************************************************************************************/
    //*****************************************************************************************************************/

    public static String menuUsuario(){
        StringBuilder sb = new StringBuilder();

        sb.append("|===============================|\n");
        sb.append("|         MENU: Usuario         |\n");
        sb.append("|===============================|\n");
        sb.append("|1.|-> Creacion de Personaje    |\n");
        sb.append("|2.|-> Ver Mis Personajes       |\n");
        sb.append("|3.|-> Ver Mundos Disponibles   |\n");
        sb.append("|4.|-> Reservar Mundo           |\n");
        sb.append("|5.|-> Ver mis Reservas         |\n");
        sb.append("|6.|-> Ver Mis Partidas         |\n");
        sb.append("|7.|-> Cerrar sesion            |\n");
        sb.append("|===============================|\n");

        return sb.toString();
    }



    public static String menuSeleccionDeClase(){
        StringBuilder sb = new StringBuilder();

        sb.append("|===============================|\n");
        sb.append("|       Creacion Personaje      |\n");
        sb.append("|===============================|\n");
        sb.append("|>>|Selecciona la Clase:        |\n");
        sb.append("|1.|-> Jugador Cuerpo a Cuerpo  |\n");
        sb.append("|2.|-> Jugador a Distancia      |\n");
        sb.append("|3.|-> Jugador Magico           |\n");
        sb.append("|===============================|\n");

        return sb.toString();
    }

    public static String menuRegisterPersonaje(){
        StringBuilder sb = new StringBuilder();

        sb.append("|===================================|\n");
        sb.append("|        Creacion Personaje         |\n");
        sb.append("|===================================|\n");
        sb.append("|>>> Ingrese el Nombre del Jugador: |\n");
        sb.append("|===================================|\n");

        return sb.toString();
    }


    public static String JugadorRegistrado(){
        StringBuilder sb = new StringBuilder();

        sb.append("|================================|\n");
        sb.append("|>>> Jugador Creado con Exito <<<|\n");
        sb.append("|================================|\n");



        return sb.toString();
    }


    public static String seleccionMundo(){
        StringBuilder sb = new StringBuilder();

        sb.append("|================================|\n");
        sb.append("|       Opciones de Mundo        |\n");
        sb.append("|================================|\n");
        sb.append("|1.|-> Ingresar a Jugar          |\n");
        sb.append("|2.|-> Ver Mundo en Especifico   |\n");
        sb.append("|3.|-> Volver...                 |\n");
        sb.append("|================================|\n");

        return sb.toString();
    }

    public static String seleccionMundoDisponible(){
        StringBuilder sb = new StringBuilder();

        sb.append("|====================================|\n");
        sb.append("|        Seleccion de Mundo          |\n");
        sb.append("|====================================|\n");
        sb.append("|1->>> Ingrese el id del Mundo:      |\n");
        sb.append("|====================================|\n");

        return sb.toString();
    }


    public static String seleccionMundoReserva(){
        StringBuilder sb = new StringBuilder();

        sb.append("|====================================|\n");
        sb.append("|        Datos para Reservar         |\n");
        sb.append("|====================================|\n");
        sb.append("|1->>> Ingrese el id del Jugador:    |\n");
        sb.append("|2->>> Ingrese el id del Mundo:      |\n");
        sb.append("|3->>> Ingrese la fecha de Comienzo: |\n");
        sb.append("|4->>> Ingrese la fecha Final        |\n");
        sb.append("|>>> Formato: dd/MM/yyyy             |\n");
        sb.append("|====================================|\n");

        return sb.toString();
    }


    public static String verReservasJugador(){
        StringBuilder sb = new StringBuilder();

        sb.append("|====================================|\n");
        sb.append("|            Reserva info            |\n");
        sb.append("|====================================|\n");
        sb.append("|1->>> Ingrese el id del Jugador:    |\n");
        sb.append("|====================================|\n");

        return sb.toString();
    }


    public static String partidasJugador(){
        StringBuilder sb = new StringBuilder();

        sb.append("|====================================|\n");
        sb.append("|            Partidas info           |\n");
        sb.append("|====================================|\n");
        sb.append("|1->>> Ingrese el id del Jugador:    |\n");
        sb.append("|====================================|\n");

        return sb.toString();
    }







    //*****************************************************************************************************************/
    //*****************************************************************************************************************/


    public static String menuModerador(){
        StringBuilder sb = new StringBuilder();

        sb.append("|===============================|\n");
        sb.append("|       MENU: Moderador         |\n");
        sb.append("|===============================|\n");
        sb.append("|1.|-> Administrar Usuarios     |\n");
        sb.append("|2.|-> Gestionar Mundos         |\n");
        sb.append("|3.|-> Ver Mundos Disponibles   |\n");
        sb.append("|4.|-> Reservar Mundo           |\n");
        sb.append("|5.|-> Ver mis Reservas         |\n");
        sb.append("|6.|-> Cerrar sesion            |\n");
        sb.append("|===============================|\n");


        return sb.toString();
    }




    //*****************************************************************************************************************/
    //*****************************************************************************************************************/
    public static String menuAdministrador(){
        StringBuilder sb = new StringBuilder();

        sb.append("|===================================|\n");
        sb.append("|       MENU: Administrador         |\n");
        sb.append("|===================================|\n");
        sb.append("|1.|-> Solicitudes de Moderador     |\n");
        sb.append("|2.|-> Administrar Usuarios         |\n");
        sb.append("|3.|-> Ver todos los Jugadores      |\n");
        sb.append("|4.|-> Reservas de Jugadores        |\n");
        sb.append("|5.|-> Gestionar Mundos             |\n");
        sb.append("|6.|-> Cerrar Sesion                |\n");
        sb.append("|===================================|\n");




        return   sb.toString();
    }


    /// CASE 1:

    public static String menuPeticionesModerador(){
       StringBuilder sb = new StringBuilder();

        sb.append("|===================================|\n");
        sb.append("|       Peticiones Moderador        |\n");
        sb.append("|===================================|\n");
        sb.append("|1.|-> Permitir Usuario             |\n");
        sb.append("|2.|-> Retroceder...                |\n");
        sb.append("|===================================|\n");


       return sb.toString();
    }

    public static String menuPermitirUsuario(){
        StringBuilder sb = new StringBuilder();

        sb.append("|===================================|\n");
        sb.append("|       Peticiones Moderador        |\n");
        sb.append("|===================================|\n");
        sb.append("|>>>| Ingrese el Gmail del Usuario  |\n");
        sb.append("|===================================|\n");

        return sb.toString();
    }

    public static String permisosOtorgados(){
        StringBuilder sb = new StringBuilder();

        sb.append("|===========================================|\n");
        sb.append("| !! Has concedido los permisos con exito !!|\n");
        sb.append("|===========================================|\n");

        return sb.toString();
    }


    ///CASE 2:

    public static String menuAdministrarUsuarios(){
        StringBuilder sb = new StringBuilder();

        sb.append("|===================================|\n");
        sb.append("|       Administrar Usuarios        |\n");
        sb.append("|===================================|\n");
        sb.append("|1.|-> Ver todos los Usuarios       |\n");
        sb.append("|2.|-> Banear Usuario               |\n");
        sb.append("|3.|-> Desbanear Usuario            |\n");
        sb.append("|4.|-> Volver...                    |\n");
        sb.append("|===================================|\n");


        return sb.toString();
    }


    public static String pedirIdUser(){
        StringBuilder sb = new StringBuilder();

        sb.append("|===================================|\n");
        sb.append("|       Administrar Usuarios        |\n");
        sb.append("|===================================|\n");
        sb.append("|>>| Ingresa el id del Usuario      |\n");
        sb.append("|===================================|\n");

        return sb.toString();
    }


    public static String confirmacionBan(Usuario user){
        StringBuilder sb = new StringBuilder();

        sb.append(">>> !! Seguro que quieres Banear a:\n"+ user.toString() +
                  "\n\n >>> Confirmar Ban: 1 -> SI/ 2 -> NO\n");

        return sb.toString();
    }

    public static String confirmacionDesban(Usuario user){
        StringBuilder sb = new StringBuilder();

        sb.append(">>> !! Seguro que quieres Desbanear a:\n"+ user.toString() +
                "\n\n >>> Confirmar Desban: 1 -> SI/ 2 -> NO\n");

        return sb.toString();
    }


    public static String gestionMundo(){
        StringBuilder sb = new StringBuilder();

        sb.append("|===================================|\n");
        sb.append("|        Gestionar Mundos           |\n");
        sb.append("|===================================|\n");
        sb.append("|1.|-> Creacion de Objetos          |\n");
        sb.append("|2.|-> Creacion de Enemigos         |\n");
        sb.append("|3.|-> Creacion de Mundos           |\n");
        sb.append("|4.|-> Volver...                    |\n");
        sb.append("|===================================|\n");

        return  sb.toString();
    }

    public static String gestionMundoObjetos(){
        StringBuilder sb = new StringBuilder();

        sb.append("|====================================|\n");
        sb.append("|        Gestionar Mundos            |\n");
        sb.append("|====================================|\n");
        sb.append("|1.|-> Crear un Arma de Guerrero     |\n");
        sb.append("|2.|-> Crear un Arma Magica          |\n");
        sb.append("|3.|-> Crear un Arma un Arco         |\n");
        sb.append("|4.|-> Crear una Pocion              |\n");
        sb.append("|5.|-> Ver todos los Objetos Creados |\n");
        sb.append("|6.|-> Volver...                     |\n");
        sb.append("|====================================|\n");

        return sb.toString();
    }




    public static String creacionArma(){
        StringBuilder sb = new StringBuilder();

        sb.append("|=================================================================|\n");
        sb.append("|                        Creacion de Arma                         |\n");
        sb.append("|=================================================================|\n");
        sb.append("|>>>|Ingresa los siguientes datos:                                |\n");
        sb.append("|1.|-> Nombre del Arma                                            |\n");
        sb.append("|2.|-> Nivel del Arma                                             |\n");
        sb.append("|3.|-> Daño del Arma                                              |\n");
        sb.append("|4.|-> Calidad = 1.COMUN, 2.RARO, 3.EPICO, 4.LEGENDARIO, 5.MITICO |\n");
        sb.append("|5.|-> Consumo de Estadistica Principal                           |\n");
        sb.append("|>>>>|Se recomienda no utilizar valores demasiados altos          |\n");
        sb.append("|=================================================================|\n\n");


        return  sb.toString();
    }


    public static String creacionDePocion(){
        StringBuilder sb = new StringBuilder();

        sb.append("|=========================================================================|\n");
        sb.append("|                           Creacion de Pocion                            |\n");
        sb.append("|=========================================================================|\n");
        sb.append("|>>>|Ingresa los Siguientes datos:                                        |\n");
        sb.append("|1.|-> Nombre de la Pocion                                                |\n");
        sb.append("|2.|-> Nivel de la Pocion                                                 |\n");
        sb.append("|3.|-> Efecto de la pocion (numero)                                       |\n");
        sb.append("|4.|-> Tipo de Pocion = 1.CURACION, 2.MANA, 3.ENERGIA, 4.DANIO, 5.CRITICO |\n");
        sb.append("|5.|-> Calidad = 1.COMUN, 2.RARO, 3.EPICO, 4.LEGENDARIO, 5.MITICO         |\n");
        sb.append("|>>>>|Se recomienda no utilizar valores demasiados altos                  |\n");
        sb.append("|=========================================================================|\n");

        return sb.toString();
    }




    public static String gestionMundoEnemigos(){
        StringBuilder sb = new StringBuilder();

        sb.append("|====================================|\n");
        sb.append("|        Gestionar Enemigo           |\n");
        sb.append("|====================================|\n");
        sb.append("|1.|-> Agregar un Enemigo            |\n");
        sb.append("|2.|-> Eliminar un Enemigo           |\n");
        sb.append("|3.|-> Ver todos los Enemigos        |\n");
        sb.append("|4.|-> Volver...                     |\n");
        sb.append("|====================================|\n");

        return sb.toString();
    }


    public static String agregarEnemigo(){
        StringBuilder sb = new StringBuilder();

        sb.append("|====================================================|\n");
        sb.append("|                   Agregar Enemigo                  |\n");
        sb.append("|====================================================|\n");
        sb.append("|>>>|Ingrese los Siguientes datos:                   |\n");
        sb.append("|1.|-> Nombre del Enemigo                            |\n");
        sb.append("|2.|-> Vida del Enemigo                              |\n");
        sb.append("|3.|-> Daño del Enemigo                              |\n");
        sb.append("|4.|-> Nivel del Enemigo                             |\n");
        sb.append("|5.|-> Tipo de Enemigo = 1.NORMAL, 2.ELITE, 3.JEFE   |\n");
        sb.append("|>>>>|Se recomienda no utilizar valores altos        |\n");
        sb.append("|====================================================|\n");

        return sb.toString();
    }

    public static String eliminarEnemigo(){
        StringBuilder sb = new StringBuilder();

        sb.append("|===================================|\n");
        sb.append("|       Eliminar Enemigo            |\n");
        sb.append("|===================================|\n");
        sb.append("|>>| Ingresa el id del Enemigo      |\n");
        sb.append("|===================================|\n");

        return sb.toString();
    }


    public static String menuMundo(){
        StringBuilder sb = new StringBuilder();

        sb.append("|====================================|\n");
        sb.append("|        Gestionar Mundos            |\n");
        sb.append("|====================================|\n");
        sb.append("|1.|-> Agregar un Mundo              |\n");
        sb.append("|2.|-> Modificar Estado Mundo        |\n");
        sb.append("|3.|-> Ver todos los Mundos          |\n");
        sb.append("|4.|-> Volver...                     |\n");
        sb.append("|====================================|\n");


        return sb.toString();
    }



    public static String agregarMundo(){
        StringBuilder sb = new StringBuilder();

        sb.append("|==========================================================|\n");
        sb.append("|                      Creacion de Mundo                   |\n");
        sb.append("|==========================================================|\n");
        sb.append("|>>>|Ingresa los siguientes datos                          |\n");
        sb.append("|1.|-> Nombre del Mundo                                    |\n");
        sb.append("|2.|-> Dificultad = 1.FACIL, 2.NORMAL,  3.DIFICIL          |\n");
        sb.append("|==========================================================|\n");

        return sb.toString();
    }


    public static String modificarMundo(){
        StringBuilder sb = new StringBuilder();

        sb.append("|====================================|\n");
        sb.append("|        Cambiar Estados             |\n");
        sb.append("|====================================|\n");
        sb.append("|1.|-> Marcar como No Disponible     |\n");
        sb.append("|2.|-> Marcar como Disponible        |\n");
        sb.append("|3.|-> Volver...                     |\n");
        sb.append("|====================================|\n");



        return sb.toString();
    }


    public static String modificarEstadoMundo(){
        StringBuilder sb = new StringBuilder();

        sb.append("|===================================================================|\n");
        sb.append("|                        Modificar Mundo                            |\n");
        sb.append("|===================================================================|\n");
        sb.append("|1.|-> Ingresa el id del Mundo:                                     |\n");
        sb.append("|2.|-> Ingresa el Motivo = 1.MANTENIMIENTO, 2.ERROR_TECNICO, 3.OTRO |\n");
        sb.append("|3.|-> Ingresa los detalles:                                        |\n");
        sb.append("|===================================================================|\n");


        return  sb.toString();
    }

    public static String modificarEstadoMundoDisponible(){
        StringBuilder sb = new StringBuilder();

        sb.append("|===================================|\n");
        sb.append("|       Modificar Mundo             |\n");
        sb.append("|===================================|\n");
        sb.append("|>>| Ingresa el id del Mundo        |\n");
        sb.append("|===================================|\n");

        return sb.toString();
    }






    //*****************************************************************************************************************/
    //*****************************************************************************************************************/


    public static String sinPermisos(){
        StringBuilder sb = new StringBuilder();

        sb.append("|=========================================================================|\n");
        sb.append("|    Debes esperar a que un Administrador te de Permisos para ingresar    |\n");
        sb.append("|=========================================================================|\n");


        return  sb.toString();
    }


    public static String operacionExitosa(){
        StringBuilder sb = new StringBuilder();

        sb.append("|============================================|\n");
        sb.append("|   Se ha hecho la Operacion Exitosamente    |\n");
        sb.append("|============================================|\n");


        return sb.toString();
    }

    public static String cerrarSesion(){
        StringBuilder sb = new StringBuilder();

        sb.append("|=====================================|\n");
        sb.append("|   Se ha cerrado sesion con Exito    |\n");
        sb.append("|=====================================|\n");


        return sb.toString();
    }


    public static String creadoConExito(){
        StringBuilder sb = new StringBuilder();

        sb.append("|===============================|\n");
        sb.append("|   Se ha Creado con Exito      |\n");
        sb.append("|===============================|\n");


        return sb.toString();
    }

    public static String salirDelSistema(){
        StringBuilder sb = new StringBuilder();

        sb.append("|===============================|\n");
        sb.append("|   Saliendo del Sistema....    |\n");
        sb.append("|===============================|\n");


        return  sb.toString();
    }


}
