package com.ust.Employee_jpastreamer.controller;

import com.ust.Employee_jpastreamer.model.Employee;
import com.ust.Employee_jpastreamer.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @PostMapping("/save")
    public List<Employee> saveEmployee(@RequestBody List<Employee> employee){
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/groupByCity")
    public Map<String, List<Employee>> groupbyEmployeeByCity(){

        return employeeService.groupbyEmployeeByCity();
    }
    @GetMapping("/findall")
    public List<Employee> groupbyEmployeeByPaymentTier(){

        return employeeService.groupbyEmployeeByPaymentTier();
    }

    @GetMapping("/findByagerange/min/max")
    public List<Employee> findByAgeRange(@PathVariable int minAge, @PathVariable int maxAge){
        return employeeService.findByAgeRange(minAge, maxAge);
    }
    @GetMapping("/groupByGender")
    public Map<String, List<Employee>> groupByGender(){
        return employeeService.groupByGender();
    }

    @GetMapping("/countOfEachGender")
    public Map<String, Long> countOfEachGender(){
        return employeeService.countOfEachGender();
    }

    @GetMapping("/getByYear/{year}")
    public List<Employee> getByYear(@PathVariable int year){
        return employeeService.getByYear(year);
    }

    @GetMapping("/groupByYear/{year}")

    public Map<String,Long> groupByYear(@PathVariable int year){
        return employeeService.groupByYear(year);
    }

    @GetMapping("/getByfilter")
    public List<Employee> getByFilter(@RequestParam String gender,@RequestParam int joiningYear,
                                      @RequestParam String education,
                                      @RequestParam int experienceInCurrentDomain){
        return employeeService.getByFilter(gender,joiningYear,education, experienceInCurrentDomain);

    }
}
