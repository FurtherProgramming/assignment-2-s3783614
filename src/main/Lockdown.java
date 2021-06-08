package main;

import java.time.LocalDate;

public class Lockdown {
    private String lockdownStatus;
    private LocalDate lockdownDate;

    public Lockdown()
    {

    }

    public Lockdown(String lockdownStatus, LocalDate lockdownDate) {
        this.lockdownStatus = lockdownStatus;
        this.lockdownDate = lockdownDate;
    }

    public String getLockdownStatus() {
        return lockdownStatus;
    }

    public void setLockdownStatus(String lockdownStatus) {
        this.lockdownStatus = lockdownStatus;
    }

    public LocalDate getLockdownDate() {
        return lockdownDate;
    }

    public void setLockdownDate(LocalDate lockdownDate) {
        this.lockdownDate = lockdownDate;
    }
}
