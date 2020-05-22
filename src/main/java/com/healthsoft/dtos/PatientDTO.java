package com.healthsoft.dtos;

import com.healthsoft.entities.Patient;
import com.healthsoft.validators.ValidEnum;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class PatientDTO {
    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    private String middleName;

    @NotNull
    private String patientID;

    @NotNull
    private Date dob;

    @NotNull
    @ValidEnum(type= Patient.Gender.class)
    private String gender;

    public Patient toPatient() {
        Patient patient = new Patient();
        BeanUtils.copyProperties(this, patient);
        return patient;
    }
}
