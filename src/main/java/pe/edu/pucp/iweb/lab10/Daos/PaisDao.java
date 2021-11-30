package pe.edu.pucp.iweb.lab10.Daos;

import pe.edu.pucp.iweb.lab10.Beans.Country;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PaisDao extends BaseDao{
    public ArrayList<Country> listar() {

        ArrayList<Country> lista = new ArrayList<>();

        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM countries")) {

            while (rs.next()) {
                Country country = new Country();
                country.setCountryId(rs.getString(1));
                country.setCountryName(rs.getString(2));
                country.setRegionId(rs.getBigDecimal(3));
                lista.add(country);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}
