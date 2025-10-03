package game.ui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import game.controller.GameController;
import game.controller.PlayerController;
import game.controller.RankingController;

public class GameRankingManager extends JFrame {

	private JTabbedPane tabbedPane;

	// 컨트롤러
	private PlayerController playerController = new PlayerController();
	private GameController gameController = new GameController();
	private RankingController rankingController = new RankingController();

	// 플레이어 관리 컴포넌트
	private DefaultTableModel playerTableModel;
	private JTable playerTable;
	private JButton btnAddPlayer, btnViewPlayer, btnSearchPlayer, btnUpdatePlayer, btnDeletePlayer;

	// 게임 관리 컴포넌트
	private DefaultTableModel gameTableModel;
	private JTable gameTable;
	private JButton btnAddGame, btnViewGame, btnSearchGame, btnUpdateGame, btnDeleteGame;

	// 랭킹 관리 컴포넌트
	private DefaultTableModel rankingTableModel;
	private JTable rankingTable;
	private JButton btnAddRanking, btnViewRanking, btnGameTopRanking, btnPlayerRanking, btnUpdateRanking,
			btnDeleteRanking;

	public GameRankingManager() {
		setTitle("게임 랭킹 관리 시스템");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		tabbedPane = new JTabbedPane();

		// 플레이어 관리 탭
		tabbedPane.addTab("플레이어 관리", createPlayerPanel());

		// 게임 관리 탭
		tabbedPane.addTab("게임 관리", createGamePanel());

		// 랭킹 관리 탭
		tabbedPane.addTab("랭킹 관리", createRankingPanel());

		add(tabbedPane, BorderLayout.CENTER);
	}

	// 플레이어 관리 탭 생성
	private JPanel createPlayerPanel() {
		JPanel panel = new JPanel(new BorderLayout());

		playerTableModel = new DefaultTableModel(new Object[] { "ID", "Name", "Email" }, 0);
		playerTable = new JTable(playerTableModel);

		JPanel btnPanel = new JPanel();
		btnAddPlayer = new JButton("등록");
		btnViewPlayer = new JButton("전체조회");
		btnSearchPlayer = new JButton("검색");
		btnUpdatePlayer = new JButton("수정");
		btnDeletePlayer = new JButton("삭제");

		btnAddPlayer.addActionListener(e -> playerController.addPlayer(this));
		btnViewPlayer.addActionListener(e -> playerController.viewAll(playerTableModel));
		btnSearchPlayer.addActionListener(e -> playerController.search(playerTableModel, this));
		btnUpdatePlayer.addActionListener(e -> playerController.update(playerTableModel, playerTable, this));
		btnDeletePlayer.addActionListener(e -> playerController.delete(playerTableModel, playerTable, this));

		btnPanel.add(btnAddPlayer);
		btnPanel.add(btnViewPlayer);
		btnPanel.add(btnSearchPlayer);
		btnPanel.add(btnUpdatePlayer);
		btnPanel.add(btnDeletePlayer);

		panel.add(new JScrollPane(playerTable), BorderLayout.CENTER);
		panel.add(btnPanel, BorderLayout.SOUTH);

		return panel;
	}

	// 게임 관리 탭 생성
	private JPanel createGamePanel() {
		JPanel panel = new JPanel(new BorderLayout());

		gameTableModel = new DefaultTableModel(new Object[] { "ID", "Title", "Genre" }, 0);
		gameTable = new JTable(gameTableModel);

		JPanel btnPanel = new JPanel();
		btnAddGame = new JButton("등록");
		btnViewGame = new JButton("전체조회");
		btnSearchGame = new JButton("검색");
		btnUpdateGame = new JButton("수정");
		btnDeleteGame = new JButton("삭제");

		btnAddGame.addActionListener(e -> gameController.addGame(this));
		btnViewGame.addActionListener(e -> gameController.viewAll(gameTableModel));
		btnSearchGame.addActionListener(e -> gameController.search(gameTableModel, this));
		btnUpdateGame.addActionListener(e -> gameController.update(gameTableModel, gameTable, this));
		btnDeleteGame.addActionListener(e -> gameController.delete(gameTableModel, gameTable, this));

		btnPanel.add(btnAddGame);
		btnPanel.add(btnViewGame);
		btnPanel.add(btnSearchGame);
		btnPanel.add(btnUpdateGame);
		btnPanel.add(btnDeleteGame);

		panel.add(new JScrollPane(gameTable), BorderLayout.CENTER);
		panel.add(btnPanel, BorderLayout.SOUTH);

		return panel;
	}

	// 랭킹 관리 탭 생성
	private JPanel createRankingPanel() {
		JPanel panel = new JPanel(new BorderLayout());

		rankingTableModel = new DefaultTableModel(new Object[] { "ID", "Player", "Game", "Score", "Date" }, 0);
		rankingTable = new JTable(rankingTableModel);

		JPanel btnPanel = new JPanel();
		btnAddRanking = new JButton("등록");
		btnViewRanking = new JButton("전체조회");
		btnGameTopRanking = new JButton("게임별 Top10");
		btnPlayerRanking = new JButton("플레이어별 기록");
		btnUpdateRanking = new JButton("수정");
		btnDeleteRanking = new JButton("삭제");

		btnAddRanking.addActionListener(e -> rankingController.addRanking(this));
		btnViewRanking.addActionListener(e -> rankingController.viewAll(rankingTableModel));
		btnGameTopRanking.addActionListener(e -> rankingController.viewTopByGame(rankingTableModel, this));
		btnPlayerRanking.addActionListener(e -> rankingController.viewByPlayer(rankingTableModel, this));
		btnUpdateRanking.addActionListener(e -> rankingController.update(rankingTableModel, rankingTable, this));
		btnDeleteRanking.addActionListener(e -> rankingController.delete(rankingTableModel, rankingTable, this));

		btnPanel.add(btnAddRanking);
		btnPanel.add(btnViewRanking);
		btnPanel.add(btnGameTopRanking);
		btnPanel.add(btnPlayerRanking);
		btnPanel.add(btnUpdateRanking);
		btnPanel.add(btnDeleteRanking);

		panel.add(new JScrollPane(rankingTable), BorderLayout.CENTER);
		panel.add(btnPanel, BorderLayout.SOUTH);

		return panel;
	}

	// 실행용 main
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new GameRankingManager().setVisible(true);
		});
	}
}
