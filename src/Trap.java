import java.util.Random;

public class Trap extends BoardTiles{

	private int strength;
	Random rand = new Random();

	public Trap() {
		super();
	}
	
	public Trap(int position) {
		setPosition(position);
		setType("Trap");
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
		for (int i = 1; i < 10 + 1; i++) {
			int pos = rand.nextInt(99);
			if ((getTotalTiles()[pos].getType()).equals("Normal")) {
				getTotalTiles()[pos] = new Trap(pos);
			} else {
				i -= 1;
			}	
		}
	}
	
	@Override
	public void trigger(Boat player, Boat opponent) {
		System.out.printf("\n%s's boat lands on a Trap tile \n", player.getPlayerName());
		player.moveBackwards(getStrength());
		System.out.printf("> Boat is pushed backwards by %d tiles \n", getStrength());
	}
	
	@Override
	public String toString()
	{
		return String.format("T");
	}
}
