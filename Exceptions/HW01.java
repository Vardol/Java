package Exceptions;

// 1) Реализуйте 3 метода, чтобы в каждом из них получить разные исключения
// 2) Реализуйте метод, принимающий в качестве аргументов два целочисленных массива, и возвращающий новый массив,
// каждый элемент которого равен разности элементов двух входящих массивов в той же ячейке. Если длины массивов не равны, необходимо как-то оповестить пользователя.

public class HW01 {


    //1) Реализуйте 3 метода, чтобы в каждом из них получить разные исключения

    // Первый метод. Метод с делением, который будет делить, без защиты от деления на 0
    public static int divide(int x, int y) {
        return x/y;
    }

    // Второй метод. Метод отображающий содержимое массива, с ошибкой IndexOutOfBounds
    public static void printArray(int[] array) {
        for (int i = 0; i <= array.length; i++) {
            System.out.println(array[i]);
        }
    }

    // Третий метод. Метод расчета факториала рекурсией
    public static int factorial(int x) {
        if (x == 1){
            return x;
        } else {
            return x*factorial(x-1);
        }
    }


    // 2) Реализуйте метод, принимающий в качестве аргументов два целочисленных массива, и возвращающий новый массив,
    // каждый элемент которого равен разности элементов двух входящих массивов в той же ячейке. Если длины массивов не равны, необходимо как-то оповестить пользователя.
    public static int[] substractArray(int[] array1, int[] array2) {
        if (array1.length != array2.length){
            System.out.println("Длины массивов не равны. Возвращаю null.");
            return null;
        }
        int[] result = new int[array1.length];
        for (int i = 0; i < array1.length; i++) {
            result[i] = array1[i] - array2[i]; 
        }
        return result;
    }

    public static void main(String[] args) {
    
    // Первый метод. Делим на 0 - получаем ошибку
    System.out.println(divide(5,0));


    // Второй метод.
    //  Делаем массив
    int[] array = new int[3];
    array[0] = 0;
    array[1] = 1;
    array[2] = 2;
    //отдаем массив в метод - получаем ошибку
    printArray(array);



    // Третий метод.
    // запускаем достаточно большое число - получаем переполнение стека
    System.out.println(factorial(18960));


    //применяем метод вычитания массивов
    //Делаем еще массив
    int[] otherArray = new int[3];
    array[0] = 1;
    array[1] = 2;

    //метод не будет выбрасывать исключение IndexOutOfBounds если длины не равны, т.к. мы его таким образом обработали.
    System.out.println(substractArray(array, otherArray));

    }
}
