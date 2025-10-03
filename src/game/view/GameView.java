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

import game.controller.GameController;
import game.dto.Game;

public class GameView extends JFrame {
	private GameController controller = new GameController();

	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField titleField, genreField;

	public GameView() {
		setTitle("게임 관리 시스템");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		// 테이블 초기화
		tableModel = new DefaultTableModel(new Object[] { "ID", "제목", "장르" }, 0);
		table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);

		// 입력 필드 + 버튼
		JPanel inputPanel = new JPanel(new FlowLayout());
		titleField = new JTextField(10);
		genreField = new JTextField(10);
		JButton addButton = new JButton("추가");
		JButton refreshButton = new JButton("새로고침");
		JButton deleteButton = new JButton("삭제");

		inputPanel.add(new JLabel("제목:"));
		inputPanel.add(titleField);
		inputPanel.add(new JLabel("장르:"));
		inputPanel.add(genreField);
		inputPanel.add(addButton);
		inputPanel.add(refreshButton);
		inputPanel.add(deleteButton);

		add(inputPanel, BorderLayout.SOUTH);

		// 버튼 이벤트
		addButton.addActionListener((ActionEvent e) -> {
			String title = titleField.getText();
			String genre = genreField.getText();
			controller.addGame(title, genre);
			refreshTable();
			titleField.setText("");
			genreField.setText("");
		});

		refreshButton.addActionListener((ActionEvent e) -> refreshTable());

		deleteButton.addActionListener((ActionEvent e) -> {
			int selectedRow = table.getSelectedRow();
			if (selectedRow >= 0) {
				int gameId = (int) tableModel.getValueAt(selectedRow, 0);
				controller.deleteGame(gameId);
				refreshTable();
			}
		});

		// 첫 실행 시 테이블 로드
		refreshTable();
	}

	// 테이블 데이터 갱신
	private void refreshTable() {
		if (table.isEditing()) {
	        table.getCellEditor().stopCellEditing(); // 편집 중지
	    }
		tableModel.setRowCount(0); // 초기화
		List<Game> games = controller.getAllGames();
		for (Game g : games) {
			tableModel.addRow(new Object[] { g.getGameId(), g.getTitle(), g.getGenre() });
		}
	}
}
