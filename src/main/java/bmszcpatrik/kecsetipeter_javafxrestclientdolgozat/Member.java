package bmszcpatrik.kecsetipeter_javafxrestclientdolgozat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Member {
    private int id;
    @SerializedName("nev")
    private String name;
    @SerializedName("minosites")
    private String minosites;
    @SerializedName("meret")
    private String size;
    @SerializedName("eletkor")
    private int age;
    @SerializedName("altalanos")
    private boolean elementary;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMinosites() {
        return minosites;
    }

    public void setMinosites(String minosites) {
        this.minosites = minosites;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isElementary() {
        return elementary;
    }

    public void setElementary(boolean elementary) {
        this.elementary = elementary;
    }

    public Member(int id, String name, String size, int age, String minosites, boolean elementary) {
        this.id = id;
        this.name = name;
        this.minosites = minosites;
        this.size = size;
        this.age = age;
        this.elementary = elementary;
    }

}
