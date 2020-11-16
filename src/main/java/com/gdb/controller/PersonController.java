package com.gdb.controller;

import com.gdb.entity.Employee;
import com.gdb.entity.Person;
import com.gdb.repository.EmployeeRepository;
import com.gdb.repository.PersonRepository;
import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {

    private PersonRepository personRepository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public PersonController(PersonRepository personRepository, EmployeeRepository employeeRepository) {
        this.personRepository = personRepository;
        this.employeeRepository = employeeRepository;
    }

    @RequestMapping(value = "/people",method = RequestMethod.GET)
    public ResponseEntity<List<Person>> fetch() {
        List<Person> persons = Lists.newArrayList(this.personRepository.findAll());
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Employee> login(@RequestBody Person param) {
    	Employee employee = this.employeeRepository.findOne(param.getId());
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
}
