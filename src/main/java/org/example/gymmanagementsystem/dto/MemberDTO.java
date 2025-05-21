package org.example.gymmanagementsystem.dto;

public class MemberDTO {
    private String memberId;
    private String dietPlanId;
    private String name;
    private String email;
    private String phone;
    private String age;

    public MemberDTO(String memberId, String dietPlanId, String name, String email, String phone, String age) {
        this.memberId = memberId;
        this.dietPlanId = dietPlanId;
        this.age = age;
        this.name = name;
        this.phone = phone;
        this.email = email;


    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getDietPlanId() {
        return dietPlanId;
    }

    public void setDietPlanId(String dietPlanId) {
        this.dietPlanId = dietPlanId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

}