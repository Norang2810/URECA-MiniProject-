package game.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import game.controller.RankingController;
import game.dto.Ranking;

public class RankingView extends JFrame {
    private RankingController controller = new RankingController();
    private RankingController rankingController;

    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField playerIdField, gameIdField, scoreField;
    private JButton top10Button;
    
    public RankingView() {
    	rankingController = new RankingController();
        setTitle("게임 랭킹 관리");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // 테이블 초기화
        tableModel = new DefaultTableModel(new Object[]{"ID", "플레이어", "게임", "점수", "등록일"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // 입력 필드 + 버튼
        JPanel inputPanel = new JPanel(new FlowLayout());
        playerIdField = new JTextField(5);
        gameIdField = new JTextField(5);
        scoreField = new JTextField(5);

        JButton addButton = new JButton("등록");
        JButton refreshButton = new JButton("새로고침");
        JButton deleteButton = new JButton("삭제");
        JButton updateButton = new JButton("점수 수정");
        JButton top10Button = new JButton("Top10 조회");
        
        
        inputPanel.add(new JLabel("플레이어ID:"));
        inputPanel.add(playerIdField);
        inputPanel.add(new JLabel("게임ID:"));
        inputPanel.add(gameIdField);
        inputPanel.add(new JLabel("점수:"));
        inputPanel.add(scoreField);

        inputPanel.add(addButton);
        inputPanel.add(top10Button);
        inputPanel.add(updateButton);
        inputPanel.add(refreshButton);
        inputPanel.add(deleteButton);

        add(inputPanel, BorderLayout.SOUTH);

        // 버튼 이벤트
        addButton.addActionListener((ActionEvent e) -> {
            int playerId = Integer.parseInt(playerIdField.getText());
            int gameId = Integer.parseInt(gameIdField.getText());
            int score = Integer.parseInt(scoreField.getText());
            controller.addRanking(playerId, gameId, score);
            refreshTable();
        });

        updateButton.addActionListener((ActionEvent e) -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int rankingId = (int) tableModel.getValueAt(selectedRow, 0);
                int newScore = Integer.parseInt(scoreField.getText());
                controller.updateRanking(rankingId, newScore);
                refreshTable();
            }
        });

        refreshButton.addActionListener((ActionEvent e) -> refreshTable());

        deleteButton.addActionListener((ActionEvent e) -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int rankingId = (int) tableModel.getValueAt(selectedRow, 0);
                controller.deleteRanking(rankingId);
                refreshTable();
            }
        });
        
        top10Button.addActionListener(e -> {
            try {
                int gameId = Integer.parseInt(gameIdField.getText()); // 입력된 게임ID 기준
                List<Ranking> top10 = rankingController.getRankingsByGame(gameId, 10); // 인스턴스 메서드 호출
                refreshTable(top10);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "게임 ID를 입력하세요.");
            }
        });

        // 첫 실행 시 데이터 로드
        refreshTable();
    }

    // 테이블 갱신
    private void refreshTable() {    	
        tableModel.setRowCount(0);
        List<Ranking> rankings = controller.getAllRankings();
        for (Ranking r : rankings) {
            tableModel.addRow(new Object[]{
                    r.getId(),
                    r.getPlayerName(),
                    r.getGameTitle(),
                    r.getScore(),
                    r.getCreatedAt()
            });
        }
    }
    //오버로딩
    private void refreshTable(List<Ranking> rankings) {
    	if (table.isEditing()) {
            table.getCellEditor().stopCellEditing(); // 
        }
        tableModel.setRowCount(0);
        for (Ranking r : rankings) {
            tableModel.addRow(new Object[]{
                    r.getId(),
                    r.getPlayerName(),
                    r.getGameTitle(),
                    r.getScore(),
                    r.getCreatedAt()
            });
        }
    }
}
