package labor.model;

public class User {
    protected int id;
    protected String name;
    protected String surname;
    protected String user_id;
    protected String user_password;

    public User(String name, String surname, String user_id, String user_password, int id) {
        this.name = name;
        this.surname = surname;
        this.user_id = user_id;
        this.user_password = user_password;
        this.id = id;
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void log_info() {
        System.out.println("Giriş yapan kullanıcı :" + this.name + " " + this.surname);
    }
}