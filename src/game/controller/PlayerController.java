package game.controller;

import game.dto.Player;
import game.service.PlayerService;

import java.util.List;

public class PlayerController {
    private final PlayerService playerService = new PlayerService();

    // 플레이어 등록
    public void addPlayer(String name, String email) {
        boolean result = playerService.addPlayer(name, email);
        if (result) {
            System.out.println("플레이어 등록 성공");
        } else {
            System.out.println("플레이어 등록 실패");
        }
    }

    // 전체 플레이어 조회
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    // 특정 플레이어 조회
    public Player getPlayerById(int playerId) {
        return playerService.getPlayerById(playerId);
    }

    // 이름 검색
    public List<Player> searchPlayersByName(String keyword) {
        return playerService.searchPlayersByName(keyword);
    }

    // 수정
    public void updatePlayer(int playerId, String newName, String newEmail) {
        playerService.updatePlayer(playerId, newName, newEmail);
    }

    // 삭제
    public void deletePlayer(int playerId) {
        playerService.deletePlayer(playerId);
    }
}
