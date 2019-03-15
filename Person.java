package sk.itsovy.javaSql;

import java.util.Date;

public class Person {
    private String name;
    private String surname;
    private Date dob;
    private String bnum;

    public Person(String name, String surname, Date dob, String pin) {
        this.name = name;
        this.surname = surname;
        this.dob = dob;
        this.bnum = pin;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Date getDob() {
        return dob;
    }

    public String getBnum() {
        return bnum;
    }
}
