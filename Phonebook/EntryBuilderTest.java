package Phonebook;
import Phonebook.Entry;
import Phonebook.Entry.Builder;

public class EntryBuilderTest {

    public static void main(String[] args) {
        Entry me = new Entry.Builder().firstName("Rinat").secondName("Minachev")
        .comments("testing").email("abc@mail.ru").build();
    
        Entry another = new Entry.Builder().firstName("Вася").secondName("Петров")
        .companyName("Microsoft").companyPosition("CEO").comments("проверка")
        .email("WGates@microsoft.com").build();


        System.out.println(me);
        System.out.println(another);
    }


    

}
