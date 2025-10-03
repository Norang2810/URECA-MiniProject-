package game.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameDAO {

    // C - 게임 등록
    public void insertGame(String title, String genre) {
        String sql = "INSERT INTO games(game_title, genre) VALUES(?, ?)";
        try (Connection con = DBManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.setString(2, genre);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //  R 전체 게임 조회
    public List<String[]> getAllGames() {
        List<String[]> list = new ArrayList<>();
        String sql = "SELECT game_id, game_title, genre FROM games ORDER BY game_id ASC";
        try (Connection con = DBManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new String[]{
                        String.valueOf(rs.getInt("game_id")),
                        rs.getString("game_title"),
                        rs.getString("genre")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // R - 특정 게임 조회 (id)
    public String[] getGameById(int gameId) {
        String sql = "SELECT game_id, game_title, genre FROM games WHERE game_id = ?";
        try (Connection con = DBManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, gameId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new String[]{
                            String.valueOf(rs.getInt("game_id")),
                            rs.getString("game_title"),
                            rs.getString("genre")
                    };
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //  R - 특정 게임 조회 (제목 검색)
    public List<String[]> getGamesByTitle(String keyword) {
        List<String[]> list = new ArrayList<>();
        String sql = "SELECT game_id, game_title, genre FROM games WHERE game_title LIKE ?";
        try (Connection con = DBManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new String[]{
                            String.valueOf(rs.getInt("game_id")),
                            rs.getString("game_title"),
                            rs.getString("genre")
                    });
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // U - 특정 게임 정보 수정
    public void updateGame(int gameId, String newTitle, String newGenre) {
        String sql = "UPDATE games SET game_title = ?, genre = ? WHERE game_id = ?";
        try (Connection con = DBManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, newTitle);
            ps.setString(2, newGenre);
            ps.setInt(3, gameId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // D - 특정 게임 삭제
    public void deleteGame(int gameId) {
        String sql = "DELETE FROM games WHERE game_id = ?";
        try (Connection con = DBManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, gameId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
