import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class stream_processing {
	
	private String path = System.getProperty("user.dir") + File.separator + "day9.txt";
	private File file = new File(path);
	private String stream;
	
	private void readFile(){
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			stream = reader.readLine();			
			reader.close();
		}
		catch (FileNotFoundException e) {e.printStackTrace();}
		catch (IOException h) {h.printStackTrace();}
	}
	
	public void processStream(){
		char[] current = stream.toCharArray();
		int score = 0;
		int tashCounter = 0;
		int multiplier = 0;
		boolean trash = false;
		for(int cursor = 0; cursor < current.length; cursor++){
			if(current[cursor] == '!'){
				cursor++;
				continue;
			}
			else if(current[cursor] == '<' && !trash){
				trash = true;
				continue;
			}
			else if(current[cursor] == '>' && trash){
				trash = false;
				continue;
			}
			else if(current[cursor] == '{' && !trash)
				multiplier++;
			else if(current[cursor] == '}' && !trash){
				score+=multiplier;
				if(multiplier > 0)
					multiplier--;
			}
			if(trash)){
				System.out.println(current[cursor]);
				tashCounter++;
			}
		}
		System.out.println("Part 1 - " + score +
				"\nPart 2 - " + tashCounter);
	}
	
	private stream_processing(){
		readFile();
		processStream();
	}
	
	public static void main(String[] args){
		new stream_processing();
	}	
}
