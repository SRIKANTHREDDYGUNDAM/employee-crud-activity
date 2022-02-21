package com.employee.crudactivity.restcontroller;

import com.employee.crudactivity.entity.Employee;
import com.employee.crudactivity.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;

    public EmployeeRestController(EmployeeService theEmployeeService){
        employeeService=theEmployeeService;
    }
    @GetMapping("employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }


    @GetMapping("employees/{employeeId}")
    public Employee findById(@PathVariable int employeeId) {
        Employee theEmployee = employeeService.findById(employeeId);
        if (theEmployee == null) {
            throw new RuntimeException("employee id not found: -" + employeeId);
        }
        return theEmployee;
    }
    @PostMapping("/employees")
    public Employee addEmployee (@RequestBody Employee theEmployee) {
        theEmployee.setId(0);
        employeeService.save(theEmployee);
        return theEmployee;
    }
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee){
        employeeService.save(theEmployee);
        return theEmployee;
    }
    @DeleteMapping("/employees/{employeeId}")
    public String deleteById(@PathVariable int employeeId){
        Employee tempEmployee=employeeService.findById(employeeId);
        if(tempEmployee==null){
            throw new RuntimeException("employee not found with id :"+employeeId);
        }

        employeeService.deleteById(employeeId);

        return "Deleted student record with id: "+employeeId;
    }
}
