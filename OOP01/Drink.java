package OOP01;

public abstract class Drink extends Item {
    protected int volume;

    
    
    public Drink(String name, int price, int volume) {
        super(name, price);
        this.volume = volume;
    }
    
    public int getVolume() {
        return this.volume;
    }

    @Override
    public String toString(){
        return String.format("%s, price: %d, volume %d", this.name, this.price, this.volume);
    }
}
