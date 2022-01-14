package com.example.cleancodefileupload.service;


import com.example.cleancodefileupload.entity.Employee;
import com.example.cleancodefileupload.repository.EmployeeRepository;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private  final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee save(Employee employee) {
        return  employeeRepository.save(employee);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public  Employee findById(Long id) {
        return  employeeRepository.findById(id).get();
    }

   /* public  List<Employee> findByName(String name){
        return employeeRepository.findByName(name);
    }*/

    public  List<Employee> findByNameQueryNative(String name){
        return employeeRepository.findByName(name);
    }


     public  List<Employee> findAllParam(String name){
        return employeeRepository.findByAllLike(name);
     }

     public  void delete(Long id) {
        Employee employee = employeeRepository.getById(id);
        employeeRepository.delete(employee);

     }

     @Scheduled(cron = " 0 11 16 * * *")
    public  Employee saveSchedule() {
        Employee employee1 = new Employee();
        employee1.setName("dfsdlg dfgdjklfg");
        employee1.setLastname("Qalay");
        return employeeRepository.save(employee1);
     }

}
