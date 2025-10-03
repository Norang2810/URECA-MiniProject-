package game.dao;

import game.dto.Game;
import game.config.JdbcHelper;

import java.util.List;

public class GameDAO {

    // C - 게임 등록
    public void insertGame(Game game) {
        String sql = "INSERT INTO games(game_title, genre) VALUES(?, ?)";
        JdbcHelper.update(sql, ps -> {
            ps.setString(1, game.getTitle());
            ps.setString(2, game.getGenre());
        });
    }

    // R - 전체 게임 조회
    public List<Game> getAllGames() {
        String sql = "SELECT game_id, game_title, genre FROM games ORDER BY game_id ASC";
        return JdbcHelper.query(sql,
                ps -> {},  // 파라미터 없음
                rs -> new Game(
                        rs.getInt("game_id"),
                        rs.getString("game_title"),
                        rs.getString("genre")
                )
        );
    }

    // R - 특정 게임 조회 by ID
    public Game getGameById(int gameId) {
        String sql = "SELECT game_id, game_title, genre FROM games WHERE game_id = ?";
        return JdbcHelper.queryOne(sql,
                ps -> ps.setInt(1, gameId),
                rs -> new Game(
                        rs.getInt("game_id"),
                        rs.getString("game_title"),
                        rs.getString("genre")
                )
        );
    }

    // R - 제목 검색
    public List<Game> getGamesByTitle(String keyword) {
        String sql = "SELECT game_id, game_title, genre FROM games WHERE game_title LIKE ?";
        return JdbcHelper.query(sql,
                ps -> ps.setString(1, "%" + keyword + "%"),
                rs -> new Game(
                        rs.getInt("game_id"),
                        rs.getString("game_title"),
                        rs.getString("genre")
                )
        );
    }

    // U - 게임 수정
    public void updateGame(Game game) {
        String sql = "UPDATE games SET game_title = ?, genre = ? WHERE game_id = ?";
        JdbcHelper.update(sql, ps -> {
            ps.setString(1, game.getTitle());
            ps.setString(2, game.getGenre());
            ps.setInt(3, game.getGameId());
        });
    }

    // D - 게임 삭제
    public void deleteGame(int gameId) {
        String sql = "DELETE FROM games WHERE game_id = ?";
        JdbcHelper.update(sql, ps -> ps.setInt(1, gameId));
    }
}
