package OOP01;

import java.util.Iterator;
import java.util.LinkedList;

//после параметризации добавил ряд абстрактных методов с параметрическим аргументом.
//Раньше их не добавлял, т.к. сигнатуру непонятно как было описывать. А теперь норм )
public interface VendingMachine<I extends Item> {
    public I getProduct(String name);
    public void remove(I i);
    public void add(I i);
    public void getPayment(int price);
    public Iterator<I> iterator();
    public LinkedList<I> getList();
}
