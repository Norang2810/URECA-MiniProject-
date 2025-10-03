package game.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {
	//	1. C - 플레이어 등록
	public void insertPlayer(String name,String email) {
		String sql = "INSERT INTO players(player_name,email) VALUES(?,?);";
		try {
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);{
				ps.setString(1, name);
	            ps.setString(2, email);
	            ps.executeUpdate();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	 // R- 플레이어 전체 조회
    public List<String[]> getAllPlayers() {
        List<String[]> list = new ArrayList<>();
        String sql = "SELECT player_id, player_name, email FROM players ORDER BY player_id ASC";
        try(Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            while(rs.next()) {
                list.add(new String[]{
                    String.valueOf(rs.getInt("player_id")),
                    rs.getString("player_name"),
                    rs.getString("email")
                });
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // R-특정 플레이어 조회 (id)
    public String[] getPlayerById(int playerId) {
        String sql = "SELECT player_id, player_name, email FROM players WHERE player_id = ?";
        try(Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, playerId);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    return new String[]{
                        String.valueOf(rs.getInt("player_id")),
                        rs.getString("player_name"),
                        rs.getString("email")
                    };
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    // R - 특정 플레이어 조회 (이름으로 검색)
    public List<String[]> getPlayersByName(String name) {
        List<String[]> list = new ArrayList<>();
        String sql = "SELECT player_id, player_name, email FROM players WHERE player_name LIKE ?";
        try (Connection con = DBManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%"); // 부분 검색
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new String[]{
                            String.valueOf(rs.getInt("player_id")),
                            rs.getString("player_name"),
                            rs.getString("email")
                    });
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    //  U - 특정 플레이어 정보 수정
    public void updatePlayer(int playerId, String newName, String newEmail) {
        String sql = "UPDATE players SET player_name = ?, email = ? WHERE player_id = ?";
        try (Connection con = DBManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, newName);
            ps.setString(2, newEmail);
            ps.setInt(3, playerId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //  D - 특정 플레이어 삭제
    public void deletePlayer(int playerId) {
        String sql = "DELETE FROM players WHERE player_id = ?";
        try (Connection con = DBManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, playerId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
}
