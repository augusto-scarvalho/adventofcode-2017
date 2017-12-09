import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;

public class registers {
	
	private String path = System.getProperty("user.dir") + File.separator + "day8.txt";
	private File file = new File(path);
	private HashMap<String, Integer> registers = new HashMap<>();
	
	private void readInstructions(){
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String[] instructions;
			String line;
			int max = 0;
		    while((line = br.readLine()) != null){
		    	instructions = line.split(" ");
		    	registers.putIfAbsent(instructions[0], 0);
		    	registers.putIfAbsent(instructions[4], 0);
		    	if(instructions[1].equals("inc"))
		    		registers.put(instructions[0], registers.get(instructions[0])+execInstruction(
		    				instructions[2], instructions[4], instructions[5], instructions[6]));
		    	else
		    		registers.put(instructions[0], registers.get(instructions[0])-execInstruction(
		    				instructions[2], instructions[4], instructions[5], instructions[6]));
		    	if(max < registers.get(instructions[0]))
		    		max = registers.get(instructions[0]); 
		    }
		    
		    br.close();
		    System.out.println("Part 1 - " + Collections.max(registers.values()) +
					"\nPart 2 - " + max);
		}
		catch (FileNotFoundException e) {e.printStackTrace();}
		catch (IOException h) {h.printStackTrace();}
	}
	
	private int execInstruction(String value, String condVar, String cond, String condValue){
		switch(cond){
		case "==":
			if(registers.get(condVar) == Integer.parseInt(condValue))
				return Integer.parseInt(value);
			break;
		case "!=":
			if(registers.get(condVar) != Integer.parseInt(condValue))
				return Integer.parseInt(value);
			break;
		case ">":
			if(registers.get(condVar) > Integer.parseInt(condValue))
				return Integer.parseInt(value);
			break;
		case "<":
			if(registers.get(condVar) < Integer.parseInt(condValue))
				return Integer.parseInt(value);
			break;
		case ">=":
			if(registers.get(condVar) >= Integer.parseInt(condValue))
				return Integer.parseInt(value);
			break;
		case "<=":
			if(registers.get(condVar) <= Integer.parseInt(condValue))
				return Integer.parseInt(value);
			break;
		}
		return 0;
	}
	
	registers(){
		readInstructions();
	}

	public static void main (String[] args){
		new registers();
	}
	
}
