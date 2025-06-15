package com.example.CourseRegistration.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data

@Entity
public class Register {

    public Register(){

    }
    public Register(String name,String email,String course){
        this.name =name;
        this.email = email;
        this.course = course;
    }

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String course;

}
