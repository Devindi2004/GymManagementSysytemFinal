package org.example.gymmanagementsystem.dto;

public class SupplierDTO {
    private String supplierId;
    private String name;
    private String contact;

    public SupplierDTO(String supplierId, String name, String contact) {
        this.supplierId = supplierId;
        this.name = name;
        this.contact = contact;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

}