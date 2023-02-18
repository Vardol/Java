package Notebook;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;


public class NotebookFilter{
    
    //метод фильтрации - принимает на вход фильтр в формате Map и список ноутбуков.
    //делает полную копию переданного Листа Ноутбуков.
    //Проверяет Map на наличие типовых ключей для фильтрации.
    //если ключ найден - пробегает по скопированному спииску, удаляя несоответствующие критерию ноутбуки
    //если ключ не найден - он не будет обработан
    

    public static LinkedList<Notebook> filterNotebooks(Map<String, String> filter, LinkedList<Notebook> notebookList){
        LinkedList<Notebook> result = new LinkedList<>();
        result.addAll(notebookList);
        
        //проверка ключа name
        if (filter.containsKey("name")){
            String name = filter.get("name");
            for (int i = 0; i < result.size(); i++) {
                if (!name.equalsIgnoreCase(result.get(i).getName())){
                    result.remove(i);
                    i--;
                }
            }
        }

//проверка ключа processor с упрощенным написанием
        if (filter.containsKey("CPU") || filter.containsKey("cpu") || filter.containsKey("processor")){
            String processor = "";
            if (filter.containsKey("CPU")){
                processor = filter.get("CPU");
            }
            if (filter.containsKey("cpu")){
                processor = filter.get("cpu");
            }
            if (filter.containsKey("processor")){
                processor = filter.get("processor");
            }
            for (int i = 0; i < result.size(); i++) {
                if (!processor.equalsIgnoreCase(result.get(i).getProcessor())){
                    result.remove(i);
                    i--;
                }
            }
        }

//дальшу все по аналогии
        if (filter.containsKey("colour") || filter.containsKey("color")){
            String colour = "";
            if (filter.containsKey("colour")){
                colour = filter.get("colour");
            }
            if (filter.containsKey("color")){
                colour = filter.get("color");
            }
            for (int i = 0; i < result.size(); i++) {
                if (!colour.equalsIgnoreCase(result.get(i).getColour())){
                    result.remove(i);
                    i--;
                }
            }
        }

        if (filter.containsKey("os")){
            String OS = filter.get("os");
            for (int i = 0; i < result.size(); i++) {
                if (!result.get(i).getOS().toLowerCase().contains((CharSequence)OS)){
                    result.remove(i);
                    i--;
                }
            }
        }

        if (filter.containsKey("graphicsCard") || filter.containsKey("video") || filter.containsKey("videocard")){
            String graphicsCard = "";
            if (filter.containsKey("video")){
                graphicsCard = filter.get("video");
            }
            if (filter.containsKey("videocard")){
                graphicsCard = filter.get("videocard");
            }
            if (filter.containsKey("graphicsCard")){
                graphicsCard = filter.get("graphicsCard");
            }
            for (int i = 0; i < result.size(); i++) {
                if (!graphicsCard.equalsIgnoreCase(result.get(i).getGraphicsCard())){
                    result.remove(i);
                    i--;
                }
            }
        }

        // для цифровых значений предусмотрены по 2 фильтра: минимум и максимум
        if (filter.containsKey("minhdd")){
            int minhddSize = Integer.parseInt(filter.get("minhdd")); //методы с цифрами будут кидать forat exception при некорректном формате ввода
            for (int i = 0; i < result.size(); i++) {
                if (minhddSize > result.get(i).getHdd()){
                    result.remove(i);
                    i--;
                }
            }
        }

        if (filter.containsKey("maxhdd")){
            int maxhddSize = Integer.parseInt(filter.get("maxhdd"));
            for (int i = 0; i < result.size(); i++) {
                if (maxhddSize < result.get(i).getHdd()){
                    result.remove(i);
                    i--;
                }
            }
        }
    
        if (filter.containsKey("minram")){
            int minramSize = Integer.parseInt(filter.get("minram"));
            for (int i = 0; i < result.size(); i++) {
                if (minramSize > result.get(i).getRam()){
                    result.remove(i);
                    i--;
                }
            }
        }

        if (filter.containsKey("maxram")){
            int maxramSize = Integer.parseInt(filter.get("maxram"));
            for (int i = 0; i < result.size(); i++) {
                if (maxramSize < result.get(i).getRam()){
                    result.remove(i);
                    i--;
                }
            }
        }

        if (filter.containsKey("minprice")){
            int minprice = Integer.parseInt(filter.get("minprice"));
            for (int i = 0; i < result.size(); i++) {
                if (minprice > result.get(i).getPrice()){
                    result.remove(i);
                    i--;
                }
            }
        }

        if (filter.containsKey("maxprice")){
            int maxprice = Integer.parseInt(filter.get("maxprice"));
            for (int i = 0; i < result.size(); i++) {
                if (maxprice < result.get(i).getPrice()){
                    result.remove(i);
                    i--;
                }
            }
        }

        return result;
    }


    //метод считывания фильтра в формате Map (для дальнейшей передачи в метод фильтрации)
    public static Map<String, String> readFilter(){
        Scanner scanner = new Scanner(System.in);
        String input = "";
        String[] currentFilter;
        Map<String, String> filter = new HashMap<>();

        //перечисляем пользователю доступные фильтры
        while (!input.equalsIgnoreCase("done")){
            System.out.println("Укажите фильтр, например: \"name Asus\". Доступные фильтры:\n- name - фильтр по имени;\n- minhdd - минимальный размер HDD;\n- maxhdd - максимальный размер HDD;");
            System.out.println("- cpu - фильтр по типу процессора;\n- minram - минимальный размер ОЗУ;\n- maxram - максимальный размер ОЗУ;");
            System.out.println("- video - фильтр по видеокарте;\n- minprice - минимальна цена;\n- maxprice - максимальная цена;");
            System.out.println("- color - фильтр по цвету;\n- OS - предустановленная операционная система;");
            input = scanner.nextLine();
            currentFilter = input.split(" ", 2);
            if (currentFilter.length > 1){
                filter.put(currentFilter[0].toLowerCase(), currentFilter[1].toLowerCase());    
            }
            System.out.println("Если вы установили все желаемые фильтры - введите \"done\"");
        }
        scanner.close();
        return filter;
        
    }


}