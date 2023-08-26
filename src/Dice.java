import java.util.Random;

public class Dice {
	
	private int range;
	
	public Dice(int range)
	{
		this.range = range;
	}
	
	public Dice()
	{
		range = 6;
	}
	
	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public int rollDice()
	{
		Random dice = new Random();
		return (1 + dice.nextInt(getRange()));
	}

	public String toString()
	{
		return String.format("Rolling...the dice shows %d!", rollDice());
	}
}
