package com.example.CourseRegistration.Controller;

import com.example.CourseRegistration.Service.CourseService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;

@Controller
public class CourseController {

    @Autowired
    CourseService service;

    @GetMapping("/")
    public String getLogin() {
        return "index";
    }

//    @ResponseBody
//    @GetMapping("/api/courses")
//    public void getCourse(){
//        service.courses();
//    }
//    @ResponseBody
//    @GetMapping("/api/enrolled")
//    public void enrolled(){
//        service.enrolled();
//    }

    @GetMapping("signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("signup")
    public String signup(@RequestParam("name") String name,
                         @RequestParam("email") String email,
                         @RequestParam("password") String password,
                         RedirectAttributes redirectAttributes) {
        try {
            service.signin(name, email, password);
            return "redirect:/login";
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/signup";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Registration failed. Please try again.");
            return "redirect:/signup";
        }
    }

    @GetMapping("login")
    public String logins() {
        return "login";
    }

    @PostMapping("login")
    public String logined(@RequestParam("email") String email,
                          @RequestParam("password") String password,
                          HttpSession session,
                          RedirectAttributes redirectAttributes) {
        try {
            boolean b = service.checkLogin(email, password);

            if (b) {
                String sessionId = session.getId();
                session.setAttribute("sid",sessionId);
                session.setAttribute("email", email);
                String name = service.getName(email);
                session.setAttribute("name", name);

                System.out.println("Session ID: " + sessionId);

                return "redirect:/main";
            } else {
                redirectAttributes.addFlashAttribute("error", "Login failed...");
                return "redirect:/login";
            }
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Login failed...");
            return "redirect:/login";
        }
    }

    @GetMapping("main")
    public String main(Model model, HttpSession session) {

        String email = session.getAttribute("email").toString();
        String name = session.getAttribute("name").toString();
        String sid = session.getAttribute("sid").toString();

        model.addAttribute("email", email);
        model.addAttribute("name", name);
        model.addAttribute("sid",sid);

        System.out.println("Session ID: " + sid);

        return "main";
    }

    @GetMapping("register")
    public String registerPage() {
        return "register";
    }


    @PostMapping("register")
    public String register(@RequestParam("name") String name,
                           @RequestParam("email") String email,
                           @RequestParam("course") String course) {
        service.register(name, email, course);
        return "redirect:/main";
    }

    @GetMapping("courses")
    public String getCoursesPage(Model model) {
        model.addAttribute("Courses", service.courses());
        return "availcourse";
    }

    @GetMapping("enrolled")
    public String getEnrolledPage(Model model) {
        model.addAttribute("Register", service.enrolled());
        return "enrolled";
    }

    @GetMapping("users")
    public String getAllUsers(Model model) {
        model.addAttribute("Users", service.users());
        return "users";
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();  // This clears all session attributes
        }

        return "redirect:/";
    }
}
