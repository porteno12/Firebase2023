package com.example.firebase2023;

public class Meeting {
    private String idMeeting;
    private String day;
    private String hour;
    private boolean status;
    private String idUser;

    public Meeting(String day, String hour, String idUser) {
        this.day = day;
        this.hour = hour;
        this.idUser = idUser;
    }

    public Meeting(String idMeeting, String day, String hour,
                   boolean status, String idUser) {
        this.idMeeting = idMeeting;
        this.day = day;
        this.hour = hour;
        this.status = status;
        this.idUser = idUser;
    }

    public String getIdMeeting() {
        return idMeeting;
    }

    public void setIdMeeting(String idMeeting) {
        this.idMeeting = idMeeting;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}
