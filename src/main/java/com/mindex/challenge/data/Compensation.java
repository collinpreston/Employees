package com.mindex.challenge.data;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Compensation entity for handling compensation data related to Employee's.
 */
@Document
public class Compensation {

    private Employee employee;

    private float salary;

    private Date effectiveDate;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }


}
