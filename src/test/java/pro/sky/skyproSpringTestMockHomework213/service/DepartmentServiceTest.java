package pro.sky.skyproSpringTestMockHomework213.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.skyproSpringTestMockHomework213.exeption.EmployeeNotFoundException;
import pro.sky.skyproSpringTestMockHomework213.model.Employee;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    private static final List<Employee> LIST = List.of(
            new Employee("Олег", "Васильев", 160000, 1),
            new Employee("Олим", "Воронов", 260000, 2),
            new Employee("Анна", "Цветкова", 300000, 2)
    );

    @Mock
    private EmployeeService serviceMock;

    private DepartmentService out;

    @BeforeEach
    void setUp() {
        when(serviceMock.list()).thenReturn(LIST);
        out = new DepartmentServiceImpl ((EmployeeService) serviceMock);

    }

    @Test
    void getEmployees() {

        List<Employee> expected = List.of(
                new Employee("Олег", "Васильев", 160000, 1),
                new Employee("Олим", "Воронов", 260000, 2)
        );
        List<Employee> result = out.getAllByDepartment(2);
        assertEquals(expected.size(), result.size());
        assertIterableEquals(expected, result);
    }

    @Test
    void shouldReturnSumSalaryCorrectly() {
        int expected = 7000;
        int result = out.getSum(1);
        assertEquals(expected, result);

        verify(serviceMock, only()).list();
    }

    @Test
    void shouldTrowEmployeeNotFoundExceptionOnSalaryMin() {
        assertThrows(EmployeeNotFoundException.class, () -> out.getMin(2));
    }

    @Test
    void shouldReturnMinSalaryCorrectly() {
        int expected = 23000;
        Employee result = out.getMin(2);

        assertEquals(expected, result);

    }

    @Test
    void shouldTrowEmployeeNotFoundExceptionOnSalaryMax() {
        assertThrows(EmployeeNotFoundException.class, () -> out.getMax(2));
    }

    @Test
    void shouldReturnMaxSalaryCorrectly() {
        int expected = 85000;
        Employee result = out.getMax(2);

        assertEquals(expected, result);

    }

    @Test
    void testGetEmployees() {
        Map<Integer, List<Employee>> result = out.getAll();
        List<Employee> res1 = result.get(1);
        List<Employee> exp1 = List.of(
                new Employee("Олег", "Васильев", 160000, 1),
                new Employee("Олим", "Воронов", 260000, 2)
        );
        Assertions.assertEquals(exp1.size(), res1.size());
        assertIterableEquals(exp1, res1);

        List<Employee> res2 = result.get(2);
        List<Employee> exp2 = List.of(
                new Employee("Анна", "Цветкова", 300000, 2)
        );
        Assertions.assertEquals(exp2.size(), res2.size());
        assertIterableEquals(exp2, res2);

        verify(serviceMock, only()).list();
    }


}
