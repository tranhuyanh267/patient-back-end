package com.healthsoft.repositories;

import com.healthsoft.dtos.SearchDTO;
import com.healthsoft.entities.Patient;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.*;

@Repository
@AllArgsConstructor
public class PatientRepositoryImpl implements PatientCustomRepository {
    private MongoTemplate mongoTemplate;

    @Override
    public List<Patient> search(SearchDTO searchDTO) {
        if(searchDTO.isSearch()) {
            // OR
        } else {
            // AND
            Query query = new Query();
            Criteria criteria = new Criteria();
            criteria.andOperator(where("firstName").regex(""), where("lastName").regex(""), where("gender").is(searchDTO.getGender()));
            query.addCriteria(criteria);
            return this.mongoTemplate.find(null, Patient.class);
        }
        return null;
    }
}
