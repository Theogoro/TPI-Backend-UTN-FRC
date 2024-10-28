package com.backend.empleados.controller.api.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.empleados.model.entity.Employee;
import com.backend.empleados.service.EmployeeService;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
  @Autowired
  private final EmployeeService employeeService;

  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping()
  public List<Employee> getEmployees(
        @RequestParam(defaultValue = "0", name = "page") int page,
        @RequestParam(defaultValue = "10", name = "size") int size
  ) {
    List<Employee> employees = employeeService.getEmployees(page, size);

    if (employees.isEmpty()) {
      throw new RuntimeException("No employees found");
    }

    return employees;
  }

  @PostMapping()
  public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
    Employee newEmployee = employeeService.createEmployee(employee);
    return ResponseEntity.ok(newEmployee);
  }
}