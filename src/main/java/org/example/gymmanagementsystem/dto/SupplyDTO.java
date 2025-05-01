package org.example.gymmanagementsystem.dto;

public class SupplyDTO {
    private String supplierId;
    private String supplementId;

    public SupplyDTO(String supplierId, String supplementId) {
        this.supplierId = supplierId;
        this.supplementId = supplementId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplementId() {
        return supplementId;
    }

    public void setSupplementId(String supplementId) {
        this.supplementId = supplementId;
    }

}