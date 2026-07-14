package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;

// Stores employees in memory. Data resets every time the app restarts.
@Service
public class EmployeeService {

    private final Map<Long, Employee> employees = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }

    public Employee getEmployeeById(long id) {
        return employees.get(id);
    }

    public Employee addEmployee(Employee employee) {
        long id = idGenerator.getAndIncrement();
        employee.setId(id);
        employees.put(id, employee);
        return employee;
    }

    public Employee updateEmployee(long id, Employee employee) {
        if (!employees.containsKey(id)) {
            return null;
        }
        employee.setId(id);
        employees.put(id, employee);
        return employee;
    }

    public boolean deleteEmployee(long id) {
        return employees.remove(id) != null;
    }
}