package game.dto;

public class Game {
	private int gameId;
	private String title;
	private String genre;

	public Game(int gameId, String title, String genre) {
		this.gameId = gameId;
		this.title = title;
		this.genre = genre;
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

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "Game [gameId=" + gameId + ", title=" + title + ", genre=" + genre + "]";
	}
}
