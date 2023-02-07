//Реализовать простой калькулятор

package HW2;
import java.util.Scanner;
import java.io.IOException;
import java.lang.Math;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class LoggedSimpleCalculator {
    static final char[] OPERATIONS = {'+','-','*','/','^'}; 

    static boolean isOperand(String input){
        if (input.isBlank()){return false;}
        if (input.length() > 1){return false;}
        for (char c : OPERATIONS) {
            if  (c == input.charAt(0)){return true;}
        }
        return false;
    }

    static boolean isNumeric(String str) { 
        try {  
          Double.parseDouble(str);
          return true;
        } catch(NumberFormatException e){  
          return false;  
        }  
    }
    
    static boolean isNumeric(String str[]) {
        boolean result = true;
        for (String string : str) {
            try {  
                Double.parseDouble(string);    
                }
                catch(NumberFormatException e){  
                result = false;  
                }
        }
        return result;
    }

    public static void main(String[] args) throws IOException{
        Logger logger = Logger.getLogger(LoggedSimpleCalculator.class.getName());
        FileHandler fileHandler = new FileHandler("calclog.log");
        logger.addHandler(fileHandler);
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        fileHandler.setFormatter(simpleFormatter);

        StringBuilder stringBuilder = new StringBuilder();

        System.out.println("Добро пожаловать в простой калькулятор. Для выхода введите exit.");
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Введите выражение для расчета. Все числа и операцию разделяйте пробелами. Для выхода - exit.");
            String expression = utils.checkQuit(scanner.nextLine());
            String input[] = expression.split(" ");

            boolean successfulCalcFlag = true;
            if (input.length != 3) {
                successfulCalcFlag = false;
            }
            else{
                if (!isNumeric(input[0]) || !isNumeric(input[2]) || !isOperand(input[1])){
                    successfulCalcFlag = false;
                }
            }
    
            if (successfulCalcFlag) {
                double result = 0.0;
                switch (input[1]) {
                    case "+":
                        result = (Double.parseDouble(input[0]) + Double.parseDouble(input[2]));
                        break;
                    case "-":
                        result = (Double.parseDouble(input[0]) - Double.parseDouble(input[2]));
                        break;
                    case "*":
                        result = (Double.parseDouble(input[0]) * Double.parseDouble(input[2]));
                        break;
                    case "/":
                        result = (Double.parseDouble(input[0]) / Double.parseDouble(input[2]));
                        break;
                    case "^":
                        result = Math.pow(Double.parseDouble(input[0]), Double.parseDouble(input[2]));
                        break;
                }
                logger.log(Level.INFO, expression + " = " + String.valueOf(result));
                System.out.println("= " + String.valueOf(result));
            } else {
                logger.log(Level.WARNING, "Некорректное выражение: " + expression);
                System.out.println("некорректное выражение");
            }
        }
    }
}