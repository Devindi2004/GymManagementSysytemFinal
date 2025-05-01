package org.example.gymmanagementsystem.dto;

public class AttendanceDTO {
    private String attendanceId;
    private String memberId;
    private String date;

    public AttendanceDTO(String attendanceId, String memberId, String date) {
        this.attendanceId = attendanceId;
        this.memberId = memberId;
        this.date = date;
    }

    public String getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(String attendanceId) {
        this.attendanceId = attendanceId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}