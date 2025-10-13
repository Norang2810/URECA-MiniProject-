package game.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import game.controller.GameController;
import game.dao.GenreDAO;
import game.dto.Game;
import game.dto.Genre;

public class GameView extends JFrame {
    private GameController controller = new GameController();
    private GenreDAO genreDAO = new GenreDAO();

    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField titleField;
    private JComboBox<Genre> genreComboBox; // 🔸 장르 선택 콤보박스

    public GameView() {
        setTitle("🎮 게임 관리 시스템");
        setSize(650, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 테이블 초기화
        tableModel = new DefaultTableModel(new Object[]{"ID", "제목", "장르"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // 입력 필드 + 버튼
        JPanel inputPanel = new JPanel(new FlowLayout());
        titleField = new JTextField(10);
        genreComboBox = new JComboBox<>(); // 🔸 콤보박스 초기화
        JButton addButton = new JButton("추가");
        JButton refreshButton = new JButton("새로고침");
        JButton deleteButton = new JButton("삭제");

        inputPanel.add(new JLabel("제목:"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("장르:"));
        inputPanel.add(genreComboBox);
        inputPanel.add(addButton);
        inputPanel.add(refreshButton);
        inputPanel.add(deleteButton);
        add(inputPanel, BorderLayout.SOUTH);

        // 🔸 콤보박스에 장르 목록 로드
        loadGenres();

        // 버튼 이벤트
        addButton.addActionListener((ActionEvent e) -> {
            String title = titleField.getText().trim();
            Genre selectedGenre = (Genre) genreComboBox.getSelectedItem();

            if (title.isEmpty() || selectedGenre == null) {
                JOptionPane.showMessageDialog(this, "제목과 장르를 모두 입력하세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
                return;
            }

            controller.addGame(title, selectedGenre.getGenreId()); // 🔸 genre_id 전달
            refreshTable();
            titleField.setText("");
            genreComboBox.setSelectedIndex(0);
        });

        refreshButton.addActionListener((ActionEvent e) -> {
            loadGenres(); // 새로고침 시 장르도 다시 로드
            refreshTable();
        });

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

    // 🔸 장르 콤보박스 로드 메서드
    private void loadGenres() {
        genreComboBox.removeAllItems();
        List<Genre> genres = genreDAO.getAllGenres();
        for (Genre g : genres) {
            genreComboBox.addItem(g);
        }
    }

    // 테이블 데이터 갱신
    private void refreshTable() {
        if (table.isEditing()) {
            table.getCellEditor().stopCellEditing();
        }
        tableModel.setRowCount(0);
        List<Game> games = controller.getAllGames();
        for (Game g : games) {
            tableModel.addRow(new Object[]{g.getGameId(), g.getTitle(), g.getGenreName()});
        }
    }

    // 실행용 main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameView().setVisible(true));
    }
}
