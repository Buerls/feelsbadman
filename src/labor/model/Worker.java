package labor.model;

public class Worker extends User {
    private String assignment;

    public Worker(String name, String surname, String user_id, String user_password,int id,String assignment) {
        super(name,surname,user_id,user_password,id);
        this.assignment = assignment;
    }

    public String getAssignment() {
        return assignment;
    }

    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }
}
