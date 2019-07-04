package org.katheer.dao;

import org.katheer.dto.Employee;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EmployeeDaoImpl implements EmployeeDao {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String insert(Employee employee) {
        String status;
        SimpleJdbcCall insert = new SimpleJdbcCall(dataSource).withProcedureName
                ("insertEmployee");

        Map<String, Object> params = new HashMap<>();
        params.put("eid", employee.getEid());
        params.put("ename", employee.getEname());
        params.put("esal", employee.getEsal());
        params.put("eaddr", employee.getEaddr());

        try {
            insert.execute(params);
            status = "Employee inserted successfully";
        } catch (Exception e) {
            e.printStackTrace();
            status = "Employee Insert Failure";
        }
        return status;
    }

    @Override
    public String update(Employee employee) {
        String status = null;
        SimpleJdbcCall update = new SimpleJdbcCall(dataSource).withProcedureName("updateEmployee");

        Map<String, Object> params = new HashMap<>();
        params.put("id", employee.getEid());
        params.put("name", employee.getEname());
        params.put("sal", employee.getEsal());
        params.put("addr", employee.getEaddr());

        try {
            update.execute(params);
            status = "Employee updated successfully";
        } catch (Exception e) {
            e.printStackTrace();
            status = "Employee update failed";
        }
        return status;
    }

    @Override
    public Employee get(int eid) {
        Employee employee = null;
        SimpleJdbcCall get = new SimpleJdbcCall(dataSource).withProcedureName("getEmployee");
        get.returningResultSet("employee", BeanPropertyRowMapper.newInstance(Employee.class));
        Map<String, Object> params = new HashMap<>();
        params.put("id", eid);

        try {
            Map<String, Object> resultSet = get.execute(params);
            ArrayList<Employee> employeeSet = (ArrayList<Employee>) resultSet.get("employee");
            employee = employeeSet.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public Employee[] getAll() {
        Employee[] employees = null;
        SimpleJdbcCall getAll = new SimpleJdbcCall(dataSource).withProcedureName("getAllEmployees");
        getAll.returningResultSet("employees", BeanPropertyRowMapper.newInstance(Employee.class));

        try {
            Map<String, Object> resultSet = getAll.execute();
            ArrayList<Employee> employeeSet = (ArrayList<Employee>) resultSet.get("employees");
            employees = new Employee[employeeSet.size()];

            int i = 0;
            while(i < employeeSet.size()) {
                employees[i] = employeeSet.get(i);
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public String delete(int eid) {
        String status;
        SimpleJdbcCall delete = new SimpleJdbcCall(dataSource).withProcedureName("deleteEmployee");

        Map<String, Object> params = new HashMap<>();
        params.put("eid", eid);

        try {
            delete.execute(params);
            status = "Employee deleted successfully";
        } catch (Exception e) {
            e.printStackTrace();
            status = "Employee delete failure";
        }

        return status;
    }

    @Override
    public float getSalary(int eid) {
        float salary = -1;
        try {
            SimpleJdbcCall getSalary =
                    new SimpleJdbcCall(dataSource).withProcedureName("getSalary");

            Map<String, Object> params = new HashMap<>();
            params.put("id", eid);
            Map<String, Object> resultSet = getSalary.execute(params);
            salary = Float.parseFloat(resultSet.get("SALARY").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return salary;
    }
}
