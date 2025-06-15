package com.example.CourseRegistration.Repository;


import com.example.CourseRegistration.Model.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users,Integer> {

    public Users findByNameAndPassword(String name, String password);

    public boolean existsByEmail(String email);

    public Users findByEmail(String email);

}
