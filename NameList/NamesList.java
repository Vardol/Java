package NameList;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

//Пусть дан список сотрудников...
//Написать программу, которая найдет и выведет повторяющиеся имена с количеством повторений.
//Отсортировать по убыванию популярности.

public class NamesList {
    public final static String[] NAMES = {"Иван Иванов", "Светлана Петрова", "Кристина Белова", "Анна Мусина", "Анна Крутова", "Иван Юрин", "Петр Лыков", "Павел Чернов", "Петр Чернышов", "Мария Федорова", "Марина Светлова", "Мария Савина", "Мария Рыкова", "Марина Лугова", "Анна Владимирова", "Иван Мечников", "Петр Петин", "Иван Ежов"};

    public static void main(String[] args) {
        String[][] namesList = new String[NAMES.length][2]; //Здесь будем хранить ФИО в разбивке на отдельные строки
        Map<String, Integer> countersTable = new HashMap<>(); //Здесь будем хранить количество повторов имени
        Integer counter = 0; //счетчик повторов имени

        //разбиваем сплитом на имя и фамилию, чтобы аботать отдельно с именами
        for (int i = 0; i < namesList.length; i++) {
            namesList[i] = NAMES[i].split(" ", 2);
        }
        //пробегаем циклами по ФИО, считая количество повторов каждого имени. По итогу кладем количество повторений на ключ Имя в наш Map
        //перед циклом проверяем - не смотрели ли мы уже повторы этого имени (наличие данного ключа в Map)
        for (int i = 0; i < namesList.length; i++) {
            String checkedName = namesList[i][0];
            counter = 0;
            if (!countersTable.containsKey(checkedName)){
                for (int j = i; j < namesList.length; j++) {
                    if (checkedName.equalsIgnoreCase(namesList[j][0])){
                        counter++;
                    }
                }

                countersTable.put(checkedName, counter);
            }
        }


        //выводим крезультат (количество повторов каждого имени)
        System.out.println(countersTable);


        //сортируем теперь обычным пузырьком по значению счетчика
        String[] tempValue;
        int maxValueIndex;
        for (int i = 0; i < namesList.length - 1; i++) {
            maxValueIndex = i;
            for (int j = i + 1; j < namesList.length; j++) {
                if (countersTable.get(namesList[j][0]) > countersTable.get(namesList[maxValueIndex][0])){
                    maxValueIndex = j;
                }
            }
            tempValue = namesList[i];
            namesList[i] = namesList[maxValueIndex];
            namesList[maxValueIndex] = tempValue;
        }

        //выводим результат. Задачи сортировать по алфаввиту не стояло, поэтому имена, встречающиеся с одинаковой частотой - вперемешку.
        for (String[] strings : namesList) {
            System.out.println(strings[0] + " " + strings[1]);
        }
    }
}
