import java.util.Date;

public class Person {

    private String name;
    private String surname;
    private Date dob;
    private String bnum;

    public Person(String name, String surname, Date dob, String bnum) {
        this.name = name;
        this.surname = surname;
        this.dob = dob;
        this.bnum = bnum;
    }

    public Person(String name, java.sql.Date dob, String bnum) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getBnum() {
        return bnum;
    }

    public void setBnum(String bnum) {
        this.bnum = bnum;
    }
}