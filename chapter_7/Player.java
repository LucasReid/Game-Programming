package chapter_7;

import java.io.Serializable;

public class Player implements Comparable<Player>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private int score;
	
	public Player(){
		score = 0;
		name = "Lizard King";
	}
	public Player(String name){
		this.name = name;
	}
	public Player(String name,int score){
		this.name = name;
		this.score = score;
	}
	
	public void setScore(int score){
		this.score = score;
	}
	
	public void addScore(int score){
		this.score += score;
	}
	public void incrementScore(){
		score++;
	}
	public int getScore(){
		return score;
	}
	
	public String getName(){
		return name;
	}
	
	@Override
	public int compareTo(Player player) {
		if(score== player.score)
			return 0;
		else if(score> player.score)
			return 1;
		else
			return -1;
	}
	@Override
	public String toString(){
		return name+ ": " +score ;
	}
}














