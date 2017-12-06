import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MemoryReallocation {
	
	private int[] memory;
	private String[] digest;
	String path = System.getProperty("user.dir") + File.separator + "day6.txt";
	private File file = new File(path);
	
	private void readFile(){
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			String[] temp;
		    line = br.readLine();
		    temp = line.split("\\s+");
		    memory = new int[temp.length];
		    for(int x = 0; x < temp.length; x++)
		    	memory[x] = Integer.parseInt(temp[x]);
		    br.close();
		}
		catch (FileNotFoundException e) {e.printStackTrace();}
		catch (IOException h) {h.printStackTrace();}
	}
	
	private void makeDigest(int index){
		digest[index] = "";
		for(int num: memory)
			digest[index] += num + " ";
		//System.out.println(digest[index] + " digest num " + index); // debug
	}
	
	private boolean arraySearch(int x){
		for(int cont = 0; cont < x; cont++){
			if(digest[x].equals(digest[cont])){
				System.out.println("Part 1 took "+ x +" interactions!");
				System.out.println("Part 2 took " + (x-cont) + " circles!");
				return true;
			}
		}
		return false;
	}
	
	private void reallocation(){
		int max = 0;
		int maxIdx = 0;
		for(int cont = 0; cont < memory.length; cont++){
			if(memory[cont] > max){
				max = memory[cont];
				maxIdx = cont;
			}
		}
		memory[maxIdx] = 0;
		int cont = 1;
		while(max > 0){
			memory[(maxIdx+cont)%memory.length]++;
			cont++;
			max--;
		}					
	}
	
	MemoryReallocation(){
		readFile();
		digest = new String[999999];
		int cont = 0;
		do{
			makeDigest(cont);
			reallocation();			
			cont++;
		}
		while(arraySearch(cont-1) == false);
	}

	public static void main(String[] args){
		new MemoryReallocation();
	}
}