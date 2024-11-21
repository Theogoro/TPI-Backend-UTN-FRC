package com.backend.empleados.controller.api.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.empleados.model.dto.EmployeeDTO;
import com.backend.empleados.model.entity.Employee;
import com.backend.empleados.service.EmployeeService;

import jakarta.validation.Valid;

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
    return employeeService.getEmployees(page, size);
  }

  @PostMapping()
  public ResponseEntity<Employee> createEmployee(@Valid @RequestBody EmployeeDTO employee) {
    Employee newEmployee = employeeService.createEmployee(employee);
    return ResponseEntity.ok(newEmployee);
  }

  @GetMapping("/{id}")
  public Employee getEmployeeById(@PathVariable(name = "id") Long id) {
    return employeeService.getEmployeeById(id);
  }

  @PutMapping("/{id}")
  public Employee updateEmployee(@PathVariable(name = "id") Long id,@Valid @RequestBody EmployeeDTO employee) {
    return employeeService.updateEmployee(id, employee);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteEmployee(@PathVariable(name = "id") Long id) {
    employeeService.deleteEmployee(id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/{id}/notifyBadTest")
  public ResponseEntity<Object> notifyBadTest(@PathVariable(name = "id") Long id) {
    System.out.println("Notifying bad test for employee with id " + id);
    employeeService.notifyBadTest(id);
    return ResponseEntity.noContent().build();
  }
}