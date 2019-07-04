package org.katheer.dao;

import org.katheer.dto.Employee;

public interface EmployeeDao {
    String insert(Employee employee);
    Employee[] getAll();
    Employee get(int eid);
    String delete(int eid);
    float getSalary(int eid);
    String update(Employee employee);
}

