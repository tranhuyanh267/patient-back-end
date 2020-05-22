package com.healthsoft.repositories;

import com.healthsoft.entities.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String>, PatientCustomRepository {
    Patient findByPatientID(String patientID);
    boolean existsByPatientIDAndDeleted(String patientID, boolean isDeleted);
}
