package OOP01;

import java.util.Iterator;
import java.util.LinkedList;

public class HotDrinksVendingMachine implements VendingMachine {
    
    //Изначально счел, что будет корректнее сделать отдельный класс, являющийся итератором, который будет возвращаться из метода iterator().
    //А не делать весь класс вендинговый аппарат - итератором
    //Но при этом счел, что раз класс итератора имеет отношение только к этому классу, то надо сделать его встроенным
    //В итоге заметил, что итератор мне нужен просто по коллекции напитков, хранящейся в LinkedListе, где итератор уже реализован
    //поэтому закомментил свою реализацию, и ниже сделал метод, который просто возвращает итератор LL.
    // protected class HotDrinksVendingMachineIterator implements Iterator<HotDrink> {
    //     private int count;
    //     private int size;
    //     private LinkedList<HotDrink> collection;

    //     HotDrinksVendingMachineIterator(HotDrinksVendingMachine hotDrinksVendingMachine){
    //         this.size = hotDrinksVendingMachine.getHotDrinksList().size();
    //         this.count = 0;
    //         this.collection = new LinkedList<HotDrink>(hotDrinksVendingMachine.getHotDrinksList()); //javadoc к такому конструктору намекнул мне, что в LL уже есть реализованный итератор
    //     }


    //     @Override
    //     public boolean hasNext() {
    //         return (count < (size - 1));
    //     }


    //     @Override
    //     public HotDrink next() {
    //         if (this.hasNext()){
    //             count++;
    //             return this.collection.get(count - 1);
    //         } else {
    //             return null;
    //         }
    //     }
        
    // }

    
    private LinkedList<HotDrink> hotDrinks;


    // Сначала сконструировал собственный итератор. Но потом понял, что список напитков хранится в LL, где уже есть реализованныый итератор.
    // Поскольку создатели LL явно лучше меня знают джаву, то не стал с ними спорить и закомментил свое творение.
    // Теперь на запрос итератора, я просто возвращаю итератор по коллекции напитков.
    // Но свой Итератор я тоже потестил - работает точно также, без изменения клиентского кода.
    // public Iterator<HotDrink> iterator(){
    //     return (new HotDrinksVendingMachineIterator(this));
    // }
    
 
    
    //Собственно вот тут я возвращаю итератор по коллекции напитков? взятый просто из LinkedList hotDrinks.
    public Iterator<HotDrink> iterator(){
    return (this.hotDrinks.iterator());
    }
    


    public HotDrinksVendingMachine(LinkedList<HotDrink> hotDrinks) {
        this.hotDrinks = new LinkedList<HotDrink>();
    }

    public HotDrinksVendingMachine() {
        this(new LinkedList<HotDrink>());
    }

    public String listDrinks(){
        String result = "";
        for (HotDrink hotDrink : hotDrinks) {
            result = result.concat(hotDrink.toString());
            result = result.concat(".\n");
        }
        result = result.substring(0, result.length() - 1);
        return result;
    }

    public LinkedList<HotDrink> getHotDrinksList(){
        return this.hotDrinks;
    }

    public int size(){
        return this.hotDrinks.size();
    }

    public void addHotDrink(HotDrink hotDrink){
        this.hotDrinks.add(hotDrink);
    }

    public void removeHotDrink(HotDrink hotDrink){
        if (this.hotDrinks.contains(hotDrink)){
            this.hotDrinks.remove(hotDrink);
        }
    }

    public HotDrink removeHotDrinkAtIndex(int index){
        return this.hotDrinks.remove(index);
    }

    public Item getProduct(String name, int volume, int temperature){
        Item result = null;
        boolean gotcha = true;
        for (HotDrink hotDrink : hotDrinks) {
            gotcha = true;
            if (!name.isEmpty() && !hotDrink.getName().equalsIgnoreCase(name)){
                gotcha = false;
            }
            if (volume > 0 && hotDrink.getVolume()!=volume){
                gotcha = false;
            }
            if (temperature !=Integer.MIN_VALUE && hotDrink.getTemperature()!=temperature){
                gotcha = false;
            }
            if (gotcha){
                this.getPayment(hotDrink.getPrice());
                this.removeHotDrink(hotDrink);
                result = hotDrink;
                return result;
            }
        }
        return result;
    }
    
    public Item getProduct(String name){
        return this.getProduct(name, -1, Integer.MIN_VALUE);
    }

    public Item getProduct(int temperature){
        return this.getProduct("", -1, temperature);
    }

    public void getPayment(int price){
        System.out.println("Payment recieved.");
    }

}
