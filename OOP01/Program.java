package OOP01;

import java.util.Iterator;

public class Program {
    public static void main(String[] args) {
        HotDrinksVendingMachine coffeeVendingMachine = new HotDrinksVendingMachine();
        coffeeVendingMachine.addHotDrink(new HotDrink("Cappuccino", 250, 250, 60));
        coffeeVendingMachine.addHotDrink(new HotDrink("Latte", 250, 300, 50));
        coffeeVendingMachine.addHotDrink(new HotDrink("Espresso", 150, 50, 60));
        coffeeVendingMachine.addHotDrink(new HotDrink("Americano", 150, 200, 50));
        coffeeVendingMachine.addHotDrink(new HotDrink("Hot milk", 100, 200, 80));
        System.out.println("Содержимое кофейного аппарата: ");
        //**использование итератора по 3 заданию**
        Iterator<HotDrink> iter = coffeeVendingMachine.iterator();
        while (iter.hasNext()){
            System.out.println(iter.next());
        }
        System.out.println("---------------");
        

        //**старый код с задания к 1 семинару**
        HotDrinksVendingMachine teaVendingMachine = new HotDrinksVendingMachine();
        teaVendingMachine.addHotDrink(new HotDrink("Black Tea", 150, 300, 60));
        teaVendingMachine.addHotDrink(new HotDrink("Red Tea", 150, 300, 60));
        teaVendingMachine.addHotDrink(new HotDrink("Green Tea", 150, 300, 60));
        teaVendingMachine.addHotDrink(new HotDrink("Fruit Tea", 150, 300, 60));
        teaVendingMachine.addHotDrink(new HotDrink("Boiling water", 30, 250, 95));
        
        System.out.println("Содержимое чайного аппарата: ");
        System.out.println(teaVendingMachine.listDrinks());
        System.out.println("---------------");
        System.out.println("Берем черный чай из кофейного аппарата");
        System.out.println(coffeeVendingMachine.getProduct("Black tea"));
        System.out.println("---------------");
        System.out.println("Берем черный чай из чайного аппарата");
        System.out.println(teaVendingMachine.getProduct("Black tea"));

        System.out.println("---------------");
        System.out.println("Берем капуч объемом 250, температурой 60");
        System.out.println(coffeeVendingMachine.getProduct("Cappuccino", 250, 60));

        
        System.out.println("---------------");
        System.out.println("Содержимое кофейного аппарата: ");
        System.out.println(coffeeVendingMachine.listDrinks());
        System.out.println("---------------");
        System.out.println("Содержимое чайного аппарата: ");
        System.out.println(teaVendingMachine.listDrinks());


    }
}
