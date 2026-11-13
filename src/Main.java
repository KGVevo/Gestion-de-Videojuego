import Clases.ClasesObjetos.JDBC;
import Clases.ClasesObjetos.Servidor;
import Enums.E_Fases;
import Exceptions.TarjetaInvalidaException;

import java.sql.SQLOutput;
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
                    System.out.println("|-> Nombre de Usuario: ");
                    String nombre = input.nextLine();
                    System.out.println("|-> Contraseña: ");
                    String password = input.nextLine();


                    switch(1){
                        case 1:{
                            System.out.println("1.|--> Ver Jugadores <--|");
                            System.out.println("2.|--> Editor de Armas <--|");
                            System.out.println("3.|--> Editor de Objetos <--|");
                            System.out.println("4.|--> Editor de Enemigos <--|");
                            System.out.println("5.|--> Editor de Mundo <--|");

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

                                }
                                break;
                                case 5:{

                                }
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

                                }
                                break;
                                case 5:{

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