package pro.sky.skyproSpringTestMockHomework213.service;

import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.skyproSpringTestMockHomework213.exeption.EmployeeAlreadyAddedException;
import pro.sky.skyproSpringTestMockHomework213.exeption.EmployeeNotFoundException;
import pro.sky.skyproSpringTestMockHomework213.model.Employee;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {
    private EmployeeService out;

    private final Employee FIRST_EMPLOYEE = new Employee(
            "Олег", "Васильев", 250000,2);
    private final Employee SECOND_EMPLOYEE = new Employee(
            "Ефим", "Васильев",240000, 3);

    @BeforeEach
    void setUp() {
        out = new EmployeeServiceImpl();
    }

    @Test
    void shouldAddCorrectEmployee() {
        Employee add = out.add(
                FIRST_EMPLOYEE.getFirstName(),
                FIRST_EMPLOYEE.getLastName(),
                FIRST_EMPLOYEE.getDepartment(),
                FIRST_EMPLOYEE.getSalary());
        assertEquals(FIRST_EMPLOYEE, add);
        assertEquals(1, out.list().size());
    }


    @Test
    void shouldThrowEmployeeAlreadyAddedException() {
        out.add(
                FIRST_EMPLOYEE.getFirstName(),
                FIRST_EMPLOYEE.getLastName(),
                FIRST_EMPLOYEE.getDepartment(),
                FIRST_EMPLOYEE.getSalary());
        Assertions.assertThrows(
                EmployeeAlreadyAddedException.class,
                () -> out.add(
                        FIRST_EMPLOYEE.getFirstName(),
                        FIRST_EMPLOYEE.getLastName(),
                        FIRST_EMPLOYEE.getDepartment(),
                        FIRST_EMPLOYEE.getSalary())
        );
    }

    @Test
    void shouldEmployeeStorageIsFullException() {
        out.add("Олег", "Васильев", 160000, 1);
        out.add("Олим", "Воронов", 260000, 2);
        out.add("Анна", "Цветкова", 300000, 2);
        out.add("Анастасия", "Крылова", 460000, 3);
        out.add("Антон", "Скворцов", 570000, 3);
        out.add("Демьян", "Репин", 600000, 4);
        out.add("Екатерина", "Васильева", 650000, 4);
        out.add("Валентина", "Стружкина", 640000, 5);
        out.add("Валерьян", "Крючков", 7000000, 5);
        out.add("Роман", "Ильин", 2500000, 5);
        Assertions.assertThrows(EmployeeAlreadyAddedException.class,
                () -> out.add("Клубника", "Ежевика", 100, 1));


    }

    @Test
    void shouldThrowBadRequestException() {
        Assertions.assertThrows(BadRequestException.class,
                () -> out.add("5", "Васильев", 2, 250000));
    }

    @Test
    void shouldThrowEmployeeNotFoundExceptionOnRemove() {
        Assertions.assertThrows(EmployeeNotFoundException.class,
                () -> out.remove(
                        SECOND_EMPLOYEE.getFirstName(),
                        SECOND_EMPLOYEE.getLastName(),
                        SECOND_EMPLOYEE.getDepartment(),
                        SECOND_EMPLOYEE.getSalary())
        );

    }
    @Test
    void shouldRemoveCorrectly(){
        out.add("Лев", "Круизов", 250000, 1);
        assertEquals(1, out.list().size());
        out.remove("Лев", "Круизов", 250000, 1);
        assertEquals(0, out.list().size());
    }

    @Test
    void shouldThrowEmployeeNotFoundExceptionOnFind() {
        Assertions.assertThrows(EmployeeNotFoundException.class,
                () -> out.find(
                        SECOND_EMPLOYEE.getFirstName(),
                        SECOND_EMPLOYEE.getLastName(),
                        SECOND_EMPLOYEE.getDepartment(),
                        SECOND_EMPLOYEE.getSalary())
        );
    }
    @Test
    void shouldFindCorrectly(){
        out.add("Лев", "Круизов", 250000, 1);
        Employee expected = new Employee("Лев", "Круизов", 250000, 1);
        Employee result = out.find("Лев", "Круизов", 250000, 1);
        assertEquals(expected, result);
    }
    @Test
    void lis(){
        out.add("Андрей","Рублёв", 500000,2);
        out.add("Иван", "Поленов", 650000, 1);
        List<Employee> expected = List.of(new Employee("Иван", "Поленов", 650000, 1),
                new Employee("Андрей","Рублёв", 500000,2));
        List<Employee> result = out.list();
        assertEquals(expected.size(), result.size());
        assertIterableEquals(expected, result);
    }



}
