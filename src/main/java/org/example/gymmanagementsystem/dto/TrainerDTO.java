package org.example.gymmanagementsystem.dto;

public class TrainerDTO {
    private String trainerId;
    private String name;
    private String contact;

    public TrainerDTO(String trainerId, String name, String contact) {
        this.trainerId = trainerId;
        this.name = name;
        this.contact = contact;
    }

    public String getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(String trainerId) {
        this.trainerId = trainerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

}