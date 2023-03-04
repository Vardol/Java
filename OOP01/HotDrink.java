package OOP01;

public class HotDrink extends Drink{
    protected int tempretature;

    public HotDrink(String name, int price, int volume, int tempretature){
        super(name, price, volume);
        this.tempretature = tempretature;
    }

    @Override
    public String toString(){
        return String.format("%s, temperature: %d",super.toString(),this.tempretature);
    }

    @Override
    public boolean equals(Object o){
        if (o == this){
            return true;
        }

        if (!(o instanceof HotDrink)){
            return false;
        }

        HotDrink anotherHotDrink = (HotDrink) o;
        if (this.name.equals(anotherHotDrink.getName()) && this.tempretature == anotherHotDrink.getTemperature() && this.volume == anotherHotDrink.getVolume()){
            return true;
        }
        else {
            return false;
        }
    }

    public int getTemperature(){
        return this.tempretature;
    }

}
