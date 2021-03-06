package chapter_7;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Random;

public class ScoreBoard {
	private static final String DATAFILE = "scores.dat";
	private ArrayList<Player> list = new ArrayList();

	public void backup() {
		try (OutputStream file = new FileOutputStream(DATAFILE);
				OutputStream buffer = new BufferedOutputStream(file);
				ObjectOutput output = new ObjectOutputStream(buffer);) {
			output.writeObject(list);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void restore() {
		// Assume that its the backup method that creates the file
		if (!new File(DATAFILE).exists())
			return;
		try (InputStream file = new FileInputStream(DATAFILE);
				InputStream buffer = new BufferedInputStream(file);
				ObjectInput input = new ObjectInputStream(buffer);) {
			list = (ArrayList<Player>) input.readObject();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}

	}

	public void addPlayer(Player player) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).compareTo(player) >= 0) {
				list.add(i, player);
				return;
			}
		}
		list.add(player);

	}
	public String getPlayer( int index){
		Player player1 = list.get(index);
		return player1.toString();
	}
//	
	public int size(){
		int size = list.size();
		return size;
	}
	
	public void trim(int count){
		if(count> list.size())
			return;
		
		for(int i = count; i>0; i--)
			list.remove(list.size()-i);
		
		list.trimToSize();
		
	}
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		
		for(Player p : list){
			builder.append(String.format("%9s    %03d%n"+"\n", p.getName(), p.getScore()));
		}
		return builder.toString();
	}
	


}









