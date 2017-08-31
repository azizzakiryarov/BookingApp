package model;

public final class Customer {

    private int id;
    private String name;
    private String mail;
    private String tel;
    private String date;
    private String time;

    public Customer(int id, String name, String mail, String tel, String date, String time) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.tel = tel;
        this.date = date;
        this.time = time;
    }

    public Customer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer setName(String name) {
        this.name = name;
        return this;
    }

    public Customer setMail(String mail) {
        this.mail = mail;
        return this;
    }

    public Customer setTel(String tel) {
        this.tel = tel;
        return this;
    }

    public Customer setDate(String date) {
        this.date = date;
        return this;
    }

    public Customer setTime(String time) {
        this.time = time;
        return this;
    }

    @Override
    public String toString() {
        return "Customer: " +
                "id: " + id +
                ", name: " + name +
                ", mail: " + mail +
                ", tel: " + tel +
                ", date: " + date +
                ", time: " + time;

    }
}
