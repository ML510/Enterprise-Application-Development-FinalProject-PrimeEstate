package com.realestate.management.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LeadDto {
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private LocalDateTime lastContact;
    private String stage;
    private String profilePic;
}
