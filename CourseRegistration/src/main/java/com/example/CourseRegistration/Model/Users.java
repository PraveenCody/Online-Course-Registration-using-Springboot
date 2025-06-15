package com.example.CourseRegistration.Model;

import jakarta.persistence.*;
import lombok.Data;


@Data@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String email;
    private String password;


    public Users(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }


    public Users() {

    }
}
