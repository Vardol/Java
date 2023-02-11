package Notebook;

import java.util.Random;

public class Notebook {
    //список значений для случайной генерации ноутбуков
    public final static String[] NAME_LIST = {"Asus","Macbook","Acer","Lenovo","ICL"};
    public final static int[] HDD_SIZE_LIST = {100,200,250,300,350,500,750,1000,1500,2000};
    public final static int[] RAM_LIST = {1,2,4,8,16};
    public final static String[] PROCESSOR_LIST = {"i3","i5","i7","i9","AMD"};
    public final static String[] OS_LIST = {"none","Windows 7","Windows 8","Windows 10"};
    public final static String[] COLOUR_LIST = {"grey","blue","red","black","white","purple with orange dots"};
    public final static String[] GRAPHICSCARD_LIST = {"Radeon","Nvidia","none"};


    //список полей
    private String name;
    private int hddSize;
    private int ram;
    private String processor;
    private int diagonal;
    private String OS;
    private String colour;
    private String graphicsCard;
    private int price;
    private boolean isOn; //на мой взгляд, способность включаться - неотъемлемое свойство ноутбука, инча он не вполне им является...



    @Override //для красивого и просто вывода
    public String toString() {
        String result = "";
        return result.concat(this.name).concat(", HDD: ").concat(Integer.toString(this.hddSize)).concat("GB, RAM: ").concat(Integer.toString(this.ram)).
        concat("GB, CPU: ").concat(this.processor).concat(", screen diagonal: ").concat(Integer.toString(this.diagonal)).
        concat("\", установленная OS: ").concat(OS).concat(", цвет: ").concat(", видеокарта: ").concat(graphicsCard).
        concat(". Стоимость: ").concat(Integer.toString(this.price)).concat(" руб.");
    }

    //стандартный конструктор с передачей всех полей
    public Notebook(String name, int hddSize, int ram, String processor, int diagonal, String OS, String colour, String graphicsCard, int price) {
        this.name = name;
        this.hddSize = hddSize;
        this.ram = ram;
        this.processor = processor;
        this.diagonal = diagonal;
        this.OS = OS;
        this.colour = colour;
        this.graphicsCard = graphicsCard;
        this.price = price;
        this.isOn = false;
    }

    //рандомный конструктор, который генерит случайный ноутбук
    public Notebook() {
        Random rand = new Random();
        this.name = NAME_LIST[rand.nextInt(NAME_LIST.length)];
        this.hddSize = HDD_SIZE_LIST[rand.nextInt(HDD_SIZE_LIST.length)];
        this.ram = RAM_LIST[rand.nextInt(RAM_LIST.length)];
        this.processor = PROCESSOR_LIST[rand.nextInt(PROCESSOR_LIST.length)];
        this.diagonal = rand.nextInt(9) + 16;
        this.OS = OS_LIST[rand.nextInt(OS_LIST.length)];
        this.colour = COLOUR_LIST[rand.nextInt(COLOUR_LIST.length)];
        this.graphicsCard = GRAPHICSCARD_LIST[rand.nextInt(GRAPHICSCARD_LIST.length)];
        this.price = rand.nextInt(100)*1000 + 20000;
        this.isOn = false;
    }

    //геттеры
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getGraphicsCard() {
        return graphicsCard;
    }

    public String getProcessor() {
        return processor;
    }

    public String getColour() {
        return colour;
    }

    public int getDiagonal() {
        return diagonal;
    }

    public int getHdd() {
        return hddSize;
    }

    public String getOS() {
        return OS;
    }

    public int getRam() {
        return ram;
    }

    public boolean isOn() {
        return isOn;
    }

    //включение и выключение ноута
    public boolean turnOn(){
        if (this.isOn){
            return false;
        } else{
            this.isOn = true;
            return true;
        }
    }

    public boolean turnOff(){
        if (!this.isOn){
            this.isOn = false;
            return true;
        } else{
            return false;
        }
    }

}
