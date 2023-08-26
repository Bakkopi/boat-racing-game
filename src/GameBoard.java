import java.util.InputMismatchException;
import java.util.Scanner;

public class GameBoard {
	
	Scanner input = new Scanner(System.in);
	
	private BoardTiles[] gameBoard;
	private Boat player1;
	private Boat player2;
	private Winner winningP;
	private Dice dice = new Dice();
	
	public void drawTitle() {
		
		System.out.println();
		System.out.println("              |    |    |");
		System.out.println("             )_)  )_)  )_)");
		System.out.println("            )___))___))___)\\      [ BOAT RACING GAME ]");
		System.out.println("           )____)____)_____)\\\\");
		System.out.println("         _____|____|____|____\\\\\\___");
		System.out.println("---------\\\\                   /------------------------");
		System.out.println("  ^^^^^ ^^^^^^^^^^^^^^^^^^^^^ ^^ ^^^^^^  ^^^^^^^^^^^");
		System.out.println("    ^^^^      ^^^^     ^^^    ^^^^^^    ^^^^^   ^^");
		System.out.println();
		
	}
	
	public void mainMenu() {
		int menuSelect = 0;
		
		drawTitle();
		System.out.println("1. Start Game");
		System.out.println("2. Instructions");
		System.out.println("3. Show Leaderboard");
		System.out.println("4. Exit");
		
		try {
			while (!(menuSelect >= 1 && menuSelect <= 4)) {
				System.out.println();
				System.out.print("Enter selection: ");
				menuSelect = input.nextInt();
				if (!(menuSelect >= 1 && menuSelect <= 4)) {
					System.out.println("Please enter a valid selection (1-4)");
				}
			} 
		} catch (InputMismatchException e) {
			System.out.println("Error: Only numerical values accepted!");
		}

		
		System.out.println();
		
		switch (menuSelect) {
			case 1:
				startGame();
				break;
				
			case 2:
				printInstructions();
				mainMenu();
				break;
				
			case 3:
				LeaderBoard LB = new LeaderBoard();
				LB.displayLeaderboard();
				mainMenu();
				break;
				
			case 4:
				System.out.println("See ya!");
				System.exit(menuSelect);
				break;
		}
	}
	
	
	public void startGame() {
		
			
		BoardTiles boardTiles = new BoardTiles();
		boardTiles.createTiles();
		gameBoard = BoardTiles.getTotalTiles();
		
		String p1Name = null;
		String p2Name = null;

		System.out.print("Enter Player 1 name: ");
		p1Name = input.next();
		
		input.nextLine();
		
		// Ensures Player 2 name is different from Player 1
		p2Name = p1Name;
		while (p1Name.equals(p2Name)) {
			System.out.print("Enter Player 2 name: ");
			p2Name = input.next();
			input.nextLine();
			
			if (p1Name.equals(p2Name)) {
				System.out.println("> Please enter a different name from Player 1\n");
			}
		}

		
		player1 = new Boat(p1Name);
		player2 = new Boat(p2Name);
		
		Boat[] playerArray = {player1, player2};
		
		int turnsTaken = 0;
		boolean gameContinue = true;
		int currentP = 0;
		
		System.out.println("Let the race begin!  \\('o '  )/ \n");
		
		// Round loop
		while (gameContinue == true) {
			
			turnsTaken++;
			
			// Calculate round number
			int roundNum = 0;
			if (turnsTaken % 2 == 1) {
				roundNum = (turnsTaken / 2) + 1;
			} else if (turnsTaken % 2 == 0) {
				roundNum = turnsTaken / 2;
			}
			
			// Sets opponent index
			int opponentP;
			if (currentP == 0) {
				opponentP = 1;
			} else {
				opponentP = 0;
			}
			
			System.out.printf("ROUND %d\n", roundNum);
						
			// Check for stuck boat
			if (playerArray[currentP].isStuck() && playerArray[currentP].getStuckTurns() == 0) {
				System.out.printf("%s is stuck in the vortex and misses this round...\n\n", playerArray[currentP].getPlayerName());
				playerArray[currentP].setStuckTurns(1);
				
				//Switch player for next turn
				if (currentP == 0) {
					currentP = 1;
				} else {
					currentP = 0;
				}
				continue;
			} else if (playerArray[currentP].isStuck() && playerArray[currentP].getStuckTurns() == 1) {
				playerArray[currentP].setStuckTurns(0);
				playerArray[currentP].setStuck(false);
				System.out.printf("%s's boat is finally out of the vortex!\n", playerArray[currentP].getPlayerName());
			}
			
			System.out.printf("Player %d's (%s) Turn\n", (currentP + 1), playerArray[currentP].getPlayerName());
			System.out.printf("Press [Enter] to roll the dice\n");
			input.nextLine();
			
			int diceRoll = dice.rollDice();
			System.out.printf("Player %d has rolled a %d\n", (currentP + 1), diceRoll);
							
			playerArray[currentP].moveForward(diceRoll);
			int newPosition =  playerArray[currentP].getPosition();
					
			
			if (newPosition == 100) {
				victoryMsg(playerArray[currentP], roundNum);
				gameContinue = false;
			} else {
				// If boat lands on special tile, trigger its unique action.
				if ((!gameBoard[newPosition].getType().equals("Normal"))) {
					gameBoard[newPosition].trigger(playerArray[currentP], playerArray[opponentP]);
				}
			}
			
			System.out.printf("\nP%d Position: %d\n", (currentP + 1) ,playerArray[currentP].getPosition());
			showBoard(gameBoard, playerArray[0], playerArray[1]);
			System.out.println();

			// Switch player for next turn
			if (currentP == 0) {
				currentP = 1;
			} else {
				currentP = 0;
			}
			
			System.out.println();
			System.out.println();
		}
				
		mainMenu();
		
	}
	
