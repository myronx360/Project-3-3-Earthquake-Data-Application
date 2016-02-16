import java.io.*;
import java.util.Scanner;

import edu.uncc.cs.bridgesV2.base.BSTElement;
import edu.uncc.cs.bridgesV2.connect.Bridges;


public class bstDriver33 {
	private static Bridges<String> bridge;
	static BSTE bst = new BSTE<>();
	private static Earthquake data;
	@SuppressWarnings("unchecked")
	public static void main(String args[]) throws Exception{
		bridge = new Bridges<String>(1, "509600458590", "mwill320");
		eQ100();
		commandConsole();
		
	}
	// sets up the console for input to do different functions
	public static void commandConsole() throws Exception{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter: large, reset, range, month, or quit");
		switch(sc.nextLine()) {
			case("large"):
				System.out.println("Quake with largest magnitude highlighted");
				bst.largest(bst.getRoot());connectElements();
				
				commandConsole();break;
			case("reset"):
				System.out.println("Nodes reset to black");
				bst.reset((bst.getRoot()));connectElements();commandConsole();break;
			case("range"):
				System.out.println("Enter lower range then max range");// range 5.2 - 60
				bst.rangeHelp(bst.getRoot(),sc.nextDouble(), sc.nextDouble());connectElements();commandConsole();break;
			case("loc"):
				System.out.println("Enter location name (alaska)");
				bst.findLocation2(bst.getRoot(), sc.next());connectElements();commandConsole();break;
			case("month"):
				System.out.println("Enter the month then year");// month Apr 2014
				bst.findMonthYear(bst.getRoot(), sc.next(), sc.next());connectElements();commandConsole();break;
			case("quit"):
				System.out.println("Goodbye");
				System.exit(0);break;
			default:commandConsole();			
		}
	}
	

	public static void eQ100() throws Exception{
		/************EQ_Tweets_100**************/
		System.out.println("/************EQ_Tweets_100**************/");
		String line;
		// process file
		BufferedReader reader = new BufferedReader(new FileReader(new File("EQ_Tweets_100.txt")));
		line = reader.readLine();
		//loop through file
		while(line != null) {
			String[] earthquakeData = line.split(" ");
			String date = (earthquakeData[3]+" "+earthquakeData[4]+" "+earthquakeData[5]);
			 // create earthquake data node
			data = new Earthquake(earthquakeData[1], earthquakeData[2], date, earthquakeData[6]);
			// create legible label
			String label = data.toString();
			// insert earthquake data node into BST 
			bst.insert2(label, Double.parseDouble(earthquakeData[1]), data);
			line = reader.readLine();
		}
		connectElements();
		System.out.println("Quake with largest magnitude:\n"+bst.largest(bst.getRoot()).getLabel()+
						   "\nNumber of tree nodes: " + bst.getNodecount());
		reader.close();
	}
	

	
	/** connect the TreeElements
	 * @throws Exception */
	public static void connectElements() throws Exception{
		
		bridge.setDataStructure(bst.getRoot(),"tree");
		bridge.visualize();
	}
}
