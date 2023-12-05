package pro.sky.skyproSpringTestMockHomework213.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pro.sky.skyproSpringTestMockHomework213.service.EmployeeServiceImpl;


@Component
public class FillEmployees implements CommandLineRunner {
    @Autowired

    private EmployeeServiceImpl employeeService;
    @Override
    public void run(String... args) {
        employeeService.add("Олег", "Васильев", 160000, 1);
        employeeService.add("Олим", "Воронов", 260000, 2);
        employeeService.add("Анна", "Цветкова", 300000, 2);
        employeeService.add("Анастасия", "Крылова", 460000, 3);
        employeeService.add("Антон", "Скворцов", 570000, 3);
        employeeService.add("Демьян", "Репин", 600000, 4);
        employeeService.add("Екатерина", "Васильева", 650000, 4);
        employeeService.add("Валентина", "Стружкина", 640000, 5);
        employeeService.add("Валерьян", "Крючков", 7000000, 5);
        employeeService.add("Роман", "Ильин", 2500000, 5);

    }
}
