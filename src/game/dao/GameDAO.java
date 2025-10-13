package game.dao;

import game.dto.Game;
import game.config.JdbcHelper;

import java.util.List;

public class GameDAO {

    // C - 게임 등록
    public void insertGame(Game game) {
        String sql = "INSERT INTO games (game_title, genre_id) VALUES (?, ?)";
        JdbcHelper.update(sql, ps -> {
            ps.setString(1, game.getTitle());
            ps.setInt(2, game.getGenreId()); // genre_id FK 전달
        });
    }

    // R - 전체 게임 조회 (JOIN으로 장르명 표시)
    public List<Game> getAllGames() {
        String sql = """
            SELECT g.game_id,
                   g.game_title,
                   ge.genre_name
            FROM games g
            LEFT JOIN genres ge ON g.genre_id = ge.genre_id
            ORDER BY g.game_id ASC
        """;

        return JdbcHelper.query(sql,
                ps -> {},
                rs -> new Game(
                        rs.getInt("game_id"),
                        rs.getString("game_title"),
                        rs.getString("genre_name")  // DTO에서 genreName 필드로 매핑
                )
        );
    }


    // R - 특정 게임 조회 by ID
    public Game getGameById(int gameId) {
        String sql = """
            SELECT g.game_id, g.game_title, ge.genre_name
            FROM games g
            LEFT JOIN genres ge ON g.genre_id = ge.genre_id
            WHERE g.game_id = ?
        """;
        return JdbcHelper.queryOne(sql,
                ps -> ps.setInt(1, gameId),
                rs -> new Game(
                        rs.getInt("game_id"),
                        rs.getString("game_title"),
                        rs.getString("genre_name")
                )
        );
    }

    // R - 제목 검색
    public List<Game> getGamesByTitle(String keyword) {
        String sql = """
            SELECT g.game_id, g.game_title, ge.genre_name
            FROM games g
            LEFT JOIN genres ge ON g.genre_id = ge.genre_id
            WHERE g.game_title LIKE ?
        """;
        return JdbcHelper.query(sql,
                ps -> ps.setString(1, "%" + keyword + "%"),
                rs -> new Game(
                        rs.getInt("game_id"),
                        rs.getString("game_title"),
                        rs.getString("genre_name")
                )
        );
    }

    // U - 게임 수정
    public void updateGame(Game game) {
        String sql = "UPDATE games SET game_title = ?, genre_id = ? WHERE game_id = ?";
        JdbcHelper.update(sql, ps -> {
            ps.setString(1, game.getTitle());
            ps.setInt(2, game.getGenreId());
            ps.setInt(3, game.getGameId());
        });
    }

    // D - 게임 삭제
    public void deleteGame(int gameId) {
        String sql = "DELETE FROM games WHERE game_id = ?";
        JdbcHelper.update(sql, ps -> ps.setInt(1, gameId));
    }
}
