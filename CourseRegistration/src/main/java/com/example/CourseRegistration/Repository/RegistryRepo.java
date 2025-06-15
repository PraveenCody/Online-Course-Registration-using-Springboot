package com.example.CourseRegistration.Repository;

import com.example.CourseRegistration.Model.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistryRepo extends JpaRepository<Register,Integer> {

}
