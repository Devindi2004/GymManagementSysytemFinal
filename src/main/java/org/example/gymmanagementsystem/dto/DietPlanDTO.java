package org.example.gymmanagementsystem.dto;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString


public class DietPlanDTO  {
    private String dietPlanId;
    private String planName;
    private String food;
    private String drink;

}