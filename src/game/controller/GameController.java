package game.controller;

import game.dto.Game;
import game.service.GameService;

import java.util.List;

public class GameController {
    private final GameService gameService = new GameService();

    // 게임 등록 (장르를 ID로 받음)
    public void addGame(String title, int genreId) {
        boolean result = gameService.addGame(title, genreId);
        if (result) {
            System.out.println("게임 등록 성공");
        } else {
            System.out.println("게임 등록 실패");
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

    // 게임 수정 (장르도 함께 수정 가능)
    public void updateGame(int gameId, String title, int genreId) {
        gameService.updateGame(gameId, title, genreId);
    }

    // 게임 삭제
    public void deleteGame(int gameId) {
        gameService.deleteGame(gameId);
    }
}
