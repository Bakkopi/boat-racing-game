
public class Winner {
	private String name;
	private int score;
	
	public Winner() {
		
	}
	public Winner(String name, int score) {
		this.name = name;
		this.score = score;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getScore() {
		return score;
	}
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return "Winner [name=" + name + ", score=" + score + "]";
	}
	
}
