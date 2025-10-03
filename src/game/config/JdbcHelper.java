package game.config;

import game.config.DBManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// 공통 JDBC 유틸
public class JdbcHelper {

    // update/insert/delete 실행
    public static int update(String sql, PreparedStatementSetter setter) {
        try (Connection con = DBManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            setter.setValues(ps);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // select 실행 → 여러 Row 반환
    public static <T> List<T> query(String sql, PreparedStatementSetter setter, ResultSetMapper<T> mapper) {
        List<T> result = new ArrayList<>();
        try (Connection con = DBManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            setter.setValues(ps);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(mapper.map(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // select 실행 → 단일 Row 반환
    public static <T> T queryOne(String sql, PreparedStatementSetter setter, ResultSetMapper<T> mapper) {
        try (Connection con = DBManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            setter.setValues(ps);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapper.map(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 파라미터 세팅용 인터페이스
    public interface PreparedStatementSetter {
        void setValues(PreparedStatement ps) throws SQLException;
    }

    // ResultSet → 객체 변환용 인터페이스
    public interface ResultSetMapper<T> {
        T map(ResultSet rs) throws SQLException;
    }
}
