package in.niraj.userbackend.controller;

import in.niraj.userbackend.entity.Employee;
import in.niraj.userbackend.entity.User;
import in.niraj.userbackend.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * created by pc on Jun, 2019
 */

@RestController
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private EmployeeService employeeService;

    @Value("${hello.world}")
    private String helloWorldMsg;

    @GetMapping("/hello")
    private String sayHello() {
        logger.info("Check config server property.");
        return helloWorldMsg;
    }


    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployee() {
        logger.info("getAllEmployee() invoked");
        List<Employee> employeeList = employeeService.findAllEmployee();
        return new ResponseEntity<>(employeeList, HttpStatus.OK);

    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") String id) {
        Employee employee = employeeService.findById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteEmployeeById(@PathVariable("id") String id) {
        logger.info("Delete employee");
        employeeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Employee> save(@RequestBody Employee employee) {
        logger.info(" Save Employee {} ", employee);
        Employee emp = employeeService.save(employee);

        return new ResponseEntity<>(emp, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Employee> update(@RequestBody Employee employee) {
        logger.info("Update Employee");
        Employee emp = employeeService.update(employee);

        return new ResponseEntity<>(emp, HttpStatus.OK);
    }

}
