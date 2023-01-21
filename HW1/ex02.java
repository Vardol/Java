//Вывести все простые числа от 1 до 1000

package HW1;

public class ex02 {
    static boolean checkIfSimple(int input) {
        if (input <= 1) {return false;}
        if (input == 2) {return true;}
        if (input == 3) {return true;}
        if (input % 2 == 0) {return false;}
        for (int i = 3; i < input; i += 2) {
            if (input % i == 0){return false;}
        }
        return true;
    }


    public static void main(String[] args) {
        String result = "";
        for (int i = 0; i <= 1000 ; i++) {
            if (checkIfSimple(i)) {
                result += i;
                result += ", ";
            }
        }
        System.out.println(result);
    }
}
