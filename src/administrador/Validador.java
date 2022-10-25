package administrador;

import java.util.Scanner;

public abstract class Validador {
    private static final Scanner entrada = new Scanner(System.in);

    //VALIDADORES DE ENTRADAS
    //Se controla cualquier posible problema en la entrada de numeros enteros
    public static int validarEntradaInt(double opcMax,boolean incluyente1, double opcMin, boolean incluyente2) {
        return (int) validarEntradaNumerical(opcMax, incluyente1, opcMin, incluyente2, true);
    }
    //Se controla cualquier posible problema en la entrada de numeros
    public static double validarEntradaNumerical(double opcMax, boolean incluyente1, double opcMin, boolean incluyente2, boolean floor){
        double opcUsuario;
        boolean repeted = false;
        do {
            try {
                if (repeted) {
                    System.out.println("PORFAVOR INGRESE UN DATO VALIDO,\n" +
                            "RECUERDE USAR ',' EN CASO DE TENER DECIMALES \n" +
                            "y que el valor este entre el "+ opcMin+" y el "+ opcMax);
                }
                System.out.println("Por favor ingrese su respuesta: ");
                opcUsuario = entrada.nextDouble();
                if(floor){
                    opcUsuario = Math.floor(opcUsuario);
                }
            } catch (Exception e) {
                opcUsuario = Math.floor(opcMin-1);
                entrada.next();
            }
            repeted = true;
        } while (opcUsuario < opcMin || opcUsuario > opcMax || (!incluyente1 && opcMax == opcUsuario) || (!incluyente2 && opcMin == opcUsuario));
        return opcUsuario;
    }

    //Se controla cualquier posible problema en la entrada de los datos de tipo double
    public static double validarEntradaDouble(double opcMax, boolean incluyente1, double opcMin, boolean incluyente2) {
        return validarEntradaNumerical(opcMax, incluyente1, opcMin, incluyente2, false);
    }

    //Se controla cualquier posible problema en la entrada de los datos de tipo String
    public static String validarEntradaTexto(boolean allowSpaces){
        String opcUsuario = "";
        boolean repeted = false;
        do {
            try {
                if (repeted) {
                    System.out.println("PORFAVOR INGRESE UN DATO VALIDO");
                }
                opcUsuario =entrada.next() + entrada.nextLine();
                if(!allowSpaces){
                    opcUsuario = opcUsuario.split(" ")[0];
                }
            } catch (Exception e) {;
                repeted = true;
            }
        } while (repeted);
        return opcUsuario;
    }
}
