package org.katheer.test;

import org.katheer.dao.EmployeeDao;
import org.katheer.dto.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("org" +
                "/katheer/resource/applicationContext.xml");
        EmployeeDao employeeDao = (EmployeeDao) context.getBean("employeeDao");

        /*
        //INSERT EMPLOYEE
        //---------------
        Employee employee1 = new Employee();
        employee1.setEid(111);
        employee1.setEname("AAA");
        employee1.setEsal(12000);
        employee1.setEaddr("Chennai");

        System.out.println(employeeDao.insert(employee1));
         */

        /*
        //DELETE EMPLOYEE
        //---------------
        System.out.println(employeeDao.delete(111));
        */

        /*
        //GET ALL EMPLOYEES
        //-----------------
        Employee[] employees = employeeDao.getAll();

        for(Employee employee : employees) {
            System.out.println(employee);
        }
        */

        /*
        //GET EMPLOYEE SALARY
        //-------------------
        System.out.println(employeeDao.getSalary(111));
        System.out.println(employeeDao.getSalary(222));
         */

        /*
        //UPDATE EMPLOYEE
        //---------------
        Employee employee = new Employee();
        employee.setEid(111);
        employee.setEname("XXX");
        employee.setEsal(7000);
        employee.setEaddr("Dindigul");
        System.out.println(employeeDao.update(employee));
         */

        /*
        //GET EMPLOYEE
        //------------
        System.out.println(employeeDao.get(222));
        */
    }
}
