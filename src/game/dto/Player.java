package game.dto;

public class Player {
	private int playerId;
	private String name;
	private String email;

	public Player(int playerId, String name, String email) {
		this.playerId = playerId;
		this.name = name;
		this.email = email;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Player [playerId=" + playerId + ", name=" + name + ", email=" + email + "]";
	}

}
