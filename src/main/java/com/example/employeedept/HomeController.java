package com.example.employeedept;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/")
    public String showDepartments(Model model){
        model.addAttribute("departments", departmentRepository.findAll());
        return "deplist";
    }

    @GetMapping("/departmentform")
    public String departmentForm(Model model){
        model.addAttribute("department", new Department());
        return "depform";
    }

    @GetMapping("/employeeform")
    public String employeeForm(@PathVariable("id") long id, Model model){
        model.addAttribute("department", departmentRepository.findById(id).get());
        model.addAttribute("employee", new Employee());
        return "empform";
    }

    @GetMapping("/emplist")
    public String showEmployees(Model model){
        model.addAttribute("employees", employeeRepository.findAll());
        model.addAttribute("departments", departmentRepository.findAll());
        return "emplist";
    }

    @GetMapping("/updatedep/{id}")
    public String updateDepartment(@PathVariable("id") long id, Model model){
        model.addAttribute("department", departmentRepository.findById(id).get());
        return "departmentform";
    }

    @GetMapping("/viewdep/{id}")
    public String viewDepartment(@PathVariable("id") long id, Model model){
        model.addAttribute("department", departmentRepository.findById(id).get());
        model.addAttribute("employees", employeeRepository.findAll());
        return "showdep";
    }

    @GetMapping("/deletedep/{id}")
    public String deleteDepartment(@PathVariable("id") long id){
        departmentRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/updateemp/{id}")
    public String updateEmployee(@PathVariable("id") long id, Model model){
        model.addAttribute("employee", employeeRepository.findById(id).get());
        return "empform";
    }

    @GetMapping("/viewemp/{id}")
    public String viewEmployee(@PathVariable("id") long id, Model model){
        model.addAttribute("employee",employeeRepository.findById(id).get());
        return "showemp";
    }

    @GetMapping("/deleteemp/{id}")
    public String deleteEmployee(@PathVariable("id") long id){
        employeeRepository.deleteById(id);
        return "redirect:/";
    }
}
