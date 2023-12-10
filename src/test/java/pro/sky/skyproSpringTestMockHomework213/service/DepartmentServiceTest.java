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
        //when(serviceMock.getMap()).thenReturn(LIST);
        out = new DepartmentServiceImpl ( serviceMock);

    }

    @Test
    void maxTest(){
        when(serviceMock.list()).thenReturn(LIST);
        assertEquals((out.getMax(2)), LIST.get(2));
        assertTrue((out.getMax(2).getSalary())== 300000);
    }

    @Test
    void getEmployees() {
        when(serviceMock.list()).thenReturn(LIST);

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
        when(serviceMock.list()).thenReturn(LIST);
        int expected = 560000;
        int result = out.getSum(2);
        assertEquals(expected, result);
        assertTrue((out.getSum(2))== 560000);


    }

    @Test
    void shouldTrowEmployeeNotFoundExceptionOnSalaryMin() {

                assertThrows(EmployeeNotFoundException.class, () -> out.getMin(2));
    }

    @Test
    void shouldReturnMinSalaryCorrectly() {
        when(serviceMock.list()).thenReturn(LIST);
        assertEquals((out.getMin(1)), LIST.get(0));
        assertTrue((out.getMin(1).getSalary())== 160000);


    }

    @Test
    void shouldTrowEmployeeNotFoundExceptionOnSalaryMax() {

        assertThrows(EmployeeNotFoundException.class, () -> out.getMax(2));
    }

    @Test
    void shouldReturnMaxSalaryCorrectly() {
        when(serviceMock.list()).thenReturn(LIST);
        assertEquals((out.getMax(2)), LIST.get(2));

    }


    @Test
    void testGetEmployees() {
        when(serviceMock.list()).thenReturn(LIST);

        Map<Integer, List<Employee>> result = out.getAlls();
        List<Employee> res1 = result.get(2);
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
