package pe.edu.pucp.iweb.lab10.Daos;

import pe.edu.pucp.iweb.lab10.Beans.Department;
import pe.edu.pucp.iweb.lab10.Beans.Employee;
import pe.edu.pucp.iweb.lab10.Beans.Job;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class EmployeeDao extends BaseDao{

    public Employee obtenerEmpleado(int employeeId) {

        Employee employee = null;

        String sql = "SELECT * FROM employees e WHERE employee_id = ?";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setInt(1, employeeId);

            try (ResultSet rs = pstmt.executeQuery();) {
                if (rs.next()) {
                    employee = new Employee();
                    employee.setEmployeeId(rs.getInt(1));
                    employee.setFirstName(rs.getString(2));
                    employee.setLastName(rs.getString(3));
                    employee.setEmail(rs.getString(4));
                    employee.setPhoneNumber(rs.getString(5));
                    employee.setHireDate(rs.getString(6));

                    Job job = new Job();
                    job.setJobId(rs.getString(7));
                    employee.setJob(job);

                    employee.setSalary(rs.getBigDecimal(8));
                    employee.setCommissionPct(rs.getBigDecimal(9));

                    Employee manager = new Employee();
                    manager.setEmployeeId(rs.getInt(10));
                    employee.setManager(manager);

                    Department department = new Department();
                    department.setDepartmentId(rs.getInt(11));
                    employee.setDepartment(department);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return employee;
    }


    public Employee validar(String email, String contrasena) throws SQLException {
        Employee employee = null;
        String sql = "SELECT * FROM employees_credentials WHERE email = ? AND password_hashed = SHA2(?,256)";
        try(Connection conn=this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,email);
            pstmt.setString(2,contrasena);
            try(ResultSet rs = pstmt.executeQuery();) {
                if(rs.next()){
                    int idEmployee = rs.getInt(1);
                    employee = this.obtenerEmpleado(idEmployee);
                }
            }
        }
        return employee;
    }
    public boolean esJefe(int idPersona){
        boolean esjefe = false;
        String sql = "SELECT * FROM departments WHERE manager_id = ?";
        try(Connection conn = this.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,idPersona);
            try(ResultSet rs = pstmt.executeQuery();) {
                if(rs.next()){
                    esjefe = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return esjefe;
    }

    public String obtenerRol(Employee employee) {
        String rol=null;
        String sql = "SELECT (SELECT max(salary) from hr.employees where job_id= ? ) FROM hr.employees where employee_id=?;";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,employee.getJob().getJobId());
            pstmt.setInt(2,employee.getEmployeeId());
            try (ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    BigDecimal bigDecimal = rs.getBigDecimal(1);
                    if(bigDecimal.doubleValue() > 15000 || this.esJefe(employee.getEmployeeId())){
                        rol = "Top 1";
                    }else if(bigDecimal.doubleValue() <= 15000 && bigDecimal.doubleValue() > 8500){
                        rol = "Top 2";
                    }else if(bigDecimal.doubleValue() <= 8500 && bigDecimal.doubleValue() > 5000){
                        rol = "Top 3";
                    }else if(bigDecimal.doubleValue() <= 5000){
                        rol="Top 4";
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rol;
    }


    public ArrayList<Employee> listarEmpleados() {
        ArrayList<Employee> employeeList = new ArrayList<>();

        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM employees e \n"
                     + "left join jobs j on (j.job_id = e.job_id) \n"
                     + "left join departments d on (d.department_id = e.department_id)\n"
                     + "left  join employees m on (e.manager_id = m.employee_id)");) {

            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(rs.getInt(1));
                employee.setFirstName(rs.getString(2));
                employee.setLastName(rs.getString(3));
                employee.setEmail(rs.getString(4));
                employee.setPhoneNumber(rs.getString(5));
                employee.setHireDate(rs.getString(6));
                Job job = new Job();
                job.setJobId(rs.getString(7));
                job.setJobTitle(rs.getString("job_title"));
                employee.setJob(job);
                employee.setSalary(rs.getBigDecimal(8));
                employee.setCommissionPct(rs.getBigDecimal(9));
                if (rs.getInt("e.manager_id") != 0) {
                    Employee manager = new Employee();
                    manager.setEmployeeId(rs.getInt("e.manager_id"));
                    manager.setFirstName(rs.getString("m.first_name"));
                    manager.setLastName(rs.getString("m.last_name"));
                    employee.setManager(manager);
                }
                if (rs.getInt("e.department_id") != 0) {
                    Department department = new Department();
                    department.setDepartmentId(rs.getInt(11));
                    department.setDepartmentName(rs.getString("d.department_name"));
                    employee.setDepartment(department);
                }
                employeeList.add(employee);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return employeeList;
    }

}
