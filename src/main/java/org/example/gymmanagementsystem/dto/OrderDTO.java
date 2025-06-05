package org.example.gymmanagementsystem.dto;

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter

public class OrderDTO {
    private String orderId;
    private String memberId;
    private String name;
    private String date;
    private String amount;

}