package pe.edu.pucp.iweb.lab10.Daos;

import pe.edu.pucp.iweb.lab10.Beans.Department;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DepartamentosDao extends BaseDao{

    public ArrayList<Department> listaDepartamentos() {
        ArrayList<Department> listaDepartamentos = new ArrayList<>();

        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM departments d " +
                     "left join employees e on d.manager_id = e.employee_id " +
                     "left join locations l on d.location_id = l.location_id");) {
            Department department;
            EmployeeDao employeeDao = new EmployeeDao();
            LocationDao locationDao = new LocationDao();
            while (rs.next()) {
                department = new Department();
                department.setDepartmentId(rs.getInt(1));
                department.setDepartmentName(rs.getString(2));
                department.setManager(employeeDao.obtenerEmpleado(rs.getInt(3)));
                department.setLocation(locationDao.obtener(rs.getInt(4)));
                listaDepartamentos.add(department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaDepartamentos;
    }
}
