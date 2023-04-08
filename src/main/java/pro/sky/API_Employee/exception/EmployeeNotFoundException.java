package pro.sky.API_Employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pro.sky.API_Employee.model.Employee;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException {

  private final Employee employee;

  public EmployeeNotFoundException(Employee employee) {
    this.employee = employee;
  }

  public Employee getEmployee() {
    return employee;
  }

}
