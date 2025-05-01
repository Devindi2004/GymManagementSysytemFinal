package org.example.gymmanagementsystem.dto;

public class SupplementDTO {
    private String supplementId;
    private String name;
    private String description;

    public SupplementDTO(String supplementId, String name, String description) {
        this.supplementId = supplementId;
        this.name = name;
        this.description = description;
    }

    public String getSupplementId() {
        return supplementId;
    }

    public void setSupplementId(String supplementId) {
        this.supplementId = supplementId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}