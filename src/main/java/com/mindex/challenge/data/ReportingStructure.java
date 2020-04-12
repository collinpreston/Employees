package com.mindex.challenge.data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Reporting Structure entity.
 */
@Document
public class ReportingStructure {

    /**
     * The employee for which the reporting structure is under (or reporting up to).
     */
    private Employee employee;

    /**
     * Equal to the total number of reports under a given employee.
     */
    private Integer numberOfReports;

    /**
     * Constructor.
     */
    public ReportingStructure() {
        this.numberOfReports = 0;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Integer getNumberOfReports() {
        return numberOfReports;
    }

    public void setNumberOfReports(Integer numberOfReports) {
        this.numberOfReports = numberOfReports;
    }


}
