package com.pm.app.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ssl.SslProperties.Bundles.Watch.File;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.pm.app.models.Employee;
import com.pm.app.services.EmployeeService;

import java.io.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    
    // This method will be called when the user navigates to the home page
    @GetMapping("/")
    public String viewHomePage(Model model,HttpSession session){
        String message = (String) session.getAttribute("success");
        if(message != null){
            model.addAttribute("success",message);
            session.removeAttribute("success");
        }
        model.addAttribute("employees",employeeService.getAllEmployees());
        return "index";
    }

    @GetMapping("/showNewEmployee")
    public String viewCreateEmployee(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee",employee);
        return "create";
    }

    @PostMapping("/storeEmployee")
    public String storeEmployee(@ModelAttribute("employee") Employee employee,HttpSession session,@RequestParam("image") MultipartFile image){
        //Apply code for upload file here 
        String uploadDir = "static/uploads/";

        String fileName = image.getOriginalFilename();

        File directory = new File(uploadDir);

        if (!directory.exists()) {
            directory.mkdirs();
        }


        session.setAttribute("success", "Employee added sucess");
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }

    @GetMapping("/showEmployee/{id}")
    public String showEdit(@PathVariable(value = "id") long id,Model model){
        //get employee form services 
        Employee employee = employeeService.getEmployeeById(id);

        //set employee as model attribute to pre-populate the form  
        model.addAttribute("employee",employee);
        return "edit";
    }

    @GetMapping("/destroyEmployee/{id}")
    public String  destroyEmployee(@PathVariable(value= "id") Long id,HttpSession session){
        this.employeeService.deleteEmployeeById(id);
        session.setAttribute("success","Employee deleted success");
        return "redirect:/";
    }
}
