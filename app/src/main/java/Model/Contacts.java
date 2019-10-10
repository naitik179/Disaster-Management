package Model;


public class Contacts {
    private String Name;
    private String Contact;

    public Contacts() {
    }

    public Contacts(String name, String contact) {
        Name = name;
        Contact = contact;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }
}