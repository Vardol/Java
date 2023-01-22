package HW1;
import java.util.*;

public class ex04 {

    static char[] normalizeLength(char[] input_array, int required_length){
        if (input_array.length >= required_length){return input_array;}
        char[] result = new char[required_length];
        int difference = required_length - input_array.length;
        for (int i = 0; i < difference; i++) {
            result[i] = '0';
        }
        for (int i = 0; i < input_array.length; i++) {
            result[i + difference] = input_array[i];
        }
        return result;
    }

    static int getIntFromArray(int[] input_array){
        int result = 0;
        for (int i = 0; i < input_array.length; i++) {
            result += input_array[i] * Math.pow(10, input_array.length - 1 - i);
        }
        return result;
    }


    public static void main(String[] args) {
        //Считываем строку
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение для расчета. Все числа и операцию разделяйте пробелами. Для выхода - exit.");
        String input[] = utils.checkQuit(scanner.nextLine()).split(" ");
        
        //разбиваем на числа и операнды, определяем максимальное число разрядов среди введенных чисел, раскладываем в массивы чаров
        int MAXLENGTH = Math.max(input[0].length(), Math.max(input[2].length(), input[4].length()));
        char[] firstNumber = normalizeLength(input[0].toCharArray(), MAXLENGTH);
        char[] secondNumber = normalizeLength(input[2].toCharArray(), MAXLENGTH);
        char[] summ = normalizeLength(input[4].toCharArray(), MAXLENGTH);
        
        //переводим в массивы интов поциферно
        int[] firstNumberIntArray = new int[firstNumber.length];
        for (int i = 0; i < firstNumberIntArray.length; i++) {
            if (Character.isDigit(firstNumber[i])){
                firstNumberIntArray[i] = Integer.parseInt(Character.toString(firstNumber[i]));
            } else {firstNumberIntArray[i] = -1;}
        }
        
        int[] secondNumberIntArray = new int[secondNumber.length];
        for (int i = 0; i < secondNumberIntArray.length; i++) {
            if (Character.isDigit(secondNumber[i])){
                secondNumberIntArray[i] = Integer.parseInt(Character.toString(secondNumber[i]));
            } else {secondNumberIntArray[i] = -1;}
        }

        int[] sumNumberIntArray = new int[summ.length];
        for (int i = 0; i < sumNumberIntArray.length; i++) {
            if (Character.isDigit(summ[i])){
                sumNumberIntArray[i] = Integer.parseInt(Character.toString(summ[i]));
            } else {sumNumberIntArray[i] = -1;}
        }

        int[] inMindIntArray = new int[MAXLENGTH]; //делаем массив интов для хранения переходов через разряд (как бы "в уме" при складывании столбиком)
        boolean failedCalc = false; //флаг отсутствия решения


        //если в сумме больше разрядов и в самом большом стоит единица (переход от суммирования прошлого разряда)
        //то обрабатываем два последних разряда суммы, как одно двузначное число в предпоследнем разряде.
        if (firstNumberIntArray[0] == 0 && secondNumberIntArray[0] == 0 && sumNumberIntArray[0] == 1 && sumNumberIntArray.length >= 2){
            sumNumberIntArray[0] = 0;
            sumNumberIntArray[1] += 10;
        }
        
        
        for (int i = MAXLENGTH - 1; i >= 0; i--) {      //бегаем по длине чисел от единиц
            //System.out.printf("i = %d: %d, %d, %d\n", i, firstNumberIntArray[i], firstNumberIntArray[i], sumNumberIntArray[i]); //строка использовалась для отладки

            //поочередно перебираем варианты неизвестных.
            if (firstNumberIntArray[i] == -1 && secondNumberIntArray[i] != -1){
                //если в сумме цифра в разряде меньше, чем известное слагаемое - то был переход через разряд - учитываем это в расчетах.
                if (sumNumberIntArray[i] < secondNumberIntArray[i]){
                    firstNumberIntArray[i] = 10 + sumNumberIntArray[i] - secondNumberIntArray[i] - inMindIntArray[i];
                    if (i == 0) {failedCalc = true;}
                    else {inMindIntArray[i-1] = 1;}
                } else {
                    firstNumberIntArray[i] = sumNumberIntArray[i] - secondNumberIntArray[i] - inMindIntArray[i];
                }
                if (firstNumberIntArray[i] == -1){failedCalc = true;}
            }
            if (firstNumberIntArray[i] != -1 && secondNumberIntArray[i] == -1){
                if (sumNumberIntArray[i] < firstNumberIntArray[i]){
                    firstNumberIntArray[i] = 10 + sumNumberIntArray[i] - secondNumberIntArray[i] - inMindIntArray[i];
                    if (i == 0) {failedCalc = true;}
                    else {inMindIntArray[i-1] = 1;}
                } else {
                    secondNumberIntArray[i] = sumNumberIntArray[i] - firstNumberIntArray[i] - inMindIntArray[i];
                }
                if (secondNumberIntArray[i] == -1){failedCalc = true;}
            }

            //логика чуть отличается, если оба числа неизвестно
            if (firstNumberIntArray[i] == -1 && secondNumberIntArray[i] == -1){
                firstNumberIntArray[i] = sumNumberIntArray[i] / 2;
                secondNumberIntArray[i] = sumNumberIntArray[i] - firstNumberIntArray[i] - inMindIntArray[i];

                //если с учетом данных "в уме", второе числа вышло вдруг отрицательным - пробуем это исправить за счет первого числа
                //не уверен, что так м.б., но на всякий случай предусмотрел.
                if (secondNumberIntArray[i] < 0){
                    secondNumberIntArray[i] +=1 ;
                    firstNumberIntArray[i] -=1 ;
                }
                //если в итоге одно из числе меньше 0 - флаг ошибки
                if (secondNumberIntArray[i] == -1 || firstNumberIntArray[i] == -1){failedCalc = true;}
            }

            //если оба числа известны (были сначала или определены нами) - мы проверяем корректность расчета
            if (firstNumberIntArray[i] != -1 && secondNumberIntArray[i] != -1){
                if ((firstNumberIntArray[i] + secondNumberIntArray[i] + inMindIntArray[i]) % 10 != sumNumberIntArray[i] % 10) {failedCalc = true;}
                if (firstNumberIntArray[i] + secondNumberIntArray[i] >= 10 && sumNumberIntArray[i] < 10) {
                    if (i == 0) {failedCalc = true;}
                    else {inMindIntArray[i-1] = 1;}
                }
            }
        //System.out.printf("Результат текущей итерацции: %d + %d = %d. ", getIntFromArray(firstNumberIntArray), getIntFromArray(secondNumberIntArray), getIntFromArray(sumNumberIntArray));//строка использовалась для отладки
        //System.out.println(failedCalc);//строка использовалась для отладки
        
        if (failedCalc) {break;} //если расчет некорректен - разрыв цикла
        }
        
        //проверка корректности итогового выражения
        if (getIntFromArray(firstNumberIntArray) + getIntFromArray(secondNumberIntArray) != getIntFromArray(sumNumberIntArray)){failedCalc = true;}
        
        //вывод результата
        if (failedCalc){System.out.println("Нет решений");}
        else {System.out.printf("%d + %d = %d", getIntFromArray(firstNumberIntArray), getIntFromArray(secondNumberIntArray), getIntFromArray(sumNumberIntArray));}
        scanner.close();

    }
}
