package com.fastcampus.dao;

import com.fastcampus.dbconnection.DBConnection;
// import com.fastcampus.model.Stadium;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class StadiumDao {
    public void insertPlayer(Integer teamId, String name, String position) {
        String sql = "INSERT INTO player (team_id, name, position) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, teamId);
            pstmt.setString(2, name);
            pstmt.setString(3, position);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Player Registration Success");
            } else {
                System.out.println("Player Registration Failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//    @Component
//    public class StadiumDao {
//        public List<Stadium> selectAll() {
//            List<Stadium> stadiumList = new ArrayList<>();
//
//            String sql = "SELECT * FROM stadium";
//
//            ResultSet rs = null;
//
//            try (Connection conn = DBConnection.getConnection();
//                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
//                rs = pstmt.executeQuery();
//
//                while (rs.next()) {
//                    stadiumList.add(
//                            new Stadium(
//                                    rs.getInt("id"),
//                                    rs.getString("name")));
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//            return stadiumList;
//        }
//    }
}
