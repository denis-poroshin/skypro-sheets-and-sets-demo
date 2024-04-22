package pro.sky.skyprosheetsandsetsdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    @RequestMapping(path = "/employee/add")
    public String addEmployee(@RequestParam("lastName") String lastName, @RequestParam("firstName") String firstName){
        return employeeService.addEmployee(lastName, firstName);
    }
    @RequestMapping(path = "/employee/remove")
    public String removeEmployee(@RequestParam("lastName") String lastName, @RequestParam("firstName") String firstName){
        return employeeService.dismissalEmployee(lastName,firstName);
    }
    @RequestMapping(path = "/employee/find")
    public String findEmployee(@RequestParam("lastName") String lastName, @RequestParam("firstName") String firstName){
        return employeeService.searchEmployee(lastName, firstName);
    }
    @GetMapping(path = "/employee")
    public String printEmployee(){
        return employeeService.printAllEmployee();
    }


}
