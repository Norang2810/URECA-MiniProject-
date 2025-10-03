package game.util;

import game.db.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class DataGenerator {

    public static void main(String[] args) {
        insertPlayers(1000);     // 1,000명 플레이어
        insertGames(50);         // 50개 게임
        insertRankings(1000000);    // 1000건 -> 100만건 데이터 추가해보기
    }

    // 플레이어 대량 생성
    public static void insertPlayers(int count) {
        String sql = "INSERT INTO players(player_name, email) VALUES(?, ?)";
        try (Connection con = DBManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            for (int i = 1; i <= count; i++) {
                ps.setString(1, "Player" + i);
                ps.setString(2, "player" + i + "@test.com");
                ps.addBatch();

                if (i % 500 == 0) {
                    ps.executeBatch();
                    ps.clearBatch();
                }
            }
            ps.executeBatch();
            System.out.println(count + "명의 플레이어 삽입 완료!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 게임 대량 생성
    public static void insertGames(int count) {
        String sql = "INSERT INTO games(game_title, genre) VALUES(?, ?)";
        String[] genres = {"RPG", "FPS", "Action", "Puzzle", "Sports"};
        try (Connection con = DBManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            for (int i = 1; i <= count; i++) {
                ps.setString(1, "Game" + i);
                ps.setString(2, genres[i % genres.length]);
                ps.addBatch();

                if (i % 200 == 0) {
                    ps.executeBatch();
                    ps.clearBatch();
                }
            }
            ps.executeBatch();
            System.out.println(count + "개의 게임 삽입 완료!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 랭킹 대량 생성
    public static void insertRankings(int count) {
        String sql = "INSERT INTO game_rankings(player_id, game_id, score) VALUES(?, ?, ?)";
        Random rand = new Random();
        try (Connection con = DBManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            for (int i = 1; i <= count; i++) {
                int playerId = rand.nextInt(1000) + 1; // 1~1000
                int gameId = rand.nextInt(50) + 1;     // 1~50
                int score = rand.nextInt(10000);       // 0~9999

                ps.setInt(1, playerId);
                ps.setInt(2, gameId);
                ps.setInt(3, score);
                ps.addBatch();

                if (i % 2000 == 0) {
                    ps.executeBatch();
                    ps.clearBatch();
                    System.out.println(i + "건 처리 완료...");
                }
            }
            ps.executeBatch();
            System.out.println(count + "개의 랭킹 삽입 완료!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
