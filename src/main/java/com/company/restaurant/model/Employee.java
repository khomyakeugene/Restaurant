package com.company.restaurant.model;

import com.company.restaurant.model.common.PhotoHolderObject;

/**
 * Created by Yevhen on 19.05.2016.
 */
public class Employee extends PhotoHolderObject {
    private String firstName;
    private String secondName;
    private String phoneNumber;
    private Float salary;
    private JobPosition jobPosition = new JobPosition();

    public int getEmployeeId() {
        return getId();
    }

    public void setEmployeeId(int employeeId) {
        setId(employeeId);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public JobPosition getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(JobPosition jobPosition) {
        this.jobPosition = jobPosition;
    }

    public String getName() {
        return getFirstName() + " " + getSecondName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;

        Employee employee = (Employee) o;

        return getEmployeeId() == employee.getEmployeeId() && (firstName != null ?
                firstName.equals(employee.firstName) :
                employee.firstName == null && (secondName != null ?
                        secondName.equals(employee.secondName) :
                        employee.secondName == null && (phoneNumber != null ?
                                phoneNumber.equals(employee.phoneNumber) :
                                employee.phoneNumber == null && (salary != null ?
                                        salary.equals(employee.salary) :
                                        employee.salary == null && (jobPosition != null ?
                                                jobPosition.equals(employee.jobPosition) :
                                                employee.jobPosition == null)))));

    }

    @Override
    public int hashCode() {
        int result = getEmployeeId();
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (salary != null ? salary.hashCode() : 0);
        result = 31 * result + (jobPosition != null ? jobPosition.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + getEmployeeId() +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", salary=" + salary +
                ", jobPosition=" + jobPosition +
                '}';
    }
}
