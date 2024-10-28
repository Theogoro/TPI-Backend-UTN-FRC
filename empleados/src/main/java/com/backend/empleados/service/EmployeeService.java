package com.backend.empleados.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
    return employeeRepository.findAll(pageRequest).getContent();
  }
}
