package game.service;

import game.dao.GameDAO;
import game.dto.Game;

import java.util.List;

public class GameService {
    private final GameDAO gameDAO = new GameDAO();

    // 게임 등록 (비즈니스 로직 포함)
    public boolean addGame(String title, int genreId) {
        if (title == null || title.isBlank()) {
            System.out.println("제목은 비워둘 수 없습니다.");
            return false;
        }

        if (genreId <= 0) {
            System.out.println("유효하지 않은 장르 ID입니다.");
            return false;
        }

        gameDAO.insertGame(new Game(title, genreId));
        return true;
    }

    // 전체 게임 목록
    public List<Game> getAllGames() {
        return gameDAO.getAllGames();
    }

    // 특정 게임 조회
    public Game getGameById(int gameId) {
        return gameDAO.getGameById(gameId);
    }

    // 제목 검색
    public List<Game> searchGamesByTitle(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            System.out.println("검색어가 비어 있습니다. 전체 목록을 반환합니다.");
            return gameDAO.getAllGames();
        }
        return gameDAO.getGamesByTitle(keyword);
    }

    // 게임 수정
    public void updateGame(int gameId, String title, int genreId) {
        if (title == null || title.isBlank()) {
            System.out.println(" 제목은 비워둘 수 없습니다.");
            return;
        }

        gameDAO.updateGame(new Game(gameId, title, String.valueOf(genreId))); 
    }

    // 게임 삭제
    public void deleteGame(int gameId) {
        gameDAO.deleteGame(gameId);
    }
}
