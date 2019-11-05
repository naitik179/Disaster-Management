package Model;

import com.example.disaster_management_v2.utils.PDFCreationUtils;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListItem {
    private String name;
    private String gender;
    private String age;
    private String address;

    public ListItem(String name, String gender, String age, String address) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.address = address;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public String getAge() {
        return age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(String age) {
        this.age = age;
    }
    /* public static List<ListItem> createDummyPdfModel() {
        PDFCreationUtils.filePath.clear();
        PDFCreationUtils.progressCount = 1;


        List<ListItem> pdfModels = new ArrayList<>();


            ListItem model = new ListItem();

        return pdfModels;
    }*/
}
