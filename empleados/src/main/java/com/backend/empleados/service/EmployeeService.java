package com.backend.empleados.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.backend.empleados.exceptions.BadRequestException;
import com.backend.empleados.exceptions.ResourceNotFoundException;
import com.backend.empleados.model.dto.EmployeeDTO;
import com.backend.empleados.model.entity.Employee;
import com.backend.empleados.repository.EmployeeRepository;

@Service
public class EmployeeService {
  
  @Autowired
  private final EmployeeRepository employeeRepository;

  public EmployeeService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  public List<Employee> getEmployees(int page, int size) {
    PageRequest pageRequest = PageRequest.of(page, size);
    List<Employee> employees = employeeRepository.findAll(pageRequest).getContent();
    if (employees.isEmpty()) {
      throw new ResourceNotFoundException("No employees found");
    }
    return employees;
  }

  public Employee createEmployee(EmployeeDTO employee) {
    if (employeeRepository.findByEmail(employee.getEmail()).isPresent()) {
      throw new BadRequestException("Email already in use");
    }
    return employeeRepository.save(employee.toEntity());
  }

  public Employee getEmployeeById(Long id) {
    return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
  }

  public void deleteEmployee(Long id) {
    employeeRepository.deleteById(id);
  }

  public Employee updateEmployee(Long id, EmployeeDTO employee) {
    Employee employeeToUpdate = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

    if (employeeRepository.findByEmail(employee.getEmail()).isPresent() && !employeeToUpdate.getEmail().equals(employee.getEmail())) {
      throw new BadRequestException("Email already in use");
    }

    Employee employeeUpdated = employee.toEntity();
    employeeUpdated.setId(employeeToUpdate.getId());

    return employeeRepository.save(employeeUpdated);
  }
}
