package game.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RankingDAO {

	// C- 랭킹 등록
	public void insertRanking(int playerId, int gameId, int score) {
		String sql = "INSERT INTO game_rankings(player_id, game_id, score) VALUES(?, ?, ?)";
		try (Connection con = DBManager.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, playerId);
			ps.setInt(2, gameId);
			ps.setInt(3, score);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// R - 전체 랭킹 조회 (JOIN 포함)
	public List<String[]> getAllRankings() {
		List<String[]> list = new ArrayList<>();
		String sql = """
				    SELECT r.id, p.player_name, g.game_title, r.score, r.created_at
				    FROM game_rankings r
				    JOIN players p ON r.player_id = p.player_id
				    JOIN games g ON r.game_id = g.game_id
				    ORDER BY r.score DESC
				    LIMIT 100
				""";
		try (Connection con = DBManager.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				list.add(new String[] { String.valueOf(rs.getInt("id")), rs.getString("player_name"),
						rs.getString("game_title"), String.valueOf(rs.getInt("score")),
						rs.getTimestamp("created_at").toString() });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<String[]> getRankingsByGame(int gameId, int limit) {
		List<String[]> list = new ArrayList<>();
		String sql = """
				    SELECT r.id, p.player_name, g.game_title, r.score, r.created_at
				    FROM game_rankings r
				    JOIN players p ON r.player_id = p.player_id
				    JOIN games g ON r.game_id = g.game_id
				    WHERE r.game_id = ?
				    ORDER BY r.score DESC
				    LIMIT ?
				""";
		try (Connection con = DBManager.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, gameId);
			ps.setInt(2, limit);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					list.add(new String[] { String.valueOf(rs.getInt("id")), rs.getString("player_name"),
							rs.getString("game_title"), String.valueOf(rs.getInt("score")),
							rs.getTimestamp("created_at").toString() });
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// R - 특정 플레이어의 모든 게임 기록 조회
	public List<String[]> getRankingsByPlayer(int playerId) {
		List<String[]> list = new ArrayList<>();
		String sql = """
				    SELECT r.id, p.player_name, g.game_title, r.score, r.created_at
				    FROM game_rankings r
				    JOIN players p ON r.player_id = p.player_id
				    JOIN games g ON r.game_id = g.game_id
				    WHERE r.player_id = ?
				    ORDER BY r.created_at DESC
				""";
		try (Connection con = DBManager.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, playerId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					list.add(new String[] { String.valueOf(rs.getInt("id")), rs.getString("player_name"),
							rs.getString("game_title"), String.valueOf(rs.getInt("score")),
							rs.getTimestamp("created_at").toString() });
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// U - 랭킹 점수 수정
	public void updateRanking(int rankingId, int newScore) {
		String sql = "UPDATE game_rankings SET score = ? WHERE id = ?";
		try (Connection con = DBManager.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, newScore);
			ps.setInt(2, rankingId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// D - 특정 랭킹 삭제
	public void deleteRanking(int rankingId) {
		String sql = "DELETE FROM game_rankings WHERE id = ?";
		try (Connection con = DBManager.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, rankingId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
