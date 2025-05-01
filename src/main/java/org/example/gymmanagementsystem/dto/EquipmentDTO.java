package org.example.gymmanagementsystem.dto;

public class EquipmentDTO {
    private String equipId;
    private String supplierId;
    private String name;
    private String type;

    public EquipmentDTO(String equipId, String supplierId, String name, String type) {
        this.equipId = equipId;
        this.supplierId = supplierId;
        this.name = name;
        this.type = type;
    }

    public String getEquipId() {
        return equipId;
    }

    public void setEquipId(String equipId) {
        this.equipId = equipId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}