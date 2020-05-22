package com.healthsoft.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class SearchDTO {
    private String firstName;
    private String lastName;
    private String gender;
    private String patientID;
    private String dob;
    private boolean search;
    private boolean withDeleted;
}
