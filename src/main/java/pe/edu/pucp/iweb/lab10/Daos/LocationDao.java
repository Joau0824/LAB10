package pe.edu.pucp.iweb.lab10.Daos;

import pe.edu.pucp.iweb.lab10.Beans.Country;
import pe.edu.pucp.iweb.lab10.Beans.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationDao extends BaseDao {
    public Location obtener(int locationId) {
        Location location = null;
        String sql = "SELECT * FROM locations left join countries c on locations.country_id = c.country_id" +
                " WHERE location_id = ?";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, locationId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    location = new Location();
                    location.setLocationId(rs.getInt(1));
                    location.setStreetAddress(rs.getString(2));
                    location.setPostalCode(rs.getString(3));
                    location.setCity(rs.getString(4));
                    location.setStateProvince(rs.getString(5));

                    Country c = new Country();
                    c.setCountryId(rs.getString("c.country_id"));
                    c.setCountryName(rs.getString("country_name"));
                    location.setCountry(c);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return location;
    }

}
