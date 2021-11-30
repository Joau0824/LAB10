package pe.edu.pucp.iweb.lab10.Daos;

import pe.edu.pucp.iweb.lab10.Beans.Department;
import pe.edu.pucp.iweb.lab10.Beans.Employee;
import pe.edu.pucp.iweb.lab10.Beans.Job;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrincipalDao extends BaseDao{

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
}
