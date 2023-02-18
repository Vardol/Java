package OOP01;

import java.util.LinkedList;

public class HotDrinksVendingMachine implements VendingMachine {
    private LinkedList<HotDrink> hotDrinks;

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
