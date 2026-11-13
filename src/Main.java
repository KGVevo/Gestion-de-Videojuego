import Clases.ClasesObjetos.JDBC;
import Clases.ClasesObjetos.Servidor;
import Enums.*;
import Exceptions.RangoException;
import Exceptions.TarjetaInvalidaException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int seleccion = 0;
        int flag = 1;

        Servidor servidor = new Servidor();
        System.out.println(JDBC.probarConexcion());







        while(flag == 1){
            System.out.println("|--> Bienvenido <--|\n");
            System.out.println("|-Seleccione una opcion-|");
            System.out.println("--> 1. Registrar Administrador");
            System.out.println("--> 2. Registrar Usuario");
            System.out.println("--> 3. Iniciar Sesion");
            System.out.println("--> 4. Salir del sistema\n\n");

            switch(seleccion = input.nextInt()){
                case 1:{
                    // servidor.agregarAdministrador();
                }
                break;
                case 2:{
                    System.out.println("1.|-> Registrar Usuario con Tarjeta <-|");
                    System.out.println("2.|-> Registrar Usuario sin tarjeta <-|");
                    System.out.println("3.|-> Volver...");

                    try{
                        seleccion = input.nextInt();
                        input.nextLine();
                        if (seleccion == 1){
                            input.nextLine();
                            System.out.println("|-> Nombre Usuario: " );
                            String nombre = input.nextLine();
                            System.out.println("|-> Gmail: " );
                            String gmail = input.nextLine();
                            System.out.println("|-> Contraseña de la cuenta: ");
                            String password = input.nextLine();
                            System.out.println("|-> Titular de la Tarjeta: " );
                            String titular = input.nextLine();
                            System.out.println("|-> Numero de Tarjeta: ");
                            int numeroTarjeta = input.nextInt();
                            input.nextLine();

                            servidor.agregarUsuario(nombre, gmail, password , titular, numeroTarjeta);
                            System.out.println("|---> USUARIO CREADO CON EXITO <---|");

                        }else if(seleccion == 2){
                            System.out.println("|-> Nombre Usuario:" );
                            String nombre = input.nextLine();
                            System.out.println("|-> Gmail: " );
                            String gmail = input.nextLine();
                            System.out.println("|-> Contraseña de la cuenta: ");
                            String password = input.nextLine();


                            servidor.agregarUsuario(nombre, gmail, password);
                            System.out.println("|---> USUARIO CREADO CON EXITO <---|");
                        }else{
                            break;
                        }
                    }catch(TarjetaInvalidaException e){
                        e.getMessage();

                    }

                }
                break;
                case 3:{

                    /*System.out.println("|-> Nombre de Usuario: ");
                    String nombre = input.nextLine();
                    System.out.println("|-> Contraseña: ");
                    String password = input.nextLine();*/

                    /*if()
                    {
                        //Si es admin pasa al primer case
                        seleccion = 1;
                    }
                    else
                    {
                        //Si es Usuario pasa al segundo case
                        seleccion = 2;
                    }*/

                    switch(1){
                        case 1:{
                            System.out.println("===========================================");
                            System.out.println("1.|--> Ver Jugadores <--|");
                            System.out.println("===========================================");
                            System.out.println("2.|--> Agregar un Objeto <--|");
                            System.out.println("3.|--> Agregar un Arma <--|");
                            System.out.println("4.|--> Agregar una Armadura <--|");
                            System.out.println("5.|--> Agregar una Pocion <--|");
                            System.out.println("6.|--> Eliminar un Objeto <--|");
                            System.out.println("7.|--> Ver Objeto por ID <--|");
                            System.out.println("8.|--> Ver todos los Objetos <--|");
                            System.out.println("===========================================");
                            System.out.println("9.|--> Agregar un Enemigo <--|");
                            System.out.println("10.|--> Eliminar un Enemigo <--|");
                            System.out.println("11.|--> Ver Enemigo por ID <--|");
                            System.out.println("12.|--> Ver todos los Enemigos <--|");
                            System.out.println("===========================================");
                            System.out.println("13.|--> Volver al menu <--|");
                            System.out.println("===========================================");



                            switch (seleccion = input.nextInt()){
                                case 1:
                                {
                                    System.out.println("|-> Lista de todos los Jugadores");
                                    System.out.println(servidor.mostrarJugadores());
                                }
                                break;
                                case 2:
                                {
                                    input.nextLine();

                                    System.out.println("|-> Nombre del Objeto: ");
                                    String nombre = input.nextLine();

                                    System.out.println("|-> Nivel del Objeto: ");
                                    int nivel = input.nextInt();

                                    input.nextLine();

                                    E_Calidad calidad = null;
                                    boolean corte = false;

                                    while(corte == false)
                                    {
                                        System.out.println("Seleccione una calidad del Objeto: ");
                                        System.out.println("1. |-> Comun <-|");
                                        System.out.println("2. |-> Raro <-|");
                                        System.out.println("3. |-> Epico <-|");
                                        System.out.println("4. |-> Legendario <-|");
                                        System.out.println("5. |-> Mitico <-|");

                                        seleccion = input.nextInt();

                                        if(seleccion == 1)
                                        {
                                            corte = true;
                                            calidad = E_Calidad.COMUN;
                                        }
                                        else if(seleccion == 2)
                                        {
                                            corte = true;
                                            calidad = E_Calidad.RARO;
                                        }
                                        else if(seleccion == 3)
                                        {
                                            corte = true;
                                            calidad = E_Calidad.EPICO;
                                        }
                                        else if(seleccion == 4)
                                        {
                                            corte = true;
                                            calidad = E_Calidad.LEGENDARIO;
                                        }
                                        else if(seleccion == 5)
                                        {
                                            corte = true;
                                            calidad = E_Calidad.MITICO;
                                        }
                                        else
                                        {
                                            System.out.println("Opcion no valida, vuelva a intentarlo");
                                        }
                                    }

                                    System.out.println("|-> Descripcion del Objeto: ");
                                    String descripcion = input.nextLine();

                                    try {
         ///*********                               JDBC.agregarObjeto(nombre, nivel, descripcion, "Objeto");
                                        servidor.agregarObjeto(nombre, nivel, calidad);
                                        System.out.println("|---> OBJETO CREADO CON EXITO <---|");
                                    }
                                    catch (RangoException re)
                                    {
                                        System.out.println(re.getMessage());
                                    }

                                }
                                break;
                                case 3:
                                {
                                    input.nextLine();

                                    System.out.println("|-> Nombre del Arma: ");
                                    String nombre = input.nextLine();

                                    System.out.println("|-> Nivel del Arma: ");
                                    int nivel = input.nextInt();

                                    input.nextLine();

                                    System.out.println("|-> Descripcion del Arma: ");
                                    String descripcion = input.nextLine();

                                    System.out.println("|-> Daño del Arma: ");
                                    int daño = input.nextInt();

                                    E_Calidad calidad = null;
                                    boolean corte = false;

                                    while(corte == false)
                                    {
                                        System.out.println("Seleccione una calidad del arma: ");
                                        System.out.println("1. |-> Comun <-|");
                                        System.out.println("2. |-> Raro <-|");
                                        System.out.println("3. |-> Epico <-|");
                                        System.out.println("4. |-> Legendario <-|");
                                        System.out.println("5. |-> Mitico <-|");

                                        seleccion = input.nextInt();

                                        if(seleccion == 1)
                                        {
                                            corte = true;
                                            calidad = E_Calidad.COMUN;
                                        }
                                        else if(seleccion == 2)
                                        {
                                            corte = true;
                                            calidad = E_Calidad.RARO;
                                        }
                                        else if(seleccion == 3)
                                        {
                                            corte = true;
                                            calidad = E_Calidad.EPICO;
                                        }
                                        else if(seleccion == 4)
                                        {
                                            corte = true;
                                            calidad = E_Calidad.LEGENDARIO;
                                        }
                                        else if(seleccion == 5)
                                        {
                                            corte = true;
                                            calidad = E_Calidad.MITICO;
                                        }
                                        else
                                        {
                                            System.out.println("Opcion no valida, vuelva a intentarlo");
                                        }
                                    }

                                    System.out.println("|-> El Arma va a consumir una cantidad <-|");
                                    System.out.println("|-> Un Guerrero consume Energia <-|");
                                    System.out.println("|-> Un Arquero consume municion <-|");
                                    System.out.println("|-> Un Mago consume mana <-|");
                                    System.out.println("|-> Cuanto consume el Arma?: ");
                                    int limite = input.nextInt();

                                    E_ClaseAsociada claseAsociada = null;
                                    corte = false;

                                        while(corte == false)
                                        {
                                            System.out.println("Seleccione una opcion del tipo de Clase que sera este arma: ");
                                            System.out.println("1. |-> Arma para Cuerpo a Cuerpo <-|");
                                            System.out.println("2. |-> Arma para Distancia <-|");
                                            System.out.println("3. |-> Arma para Mago <-|");

                                            seleccion = input.nextInt();

                                            if(seleccion == 1)
                                            {
                                                corte = true;
                                                claseAsociada = E_ClaseAsociada.CUERPO_A_CUERPO;
                                            }
                                            else if(seleccion == 2)
                                            {
                                                corte = true;
                                                claseAsociada = E_ClaseAsociada.DISTANCIA;
                                            }
                                            else if(seleccion == 3)
                                            {
                                                corte = true;
                                                claseAsociada = E_ClaseAsociada.MAGO;
                                            }
                                            else
                                            {
                                                System.out.println("Opcion no valida, vuelva a intentarlo");
                                            }
                                        }
                                    try {
          //*******                              JDBC.crearArma(nombre, nivel, descripcion, claseAsociada.getClaseAsociada());
                                        servidor.agregarArma(nombre, nivel, daño, calidad, limite, claseAsociada);
                                        System.out.println("|---> ARMA CREADA CON EXITO <---|");
                                    }
                                    catch (RangoException re)
                                    {
                                        System.out.println(re.getMessage());
                                    }

                                }
                                case 4:

                                    input.nextLine();

                                    System.out.println("|-> Nombre del Proteccion: ");
                                    String nombre = input.nextLine();

                                    System.out.println("|-> Nivel del Proteccion: ");
                                    int nivel = input.nextInt();

                                    input.nextLine();

                                    E_Calidad calidad = null;
                                    boolean corte = false;

                                    while(corte == false)
                                    {
                                        System.out.println("Seleccione una calidad de la Proteccion: ");
                                        System.out.println("1. |-> Comun <-|");
                                        System.out.println("2. |-> Raro <-|");
                                        System.out.println("3. |-> Epico <-|");
                                        System.out.println("4. |-> Legendario <-|");
                                        System.out.println("5. |-> Mitico <-|");

                                        seleccion = input.nextInt();

                                        if(seleccion == 1)
                                        {
                                            corte = true;
                                            calidad = E_Calidad.COMUN;
                                        }
                                        else if(seleccion == 2)
                                        {
                                            corte = true;
                                            calidad = E_Calidad.RARO;
                                        }
                                        else if(seleccion == 3)
                                        {
                                            corte = true;
                                            calidad = E_Calidad.EPICO;
                                        }
                                        else if(seleccion == 4)
                                        {
                                            corte = true;
                                            calidad = E_Calidad.LEGENDARIO;
                                        }
                                        else if(seleccion == 5)
                                        {
                                            corte = true;
                                            calidad = E_Calidad.MITICO;
                                        }
                                        else
                                        {
                                            System.out.println("Opcion no valida, vuelva a intentarlo");
                                        }
                                    }

                                    System.out.println("|-> Porcentaje de Proteccion (Solo Enteros del 1 al 100): ");
                                    int proteccion = input.nextInt();

                                    E_ClaseAsociada claseAsociada = null;
                                    corte = false;

                                    while(corte == false)
                                    {
                                        System.out.println("Seleccione una opcion del tipo de Clase que sera esta armadura: ");
                                        System.out.println("1. |-> Armadura para Cuerpo a Cuerpo <-|");
                                        System.out.println("2. |-> Armadura para Distancia <-|");
                                        System.out.println("3. |-> Armadura para Mago <-|");

                                        seleccion = input.nextInt();

                                        if(seleccion == 1)
                                        {
                                            corte = true;
                                            claseAsociada = E_ClaseAsociada.CUERPO_A_CUERPO;
                                        }
                                        else if(seleccion == 2)
                                        {
                                            corte = true;
                                            claseAsociada = E_ClaseAsociada.DISTANCIA;
                                        }
                                        else if(seleccion == 3)
                                        {
                                            corte = true;
                                            claseAsociada = E_ClaseAsociada.MAGO;
                                        }
                                        else
                                        {
                                            System.out.println("Opcion no valida, vuelva a intentarlo");
                                        }
                                    }

                                    input.nextLine();

                                    System.out.println("|-> Descripcion de la Armadura: ");
                                    String descripcion = input.nextLine();

                                    try {
          //*******                              JDBC.agregarObjeto(nombre, nivel, descripcion, claseAsociada.getClaseAsociada());
                                        servidor.agregarArmadura(nombre, nivel, calidad, proteccion, claseAsociada);
                                        System.out.println("|---> ARMADURA CREADA CON EXITO <---|");
                                    }
                                    catch (RangoException re)
                                    {
                                        System.out.println(re.getMessage());
                                    }

                                    break;
                                case 5:

                                    input.nextLine();

                                    System.out.println("|-> Nombre de la Pocion: ");
                                    nombre = input.nextLine();

                                    System.out.println("|-> Nivel de la Pocion: ");
                                    nivel = input.nextInt();

                                    input.nextLine();

                                    calidad = null;
                                    corte = false;

                                    while(corte == false)
                                    {
                                        System.out.println("Seleccione una calidad de la Pocion: ");
                                        System.out.println("1. |-> Comun <-|");
                                        System.out.println("2. |-> Raro <-|");
                                        System.out.println("3. |-> Epico <-|");
                                        System.out.println("4. |-> Legendario <-|");
                                        System.out.println("5. |-> Mitico <-|");

                                        seleccion = input.nextInt();

                                        if(seleccion == 1)
                                        {
                                            corte = true;
                                            calidad = E_Calidad.COMUN;
                                        }
                                        else if(seleccion == 2)
                                        {
                                            corte = true;
                                            calidad = E_Calidad.RARO;
                                        }
                                        else if(seleccion == 3)
                                        {
                                            corte = true;
                                            calidad = E_Calidad.EPICO;
                                        }
                                        else if(seleccion == 4)
                                        {
                                            corte = true;
                                            calidad = E_Calidad.LEGENDARIO;
                                        }
                                        else if(seleccion == 5)
                                        {
                                            corte = true;
                                            calidad = E_Calidad.MITICO;
                                        }
                                        else
                                        {
                                            System.out.println("Opcion no valida, vuelva a intentarlo");
                                        }
                                    }

                                    System.out.println("|-> Efecto de la Pocion: ");
                                    double efecto = input.nextDouble();

                                    input.nextLine();

                                    E_TipoPocion tipoPocion = null;
                                    corte = false;

                                    while(corte == false)
                                    {
                                        System.out.println("Seleccione una calidad de la Pocion: ");
                                        System.out.println("1. |-> Curacion <-|");
                                        System.out.println("2. |-> Mana <-|");
                                        System.out.println("3. |-> Energia <-|");
                                        System.out.println("4. |-> Daño <-|");
                                        System.out.println("5. |-> Critico <-|");

                                        seleccion = input.nextInt();

                                        if(seleccion == 1)
                                        {
                                            corte = true;
                                            tipoPocion = E_TipoPocion.CURACION;
                                        }
                                        else if(seleccion == 2)
                                        {
                                            corte = true;
                                            tipoPocion = E_TipoPocion.MANA;
                                        }
                                        else if(seleccion == 3)
                                        {
                                            corte = true;
                                            tipoPocion = E_TipoPocion.ENERGIA;
                                        }
                                        else if(seleccion == 4)
                                        {
                                            corte = true;
                                            tipoPocion = E_TipoPocion.DAÑO;
                                        }
                                        else if(seleccion == 5)
                                        {
                                            corte = true;
                                            tipoPocion = E_TipoPocion.CRITICO;
                                        }
                                        else
                                        {
                                            System.out.println("Opcion no valida, vuelva a intentarlo");
                                        }
                                    }

                                    try {

              //*******                     JDBC.agregarObjeto(nombre, nivel, descripcion, claseAsociada.getClaseAsociada());
                                        servidor.agregarPocion(nombre, nivel, efecto, tipoPocion, calidad);
                                        System.out.println("|---> POCION CREADO CON EXITO <---|");
                                    }
                                    catch (RangoException re)
                                    {
                                        System.out.println(re.getMessage());
                                    }

                                    break;
                                case 6:

                                    System.out.println("|-> Ingrese la ID del objeto: ");
                                    int id = input.nextInt();

           ///***********                              JDBC.eliminarObjeto(id); //No existe el metodo
                                    servidor.eliminarObjeto(id);
                                    System.out.println("|---> OBJETO ELIMINADO CON EXITO <---|");

                                    break;
                                case 7:

                                    System.out.println("|-> Ingrese la ID del objeto: ");
                                    id = input.nextInt();

                                    System.out.println(servidor.verObjetoID(id));

                                    break;
                                case 8:

                                    System.out.println("|-> Lista de Todos los Objetos");
                                    System.out.println(servidor.verObjetoTodos());

                                    break;
                                case 9:
                                    input.nextLine();

                                    System.out.println("|-> Nombre del Enemigo: ");
                                    nombre = input.nextLine();

                                    System.out.println("|-> Vida del Enemigo: ");
                                    int vida = input.nextInt();

                                    System.out.println("|-> Daño del Enemigo: ");
                                    int daño = input.nextInt();

                                    System.out.println("|-> Nivel del Enemigo: ");
                                    nivel = input.nextInt();

                                    input.nextLine();

                                    E_TipoDeEnemigo tipoEnemigo = null;
                                    corte = false;

                                    while(corte == false)
                                    {
                                        System.out.println("Seleccione una calidad del Arma: ");
                                        System.out.println("1. |-> NORMAL <-|");
                                        System.out.println("2. |-> ELITE <-|");
                                        System.out.println("3. |-> JEFE <-|");

                                        seleccion = input.nextInt();

                                        if(seleccion == 1)
                                        {
                                            corte = true;
                                            tipoEnemigo = E_TipoDeEnemigo.NORMAL;
                                        }
                                        else if(seleccion == 2)
                                        {
                                            corte = true;
                                            tipoEnemigo = E_TipoDeEnemigo.ELITE;
                                        }
                                        else if(seleccion == 3)
                                        {
                                            corte = true;
                                            tipoEnemigo = E_TipoDeEnemigo.JEFE;
                                        }
                                        else
                                        {
                                            System.out.println("Opcion no valida, vuelva a intentarlo");
                                        }
                                    }

                                    JDBC.agregarEnemigo(nombre, vida, nivel);
                                    servidor.agregarEnemigo(nombre, vida, daño, nivel, tipoEnemigo);
                                    System.out.println("|---> ENEMIGO CREADO CON EXITO <---|");

                                    break;
                                case 10:

                                    System.out.println("|-> Ingrese la ID del Enemigo: ");
                                    id = input.nextInt();

            ///***********                              JDBC.eliminarEnemigo(id); //No existe el metodo

                                    servidor.eliminarEnemigo(id);

                                    break;
                                case 11:

                                    System.out.println("|-> Ingrese la ID del Enemigo: ");
                                    id = input.nextInt();

                                    System.out.println(servidor.verEnemigoID(id));

                                    break;
                                case 12:

                                    System.out.println("|-> Lista de Todos los Enemigos");
                                    System.out.println(servidor.verEnemigoTodos());

                                    break;
                                case 13:

                                    //Juan debe terminar con mundo

                                    break;
                            }
                        }
                    break;

                        case 2:
                            System.out.println("1.|--> Crear Mundo <--|");
                            System.out.println("2.|--> Iniciar Partida <--|");
                            System.out.println("3.|--> Mostrar Tus Jugadores <--|");
                            System.out.println("4.|--> Borrar un Jugador <--|");
                            System.out.println("5.|--> Cerrar Sesion <--|");

                            switch (seleccion = input.nextInt()){
                                case 1:{

                                }
                                break;
                                case 2:{

                                }
                                break;
                                case 3:{



                                }
                                break;
                                case 4:{

                                    System.out.println("|-> Ingrese la ID de su Jugador: ");
                                    int id = input.nextInt();

                                    servidor.eliminarJugador(id);

                                }
                                break;
                            }
                    }

                    break;
                }
                case 4:{
                    System.out.println("--> Saliendo del Sistema...");

                    flag = 0;
                }
                break;
                default:{
                    System.out.println("|--> Opcion no valida <--|");
                }
            }
        }







    }
}