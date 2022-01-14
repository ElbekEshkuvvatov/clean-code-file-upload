package com.example.cleancodefileupload.repository;

import com.example.cleancodefileupload.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  List<Employee> findByName(String name);

  List<Employee>  findByNameAndLastname(String name, String lastname);

  @Query("select  e from Employee  e where  e.name =:name")   // JPA query
    List<Employee> findByNameQuery(@Param("name") String name);

    @Query( value = "select  * from employee  e where  e.name =:name", nativeQuery = true)   // JPA query
    List<Employee> findByNameQueryNative(@Param("name") String name);


    List<Employee> findAllByNameLike(String name);


    List<Employee> findAllByNameStartingWith(String name);

    List<Employee> findAllByNameEndingWith(String name);

    @Query("select  e from  Employee  e where  e.name  like upper(  concat('%', :name, '%') )")
    List<Employee> findByAllLike(@Param("name") String name);


    @Query(value ="select * from employee e where  e.name like  '%' :name '%'", nativeQuery = true)
    List<Employee> findAllNativeLike(@Param("name") String name);

}
