package main;

public class User {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String secQuestion;
    private String secAnswer;
    private String role;
    private int empId;
    private String status;

    private String previousTable;



    public User()
    {

    }

    public User(String fName, String lName, String userName, String password, String secQuestion,
                String secAnswer, String role, int empId, String status, String previousDesk)
    {
        this.firstName = fName;
        this.lastName = lName;
        this.username = userName;
        this.password = password;
        this.secQuestion = secQuestion;
        this.secAnswer = secAnswer;
        this.role = role;
        this.empId = empId;
        this.status = status;
        this.previousTable = previousDesk;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String fName) {
        this.firstName = fName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecQuestion() {
        return secQuestion;
    }

    public void setSecQuestion(String secQuestion) {
        this.secQuestion = secQuestion;
    }

    public String getSecAnswer() {
        return secAnswer;
    }

    public void setSecAnswer(String secAnswer) {
        this.secAnswer = secAnswer;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPreviousTable() {
        return previousTable;
    }

    public void setPreviousTable(String previousTable) {
        this.previousTable = previousTable;
    }
}
