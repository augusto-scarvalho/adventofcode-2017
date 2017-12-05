import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TwistyTrampolines {
	
	private static int[] originalInstructions;
	String path = System.getProperty("user.dir") + File.separator + "day5.txt";
	private File file = new File(path);
	
	private void getrows(){

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			int cont = 0;
		    while (br.readLine() != null) 
		    	cont++;
		    
		    originalInstructions = new int[cont];
		    br.close();		    
		}
		catch (FileNotFoundException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
	}
	
	private void readInstructions(){
		getrows();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
		    int cont = 0;
		    while ((line = br.readLine()) != null) {
		    	originalInstructions[cont] = Integer.parseInt(line);
		    	cont++;
		    }
		    br.close();
		}
		catch (FileNotFoundException e) {e.printStackTrace();}
		catch (IOException h) {h.printStackTrace();}
	}
	
	private int part1(int state, int interactions, int[] instructions){
		while(state < instructions.length && state >= 0){
			int temp = state;
			state+= instructions[temp];
			instructions[temp]++;
			interactions++;
		}
		return interactions;
	}
	
	private int part2(int state, int interactions, int[] instructions){
		while(state < instructions.length && state >= 0){
			int temp = state;
			state+= instructions[temp];
			
			if(instructions[temp] >= 3)
				instructions[temp]--;
			else 
				instructions[temp]++;
			interactions++;
		}
		return interactions;
	}
	
	TwistyTrampolines(){
		readInstructions();
		System.out.println("Part 1 took "+ part1(0, 0, originalInstructions.clone()) +" to reach the exit");
		System.out.println("Part 2 took "+ part2(0, 0, originalInstructions.clone()) +" to reach the exit");
	}

	public static void main(String[] args){
		new TwistyTrampolines();
	}
}