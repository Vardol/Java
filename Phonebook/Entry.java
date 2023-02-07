package Phonebook;

import java.util.LinkedList;

public class Entry {
    private String name;
    private LinkedList<String> phoneNumberList;


    public Entry(String name, LinkedList<String> phoneNumberList){
        this.name = name;
        this.phoneNumberList = phoneNumberList;
    }

    public Entry(String name){
        this.name = name;
        this.phoneNumberList = new LinkedList<String>();
    }

    protected void addPhone(String phoneNumber){
        this.phoneNumberList.add(phoneNumber);
    }

    protected void removePhone(int index){
        if (index < this.phoneNumberList.size() && index >= 0){
            this.phoneNumberList.remove(index);
        }
    }

    protected void removePhone(String phoneNumber){
        int index = this.phoneNumberList.indexOf(phoneNumber);
        if (index != -1){
            this.phoneNumberList.remove(index);
        }
    }

    protected void clearAllPhones(){
        this.phoneNumberList = new LinkedList<String>();
    }

    @Override
    public String toString(){
        String result = this.name;
        result += ": ";
        if (this.phoneNumberList.size() > 0) {
            for (String string : this.phoneNumberList) {
                result += string;
                result += ", ";
            }
            result = result.substring(0, result.length() - 3);
            result += ".";
        } else {
            result += "---";
        }
        return result;
    }

    protected LinkedList<String> getLinkedList(){
        LinkedList<String> result = new LinkedList<>();
        result.add(this.name);
        if (this.phoneNumberList.size() > 0) {
            for (String string : this.phoneNumberList) {
                result.add(string);
            }
        }
        return result;
    }

    protected String getName(){
        return this.name;
    }

    protected LinkedList<String> getPhones(){
        LinkedList<String> result = new LinkedList<>();
        if (this.phoneNumberList.size() > 0) {
            for (String string : this.phoneNumberList) {
                result.add(string);
            }
        } else {
            result.add("---");
        }
        return result;
    }
}
