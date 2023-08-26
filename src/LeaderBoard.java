import java.io.*;
import java.util.*;

public class LeaderBoard {
	
	Scanner winput;
	Formatter woutput;
	private ArrayList<Winner> leaderboard = new ArrayList<Winner>();

	public void loadLB()
	{
		winput = openInputFile("LeaderBoard.txt");
		readScores();
		
	}
	
	public void storeLB()
	{
		woutput = openOutputFile("LeaderBoard.txt");
		writeScores();
		closeOutputFile(woutput);
	}
	
	public Scanner openInputFile(String filename)
	{
		Scanner tempinput=null;
		try
		{
			tempinput = new Scanner(new File(filename));
		}
		catch (FileNotFoundException fileNotFoundException)
		{
			System.err.println("Error opening file.");
			System.exit(1);
		}
		return tempinput;
	}


	public Formatter openOutputFile(String filename)
	{
		Formatter tempoutput=null;
		try
		{
			tempoutput = new Formatter(new File(filename));
		}
		catch (FileNotFoundException fileNotFoundException)
		{
			System.err.println("Error opening file.");
			System.exit(1);
		}
		return tempoutput;
	}
	
	public void writeScores() {
		
		try {
			int LBsize = 0;
			if (leaderboard.size() <= 5) {
				LBsize = leaderboard.size();
			} else {
				LBsize = 5;
			}
			
			for ( int i = 0; i < LBsize; i++ ) {
				woutput.format("%d %s \n", leaderboard.get(i).getScore(), leaderboard.get(i).getName());
			}
			System.out.println();
		}
		catch (Exception e) { 
			System.out.println("An error occured while writing to the LeaderBoard file");
			e.printStackTrace();
		}
		
	}
	
	public void checkLB(Winner currentP) {
		createLBFile();
		loadLB();
		readScores();
		
		int pScore = currentP.getScore();
		
		if (leaderboard.size() == 0) {
			leaderboard.add(currentP);
		} else {
			for (int i = 0; i < leaderboard.size(); i++) {
				
				// Checks if score is less/better than the Winner in the leaderboard
				if (pScore <= (leaderboard.get(i).getScore())) {
					leaderboard.add(i, currentP);
					
					if (leaderboard.size() > 5) {
						leaderboard.remove(leaderboard.size()-1);
					}

					System.out.printf("\n> %s has entered the Top 5!", currentP.getName());
					
					break;
				} 
				
				// Adds Winner regardless of score if leaderboard has less than 5 objects
				if (leaderboard.size() < 5) {
					
					// Checks if score is larger than last Winner on the list
					if (pScore >= leaderboard.get(i).getScore() && leaderboard.size() == i + 1) {
						leaderboard.add(currentP);
						break;
					} 
					
					// Checks if score is between two Winner objects
					if (pScore > leaderboard.get(i).getScore() && pScore <= leaderboard.get(i + 1).getScore()) {
						leaderboard.add(i + 1, currentP);
						break;
					}
					
				} 
			}

		}
		
		closeInputFile(winput);
		storeLB();
	}

	public void readScores(){
		
		try {
			while (winput.hasNext()) {
				Winner winners = new Winner();
				winners.setScore(winput.nextInt());
				winners.setName(winput.next());
				
				leaderboard.add(winners); // append the list into the board as array	
			}
		}
		catch (Exception e) {
			System.out.println("Error has occured while reading from the LeaderBoard file");
			winput.close();
			e.printStackTrace();
		}
	}
	
	public void displayLeaderboard()
	{
		loadLB();
		System.out.println("        *Top 5 Highscores*");
		System.out.println("================================");
		System.out.printf("%-3s %-22s %-10s\n","", "Name", "Score");
		System.out.println("================================");
		for (int i = 0; i < leaderboard.size(); i++)
		{
			if (i < leaderboard.size()) {
				System.out.printf("%-3d %-22s %-10d\n", (i + 1), leaderboard.get(i).getName(), leaderboard.get(i).getScore());
			}
		}
		System.out.println("================================");
		System.out.println();
	}
	
	public void closeInputFile(Scanner input)
	{
		if (input!=null)
			input.close();
	}

	public void closeOutputFile(Formatter output)
	{
		if (output!=null)
			output.close();
	}
	
	public void createLBFile() {
		
		 try {
		      File myObj = new File("LeaderBoard.txt");
		      
		      myObj.createNewFile();
		    } catch (IOException e) {
		      System.out.println("Error creating Leaderboard file.");
		      e.printStackTrace();
		    }
		
	}
	
}
