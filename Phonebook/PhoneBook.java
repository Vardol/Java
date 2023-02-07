package Phonebook;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

//Задание.
//1. Реализуйте структуру телефонной книги с помощью HashMap, учитывая, что 1 человек может иметь несколько телефонов.

//Сделал хэшмап из String, LinkedList -> соответственно в String хранится ФИО, и это - ключ. А в LL хранятся телефоны.

//Из спортивного интереса описал класс представляющим запись в книге - Entry, чтобы можно было определять поведение и свойства записи.
//Но чтобы реализовать через хэшмап, не пользовался этим классом, т.к. было бы только сложнее. 

public class PhoneBook {

    private static void printHelp(){
        System.out.println("Список доступных команд:\n- new -> создание новой записи.");
        System.out.println("- addphone -> добавление телефона к существующей записи.");
        System.out.println("- find -> отображение телефонов по введенному имени.");
        System.out.println("- show -> отображение вссей телефонной книги.");
        System.out.println("- delete -> удаление записи по введенному имени.");
        System.out.println("- removephone -> удаление номера телефона у указанного имени.");
        System.out.println("- dropall -> удаление всей телефонной книги.");
        System.out.println("- help -> вывод списка команд.");
        System.out.println("- exit -> выход.");
        System.out.println("\nКоманда и параметры (данные имени и телефонов) вводятся через пробел.\nФормат ввода команды: <КОМАНДА> <ИМЯ> <ТЕЛЕФОНЫ ЧЕРЕЗ ПРОБЕЛ>");
        System.out.println("Имя и телефон/телефоны вводятся при необходимости.>");
    }
    
    public static Map<String, LinkedList<String>> phoneBook = new HashMap<>();
    
    public static void main(String[] args) {
        System.out.println("Добро пожаловать в телефонную книгу.");
        printHelp();
        Scanner scanner = new Scanner(System.in);
        String name = "";
        LinkedList<String> phonelist = new LinkedList<>();

        while (true) {
            System.out.println("\nВведите команду. Для выхода - exit.");
            LinkedList<String> input = new LinkedList<>(Arrays.asList(utils.checkQuit(scanner.nextLine()).split(" ", 0)));

            if (input.size() <= 0){
                System.out.println("Пустой ввод.\n");
                continue;
            }

            String command = input.remove(0).toLowerCase();
            switch (command) {
                case "new":
                    if (input.size() >= 1){
                        if (!phoneBook.containsKey(input.get(0))){
                            name = input.remove(0);
                            phoneBook.putIfAbsent(name, input);
                        } else{
                            System.out.println("Запись уже присутствует в книге.\nВоспользуйтесь addphone, для добавления к ней телефонов");
                        }
                    } else {
                        System.out.println("Пустая запись.");
                    }
                    break;
            
                case "addphone":
                    if (input.size() >= 2){
                        if (phoneBook.containsKey(input.get(0))){
                            name = input.remove(0);
                            for (String string : phoneBook.get(name)) {
                                input.add(string);
                            }
                            phoneBook.put(name, input);
                        } else{
                            System.out.println("Запись отсутствует в книге.");
                        }
                    } else {
                        System.out.println("Слишком короткая запись.");
                    }
                    break;

                case "find":
                    if (input.size() >= 1){
                        if (phoneBook.containsKey(input.get(0))){
                            name = input.remove(0);
                            System.out.println(name + " - " + phoneBook.get(name));
                            } else {
                            System.out.println("Нет такой записи.");
                        }
                    } else {
                        System.out.println("Пустая запись.");
                    }
                    break;


                case "show":
                    System.out.println(phoneBook);
                    break;

                case "delete":
                    if (input.size() >= 1){
                        if (phoneBook.containsKey(input.get(0))){
                            name = input.remove(0);
                            phoneBook.remove(name);
                        } else {
                            System.out.println("Нет такой записи.");
                        }
                    } else {
                        System.out.println("Пустая запись.");
                    }
                    break;

                case "removephone":
                    if (input.size() >= 2){
                        if (phoneBook.containsKey(input.get(0))){
                            name = input.remove(0);
                            phonelist = phoneBook.get(name);
                            if (phonelist.contains(input.get(0))){
                                phonelist.remove(input.get(0));
                            } else {
                                System.out.println("Нет такого телефона.");
                            }
                        } else {
                            System.out.println("Нет такой записи.");
                        }
                    } else {
                        System.out.println("Слишком короткая запись.");
                    }
                    break;

                case "dropall":
                    phoneBook = new HashMap<>();
                    break;

                case "help":
                    printHelp();
                    break;

                default:
                    System.out.println("Нераспознанная команда.");
                    break;
            }


        }
    }
}
