package OOP01;

public abstract class Item {
    protected String name;
    protected int price;
    
    public Item(String name, int price){
        this.name = name;
        this.price = price;
    }

    
    public String getName(){
        return this.name;
    }
    
    public int getPrice(){
        return this.price;
    }

    
    
    @Override
    public String toString(){
        return String.format("%s, price: %d", this.name, this.price);
    }
}
