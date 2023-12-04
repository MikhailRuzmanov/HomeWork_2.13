package pro.sky.skyproSpringTestMockHomework213.service;


import pro.sky.skyproSpringTestMockHomework213.model.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface EmployeeService {


    Employee add(String firstName, String lastName, int salary, int department);
    Employee remove(String firstName, String lastName, int salary, int department);

    Employee find(String firstName, String lastName, int salary, int department);

    Collection<Employee> findAll();

    Map<String, Employee> getMap();

    List<Employee> list();
}
