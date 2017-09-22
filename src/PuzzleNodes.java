import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class PuzzleNodes {
	
	//state of a solved puzzle
	//should always remain the same
	public String solved[][] = new String[][] {
			{"18", "19", "20", "1", "2", "3", "4", "5"},
			{"17", null, null, null, null, null, null, "6"},
			{"16", null, null, null, null, null, null, "7"},
			{"15", "14", "13", "12", "11", "10", "9", "8"},
		};
	
	//current state of the puzzle
	//this is the array that is updated throughout the process
	public String[][] currentState = new String[][] {
			{"18", "19", "20", "1", "2", "3", "4", "5"},
			{"17", null, null, null, null, null, null, "6"},
			{"16", null, null, null, null, null, null, "7"},
			{"15", "14", "13", "12", "11", "10", "9", "8"},
	};
	
	//private static int nodeDepth = 0;
	
	//private int hValue = 0;
	
	//private int gValue = 0;
	
	//h+g
	//private int fValue = 0;
	
	
	//list of the possible successors of the current configuration
	public ArrayList<PuzzleNodes> successors = new ArrayList<PuzzleNodes>();
	//public Queue<PuzzleNodes> qSucc = new LinkedList<PuzzleNodes>();
	
	/*
	public void setNodeDepth(int depth){
		nodeDepth = depth;
	}
	
	public void sethValue(int heuristic){
		hValue = heuristic;
	}
	
	public void gValue(int pathCost){
		gValue = pathCost;
	}
	
	public int getNodeDepth(){
		return nodeDepth;
	}
	
	public int gethValue(){
		return hValue;
	}
	
	public int getgValue(){
		gValue = nodeDepth;
		return gValue;
	}
	
	public void setCurrentState(String[][] state){
		currentState = state;
	}
	
	public String[][] getCurrentState(){
		return currentState;
	}
	
	public int getfValue(){
		return fValue;
	}
	
	public void setfValue(int newF){
		fValue = newF;
	}
	*/





}
