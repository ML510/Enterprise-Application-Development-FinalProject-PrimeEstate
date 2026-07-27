package com.realestate.management.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LeadStageDto {
    private Integer id;
    private String name;
    private Date createdDate;
    private String description;
}
