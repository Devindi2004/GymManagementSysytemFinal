package org.example.gymmanagementsystem.dto;

public class ClassEquipmentDTO {
    private String equipId;
    private String classId;

    public ClassEquipmentDTO(String equipId, String classId) {
        this.equipId = equipId;
        this.classId = classId;
    }

    public String getEquipId() {
        return equipId;
    }

    public void setEquipId(String equipId) {
        this.equipId = equipId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

}