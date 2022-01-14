package com.example.cleancodefileupload.controller;



import com.example.cleancodefileupload.entity.Employee;
import com.example.cleancodefileupload.security.SecurityUtils;
import com.example.cleancodefileupload.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private  final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employees")
    public ResponseEntity create(@RequestBody Employee employee) {
        Employee employee1 = employeeService.save(employee);
        return  ResponseEntity.ok(employee1);
    }

    @GetMapping("/employees")
    public  ResponseEntity getAll(){
        List<Employee> employeeList = employeeService.findAll();
        return  ResponseEntity.ok(employeeList);
    }

    @GetMapping("/employees/{name}")
    public  ResponseEntity getAll(@PathVariable String name){
        Optional<String>optionalS= SecurityUtils.getCurrentUserName();
        List<Employee> employeeList = employeeService.findByNameQueryNative(name);
        return  ResponseEntity.ok(employeeList);
    }

    @GetMapping("/employees/search")
    public  ResponseEntity getAllSearch(@RequestParam String name){
        List<Employee> employeeList = employeeService.findAllParam(name);
        return  ResponseEntity.ok(employeeList);
    }


}
