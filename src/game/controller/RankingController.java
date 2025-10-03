package game.controller;

import game.dto.Ranking;
import game.service.RankingService;

import java.util.List;

public class RankingController {
    private final RankingService rankingService = new RankingService();

    // 랭킹 등록
    public void addRanking(int playerId, int gameId, int score) {
        boolean result = rankingService.addRanking(playerId, gameId, score);
        if (result) {
            System.out.println("랭킹 등록 성공");
        } else {
            System.out.println("랭킹 등록 실패");
        }
    }

    // 전체 랭킹 조회
    public List<Ranking> getAllRankings() {
        return rankingService.getAllRankings();
    }

    // 특정 게임 랭킹
    public List<Ranking> getRankingsByGame(int gameId, int limit) {
        return rankingService.getRankingsByGame(gameId, limit);
    }

    // 특정 플레이어 랭킹
    public List<Ranking> getRankingsByPlayer(int playerId) {
        return rankingService.getRankingsByPlayer(playerId);
    }

    // 점수 수정
    public void updateRanking(int rankingId, int newScore) {
        rankingService.updateRanking(rankingId, newScore);
    }

    // 삭제
    public void deleteRanking(int rankingId) {
        rankingService.deleteRanking(rankingId);
    }
}
