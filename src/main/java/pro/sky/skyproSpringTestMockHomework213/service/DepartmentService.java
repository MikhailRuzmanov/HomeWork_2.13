package pro.sky.skyproSpringTestMockHomework213.service;


import pro.sky.skyproSpringTestMockHomework213.model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    public List<Employee> getAllByDepartment(int dep);
    public Employee getMin(int dep);
    public Employee getMax(int dep);


    int getSum(int dep);

    public Map<Integer, List<Employee>> getAll();


}
