package com.example.CourseRegistration.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor

@Entity
public class Courses {

    Courses(){

    }

    @Id
    private int cid;
    private String cname;
    private String trainer;
    private int time;
}
