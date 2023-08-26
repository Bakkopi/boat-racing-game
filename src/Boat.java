
public class Boat {

	private String playerName;
	private int position;
	private boolean stuck;
	private int stuckTurns;
	
	public Boat(String playerName) {
		setPlayerName(playerName);
		setPosition(0);
		setStuck(false);
		setStuckTurns(0);
	}
	
	public Boat(String playerName, int position) {
		this.playerName = playerName;
		this.position = position;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		if (position > 100)
		{
			this.position = 100;
		}
		else if (position < 0)
		{
			this.position = 0;
		}
		else
		{
			this.position = position;
		}
	}
	
	public boolean isStuck() {
		return stuck;
	}

	public void setStuck(boolean stuck) {
		this.stuck = stuck;
	}
	
	public int getStuckTurns() {
		return stuckTurns;
	}

	public void setStuckTurns(int stuckTurns) {
		this.stuckTurns = stuckTurns;
	}
	
	public void moveForward(int steps)
	{
		setPosition(getPosition() + steps);
	}
	
	public void moveBackwards(int steps)
	{
		setPosition(getPosition() - steps);
	}
	
	public String toString()
	{
		return String.format("%s: tile %d", getPlayerName(), getPosition());
	}
	
}
