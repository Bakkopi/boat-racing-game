import java.util.Random;

public class Current extends BoardTiles {

	private int strength;
	Random rand = new Random();

	public Current() {
		super();
	}
	
	public Current(int position) {
		setPosition(position);
		setType("Current");
		setStrength(rand.nextInt(4) + 3);
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}
	

	@Override
	public void createTiles()
	{
		for (int i = 0; i < 10 + 1; i++) {
			int pos = rand.nextInt(99);
			
			if ((getTotalTiles()[pos].getType()).equals("Normal")) {
				getTotalTiles()[pos] = new Current(pos);
			} else {
				i -= 1;
			}	
		}
	}
	
	@Override
	public void trigger(Boat player, Boat opponent) {
		System.out.printf("\n%s's boat lands on a Current tile \n", player.getPlayerName());
		player.moveForward(getStrength());
		System.out.printf("> Boat is pushed forward by %d tiles \n", getStrength());
	}
	
	@Override
	public String toString()
	{
		return String.format("C");
	}
	
}
