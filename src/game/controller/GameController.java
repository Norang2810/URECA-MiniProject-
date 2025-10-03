package game.controller;

import game.dto.Game;
import game.service.GameService;

import java.util.List;

public class GameController {
    private final GameService gameService = new GameService();

    // 게임 등록
    public void addGame(String title, String genre) {
        boolean result = gameService.addGame(title, genre);
        if (result) {
            System.out.println("✅ 게임 등록 성공");
        } else {
            System.out.println("❌ 게임 등록 실패");
        }
    }

    // 전체 게임 조회
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    // 특정 게임 조회
    public Game getGameById(int gameId) {
        return gameService.getGameById(gameId);
    }

    // 제목으로 검색
    public List<Game> searchGamesByTitle(String keyword) {
        return gameService.searchGamesByTitle(keyword);
    }

    // 게임 수정
    public void updateGame(int gameId, String title, String genre) {
        gameService.updateGame(gameId, title, genre);
    }

    // 게임 삭제
    public void deleteGame(int gameId) {
        gameService.deleteGame(gameId);
    }
}
