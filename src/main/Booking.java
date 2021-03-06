package main;

import java.time.LocalDate;

public class Booking
{

    private int bookingId;
    private String empUsername;
    private LocalDate date;
    private String table;
    private String status;
    private String reservation;

    public Booking()
    {

    }

    public Booking(int bookingId, String empUsername, LocalDate date,
                   String table, String status, String reservation)
    {
        this.bookingId = bookingId;
        this.empUsername = empUsername;
        this.date = date;
        this.table = table;
        this.status = status;
        this.reservation = reservation;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getEmpUsername() {
        return empUsername;
    }

    public void setEmpUsername(String empUsername) {
        this.empUsername = empUsername;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReservation() {
        return reservation;
    }

    public void setReservation(String reservation) {
        this.reservation = reservation;
    }


}
