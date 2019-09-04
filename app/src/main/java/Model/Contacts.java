package Model;

public class Contacts {

    private String name;
    private String mob;

    public  Contacts(String name,String mob){
        this.name = name;
        this.mob = mob;
    }

    public String getName(){
        return name;
    }

    public String getMob(){
        return mob;
    }
}