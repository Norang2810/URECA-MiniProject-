package game.service;

import game.dao.RankingDAO;
import game.dto.Ranking;

import java.util.List;

public class RankingService {
    private final RankingDAO rankingDAO = new RankingDAO();

    // 랭킹 등록 (점수 검증 추가)
    public boolean addRanking(int playerId, int gameId, int score) {
        if (score < 0) {
            System.out.println(" 점수는 0 이상이어야 합니다.");
            return false;
        }
        rankingDAO.insertRanking(new Ranking(0, playerId, gameId, score, null));
        return true;
    }

    // 전체 랭킹 조회
    public List<Ranking> getAllRankings() {
        return rankingDAO.getAllRankings();
    }

    // 특정 게임 랭킹
    public List<Ranking> getRankingsByGame(int gameId, int limit) {
        return rankingDAO.getRankingsByGame(gameId, limit);
    }

    // 특정 플레이어 랭킹
    public List<Ranking> getRankingsByPlayer(int playerId) {
        return rankingDAO.getRankingsByPlayer(playerId);
    }

    // 점수 수정
    public void updateRanking(int rankingId, int newScore) {
        rankingDAO.updateRanking(rankingId, newScore);
    }

    // 랭킹 삭제
    public void deleteRanking(int rankingId) {
        rankingDAO.deleteRanking(rankingId);
    }
}
