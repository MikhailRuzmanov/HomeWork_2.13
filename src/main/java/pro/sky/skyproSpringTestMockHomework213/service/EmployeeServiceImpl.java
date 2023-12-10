package pro.sky.skyproSpringTestMockHomework213.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pro.sky.skyproSpringTestMockHomework213.exeption.EmployeeAlreadyAddedException;
import pro.sky.skyproSpringTestMockHomework213.exeption.EmployeeNotFoundException;
import pro.sky.skyproSpringTestMockHomework213.model.Employee;


import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    public static final int MAX = 10;

    private final Map<String, Employee> employeeMap;


    public EmployeeServiceImpl() {
        this.employeeMap = new HashMap<>();

    }

    @Override
    public Employee add (String firstName, String lastName, int salary, int department){


        Employee employee = new Employee(firstName, lastName, salary, department);
        String s= firstName + lastName;

        if(employeeMap.size()>=MAX)
            throw new EmployeeAlreadyAddedException();

        if (employeeMap.containsKey(s)) {
            throw new EmployeeAlreadyAddedException();
        }
        employeeMap.put(s, employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName, int salary, int department){
        Employee employee = new Employee(firstName, lastName, salary, department);
        if (employeeMap.containsValue(employee)) {
            String s= firstName + lastName;
            employeeMap.remove(s);
            return employee;
        }
        throw new EmployeeNotFoundException();
    }


    @Override
    public Employee find (String firstName, String lastName, int salary, int department){
        Employee employee = new Employee(firstName, lastName, salary, department);
        String s= firstName + lastName;
        if (employeeMap.containsKey(s)) {
            return employee;
        }

        throw new EmployeeNotFoundException();
    }


    @Override
    public Collection<Employee> findAll () {
        return Collections.unmodifiableCollection(employeeMap.values());
    }

    @Override
    public Map<String, Employee> getMap(){

        return employeeMap;
    }
    @Override
    public List<Employee>list(){

        return new ArrayList<>(employeeMap.values());
    }

    public Employee getEmployee(String firstName, String lastName){
        return employeeMap.get(getKey(firstName,lastName));
    }
    private String getKey(String firstName, String lastName){
        return firstName+lastName;
    }

    




}
