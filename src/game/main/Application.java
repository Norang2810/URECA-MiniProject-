package game.main;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import game.view.GameView;
import game.view.PlayerView;
import game.view.RankingView;

public class Application extends JFrame {

	private static final long serialVersionUID = 1L;

	public Application() {
        setTitle("게임 관리 메인 메뉴");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1, 10, 10));

        JButton gameButton = new JButton("게임 관리");
        JButton playerButton = new JButton("플레이어 관리");
        JButton rankingButton = new JButton("랭킹 관리");

        add(gameButton);
        add(playerButton);
        add(rankingButton);

        // 버튼 이벤트
        gameButton.addActionListener(e -> new GameView().setVisible(true));
        playerButton.addActionListener(e -> new PlayerView().setVisible(true));
        rankingButton.addActionListener(e -> new RankingView().setVisible(true));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Application app = new Application();
            app.setVisible(true);
        });
    }
}
