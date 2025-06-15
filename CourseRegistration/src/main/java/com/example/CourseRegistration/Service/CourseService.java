package com.example.CourseRegistration.Service;

import com.example.CourseRegistration.Model.Courses;
import com.example.CourseRegistration.Model.Register;
import com.example.CourseRegistration.Model.Users;
import com.example.CourseRegistration.Repository.CourseRepo;
import com.example.CourseRegistration.Repository.RegistryRepo;
import com.example.CourseRegistration.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    CourseRepo repo1;

    @Autowired
    RegistryRepo repo2;

    @Autowired
    UserRepo repo3;

    public List<Courses> courses() {
        return repo1.findAll();
    }

    public List<Register> enrolled() {
        return repo2.findAll();
    }

    public List<Users> users(){
        return repo3.findAll();
    }

    public String register(String name, String email, String course) {
        Register r = new Register(name,email,course);
        repo2.save(r);
        return "enrolled";
    }

    public void signin(String name, String email, String password) {

        if(repo3.existsByEmail(email)){
            throw new DataIntegrityViolationException("Email already registered");
        }

        Users u = new Users(name,email,password);
        repo3.save(u);
    }

    public boolean checkLogin(String email, String password) {

        if(!repo3.existsByEmail(email)){
            throw new DataIntegrityViolationException("Email Not Registered...!");
        }
        else {

            boolean bool = false;
            Users user = repo3.findByEmail(email);
            String userpass = user.getPassword();
            if (userpass.equals(password)) {
                bool = true;
            }

            return bool;
        }
    }

    public String getName(String email) {
        Users user = repo3.findByEmail(email);
        return user.getName();
    }

}
