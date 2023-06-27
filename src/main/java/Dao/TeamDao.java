package Dao;

import DBConnection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TeamDao {
    public void insertTeam(int stadiumId, String name) {
        String sql = "INSERT INTO TEAM (stadium_id, name) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, stadiumId);
            pstmt.setString(2, name);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Team Registration Success");
            } else {
                System.out.println("Team Registration Failed");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
