package Clases.ClasesObjetos;

import Exceptions.GmailInvalidoException;

import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Consola {

    private static final Scanner sc = new Scanner(System.in);


    public static String pedirString(String mensaje){
        System.out.println(mensaje);

        return sc.nextLine();
    }

    public static int pedirInt(String mensaje){
        System.out.println(mensaje);

        return sc.nextInt();
    }


    /// Si lo que se escribio no es un numero, tira InputMismatchException.
    public static int pedirInt(){
        String linea = sc.nextLine();

        try {
            return Integer.parseInt(linea.trim());
        } catch (NumberFormatException e) {
            throw new InputMismatchException("|====================================|\n" +
                                             "|ERROR: !! No es un numero Valido !! |\n" +
                                             "|====================================|\n");
        }
    }




    public static void verificarFormatoGmail(String gmailUser) throws GmailInvalidoException {
        if(!gmailUser.endsWith("@gmail.com")){
            throw new GmailInvalidoException("|=================================================|\n" +
                                             "|ERROR: !! El formato del Gmail no es correcto !! |\n" +
                                             "|=================================================|\n");
        }

        if(gmailUser.length() < 18){
            throw new GmailInvalidoException("|======================================================|\n" +
                                             "|ERROR: !! El Gmail contiene menos de 18 caracteres !! |\n" +
                                             "|======================================================|\n");
        }


    }


}
