package pro.sky.API_Employee.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.API_Employee.exception.EmployeeNotFoundException;
import pro.sky.API_Employee.model.Employee;
import pro.sky.API_Employee.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

  private final EmployeeService employeeService;

  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping("/add")  // http://localhost:8080/employee/add?firstName=***&lastName=***
  public Employee add(@RequestParam String firstName, @RequestParam String lastName) {
    return employeeService.add(firstName, lastName);
  }

  @GetMapping("/remove") //http://localhost:8080/employee/remove?firstName=***&lastName=***
  public Employee remove(@RequestParam String firstName, @RequestParam String lastName) {
    return employeeService.remove(firstName, lastName);
  }

  @GetMapping("/find")
  public Employee find(@RequestParam String firstName, @RequestParam String lastName) {
    return employeeService.find(firstName, lastName);
  }

  @GetMapping
  public List<Employee> list() {
    return employeeService.list();
  }

  @ExceptionHandler(EmployeeNotFoundException.class)
  public ResponseEntity<String> employeeNotFoundExceptionHandler(EmployeeNotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body("Сотрудник " + e.getEmployee() + " не найден!");
  }

}
