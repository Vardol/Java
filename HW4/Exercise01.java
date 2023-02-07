package HW4;

import java.util.LinkedList;

//1. Пусть дан LinkedList с несколькими элементами. Реализуйте метод, который вернет “перевернутый” список.


public class Exercise01 {

        // Реализация метода по заданию. Специально делал без указания типа, чтобы работал с разными типами данных. Вроде работает.
    static LinkedList reverseLinkedList(LinkedList inputList) {
        LinkedList result = new LinkedList();
        for (Object object : inputList) {
            result.addFirst(object);
        }
        return result;
    }

    public static void main(String[] args) {
        LinkedList myList = new LinkedList();
        myList.add(true);
        myList.add('!');
        myList.add(2023);
        myList.add("текст");
        myList.add(36.6);

        System.out.println(reverseLinkedList(myList));

    }
    
}