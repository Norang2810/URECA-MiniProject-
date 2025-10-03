package game.controller;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import game.db.PlayerDAO;

public class PlayerController {
    private PlayerDAO dao = new PlayerDAO();

    public void addPlayer(JFrame parent) {
        String name = JOptionPane.showInputDialog(parent, "플레이어 이름:");
        String email = JOptionPane.showInputDialog(parent, "이메일:");
        if (name != null && email != null) {
            dao.insertPlayer(name, email);
            JOptionPane.showMessageDialog(parent, "플레이어 등록 완료!");
        }
    }

    public void viewAll(DefaultTableModel model) {
        model.setRowCount(0);
        List<String[]> list = dao.getAllPlayers();
        for (String[] row : list) model.addRow(row);
    }

    public void search(DefaultTableModel model, JFrame parent) {
        String keyword = JOptionPane.showInputDialog(parent, "검색할 이름:");
        if (keyword != null) {
            model.setRowCount(0);
            List<String[]> list = dao.getPlayersByName(keyword);
            for (String[] row : list) model.addRow(row);
        }
    }

    public void update(DefaultTableModel model, JTable table, JFrame parent) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int id = Integer.parseInt((String) model.getValueAt(row, 0));
            String newName = JOptionPane.showInputDialog(parent, "새 이름:", model.getValueAt(row, 1));
            String newEmail = JOptionPane.showInputDialog(parent, "새 이메일:", model.getValueAt(row, 2));
            dao.updatePlayer(id, newName, newEmail);
            JOptionPane.showMessageDialog(parent, "수정 완료!");
        } else {
            JOptionPane.showMessageDialog(parent, "플레이어를 선택하세요.");
        }
    }

    public void delete(DefaultTableModel model, JTable table, JFrame parent) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int id = Integer.parseInt((String) model.getValueAt(row, 0));
            dao.deletePlayer(id);
            JOptionPane.showMessageDialog(parent, "삭제 완료!");
        }
    }
}
