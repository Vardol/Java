package OOP01;

public class Item {
    protected String name;
    protected int price;
    protected int volume;
    
    public Item(String name, int price, int volume){
        this.name = name;
        this.price = price;
        this.volume = volume;
    }

    
    public String getName(){
        return this.name;
    }
    
    public int getPrice(){
        return this.price;
    }
    
    public int getVolume(){
        return this.price;
    }
    
    
    @Override
    public String toString(){
        return String.format("%s, price: %d, volume %d", this.name, this.price, this.volume);
    }
}
