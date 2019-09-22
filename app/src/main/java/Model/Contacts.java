package Model;

public class Contacts {

    private String Name;
    private String Contact;

    public  Contacts(String Name,String Contact){
        this.Name = Name;
        this.Contact = Contact;
    }


    public Contacts() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }
}