package com.demoautomationproject.testdataprovider;

public class Customer {

    private String firstName;
    private String lastName;
    private String zipCode;

    public Customer(String firstName, String lastName, String zipCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.zipCode = zipCode;
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    public String zipCode() {
        return zipCode;
    }


}
