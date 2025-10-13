package game.dao;

import game.dto.Genre;
import game.config.JdbcHelper;

import java.util.List;

public class GenreDAO {

    // R - 전체 장르 조회
    public List<Genre> getAllGenres() {
        String sql = "SELECT genre_id, genre_name FROM genres ORDER BY genre_id ASC";
        return JdbcHelper.query(sql,
                ps -> {},
                rs -> new Genre(
                        rs.getInt("genre_id"),
                        rs.getString("genre_name")
                )
        );
    }

    // C - 장르 등록
    public void insertGenre(String genreName) {
        String sql = "INSERT INTO genres (genre_name) VALUES (?)";
        JdbcHelper.update(sql, ps -> ps.setString(1, genreName));
    }

    // U - 장르명 수정
    public void updateGenre(int genreId, String newName) {
        String sql = "UPDATE genres SET genre_name = ? WHERE genre_id = ?";
        JdbcHelper.update(sql, ps -> {
            ps.setString(1, newName);
            ps.setInt(2, genreId);
        });
    }

    // D - 장르 삭제
    public void deleteGenre(int genreId) {
        String sql = "DELETE FROM genres WHERE genre_id = ?";
        JdbcHelper.update(sql, ps -> ps.setInt(1, genreId));
    }
}
