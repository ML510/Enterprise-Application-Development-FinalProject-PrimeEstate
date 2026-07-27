package com.realestate.management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LeadStageEntity {
    private Integer id;
    private String name;
    private Date createdDate;
    private String description;

}
