package game.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import game.controller.PlayerController;
import game.dto.Player;

public class PlayerView extends JFrame {
    private PlayerController controller = new PlayerController();

    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField nameField, emailField;

    public PlayerView() {
        setTitle("플레이어 관리");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // 테이블 초기화
        tableModel = new DefaultTableModel(new Object[]{"ID", "이름", "이메일"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // 입력 필드 + 버튼
        JPanel inputPanel = new JPanel(new FlowLayout());
        nameField = new JTextField(10);
        emailField = new JTextField(15);
        JButton addButton = new JButton("추가");
        JButton refreshButton = new JButton("새로고침");
        JButton deleteButton = new JButton("삭제");

        inputPanel.add(new JLabel("이름:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("이메일:"));
        inputPanel.add(emailField);
        inputPanel.add(addButton);
        inputPanel.add(refreshButton);
        inputPanel.add(deleteButton);

        add(inputPanel, BorderLayout.SOUTH);

        // 버튼 이벤트
        addButton.addActionListener((ActionEvent e) -> {
            String name = nameField.getText();
            String email = emailField.getText();
            controller.addPlayer(name, email);
            refreshTable();
            nameField.setText("");
            emailField.setText("");
        });

        refreshButton.addActionListener((ActionEvent e) -> refreshTable());

        deleteButton.addActionListener((ActionEvent e) -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int playerId = (int) tableModel.getValueAt(selectedRow, 0);
                controller.deletePlayer(playerId);
                refreshTable();
            }
        });

        // 첫 실행 시 데이터 로드
        refreshTable();
    }

    // 테이블 갱신
    private void refreshTable() {
        tableModel.setRowCount(0);
        if (table.isEditing()) {
            table.getCellEditor().stopCellEditing(); // 편집 중지
        }
        List<Player> players = controller.getAllPlayers();
        for (Player p : players) {
            tableModel.addRow(new Object[]{p.getPlayerId(), p.getName(), p.getEmail()});
        }
    }
}
