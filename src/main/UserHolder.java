package main;

import java.time.LocalDate;
import java.util.Date;

public class UserHolder
{

    private User user;

    //Used for passing user details
    private int empId;

    //Used to store booking details when selecting a table to book
    private String TableNo;
    private LocalDate date;

    private String condition = "No Restriction";

    private LocalDate conditionDate;
    private final static UserHolder INSTANCE = new UserHolder();

    private UserHolder() {}

    public static UserHolder getInstance()
    {
        return INSTANCE;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getTableNo() {
        return TableNo;
    }

    public void setTableNo(String tableNo) {
        TableNo = tableNo;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public LocalDate getConditionDate() {
        return conditionDate;
    }

    public void setConditionDate(LocalDate conditionDate) {
        this.conditionDate = conditionDate;
    }
}
