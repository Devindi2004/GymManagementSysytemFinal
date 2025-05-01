package org.example.gymmanagementsystem.dto;

public class OrderDTO {
    private String orderId;
    private String memberId;
    private String name;
    private String date;
    private String amount;

    public OrderDTO(String orderId, String memberId, String name, String date, String amount) {
        this.orderId = orderId;
        this.memberId = memberId;
        this.name = name;
        this.date = date;
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}