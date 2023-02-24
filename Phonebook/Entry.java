package Phonebook;

import java.util.LinkedList;

public class Entry {

    public static class Builder {
        private Entry instance = new Entry();
        
        public Builder firstName(String firstName){
            this.instance.setFirstName(firstName);
            return this;
        }

        public Builder secondName(String secondName){
            this.instance.setSecondName(secondName);
            return this;
        }

        public Builder email(String email){
            this.instance.setEmail(email);
            return this;
        }

        public Builder companyName(String companyName){
            this.instance.setCompanyName(companyName);
            return this;
        }


        public Builder companyPosition(String companyPosition){
            this.instance.setCompanyPosition(companyPosition);
            return this;
        }

        public Builder phoneNumberList(LinkedList<String> phoneNumberList){
            this.instance.phoneNumberList = new LinkedList<String>(phoneNumberList);
            return this;
        }

        public Builder comments(String comments){
            this.instance.setComments(comments);
            return this;
        }

        public Entry build(){
            if (this.instance.phoneNumberList == null){
                this.instance.phoneNumberList = new LinkedList<String>();
            }
            if (this.instance.isEmpty()){
                return null;
            } else {
                return this.instance;
            }
        }

    }

    private String firstName;
    private String secondName;
    private String email;
    private String companyName;
    private String companyPosition;
    private String comments;
    private LinkedList<String> phoneNumberList;


    /**
     * @return returns TRUE if all String fields return TRUE in isEmpty(). Field 'comments' is ignored. 
     */
    public boolean isEmpty(){
        return (this.firstName.isEmpty() && this.secondName.isEmpty() && this.email.isEmpty()
                && this.companyName.isEmpty() && this.companyPosition.isEmpty());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyPosition() {
        return companyPosition;
    }

    public void setCompanyPosition(String companyPosition) {
        this.companyPosition = companyPosition;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    private Entry(){
        super();
    }

    public Entry(String firstName, LinkedList<String> phoneNumberList){
        this.firstName = firstName;
        this.phoneNumberList = phoneNumberList;
    }

    public Entry(String firstName){
        this.firstName = firstName;
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
        String result = "";
        if (this.firstName != null){
            result = result.concat(this.firstName);
        }

        if (this.secondName != null){
            result = result.concat(" ").concat(this.secondName);
        }

        if (result.isEmpty()){
            result = result.concat("n/a");
        }

        result = result.concat(".");

        if (this.email != null){
            result = result.concat(" e-mail: ").concat(this.email).concat(",");
        }

        if (this.companyName != null){
            result = result.concat(" Company name: ").concat(this.companyName).concat(",");
        }

        if (this.companyPosition != null){
            result = result.concat(" Company position: ").concat(this.companyPosition).concat(",");
        }

        result = result.concat(" phones: ");

        if (this.phoneNumberList.size() > 0) {
            for (String string : this.phoneNumberList) {
                result = result.concat(string).concat(", ");
            }
            result = result.substring(0, result.length() - 3);
            result += ".\n";
        } else {
            result += "---\n";
        }

        if (this.comments != null){
            result = result.concat("Commentaries: ").concat(this.comments);
        }

        return result;
    }

    protected LinkedList<String> getLinkedList(){
        LinkedList<String> result = new LinkedList<>();
        result.add(this.firstName);
        if (this.phoneNumberList.size() > 0) {
            for (String string : this.phoneNumberList) {
                result.add(string);
            }
        }
        return result;
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
