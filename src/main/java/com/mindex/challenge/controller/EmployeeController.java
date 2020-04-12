package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee")
    public Employee create(@RequestBody Employee employee) {
        LOG.debug("Received employee create request for [{}]", employee);

        return employeeService.create(employee);
    }

    @GetMapping("/employee/{id}")
    public Employee read(@PathVariable String id) {
        LOG.debug("Received employee read request for id [{}]", id);

        return employeeService.read(id);
    }

    @PutMapping("/employee/{id}")
    public Employee update(@PathVariable String id, @RequestBody Employee employee) {
        LOG.debug("Received employee create request for id [{}] and employee [{}]", id, employee);

        employee.setEmployeeId(id);
        return employeeService.update(employee);
    }

    // START, Task 1

    /**
     * Controller method to present a REST interface for retrieving the Reporting
     * Structure for a single employee.
     * @param employeeId The employeeId for the person at the head of the report chain.
     * @return The Reporting Structure entity with data for the employeeId.
     */
    @GetMapping("/reportingstructure/{employeeId}")
    public ReportingStructure getReportingStructureById(@PathVariable String employeeId) {
        LOG.debug("Received reporting structure request for employee id [{}]", employeeId);

        return employeeService.getReportingStructureById(employeeId);
    }
    // END, Task 1

    // START, Task 2.

    /**
     * Controller method to present a REST interface for creating a compensation entry.
     * @param compensation compensation data being created.
     * @return The compensation data that was persisted.
     */
    @PostMapping("/compensation")
    public Compensation createCompensation(@RequestBody Compensation compensation) {
        LOG.debug("Received compensation create request for [{}]", compensation);

        return employeeService.createCompensation(compensation);
    }

    /**
     * Controller method to present a REST interface for reading the compensation data
     * associated to a specific employeeId.
     * @param employeeId The employeeId for the compensation data being searched for.
     * @return The compensation data found matching the employeeId.
     */
    @GetMapping("/compensation/{employeeId}")
    public Compensation readCompensationByEmployeeId(@PathVariable String employeeId) {
        LOG.debug("Received employee compensation read request for id [{}]", employeeId);

        return employeeService.readCompensationByEmployeeId(employeeId);
    }
    // END, Task 2.
}
