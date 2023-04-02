package pro.sky.API_Employee.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import pro.sky.API_Employee.exception.EmployeeAlreadyAddedException;
import pro.sky.API_Employee.exception.EmployeeNotFoundException;
import pro.sky.API_Employee.exception.EmployeeStorageIsFullException;
import pro.sky.API_Employee.model.Employee;

@Service
public class EmployeeService {

  private static final int SIZE = 3;

  private final List<Employee> employees = new ArrayList<>(SIZE);

  @PostConstruct
  public void init() {
    employees.add(new Employee("Вася", "Петров"));
    employees.add(new Employee("Иван", "Иванов"));
  }

  public Employee add(String firstName, String lastName) {
    Employee employee = new Employee(firstName, lastName);
    if (employees.size() < SIZE) {
      for (Employee emp : employees) {
        if (emp.equals(employee)) {
          throw new EmployeeAlreadyAddedException();
        }
      }
      employees.add(employee);
      return employee;
    }
    throw new EmployeeStorageIsFullException();
  }

  public Employee find(String firstName, String lastName) {
    Employee employee = new Employee(firstName, lastName);
    if (employees.contains(employee)) {
      return employee;
    }
    throw new EmployeeNotFoundException(employee);
  }

  public Employee remove(String firstName, String lastName) {
    Employee employee = new Employee(firstName, lastName);
    if (employees.remove(employee)) {
      return employee;
    }
    throw new EmployeeNotFoundException(employee);
  }

  public List<Employee> list() {
    return Collections.unmodifiableList(employees);
  }

}
