package pro.sky.skyproSpringTestMockHomework213.service;

import org.springframework.stereotype.Service;
import pro.sky.skyproSpringTestMockHomework213.model.Employee;
import pro.sky.skyproSpringTestMockHomework213.exeption.EmployeeNotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeServiceImpl;

    public DepartmentServiceImpl(EmployeeService employeeServiceImpl) {
        this.employeeServiceImpl = employeeServiceImpl;
    }

    public List<Employee>getAllByDepartment(int dep){
        return employeeServiceImpl.list()
                .stream()
                .filter(employee -> employee.getDepartment() == dep)
                .collect(Collectors.toList());
    }
    @Override
    public Employee getMin(int dep){
    return employeeServiceImpl.list()
            .stream()
            .filter(employee -> employee.getDepartment() == dep)
            .min(Comparator.comparingDouble(employeeServiceImpl-> employeeServiceImpl.getSalary()))
            .orElseThrow(EmployeeNotFoundException::new);

}
    @Override
    public Employee getMax(int dep){
        return employeeServiceImpl.list()
                .stream()
                .filter(employee -> employee.getDepartment() == dep)
                .max(Comparator.comparingDouble(employeeServiceImpl-> employeeServiceImpl.getSalary()))
                .orElseThrow(EmployeeNotFoundException::new);
    }
    @Override
    public int getSum(int dep){
        return employeeServiceImpl.list()
                .stream()
                .filter(employee -> employee.getDepartment() == dep)
                .mapToInt(Employee::getSalary)
                .sum();
    }


    @Override
    public Map<Integer, List<Employee>> getAll() {
        return employeeServiceImpl.findAll()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
    @Override
    public Map<Integer, List<Employee>> getAlls() {
        return employeeServiceImpl.list()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

}
