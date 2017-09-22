import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class PuzzleSolver {
	
	//private static int maxDepth = 1;
	
	private static String[][] startState = new String[4][8];
	
	private static int nodesExpanded = 0;
	
	/*
	public static void setMaxDepth(int randMoves){
		maxDepth = 2*randMoves;
	}
	*/
	

	
	//h(x) 
	public static int heuristicCalc(PuzzleNodes node){
		
		String[][] current = new String[4][8];
		String[][] solved = new String[4][8];
		current = node.currentState;
		solved = node.solved;
		int heuristicValue = 0;
		int[] pieceDist = new int[20];
		
		//create deep copy so we dont change our current puzzle config during calculations
		String[][] copy = new String[4][8];
		for(int i = 0;i < 4; i++){
			for(int j = 0; j<8;j++){
				copy[i][j]= current[i][j];
			}
		}
		//System.out.println("Copy For Loop");
		
		//find location of the 1 in the puzzle
		int oneRow = 0;
		int oneCol = 0;
		
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 8; j++){
				if(copy[i][j]=="1"){
					oneRow = i;
					oneCol = j;
				}
			}
		}
		//System.out.println("One is at: ("+oneRow+","+oneCol+")");
		
		//find distance of each piece from the 1
		
		for(int p = 1; p <=20; p++){
			//finds the location of each piece on the puzzle and sets it as an x and y coordinate
			//System.out.println(p);
			int x = 0, y = 0;
			for(int r = 0; r < 4; r++){
				for(int c = 0; c < 8; c++){
					//System.out.println(copy[r][c]);
					if(copy[r][c]==null){
						c=7;
					}
					if(Integer.parseInt(copy[r][c])==p){
						y = r;
						x = c;
						//System.out.println("Piece "+p+" is at: ("+y+","+x+")");
					}
				}
			}
			
			
			//System.out.println("location of piece For Loop");
			//finds the distance each piece must move around the board to be at 1
			int dist = 0;
			while((y != oneRow) || (x != oneCol) ){
				//PuzzleOperations.moveLeft(current);
				dist++;
				//shift puzzle counterclockwise
				//shift on top row
				if(x > 0 && y == 0){
					x--;
				}
				//shift on bottom row
				else if(x < 7 && y == 3){
					x++;
				}
				//left side shift
				else if(x == 0 && y < 4){
					y++;
				}
				
				//right side shift
				else if(x == 7 && y > 0){
					y--;
				}
				
				pieceDist[p-1] = dist;
			}
			pieceDist[p-1] = Math.abs((p-1)-pieceDist[p-1]);
		}
		
		for(int i = 0; i < 20; i++){
			heuristicValue = heuristicValue+pieceDist[i];
			//System.out.print(pieceDist[i]+ " ");
		}
		//System.out.println();
		//System.out.println("no mod h = "+ heuristicValue);
		
		heuristicValue+=19;
		heuristicValue = (int) Math.floor(heuristicValue/20);
		
		//System.out.println("h = "+ heuristicValue);
		//node.sethValue(heuristicValue);
		
		return heuristicValue;
	}
	
	
	public static boolean isGoal(PuzzleNodes node){
		
		for(int i  = 0; i < 4; i++){
			for(int j = 0; j < 8; j++){
				if(node.currentState[i][j] != node.solved[i][j]){
					return false;
				}
			}
		}
		return true;
	}

	
	/*
	//looks at each of the possible successors of the current node
	//and returns the one with the lowest heuristic value
	public static PuzzleNodes nextSuccessor(PuzzleNodes currentNode){
		
		nodesExpanded++;
		PuzzleNodes left = new PuzzleNodes();
		PuzzleNodes right = new PuzzleNodes();
		PuzzleNodes rot = new PuzzleNodes();
		int minH = 0;
		
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 8; j++){
				left.currentState[i][j] = currentNode.currentState[i][j];
				right.currentState[i][j] = currentNode.currentState[i][j];
				rot.currentState[i][j] = currentNode.currentState[i][j];
			}
		}
		
		left.setCurrentState(PuzzleOperations.moveLeft(left.currentState));
		right.setCurrentState(PuzzleOperations.moveRight(right.currentState));
		rot.setCurrentState(PuzzleOperations.rotate(rot.currentState));
		
		left.setNodeDepth(currentNode.getNodeDepth()+1);
		right.setNodeDepth(currentNode.getNodeDepth()+1);
		rot.setNodeDepth(currentNode.getNodeDepth()+1);
		
		left.sethValue(heuristicCalc(left));
		System.out.println("left h:"+ left.gethValue());
		right.sethValue(heuristicCalc(right));
		System.out.println("right h:"+ rot.gethValue());
		rot.sethValue(heuristicCalc(rot));
		System.out.println("rot h:"+ rot.gethValue());
		
		minH = Math.min(left.gethValue(), Math.min(right.gethValue(), rot.gethValue()));
		
		if(minH == left.gethValue()){
			System.out.println("Left");
			return left;
		}
		else if(minH == right.gethValue()){
			System.out.println("Right");
			return right;
		}
		else{
			System.out.println("Rotate");
			return rot;
		}
		
	}
	*/
	
	public static void idaSuccessors(PuzzleNodes node){
	
		nodesExpanded++;
		//System.out.println("Nodes Expanded " + nodesExpanded);
		PuzzleNodes left = new PuzzleNodes();
		PuzzleNodes right = new PuzzleNodes();
		PuzzleNodes rot = new PuzzleNodes();
		
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 8; j++){
				left.currentState[i][j] = node.currentState[i][j];
				right.currentState[i][j] = node.currentState[i][j];
				rot.currentState[i][j] = node.currentState[i][j];
			}
		}
		
		PuzzleOperations.moveLeft(left.currentState);
		PuzzleOperations.moveRight(right.currentState);
		PuzzleOperations.rotate(rot.currentState);
		
		//left.setNodeDepth(node.getNodeDepth()+1);
		//right.setNodeDepth(node.getNodeDepth()+1);
		//rot.setNodeDepth(node.getNodeDepth()+1);
		
		node.successors.add(left);
		node.successors.add(right);
		node.successors.add(rot);
	}
	
	/*
	public static PuzzleNodes smaSolve(PuzzleNodes Puzzle){
		//PuzzleNodes startNode = new PuzzleNodes();
		//startNode.setCurrentState(startState);
		
		Queue<PuzzleNodes> queue = new LinkedList<PuzzleNodes>();
		queue.add(Puzzle);
		PuzzleNodes node = new PuzzleNodes();
		//int nodeExpanded = 0;
		
		while(true){
			if(queue.isEmpty()){
				System.out.println("Failed");
				break;
			}
			
			node = queue.peek();
			
			if(heuristicCalc(node)==0){
				System.out.println("Solved!");
				System.out.println(nodesExpanded);
				return Puzzle;
			}
			//System.out.print("Past first hcalc");
			PuzzleNodes successor = new PuzzleNodes();
			
			successor = nextSuccessor(node);
			
			//System.out.println(nodeExpanded);
			node.addSuccessor(successor);
			
			
			if (heuristicCalc(successor)!=0 && successor.getNodeDepth()==maxDepth){
				successor.setfValue((int) Double.POSITIVE_INFINITY);
			}
			
			else{
				successor.setfValue(Math.max(node.getfValue(), (successor.gethValue()+successor.getgValue())));
			}
			
			//check to see if all node children are already 
			//added by shorter path
			if(queue.containsAll(node.qSucc)){
				queue.remove(node);
			}
			
			
			//add successor to the queue
			queue.add(successor);
			
		}
		//will never return here
		//puzzle should be solved with a queue of children that represent the steps taken to solve
		return Puzzle;
	}
	*/
	
	//use itterative deepening a* search
	public static void idaSolve(PuzzleNodes Puzzle){
		//System.out.println("IDA* Solve");
		int bound = heuristicCalc(Puzzle);
		int t = 0;
		while(true){
			t = idaSearch(Puzzle, 0 ,bound);
			if(t==-1){
				System.out.println("Solved!");
				break;
			}
			if(t == (int)Double.POSITIVE_INFINITY){
				System.out.println("Not Found");
				break;
			}
			bound = t;
			
		}
		//System.out.println("Nodes Expanded: " + nodesExpanded);
	}
	
	//search function for ida*
	public static int idaSearch(PuzzleNodes node, int g, int bound){
		PuzzleGUI.updateBoard(node.currentState);

		int h = heuristicCalc(node);
		int f = g+h;
		if(f > bound){
			return f;
		}
		if(isGoal(node)){
			return -1;
		}
		int min  = (int)Double.POSITIVE_INFINITY;
		int t = 0;
		idaSuccessors(node);
		for(int i = 0; i < node.successors.size();i++){
			t = idaSearch(node.successors.get(i),g+1,bound);
			if(t==-1){
				return -1;
			}
			if(t < min){
				min = t;
			}
		}
		return min;
	}
	
	//function that creates 5 k random puzzles where 10 <= k <= 100 
	//then attempts to solve them and prints the results
	public static void simulate(){
		
		for(int i = 10; i<=100; i++){
			System.out.println();
			System.out.println("*****Random value "+ i+"*****");
			for(int j = 1; j <= 5; j++){
				System.out.println();
				System.out.println("Puzzle "+ j);
				PuzzleNodes sim = new PuzzleNodes();
				PuzzleOperations.randomizer(i, sim.currentState);
				idaSolve(sim);
				System.out.println("Nodes Expanded: "+ nodesExpanded);
				PuzzleOperations.reset(sim.currentState);
				nodesExpanded = 0;
			}
		}
	}
	
	
	public static void simulate2(){
		
		for(int i = 1; i<=9; i++){
			System.out.println();
			System.out.println("*****Random value "+ i+"*****");
			for(int j = 1; j <= 5; j++){
				System.out.println();
				System.out.println("Puzzle "+ j);
				PuzzleNodes sim = new PuzzleNodes();
				PuzzleOperations.randomizer(i, sim.currentState);
				idaSolve(sim);
				System.out.println("Nodes Expanded: "+ nodesExpanded);
				PuzzleOperations.reset(sim.currentState);
				nodesExpanded = 0;
			}
		}
	}
	

}
