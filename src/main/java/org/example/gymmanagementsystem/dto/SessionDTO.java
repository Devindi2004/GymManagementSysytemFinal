package org.example.gymmanagementsystem.dto;

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString


public class SessionDTO {
    private String sessionId;
    private String classID;
    private String type;
    private String time;
    private String date;


}