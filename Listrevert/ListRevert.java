package Listrevert;

import java.util.LinkedList;

public class ListRevert {

// Необходимо реализовать метод разворота связного списка (двухсвязного или односвязного на выбор)..

        // Реализация метода по заданию. Специально делал без указания типа, чтобы работал с разными типами данных. Вроде работает.
        // можно, как вариант поставить везде "int" - тоже будет работать
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
