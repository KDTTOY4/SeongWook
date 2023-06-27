package Service;

import DBConnection.DBConnection;
import Dao.TeamDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeamService {
    private TeamDao teamDao;

    public TeamService() {
        this.teamDao = new TeamDao();
    }

    public void registerTeam(int stadiumId, String name) {
        teamDao.insertTeam(stadiumId, name);
    }

    public void printTeamList() {
        String sql = "SELECT id, name FROM team";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("===== Team List =====");
            System.out.printf("%-10s %-20s\n", "Team ID", "Team Name");
            System.out.println("--------------------------");
            while (rs.next()) {
                int id = rs.getInt("id");
                String teamName = rs.getString("name");

                System.out.printf("%-10s %-20s\n", id, teamName);
            }
            System.out.println("--------------------------");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
