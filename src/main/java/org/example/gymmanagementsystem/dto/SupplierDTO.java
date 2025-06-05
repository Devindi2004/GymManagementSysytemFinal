package org.example.gymmanagementsystem.dto;

import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Setter

public class SupplierDTO {
    private String supplierId;
    private String name;
    private String contact;
    private String item;

//    public SupplierDTO(String supplierId, String suppNName, String contact, String item) {
//    }
}