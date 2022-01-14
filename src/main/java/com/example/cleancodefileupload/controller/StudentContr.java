package com.example.cleancodefileupload.controller;


import com.example.cleancodefileupload.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentContr {

    @GetMapping("/students/all")
    public ResponseEntity getAll(){

        Student student = new Student(1, "Aziz", "Azizov", "`1 -kurs");
        Student student1 = new Student(2, "Alimardon", "Alimardonov", "`2 -kurs");
        Student student2 = new Student(3, "Nurbek", "Nurbekov", "`3 -kurs");

        List<Student> students = new ArrayList<>();

        students.add(student);
        students.add(student1);
        students.add(student2);

        return ResponseEntity.ok(students);



    }

    @GetMapping("/students/{id}")
    public  ResponseEntity getOne(@PathVariable Long id) {
        Student student = new Student(id, "Aziz", "Azizov", "`1 -kurs");
        return ResponseEntity.ok(student);
    }

    @GetMapping("/students")
    public  ResponseEntity getOne(@RequestParam Long id,
                                  @RequestParam  String name,
                                  @RequestParam String lastName,
                                  @RequestParam String course) {
        Student student = new Student(id, name, lastName, course);
        return ResponseEntity.ok(student);
    }

    @PostMapping("/students")
    public ResponseEntity create(@RequestBody Student student) {
       return ResponseEntity.ok(student);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity update( @PathVariable Long id ,  @RequestBody Student studentNew) {
        Student student = new Student(id , "Aziz", "Azizov", "`1 -kurs");
        student.setId(studentNew.getId());
        student.setName(studentNew.getName());
        student.setLastname(studentNew.getLastname());
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/students/{id}")
    public  ResponseEntity delete(@PathVariable Long id) {
        return  ResponseEntity.ok("Malumot o'cgirildi");
    }

}
