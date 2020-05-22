package com.healthsoft.repositories;

import com.healthsoft.dtos.SearchDTO;
import com.healthsoft.entities.Patient;

import java.util.List;

public interface PatientCustomRepository {
    List<Patient> search(SearchDTO searchDTO);
}
