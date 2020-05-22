package com.healthsoft.services;

import com.healthsoft.dtos.PatientDTO;
import com.healthsoft.dtos.SearchDTO;
import com.healthsoft.entities.Patient;
import com.healthsoft.repositories.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PatientService {
    private PatientRepository patientRepository;

    public Patient createPatient(PatientDTO patientDTO) {
        boolean isPatientIdUsed = this.patientRepository.existsByPatientIDAndDeleted(patientDTO.getPatientID(), false);
        if (isPatientIdUsed) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Patient patient = this.patientRepository.findByPatientID(patientDTO.getPatientID());
        if (patient != null && patient.isDeleted()) {
            BeanUtils.copyProperties(patientDTO, patient);
            patient.setDeleted(false);
            return this.patientRepository.save(patient);
        } else {
            Patient newPatient = patientDTO.toPatient();
            newPatient.setId(UUID.randomUUID().toString());
            return this.patientRepository.save(newPatient);
        }
    }

    public void deletePatient(String patientID) {
        Optional<Patient> patientOpt = this.patientRepository.findById(patientID);
        if (patientOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Patient patient = patientOpt.get();
        patient.setDeleted(true);
        this.patientRepository.save(patient);
    }

    public Patient getPatient(String patientID) {
        Optional<Patient> patientOpt = this.patientRepository.findById(patientID);
        if (patientOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return patientOpt.get();
    }

    public Patient updatePatient(String patientID, PatientDTO patientDTO) {
        Optional<Patient> existingPatientOpt = this.patientRepository.findById(patientID);
        if (existingPatientOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Patient existingPatient = existingPatientOpt.get();
        BeanUtils.copyProperties(patientDTO, existingPatient);

        return this.patientRepository.save(existingPatient);
    }

    public List<Patient> searchPatients() {
        return this.patientRepository.findAll();
    }
}
