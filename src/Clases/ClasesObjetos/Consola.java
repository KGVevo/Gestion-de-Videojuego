package Clases.ClasesObjetos;

import Exceptions.GmailInvalidoException;

import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Consola {

    private static final Scanner sc = new Scanner(System.in);


    public static String pedirString(String mensaje){
        System.out.print(mensaje);

        return sc.nextLine();
    }


    /// Si lo que se escribio no es un numero, tira InputMismatchException.
    public static int pedirInt(String mensaje){
        System.out.print(mensaje);
        String linea = sc.nextLine();

        try {
            return Integer.parseInt(linea.trim());
        } catch (NumberFormatException e) {
            throw new InputMismatchException("|====================================|\n" +
                                             "|ERROR: !! No es un numero Valido !! |\n" +
                                             "|====================================|\n");
        }
    }




}
