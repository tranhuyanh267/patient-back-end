package com.healthsoft.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Data
@Document
public class Patient {
    @Id
    private String id;
    private String patientID;
    private String firstName;
    private String lastName;
    private String middleName;
    private Date dob;
    private Gender gender;
    private boolean deleted;

    public enum Gender {
        M, F ,O
    }

}
