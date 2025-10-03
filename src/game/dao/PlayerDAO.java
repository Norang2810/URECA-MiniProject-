package game.dao;

import game.dto.Player;
import game.config.JdbcHelper;

import java.util.List;

public class PlayerDAO {

    // C - 플레이어 등록
    public void insertPlayer(Player player) {
        String sql = "INSERT INTO players(player_name, email) VALUES(?, ?)";
        JdbcHelper.update(sql, ps -> {
            ps.setString(1, player.getName());
            ps.setString(2, player.getEmail());
        });
    }

    // R - 전체 플레이어 조회
    public List<Player> getAllPlayers() {
        String sql = "SELECT player_id, player_name, email FROM players ORDER BY player_id ASC";
        return JdbcHelper.query(sql,
                ps -> {}, // 파라미터 없음
                rs -> new Player(
                        rs.getInt("player_id"),
                        rs.getString("player_name"),
                        rs.getString("email")
                )
        );
    }

    // R - 특정 플레이어 조회 by ID
    public Player getPlayerById(int playerId) {
        String sql = "SELECT player_id, player_name, email FROM players WHERE player_id = ?";
        return JdbcHelper.queryOne(sql,
                ps -> ps.setInt(1, playerId),
                rs -> new Player(
                        rs.getInt("player_id"),
                        rs.getString("player_name"),
                        rs.getString("email")
                )
        );
    }

    // R - 이름 검색
    public List<Player> getPlayersByName(String keyword) {
        String sql = "SELECT player_id, player_name, email FROM players WHERE player_name LIKE ?";
        return JdbcHelper.query(sql,
                ps -> ps.setString(1, "%" + keyword + "%"),
                rs -> new Player(
                        rs.getInt("player_id"),
                        rs.getString("player_name"),
                        rs.getString("email")
                )
        );
    }

    // U - 플레이어 수정
    public void updatePlayer(Player player) {
        String sql = "UPDATE players SET player_name = ?, email = ? WHERE player_id = ?";
        JdbcHelper.update(sql, ps -> {
            ps.setString(1, player.getName());
            ps.setString(2, player.getEmail());
            ps.setInt(3, player.getPlayerId());
        });
    }

    // D - 플레이어 삭제
    public void deletePlayer(int playerId) {
        String sql = "DELETE FROM players WHERE player_id = ?";
        JdbcHelper.update(sql, ps -> ps.setInt(1, playerId));
    }
}
