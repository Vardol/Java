package HW4;
//3*. В калькулятор добавьте возможность отменить последнюю операцию.

// У меня калькулятор в принципе выполнял по одному действию. Поэтому функцию undo использовал для отмены логгирования последней операции

import HW4.MyQueue; // реализация queue с указанными методами находится в этом классе


import java.util.Scanner;
import java.io.IOException;
import java.lang.Math;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class SimpleCalculatorUndo {

    

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
        
        MyQueue queue = new MyQueue(); // создаю для себя очередь

        Logger logger = Logger.getLogger(SimpleCalculatorUndo.class.getName());
        FileHandler fileHandler = new FileHandler("calclog.log");
        logger.addHandler(fileHandler);
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        fileHandler.setFormatter(simpleFormatter);

        System.out.println("Добро пожаловать в простой калькулятор. Для выхода введите exit.");
        Scanner scanner = new Scanner(System.in);
        while (true){

            //Сначала обрабатываем очередь, если в ней два элемента - пишем более старый (первый) в лог.
            // у нас остается один предыдущий элемент для записи
            if (queue.getSize() >= 2){
                logger.log(Level.INFO, (String)queue.dequeue());
            }

            System.out.println("Введите выражение для расчета. Все числа и операцию разделяйте пробелами. Для выхода - exit. Для отмены операции - undo.");
            String expression = scanner.nextLine();
            
            //если введен "undo" - удаляем элемент из очереди
            if (expression.equalsIgnoreCase("undo") && queue.getSize() > 0){
                queue.dequeue();
                System.out.println("Последняя операция, не будет залоггирована.\n");
                continue;
            }

            if (expression.equalsIgnoreCase("exit") && queue.getSize() > 0){
                if (queue.getSize() > 0){
                    logger.log(Level.INFO, (String)queue.dequeue()); //при выходе логгируем ппоследнюю запись, если она была
                }
                System.out.println("До новых встреч!");
                System.exit(0);
            }

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
                queue.enqueue((String)(expression + " = " + String.valueOf(result))); //добавляем в очередь строку для логгирования с последней операции
                System.out.println("= " + String.valueOf(result));
            } else {
                queue.enqueue((String)("Некорректное выражение: " + expression)); //добавляем в очередь строку для логгирования с последней операции
                System.out.println("некорректное выражение");
            }
        }
    }
}
