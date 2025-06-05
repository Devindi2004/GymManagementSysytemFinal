package org.example.gymmanagementsystem.dto;

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter


public class EquipmentDTO {
    private String equipId;
    private String supplierId;
    private String classId;
    private String name;
    private String type;

}
