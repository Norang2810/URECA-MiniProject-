package game.controller;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import game.db.RankingDAO;

public class RankingController {
    private RankingDAO dao = new RankingDAO();

    // 랭킹 등록
    public void addRanking(JFrame parent) {
        String playerIdStr = JOptionPane.showInputDialog(parent, "플레이어 ID:");
        String gameIdStr = JOptionPane.showInputDialog(parent, "게임 ID:");
        String scoreStr = JOptionPane.showInputDialog(parent, "점수:");
        if (playerIdStr != null && gameIdStr != null && scoreStr != null) {
            int playerId = Integer.parseInt(playerIdStr);
            int gameId = Integer.parseInt(gameIdStr);
            int score = Integer.parseInt(scoreStr);
            dao.insertRanking(playerId, gameId, score);
            JOptionPane.showMessageDialog(parent, "랭킹 등록 완료!");
        }
    }

    // 전체 랭킹 조회
    public void viewAll(DefaultTableModel model) {
        model.setRowCount(0);
        List<String[]> list = dao.getAllRankings();
        for (String[] row : list) {
            model.addRow(row);
        }
    }

    // 특정 게임 Top N 랭킹
    public void viewTopByGame(DefaultTableModel model, JFrame parent) {
        String gameIdStr = JOptionPane.showInputDialog(parent, "게임 ID:");
        if (gameIdStr != null) {
            int gameId = Integer.parseInt(gameIdStr);
            model.setRowCount(0);
            List<String[]> list = dao.getRankingsByGame(gameId, 10);
            for (String[] row : list) {
                model.addRow(row);
            }
        }
    }

    // 특정 플레이어 기록 조회
    public void viewByPlayer(DefaultTableModel model, JFrame parent) {
        String playerIdStr = JOptionPane.showInputDialog(parent, "플레이어 ID:");
        if (playerIdStr != null) {
            int playerId = Integer.parseInt(playerIdStr);
            model.setRowCount(0);
            List<String[]> list = dao.getRankingsByPlayer(playerId);
            for (String[] row : list) {
                model.addRow(row);
            }
        }
    }

    // 랭킹 수정 (점수 변경)
    public void update(DefaultTableModel model, JTable table, JFrame parent) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int rankingId = Integer.parseInt((String) model.getValueAt(row, 0));
            String newScoreStr = JOptionPane.showInputDialog(parent, "새 점수:", model.getValueAt(row, 3));
            if (newScoreStr != null) {
                int newScore = Integer.parseInt(newScoreStr);
                dao.updateRanking(rankingId, newScore);
                JOptionPane.showMessageDialog(parent, "점수 수정 완료!");
            }
        } else {
            JOptionPane.showMessageDialog(parent, "수정할 랭킹을 선택하세요.");
        }
    }

    // 랭킹 삭제
    public void delete(DefaultTableModel model, JTable table, JFrame parent) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int rankingId = Integer.parseInt((String) model.getValueAt(row, 0));
            dao.deleteRanking(rankingId);
            JOptionPane.showMessageDialog(parent, "랭킹 삭제 완료!");
        } else {
            JOptionPane.showMessageDialog(parent, "삭제할 랭킹을 선택하세요.");
        }
    }
}
