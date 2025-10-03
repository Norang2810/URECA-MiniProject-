package game.service;

import game.dao.PlayerDAO;
import game.dto.Player;

import java.util.List;

public class PlayerService {
	private final PlayerDAO playerDAO = new PlayerDAO();

	// 플레이어 등록 (이메일 검증 추가)
	public boolean addPlayer(String name, String email) {
		if (name == null || name.isBlank()) {
			System.out.println(" 이름은 비워둘 수 없습니다.");
			return false;
		}
		if (email == null || !email.contains("@")) {
			System.out.println(" 잘못된 이메일 형식입니다.");
			return false;
		}
		playerDAO.insertPlayer(new Player(0, name, email));
		return true;
	}

	// 전체 플레이어 조회
	public List<Player> getAllPlayers() {
		return playerDAO.getAllPlayers();
	}

	// 특정 플레이어 조회
	public Player getPlayerById(int playerId) {
		return playerDAO.getPlayerById(playerId);
	}

	public List<Player> searchPlayersByName(String keyword) {
		return playerDAO.getPlayersByName(keyword);
	}

	// 플레이어 수정
	public void updatePlayer(int playerId, String newName, String newEmail) {
		playerDAO.updatePlayer(new Player(playerId, newName, newEmail));
	}

	// 플레이어 삭제
	public void deletePlayer(int playerId) {
		playerDAO.deletePlayer(playerId);
	}
}
