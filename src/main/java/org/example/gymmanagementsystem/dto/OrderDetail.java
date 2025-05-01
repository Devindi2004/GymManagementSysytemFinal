package org.example.gymmanagementsystem.dto;

public class OrderDetail {
    private String orderId;
    private String supplementId;

    public OrderDetail(String orderId, String supplementId) {
        this.orderId = orderId;
        this.supplementId = supplementId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSupplementId() {
        return supplementId;
    }

    public void setSupplementId(String supplementId) {
        this.supplementId = supplementId;
    }

}