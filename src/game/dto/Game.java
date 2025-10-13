package game.dto;

import java.sql.Timestamp;

public class Game {
	private int gameId; // PK
	private String title; // 게임 이름
	private int genreId; // FK -> genres.genre_id
	private String genreName; // JOIN 결과용 (표시용)
	private Timestamp createdAt;

	// 생성자 (조회 시 사용)
	public Game(int gameId, String title, String genreName) {
		this.gameId = gameId;
		this.title = title;
		this.genreName = genreName;
	}

	// 생성자 (등록 시 사용)
	public Game(String title, int genreId) {
		this.title = title;
		this.genreId = genreId;
	}

	// 생성자 (수정 시 사용)
	public Game(int gameId, String title, int genreId) {
		this.gameId = gameId;
		this.title = title;
		this.genreId = genreId;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getGenreId() {
		return genreId;
	}

	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Game [gameId=" + gameId + ", title=" + title + ", genreId=" + genreId + ", genreName=" + genreName
				+ "]";
	}
}
