package com.fastcampus.dao;

import com.fastcampus.dbconnection.DBConnection;
import com.fastcampus.model.Team;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamDao {
  public static void insertTeam(Integer stadiumId, String name) {
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

  public static List<Team> selectAll() {
    List<Team> teamList = new ArrayList<>();

    String sql =
        "SELECT s.name t.name t.created_at FROM team t JOIN stadium s ON s.id = t.stadium_id";

    ResultSet rs = null;

    try (Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      rs = pstmt.executeQuery();

      while (!rs.next()) {
        System.out.println(rs.getRow());
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (rs != null && !rs.isClosed()) {
          rs.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return teamList;
  }
}