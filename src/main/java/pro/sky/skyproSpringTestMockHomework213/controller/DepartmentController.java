package pro.sky.skyproSpringTestMockHomework213.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.skyproSpringTestMockHomework213.model.Employee;
import pro.sky.skyproSpringTestMockHomework213.service.DepartmentService;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllByDep(@RequestParam(required = false) Integer dep){
        return dep == null ?
                ResponseEntity.ok(service.getAll())
                :
                ResponseEntity.ok(service.getAllByDepartment(dep));

    }
    @GetMapping("{id}/salary/sum")
    public int getSum(@PathVariable("id") int dep){
        return service.getSum(dep);
    }

    @GetMapping("{id}/salary/min")
    public Employee getMin(@PathVariable("id") int dep){

        return service.getMin(dep);
    }

    @GetMapping("{id}/salary/max")
    public Employee getMax(@PathVariable("id") int dep){
        return service.getMax(dep);

    }
    @GetMapping("/employees")
    public Map<Integer, List<Employee>> getEmployees(){
        return service.getAll();
    }


}
