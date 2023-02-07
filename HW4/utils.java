package HW4;
import java.util.Scanner;

public class utils {

    static double roundDouble(double input, int decimals){
        double result = input;
        for (int i = 0; i < decimals; i++) {
            result *= 10;
        }
        result = Math.round(input);
        for (int i = 0; i < decimals; i++) {
            result /= 10;
        }
        return result;
    }

    static int readNatural() {
        System.out.printf("Введите натуральное число - ");
        int input = -1;
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()){input = scanner.nextInt();}
        else {input = readNatural();}
        scanner.close();
        if (input == 0) {input = readNatural();}
        if (input < 0) {input *= -1;}
        return input;
    }

    static int readNatural(String promptingMessage) {
        System.out.print(promptingMessage);
        int input = -1;
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()){input = scanner.nextInt();}
        else {input = readNatural(promptingMessage);}
        if (input == 0) {input = readNatural(promptingMessage);}
        if (input < 0) {input *= -1;}
        scanner.close();
        return input;
    }

    static String checkQuit(String input){
        String[] QUITVALUES = {"quit","exit","close","stop"};
        for (String string : QUITVALUES) {
            if (string.equalsIgnoreCase(input)){System.exit(0);}
        }
        return input;
    }
}
