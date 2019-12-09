package org.fasttrackit.onlineshop.transfer;

import javax.validation.constraints.NotBlank;


public class SaveCustomerRequest {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

    @Override
    public String toString() {
        return "SaveCustomerRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
