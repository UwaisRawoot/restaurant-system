package za.ac.cput.repository;

import za.ac.cput.entity.Employee;

import java.util.HashSet;
import java.util.Set;

public class EmployeeRepository implements IEmployeeRepository{

    private static EmployeeRepository repository = null;
    private Set<Employee> employeeDataBase = null;

    private EmployeeRepository(){
        employeeDataBase = new HashSet<Employee>();
    }

    public static EmployeeRepository getRepository(){
        if(repository == null){
            repository = new EmployeeRepository();
        }
        return repository;
    }

    @Override
    public Employee create(Employee employee) {
        boolean success = employeeDataBase.add(employee);
        if (!success)
            return null;
        return employee;
    }

    @Override
    public Employee read(String empId) {

        Employee employee = employeeDataBase.stream()
                .filter(em -> em.getEmpNumber().equals(empId))
                .findAny()
                .orElse(null);
        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        Employee oldEmployee = read(employee.getEmpNumber());
        if (oldEmployee != null) {
            employeeDataBase.remove(oldEmployee);
            employeeDataBase.add(employee);
            return employee;
        }
        return null;
    }

    @Override
    public boolean delete(String empId) {
        Employee employeeToDelete = read(empId);
        if (employeeToDelete == null)
            return false;
        employeeDataBase.remove(employeeToDelete);
        return true;
    }

    @Override
    public Set<Employee> getAll() {
        return employeeDataBase;
    }
}