	public void showBoard(BoardTiles[] board, Boat p1, Boat p2) {
		for (int i = 0; i < board.length; i++) {
			System.out.print("=");
		}
		System.out.println();
		for (int i = 0; i < board.length; i++) {
			if (p1.getPosition() == i) {
				System.out.print("1");
			} else if (p2.getPosition() == i) {
				System.out.print("2");
			} else {
				System.out.print(board[i].toString());
			}
		}
		System.out.println();
		for (int i = 0; i < board.length; i++) {
			System.out.print("=");
		}
	}
	
	public void printInstructions() {
		for (int i = 0; i < 80; i++) {
			System.out.print("=");
		}
		System.out.println();
		System.out.println();
		System.out.println("Welcome to boat racing game!");
		System.out.println();
		System.out.println("- First to reach the finish line wins!");
		System.out.println();
		System.out.println("- There are 3 types of special tiles:");
		System.out.println("   Current [C] - Pushes the boat forward");
		System.out.println("   Trap    [T] - Pushes the boat backwards");
		System.out.println("   Chance  [*] - Activates a random event!");
		System.out.println();
		System.out.println("- Chance events:");
		System.out.println("   Gambit - Guess the value of the dice rolled and move by twice its value.");
		System.out.println("   Cannon - Pushes opponent back by the value of dice rolled.");
		System.out.println("   Vortex - Player is stuck and misses 1 turn.");
		System.out.println("   Lightning - Players are both zapped and switch positions.");
		System.out.println();
		System.out.println("Best of luck!");
		System.out.println();
		for (int i = 0; i < 80; i++) {
			System.out.print("=");
		}
		System.out.println();
		System.out.println();
	}
	
	public void victoryMsg(Boat winner, int rounds) {
		System.out.printf("\n>> Congratulations! %s has won the race in %d rounds!  \\(^ o ^  )/ <<\n", winner.getPlayerName(), rounds);
		winningP = new Winner(winner.getPlayerName(), rounds);
		LeaderBoard LB = new LeaderBoard();
		LB.checkLB(winningP);
	}
}
