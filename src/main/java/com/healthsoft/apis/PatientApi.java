package com.healthsoft.apis;

import com.healthsoft.dtos.PatientDTO;
import com.healthsoft.dtos.SearchDTO;
import com.healthsoft.entities.Patient;
import com.healthsoft.services.PatientService;
import com.healthsoft.validators.ValidUUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@AllArgsConstructor
@RestController
@RequestMapping("patients")
public class PatientApi {
    private PatientService patientService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> getPatients() {
        return this.patientService.searchPatients();
    }

    @GetMapping("{patientID}")
    @ResponseStatus(HttpStatus.OK)
    public Patient getPatientDetails(@PathVariable @ValidUUID String patientID) {
        return this.patientService.getPatient(patientID);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Patient createPatient(@RequestBody PatientDTO patientDTO) {
        return this.patientService.createPatient(patientDTO);
    }


    @DeleteMapping("{patientID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePatient(@PathVariable @ValidUUID String patientID) {
        this.patientService.deletePatient(patientID);
    }

    @PutMapping("{patientID}")
    @ResponseStatus(HttpStatus.OK)
    public Patient updatePatient(@PathVariable @ValidUUID String patientID, @RequestBody PatientDTO patientDTO) {
        return this.patientService.updatePatient(patientID, patientDTO);
    }

}
