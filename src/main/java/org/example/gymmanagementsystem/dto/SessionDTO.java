package org.example.gymmanagementsystem.dto;

public class SessionDTO {
    private String sessionId;
    private String classId;
    private String type;
    private String time;
    private String date;

    public SessionDTO(String sessionId, String classId, String type, String time, String date) {
        this.sessionId = sessionId;
        this.classId = classId;
        this.type = type;
        this.time = time;
        this.date = date;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}