package Clases.ClasesObjetos;
import java.util.Random;

public class SistemaDeProbabilidad {
    private static final Random random = new Random();


    /// Una funcion sencilla para usar de Probabilidad de x cosa.
    /// ej:  ataques criticos, definir comportamiento de enemigos, etc
    public static boolean generarProb(double probabilidad){
        double generadorProb =  random.nextDouble(100);

        if(generadorProb <= probabilidad){
            return true;
        }

        return false;
    }

    /// Genera un Int de 0 al X limite
    public static int generadorInt(int limite){
        return random.nextInt(limite);
    }






}
