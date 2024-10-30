package com.backend.empleados.model.dto;

import com.backend.empleados.model.entity.Employee;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class EmployeeDTO {
    private Long id;

    @NotEmpty(message = "El campo 'firstName' es obligatorio.")
    private String firstName;

    @NotEmpty(message = "El campo 'lastName' es obligatorio.")
    private String lastName;

    @NotEmpty(message = "El campo 'email' es obligatorio.")
    @Email(message = "El campo 'email' debe ser un correo electrónico válido.")
    private String email;

    @NotEmpty(message = "El campo 'phoneNumber' es obligatorio.")
    private String phoneNumber;

    public Employee toEntity() {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setEmail(email);
        employee.setPhoneNumber(phoneNumber);
        return employee;
    }
}
