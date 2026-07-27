package com.realestate.management.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ViewingDto {
    private String name;
    private String email;
    private String phone;
    private Date viewingDate;
    private Time time;
    private String note;
}
