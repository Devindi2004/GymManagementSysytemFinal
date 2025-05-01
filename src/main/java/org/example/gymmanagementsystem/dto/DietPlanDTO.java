package org.example.gymmanagementsystem.dto;

public class DietPlanDTO {
    private String dietPlanId;
    private String planName;
    private String food;
    private String drink;

    public DietPlanDTO(String dietPlanId, String planName, String food, String drink) {
        this.dietPlanId = dietPlanId;
        this.planName = planName;
        this.food = food;
        this.drink = drink;
    }

    public String getDietPlanId() {
        return dietPlanId;
    }

    public void setDietPlanId(String dietPlanId) {
        this.dietPlanId = dietPlanId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

}
