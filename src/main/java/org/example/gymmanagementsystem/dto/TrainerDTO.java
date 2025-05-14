package org.example.gymmanagementsystem.dto;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class TrainerDTO {
    private String trainerId;
    private String name;
    private String contact;
    private String specialization;

}