package org.example.gymmanagementsystem.dto;

public class TrainerSessionDTO {
    private String sessionId;
    private String memberId;

    public TrainerSessionDTO(String sessionId, String memberId) {
        this.sessionId = sessionId;
        this.memberId = memberId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

}