package com.example.cleancodefileupload.repository;


import com.example.cleancodefileupload.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String username);

    @Query("select  u from  User  u where  u.userName=:userName")
    User findByLogin(@Param("userName") String userName);

    boolean existsByUserName(String userName);

}
