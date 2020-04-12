package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;

public interface EmployeeService {
    Employee create(Employee employee);
    Employee read(String id);
    Employee update(Employee employee);
    // START, Task 1.

    /**
     * Interface method for retrieving the reporting structure by an employeeId.
     * @param id The employeeId for the head of the reporting chain.
     * @return The ReportingStructure entity for the employeeId.
     */
    ReportingStructure getReportingStructureById(String id);
    // END, Task 1.

    // START, Task 2.

    /**
     * Method for creating a compensation entry.
     * @param compensation The compensation data to be persisted.
     * @return The compensation data entered in.
     */
    Compensation createCompensation(Compensation compensation);

    /**
     * Method for retrieving the compensation data for an employeeId.
     * @param employeeId The employeeId for which to search for compensation data.
     * @return The compensation data related to the employeeId.
     */
    Compensation readCompensationByEmployeeId(String employeeId);
    // END, Task 2.
}



