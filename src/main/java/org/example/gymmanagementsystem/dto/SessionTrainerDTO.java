package org.example.gymmanagementsystem.dto;

public class SessionTrainerDTO {
    private String sessionId;
    private String trainerId;

    public SessionTrainerDTO(String sessionId, String trainerId) {
        this.sessionId = sessionId;
        this.trainerId = trainerId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(String trainerId) {
        this.trainerId = trainerId;
    }

}