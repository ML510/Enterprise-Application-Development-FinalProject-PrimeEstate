package com.realestate.management.Dto;

import com.realestate.management.enums.PropertyStatus;
import com.realestate.management.enums.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PropertyDto {
    private Integer id;
    private String name;
    private State state;
    private Double price;
    private String url;
    private PropertyStatus status;
    private Integer maxBedCount;
    private Integer minBedCount;
    private Integer sqft;
    private String dsc;
}
