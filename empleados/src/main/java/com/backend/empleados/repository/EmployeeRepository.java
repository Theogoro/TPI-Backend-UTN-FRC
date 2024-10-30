package com.backend.empleados.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.backend.empleados.model.entity.Employee;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long>, CrudRepository<Employee, Long> {

  Optional<Employee> findByEmail(String email);
}