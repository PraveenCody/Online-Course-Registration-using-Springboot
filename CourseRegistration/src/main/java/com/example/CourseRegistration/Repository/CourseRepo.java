package com.example.CourseRegistration.Repository;

import com.example.CourseRegistration.Model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo extends JpaRepository<Courses,Integer> {



}
