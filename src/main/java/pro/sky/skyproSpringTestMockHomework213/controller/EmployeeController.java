package pro.sky.skyproSpringTestMockHomework213.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.skyproSpringTestMockHomework213.model.Employee;
import pro.sky.skyproSpringTestMockHomework213.service.EmployeeService;


import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService service) {
        this.employeeService = service;
    }
    @GetMapping("/add")
    public Employee addEmployee(@RequestParam String firstName,
                                @RequestParam String lastName,
                                @RequestParam int salary,
                                @RequestParam int department){
        return employeeService.add(firstName, lastName, salary, department);
    }
    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam String firstName,
                                   @RequestParam String lastName,
                                   @RequestParam int salary,
                                   @RequestParam int department){
        return employeeService.remove(firstName, lastName, salary, department);
    }
    @GetMapping("/find")
    public Employee findEmployee(@RequestParam String firstName,
                                 @RequestParam String lastName,
                                 @RequestParam int salary,
                                 @RequestParam int department){
        return employeeService.find(firstName, lastName, salary, department);
    }

    @GetMapping
    public Collection<Employee> findAll(){
        return employeeService.findAll();
    }

    @GetMapping("/list")
    public List<Employee> list(){
        return employeeService.list();
    }
}
