// Вычислить n-ое треугольного число(сумма чисел от 1 до n), n! (произведение чисел от 1 до n)
package HW1;


public class ex01 {
    static int summInRange(int number) {
        int result = 0;
        while (number > 0) {
            result += number;
            number--;
        }
        return result;
    }

    static long factorial(int number) {
        if (number > 20){return 0;}
        long result = 1;
        while (number > 1) {
            result *= number;
            number--;
        }
        return result;
    }

    public static void main(String[] args) {
        int input = utils.readNatural();
        System.out.printf("треугольное число - %d.\n",summInRange(input));
        long factorial = factorial(input);
        if (factorial < 1){System.out.printf("Слишком большое число для расчета факториала.\n");}
        else {System.out.printf("факториал числа - %d.\n", factorial(input));}
    }
}