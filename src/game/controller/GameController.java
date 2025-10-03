package game.controller;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import game.db.GameDAO;

public class GameController {
    private GameDAO dao = new GameDAO();

    // 게임 등록
    public void addGame(JFrame parent) {
        String title = JOptionPane.showInputDialog(parent, "게임 제목:");
        String genre = JOptionPane.showInputDialog(parent, "장르:");
        if (title != null && genre != null) {
            dao.insertGame(title, genre);
            JOptionPane.showMessageDialog(parent, "게임 등록 완료!");
        }
    }

    // 전체 게임 조회
    public void viewAll(DefaultTableModel model) {
        model.setRowCount(0);
        List<String[]> list = dao.getAllGames();
        for (String[] row : list) {
            model.addRow(row);
        }
    }

    // 게임 검색 (제목)
    public void search(DefaultTableModel model, JFrame parent) {
        String keyword = JOptionPane.showInputDialog(parent, "검색할 게임 제목:");
        if (keyword != null) {
            model.setRowCount(0);
            List<String[]> list = dao.getGamesByTitle(keyword);
            for (String[] row : list) {
                model.addRow(row);
            }
        }
    }

    // 게임 수정
    public void update(DefaultTableModel model, JTable table, JFrame parent) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int id = Integer.parseInt((String) model.getValueAt(row, 0));
            String newTitle = JOptionPane.showInputDialog(parent, "새 제목:", model.getValueAt(row, 1));
            String newGenre = JOptionPane.showInputDialog(parent, "새 장르:", model.getValueAt(row, 2));
            if (newTitle != null && newGenre != null) {
                dao.updateGame(id, newTitle, newGenre);
                JOptionPane.showMessageDialog(parent, "게임 수정 완료!");
            }
        } else {
            JOptionPane.showMessageDialog(parent, "수정할 게임을 선택하세요.");
        }
    }

    // 게임 삭제
    public void delete(DefaultTableModel model, JTable table, JFrame parent) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int id = Integer.parseInt((String) model.getValueAt(row, 0));
            dao.deleteGame(id);
            JOptionPane.showMessageDialog(parent, "게임 삭제 완료!");
        } else {
            JOptionPane.showMessageDialog(parent, "삭제할 게임을 선택하세요.");
        }
    }
}
