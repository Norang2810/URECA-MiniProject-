package game.service;

import game.dao.GameDAO;
import game.dto.Game;

import java.util.List;

public class GameService {
	private final GameDAO gameDAO = new GameDAO();

	// 게임 등록 (비즈니스 로직 포함)
	public boolean addGame(String title, String genre) {
		if (title == null || title.isBlank()) {
			System.out.println(" 제목은 비워둘 수 없습니다.");
			return false;
		}
		if (genre == null || genre.isBlank()) {
			genre = "기타"; // 기본 장르 설정
		}
		gameDAO.insertGame(new Game(0, title, genre));
		return true;
	}

	// 전체 게임 목록
	public List<Game> getAllGames() {
		return gameDAO.getAllGames();
	}

	// 게임 검색
	public Game getGameById(int gameId) {
		return gameDAO.getGameById(gameId);
	}

	public List<Game> searchGamesByTitle(String keyword) {
		return gameDAO.getGamesByTitle(keyword);
	}

	// 게임 수정
	public void updateGame(int gameId, String title, String genre) {
		gameDAO.updateGame(new Game(gameId, title, genre));
	}

	// 게임 삭제
	public void deleteGame(int gameId) {
		gameDAO.deleteGame(gameId);
	}
}
