package com.example.employeedept;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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


    @GetMapping("/emplist")
    public String showEmployees(Model model){
        model.addAttribute("employees", employeeRepository.findAll());
        model.addAttribute("departments", departmentRepository.findAll());
        return "emplist";
    }

    @PostMapping("/processDepartment")
    public String processDepartment(@Valid Department department, BindingResult result){
        if(result.hasErrors()){
            return "depform";
        }
        departmentRepository.save(department);
        return "redirect:/";
    }

    @PostMapping("/processEmployee")
    public String processEmployee(@Valid Employee employee, BindingResult result){
        if(result.hasErrors()){
            return "empform";
        }
        employeeRepository.save(employee);
        return "redirect:/";
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
        model.addAttribute("department", employeeRepository.findAll());
        return "empform";
    }

    @GetMapping("/viewemp/{id}")
    public String viewEmployee(@PathVariable("id") long id, Model model){
        model.addAttribute("employee",employeeRepository.findById(id).get());
        return "showemp";
    }

    @GetMapping("/addemployee")
    public String employeeForm(Model model){
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("employee", new Employee());
        return "empform";
    }

    @GetMapping("/deleteemp/{id}")
    public String deleteEmployee(@PathVariable("id") long id){
        employeeRepository.deleteById(id);
        return "redirect:/";
    }
}
