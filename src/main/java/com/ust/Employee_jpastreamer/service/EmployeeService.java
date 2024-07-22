package com.ust.Employee_jpastreamer.service;



import com.speedment.jpastreamer.application.JPAStreamer;
import com.ust.Employee_jpastreamer.model.Employee;
import com.ust.Employee_jpastreamer.repository.Employeerepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private Employeerepo employeeRepository;

    private final JPAStreamer jpaStreamer;

    @Autowired
    public EmployeeService(final JPAStreamer jpaStreamer) {
        this.jpaStreamer = jpaStreamer;
    }


    public Map<String, List<Employee>> groupbyEmployeeByCity() {
        return jpaStreamer.stream(Employee.class)
                .collect(Collectors.groupingBy(Employee::getCity));
    }

    public List<Employee> groupbyEmployeeByPaymentTier() {
        return employeeRepository.findAll() ;
    }

    public List<Employee> saveEmployee(List<Employee> employee){
        return employeeRepository.saveAll(employee);
    }


    public List<Employee> findByAgeRange(int minAge, int maxAge) {
        return jpaStreamer.stream(Employee.class)
                .filter(employee -> employee.getAge() >= minAge && employee.getAge() <= maxAge)
                .toList();
    }

    public Map<String, List<Employee>> groupByGender() {
        return jpaStreamer.stream(Employee.class)
               .collect(Collectors.groupingBy(Employee::getGender));
    }

    public Map<String, Long> countOfEachGender() {
        return jpaStreamer.stream(Employee.class)
                .collect(Collectors.groupingBy(Employee::getGender,Collectors.counting()));

    }

    public List<Employee> getByYear(int year) {
        return jpaStreamer.stream(Employee.class)
                .filter(employee -> employee.getJoiningYear() == year)
                .collect(Collectors.toList());
    }


    public Map<String, Long> groupByYear(int year) {
        return jpaStreamer.stream(Employee.class)
                .filter(employee -> employee.getJoiningYear() == year)
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));

    }

    public List<Employee> getByFilter(String gender, int joiningYear, String education, int experienceInCurrentDomain) {
        return jpaStreamer.stream(Employee.class)
               .filter(employee -> employee.getGender().equalsIgnoreCase(gender) && employee.getJoiningYear() == joiningYear && employee.getEducation().equalsIgnoreCase(education) && employee.getExperienceInCurrentDomain() >= experienceInCurrentDomain)
               .collect(Collectors.toList());
    }
}
