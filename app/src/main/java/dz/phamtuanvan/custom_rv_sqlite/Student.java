package dz.phamtuanvan.custom_rv_sqlite;

public class Student {
    private int Id;
    private String Name;
    private String Number;

    public Student(int id, String name, String number) {
        Id = id;
        Name = name;
        Number = number;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }
}
