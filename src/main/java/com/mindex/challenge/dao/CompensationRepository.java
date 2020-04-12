package com.mindex.challenge.dao;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Spring auto creating of CRUD operations related to the
 * Compensation entity.
 */
@Repository
public interface CompensationRepository extends MongoRepository<Compensation, String> {
    /**
     * Method for reading the compensation data by Employee (entity).
     * @param employee The employee entity for which to find compensation data for.
     * @return The compensation data related to the employee parameter object.
     */
    Compensation findByEmployee(Employee employee);

}

