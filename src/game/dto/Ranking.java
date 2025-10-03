package game.dto;

import java.sql.Timestamp;

public class Ranking {
    private int id;
    private int playerId;
    private int gameId;
    private int score;
    private Timestamp createdAt;

    // JOIN 결과용 필드
    private String playerName;
    private String gameTitle;

    public Ranking(int id, int playerId, int gameId, int score, Timestamp createdAt) {
        this.id = id;
        this.playerId = playerId;
        this.gameId = gameId;
        this.score = score;
        this.createdAt = createdAt;
    }

    // JOIN된 이름, 게임까지 포함하는 생성자
    public Ranking(int id, String playerName, String gameTitle, int score, Timestamp createdAt) {
        this.id = id;
        this.playerName = playerName;
        this.gameTitle = gameTitle;
        this.score = score;
        this.createdAt = createdAt;
    }

    public int getId() { return id; }
    public int getPlayerId() { return playerId; }
    public int getGameId() { return gameId; }
    public int getScore() { return score; }
    public Timestamp getCreatedAt() { return createdAt; }

    public String getPlayerName() { return playerName; }
    public String getGameTitle() { return gameTitle; }

    @Override
    public String toString() {
        if (playerName != null && gameTitle != null) {
            return "[" + id + "] " + playerName + " - " + gameTitle + " : " + score + "점 (" + createdAt + ")";
        }
        return "[" + id + "] PID=" + playerId + ", GID=" + gameId + " : " + score + "점 (" + createdAt + ")";
    }
}
