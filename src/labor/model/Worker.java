package labor.model;

public class Worker extends User {
    private String assignment;

    public Worker(String name, String surname, String user_id, String user_password, int id, String assignment) {
        super(name, surname, user_id, user_password, id);
        this.assignment = assignment;
    }

    public String getAssignment() {
        return assignment;
    }

    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }

    public void log_info() {
        System.out.println("Giriş yapan çalışan : " + this.name + " " + this.surname + ", Yetkisi : " + this.assignment);
    }
}
