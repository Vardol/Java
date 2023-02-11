package Notebook;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {

        //Ноутбук и Фильтр вынесены в отдельные классы.

        //30 - магическое число. Мне нравится 30.
        System.out.println("В наличии 30 ноутбуков:");

        //Генерируем 30 слуайных ноутбуков
        LinkedList<Notebook> notebooksList = new LinkedList<Notebook>();
        for (int i = 0; i < 30; i++) {
            notebooksList.add(new Notebook());
            System.out.println((i + 1) + ". " + notebooksList.get(i)); //выводим их простой sysout, т.к. мы озаботились override для toString в нашем классе Notebook
        }


        System.out.println("");

        //Наполняем новй список ноутбуков результатом фильтрации с учетом считанного от пользователя фильтра.
        //Для этого создаем объект нашего класса NotebookFilter, в котором реализован этот функционал
        NotebookFilter notebookFilter = new NotebookFilter();
        LinkedList<Notebook> filteredNotebooksList = notebookFilter.filterNotebooks(notebookFilter.readFilter(), notebooksList);
        //пишем сколько получилось подходящих ноутов и выводим их списком
        System.out.println("\nФильтрам соответствуют " + filteredNotebooksList.size() + " ноутбуков:"); 
        for (int i = 0; i < filteredNotebooksList.size(); i++) {
            System.out.println((i + 1) + ". " + filteredNotebooksList.get(i));
        }
    }
}
