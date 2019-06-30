package in.niraj.userbackend.service;


import in.niraj.userbackend.entity.Employee;
import in.niraj.userbackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    public List<Employee> findAllEmployee() {
        Iterable<Employee> employees = employeeRepository.findAll();
        Iterator it = employees.iterator();
        List<Employee> employeeList = new ArrayList<>();
        while(it.hasNext()) {
            employeeList.add((Employee)it.next());
        }

        return employeeList;
    }

    public Employee save(Employee employee) {
       return employeeRepository.save(employee);
    }

    public Employee update(Employee employee) {
        Optional<Employee>  upEmployee = employeeRepository.findById(employee.getId());

        if (upEmployee.isPresent()) {
            Employee emp = upEmployee.get();
            emp.setDepartment(employee.getDepartment());
            emp.setFirstName(employee.getFirstName());
            emp.setLastName(employee.getLastName());
            employeeRepository.save(emp);
        }
        return employee;

    }

    public Employee findById(String id) {
        Optional<Employee>  upEmployee = employeeRepository.findById(Integer.parseInt(id));
        if (upEmployee.isPresent()) {
            Employee emp = upEmployee.get();
            return emp;

        }
        return null;
    }

    public boolean delete(String id) {
        try {
            employeeRepository.deleteById(Integer.parseInt(id));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}
