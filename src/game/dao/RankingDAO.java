package game.dao;

import game.dto.Ranking;
import game.config.JdbcHelper;

import java.util.List;

public class RankingDAO {

    // C - 랭킹 등록
    public void insertRanking(Ranking ranking) {
        String sql = "INSERT INTO game_rankings(player_id, game_id, score) VALUES(?, ?, ?)";
        JdbcHelper.update(sql, ps -> {
            ps.setInt(1, ranking.getPlayerId());
            ps.setInt(2, ranking.getGameId());
            ps.setInt(3, ranking.getScore());
        });
    }

    // R - 전체 랭킹 조회 (JOIN 포함)
    public List<Ranking> getAllRankings() {
        String sql = """
            SELECT r.id, p.player_name, g.game_title, r.score, r.created_at
            FROM game_rankings r
            JOIN players p ON r.player_id = p.player_id
            JOIN games g ON r.game_id = g.game_id
            ORDER BY r.score DESC
            LIMIT 100
        """;
        return JdbcHelper.query(sql,
                ps -> {}, // 파라미터 없음
                rs -> new Ranking(
                        rs.getInt("id"),
                        rs.getString("player_name"),
                        rs.getString("game_title"),
                        rs.getInt("score"),
                        rs.getTimestamp("created_at")
                )
        );
    }

    // R - 특정 게임 랭킹 조회
    public List<Ranking> getRankingsByGame(int gameId, int limit) {
        String sql = """
            SELECT r.id, p.player_name, g.game_title, r.score, r.created_at
            FROM game_rankings r
            JOIN players p ON r.player_id = p.player_id
            JOIN games g ON r.game_id = g.game_id
            WHERE r.game_id = ?
            ORDER BY r.score DESC
            LIMIT ?
        """;
        return JdbcHelper.query(sql,
                ps -> {
                    ps.setInt(1, gameId);
                    ps.setInt(2, limit);
                },
                rs -> new Ranking(
                        rs.getInt("id"),
                        rs.getString("player_name"),
                        rs.getString("game_title"),
                        rs.getInt("score"),
                        rs.getTimestamp("created_at")
                )
        );
    }

    // R - 특정 플레이어 기록 조회
    public List<Ranking> getRankingsByPlayer(int playerId) {
        String sql = """
            SELECT r.id, p.player_name, g.game_title, r.score, r.created_at
            FROM game_rankings r
            JOIN players p ON r.player_id = p.player_id
            JOIN games g ON r.game_id = g.game_id
            WHERE r.player_id = ?
            ORDER BY r.created_at DESC
        """;
        return JdbcHelper.query(sql,
                ps -> ps.setInt(1, playerId),
                rs -> new Ranking(
                        rs.getInt("id"),
                        rs.getString("player_name"),
                        rs.getString("game_title"),
                        rs.getInt("score"),
                        rs.getTimestamp("created_at")
                )
        );
    }

    // U - 점수 수정
    public void updateRanking(int rankingId, int newScore) {
        String sql = "UPDATE game_rankings SET score = ? WHERE id = ?";
        JdbcHelper.update(sql, ps -> {
            ps.setInt(1, newScore);
            ps.setInt(2, rankingId);
        });
    }

    // D - 랭킹 삭제
    public void deleteRanking(int rankingId) {
        String sql = "DELETE FROM game_rankings WHERE id = ?";
        JdbcHelper.update(sql, ps -> ps.setInt(1, rankingId));
    }
}
