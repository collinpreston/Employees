package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);


    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompensationRepository compensationRepository;


    @Override
    public Employee create(Employee employee) {
        LOG.debug("Creating employee [{}]", employee);

        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeRepository.insert(employee);

        return employee;
    }

    @Override
    public Employee read(String id) {
        LOG.debug("Reading employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        LOG.debug("Updating employee [{}]", employee);

        return employeeRepository.save(employee);
    }

    // START, Task 1.

    /**
     * Implementation method for retrieving the reporting structure entity
     * for an employeeId.
     * @param employeeId The employeeId which will be the head of the report chain.
     * @return The ReportingStructure for the employeeId.
     */
    @Override
    public ReportingStructure getReportingStructureById(String employeeId) {
        LOG.debug("Getting reporting structure for the employee with id [{}]", employeeId);

        Employee employee = employeeRepository.findByEmployeeId(employeeId);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + employeeId);
        }

        ReportingStructure employeeReportingStructure = new ReportingStructure();
        employeeReportingStructure.setEmployee(employee);

        // Here we retrieve the tree of reports.
        employeeReportingStructure.setNumberOfReports(getTotalReportsRecursively(employee));

        return employeeReportingStructure;
    }

    /**
     * This is a recursive method which calculates the number of employees in the report chain below a single employee.
     * @param employee The employee entity object for which we are calculating the number of reporting employees below them.
     * @return Returns an int value representing the number of employees reporting up to the Employee parameter.
     */
    private int getTotalReportsRecursively(Employee employee) {
        // Initialize the counter variable.
        int numberOfReports = 0;

        // Use the method from the EmployeeRepository interface to get the number of direct reports for the Employee.
        List<Employee> directReportList = employee.getDirectReports();

        // Check to make sure that the employee
        if (!(directReportList == null)) {
            for (Employee reportingEmployee : directReportList) {
                numberOfReports++;
                numberOfReports += getTotalReportsRecursively(reportingEmployee);
            }
        }
        return numberOfReports;
    }
    // END, Task 1.

    // START, Task 2.

    /**
     * Implementation method for creating compensation and storing in the persistence layer.
     * @param compensation The compensation data to be persisted.
     * @return The compensation data entered.
     */
    @Override
    public Compensation createCompensation(Compensation compensation) {
        LOG.debug("Creating employee compensation [{}]", compensation);

        compensationRepository.insert(compensation);
        return compensation;
    }

    /**
     * Implementation method for reading the compensation for an employeeId.
     * @param employeeId The employeeId for which to search for compensation data.
     * @return The Compensation entity for the employeeId.
     */
    @Override
    public Compensation readCompensationByEmployeeId(String employeeId) {
        LOG.debug("Reading employee compensation for employee with id [{}]", employeeId);

        Employee employee = employeeRepository.findByEmployeeId(employeeId);

        Compensation compensation = compensationRepository.findByEmployee(employee);

        if (compensation == null) {
            throw new RuntimeException("Invalid employeeId: " + employeeId);
        }

        return compensation;
    }
    // END, Task 2.

}
