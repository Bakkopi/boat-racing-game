import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Chance extends BoardTiles {
	private int option;
	Random rand = new Random();

	public Chance() {
		super();
	}
	
	public Chance(int position, int option) {
		setPosition(position);
		setType("Chance");
		setOption(option);
	}
	
	public int getOption() {
		return option;
	}

	public void setOption(int option) {
		this.option = option;
	}
	
	public boolean compareValues(int dice, int guess) {
		if (dice == guess) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void createTiles() {
		// Creates 2 tiles for each Chance option.
		
		for (int option = 1; option < 5; option++) {
			for (int i = 0; i < 2; i++) {
				int pos = rand.nextInt(99);
				if ((getTotalTiles()[pos].getType()).equals("Normal")) {
					getTotalTiles()[pos] = new Chance(pos, option);
				} else {
					i -= 1;
				}	
			}
		}

	}
	
	@Override
	public void trigger(Boat player, Boat opponent) {
		
		Dice dice = new Dice();
		Scanner input = new Scanner(System.in);
		
		System.out.printf("\n%s's boat lands on a Chance tile \n\n", player.getPlayerName());
		
		switch (getOption()) {
		case 1:
			int guess = 0;
			
			System.out.printf("An apparition appears before %s, offering up a gambit...\n", player.getPlayerName());
			System.out.printf("\"Guess a number and roll the dice\", it says.\n\n");
			System.out.printf("If the guess is right, advance by twice the dice value. If not, move backwards accordingly.\n");
			
			try {
				while (!(guess >= 1 && guess <= dice.getRange())) {
					System.out.printf("Guess [1 - %d]: ", dice.getRange());
					guess = input.nextInt();
					if (!(guess >= 1 && guess <= dice.getRange())) {
						System.out.printf("Guess must be between 1 and %d\n", dice.getRange());
					}
				}
			} catch (InputMismatchException e) {
				System.out.println("Error: Only numerical values accepted!\n");
			}
			
			
			System.out.print("Press [Enter] to roll dice.");
			input.nextLine();
			int gambitDice = dice.rollDice();
			int diceDoubled = gambitDice * 2;
			System.out.printf("\nThe dice value is... %d!\n\n", gambitDice);
			
			if (compareValues(gambitDice, guess)) {
				System.out.printf("> \"Well done...\" - The apparition pushes %s forward by %d tiles.\n", player.getPlayerName(), diceDoubled);
				player.moveForward(diceDoubled);
			} else {
				System.out.printf("> \"Better luck next time...\" - The apparition pushes %s backwards by %d tiles.\n", player.getPlayerName(), diceDoubled);
				player.moveBackwards(diceDoubled);
			}
			
			break;
			
		case 2:
						
			System.out.printf("%s loads up their cannons and points them towards %s's boat!\n", player.getPlayerName(), opponent.getPlayerName());
			System.out.print("Roll the dice to determine the cannon power. Press [Enter] ");
			input.nextLine();
			int diceValue = dice.rollDice();
			System.out.printf("> Fire away! %s is staggered backwards by %d tiles\n", opponent.getPlayerName(), diceValue);
			opponent.moveBackwards(diceValue);
			break;
		
		case 3:
			
			System.out.printf("Oh no! %s's boat is stuck in a vortex!\n", player.getPlayerName());
			System.out.printf("> %s will miss out on the next turn...\n", player.getPlayerName());
			player.setStuck(true);
			break;
		
		case 4:
			
			System.out.printf("A menacing thunder storm is brewing...\n");
			System.out.printf("> Lightning strikes both boats at once! %s and %s switch positions!\n\n", player.getPlayerName(), opponent.getPlayerName());
			
			
			int initPlayPos = player.getPosition();
			int initOppPos = opponent.getPosition();
			
			player.setPosition(initOppPos);
			opponent.setPosition(initPlayPos);
			
			System.out.printf("%s is now at tile: %d\n", player.getPlayerName(), player.getPosition());
			System.out.printf("%s is now at tile: %d\n", opponent.getPlayerName(), opponent.getPosition());
			break;
		
		}
	}
	
	@Override
	public String toString() {
		return String.format("*");
	}
}
