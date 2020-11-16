package com.gdb.repository;

import com.gdb.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("EmployeeRepository")
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
