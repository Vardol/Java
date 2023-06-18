package Exceptions;

import java.util.Scanner;

public class HW02 {

    // 1. Реализуйте метод, который запрашивает у пользователя ввод дробного числа
    // (типа float), и возвращает введенное значение. Ввод текста вместо числа не
    // должно приводить к падению приложения, вместо этого, необходимо повторно
    // запросить у пользователя ввод данных.

    // Собственно метод считывающий float
    static float readFloat(String promptingMessage, Scanner scanner) {
        System.out.print(promptingMessage);
        Double input = null;

        while (input == null) {
            try {
                input = Double.parseDouble(scanner.nextLine());
            } catch (Exception e) {
                System.out.println(promptingMessage);
            }
        }
        return (float) (double) input;
    }

    // 4. Разработайте программу, которая выбросит Exception, когда пользователь вводит
    // пустую строку. Пользователю должно показаться сообщение, что пустые строки
    // вводить нельзя.

    // Метод считывающий строку с консоли и выбрасывающий Exception, если строка
    // пустая
    static String readString(String promptingMessage, Scanner scanner) throws RuntimeException {
        System.out.print(promptingMessage);
        String input = "";
        input = input.concat(scanner.nextLine());
        if (input == "") {
            throw (new RuntimeException());
        } else {
            return input;
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // тестим метод считывания float
        float x = readFloat("Введите десятичную дробь: ", scanner);
        System.out.println("Вы ввели " + x);

        // Используем метод считывания не пустой строки
        try {
            String str = readString("Введите непустую строку - ", scanner);
            System.out.println("Вы ввели " + str);
        } catch (RuntimeException e) {
            System.out.println("Недопустим ввод пустой строки!");
        }

        scanner.close();
        
    }
}




// НИЖЕ ИДУТ ЗАДАНИЯ 2 и 3 НА ИСПРАВЛЕНИЕ.


// 2. Если необходимо, исправьте данный код (задание 2)
/*
// Задание 2
try {
    int d = 0;
    int[] intArray = new int[9];                            // создал массив
    intArray[8] = 10;                                       // положил значение по индексу, который использовался в коде
    double catchedRes1 = intArray[8] / d;                   // массив не был создан
    System.out.println("catchedRes1 = " + catchedRes1);
} catch (ArithmeticException e) {
    System.out.println("Catching exception: " + e);
}

*/



// 3. Дан следующий код, исправьте его там, где требуется (задание 3
// https://docs.google.com/document/d/17EaA1lDxzD5YigQ5OAal60fOFKVoCbEJqooB9XfhT7w/edit)

/*
public static void printSum(Integer a, Integer b) throws FileNotFoundException { // непонятно почему заявлено такое исключение, этот код явно не может его вызвать
   System.out.println(a + b);
}

public static void main(String[] args) throws Exception {
    try {
        int a = 90;
        int b = 3;
        System.out.println(a / b);
        printSum(23, 234);
        int[] abc = { 1, 2 };
        abc[3] = 9;
    } catch (NullPointerException ex) {
        System.out.println("Указатель не может указывать на null!");
    } catch (IndexOutOfBoundsException ex) {
        System.out.println("Массив выходит за пределы своего размера!");
    } catch (Throwable ex) {        //Этот блок catch был в самом начале. Поскольку в нем указан Предок всех исключений, то нижние никогда не выполнятся, поэтому я его перенес вниз
        System.out.println("Что-то пошло не так...");
    }
}
*/