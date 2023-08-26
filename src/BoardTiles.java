
public class BoardTiles {
	
	private int position;
	private String type;
	private static BoardTiles[] totalTiles = new BoardTiles[100];
	
	public BoardTiles() {
		
	}
	
	public BoardTiles(int pos) {
		setPosition(pos);
		setType("Normal");
	}
	
	public int getPosition() {
		return position;
	}
	
	public void setPosition(int p) {
		if (p >= 0 && p <= 99) {
			position = p;
		} else {
			throw new IllegalArgumentException("Tile position must be between 0 and 99");
		}
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String t) {
		type = t;
	}
	
	public static BoardTiles[] getTotalTiles() {
		return totalTiles;
	}
	
	public void createTiles() {
		
		
		for (int i = 0; i < 100; i++) {
			totalTiles[i] = new BoardTiles(i);
		}
		
		BoardTiles[] specialTiles = {new Current(), new Trap(), new Chance()};
		for (BoardTiles t: specialTiles) {
			t.createTiles();
		}
		
	}
	
	public void trigger(Boat player, Boat opponent) {

	}
	
	public String toString() {
		return String.format(" ");
	}
	
}
