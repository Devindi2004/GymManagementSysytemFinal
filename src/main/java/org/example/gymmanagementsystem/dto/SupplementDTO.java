package org.example.gymmanagementsystem.dto;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class SupplementDTO {
    private String supplimentId;
    private String name;
    private String description;
    private String price;
    private String quantity;

}