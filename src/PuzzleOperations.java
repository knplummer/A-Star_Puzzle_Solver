import java.util.ArrayList;
import java.util.Random;


public class PuzzleOperations {
	
	private static String[][] solvedPuzzle = new String[][] {
			{"18", "19", "20", "1", "2", "3", "4", "5"},
			{"17", null, null, null, null, null, null, "6"},
			{"16", null, null, null, null, null, null, "7"},
			{"15", "14", "13", "12", "11", "10", "9", "8"},
		};
	
	public void setPuzzle(String [][] update){
		solvedPuzzle = update;
	}
	
	public static String[][] getPuzzle(){
		return solvedPuzzle;
	}
	
	public static String[][] randomizer(int totalMoves, String startTable[][]){
		
		Random randMove = new Random();
		int move = 0;
		int rightCount = 0;
		int leftCount = 0;
		//a list of all previous moves. Checks this to make sure no moves are being undone
		ArrayList<String> previousMoves = new ArrayList<String>();
		
		//performs the number of random adjustments the user chose
		for(int i = 0; i < totalMoves-1;i++){
			
			//first move of the randomization cannot undo anything
			move = randMove.nextInt(3);
			//System.out.println(move);
			if(move == 0 && previousMoves.size()==0){
				//System.out.println("Rotate as first opperation");
				rotate(startTable);
				previousMoves.add("Rotate");
			}
			if(move == 1 && previousMoves.size()==0){
				//System.out.println("Left as first opperation");
				moveLeft(startTable);
				leftCount++;
				previousMoves.add("Left");
			}
			if(move == 2 && previousMoves.size()==0){
				//System.out.println("Right as first opperation");
				moveRight(startTable);
				rightCount++;
				previousMoves.add("Right");
			}
			
			//doesn't allow moves to be undone, keeps retrying the same turn until it does not undo a move
			if(move == 0 && previousMoves.size()>0){
				if(previousMoves.get(previousMoves.size()-1)=="Rotate"){
					//System.out.println("Back to rotates, nothing done");
					i--;
				}
				else{
					//System.out.println("Rotated");
					rotate(startTable);
					leftCount = 0;
					rightCount = 0;
					previousMoves.add("Rotate");
				}
			}
			
			if(move == 1 && previousMoves.size()>0){
				if(previousMoves.get(previousMoves.size()-1)=="Right"){
					//System.out.println("Undoes Left shift, nothing done");
					i--;
				}
				else if(leftCount!=20){
					//System.out.println("Moved left");
					moveLeft(startTable);
					leftCount++;
					rightCount = 0;
					previousMoves.add("Left");

				}
				else{
					i--;
				}
			}
			
			if(move == 2 && previousMoves.size()>0){
				if(previousMoves.get(previousMoves.size()-1)=="Left"){
					//System.out.println("Undoes Right shift, nothing done");
					i--;
				}
				else if(rightCount != 20){
					//System.out.println("Moved Right");
					moveRight(startTable);
					rightCount++;
					leftCount = 0;
					previousMoves.add("Right");
				}
				else{
					i--;
				}
			}
			
			//System.out.println("moved");
			//gui.updateBoard(startTable);
			
			/*
			try {
			    Thread.sleep(1000);                 //1000 milliseconds is one second.
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			*/
			
		}
		/*
		//prints the moves performed by the randomizer
		for(int i = 0; i<previousMoves.size();i++){
			System.out.print(previousMoves.get(i)+" ");
		}
		*/
		//System.out.println();
		return startTable;
	}
	
	//swaps the 2 numbers that would be swapped by rotating the wheel on the real puzzle
	public static String [][] rotate(String board[][]){
		String temp1 = "";
		temp1 = board[0][2];
		board[0][2] = board[0][5];
		board[0][5]=temp1;
		
		String temp2 = "";
		temp2 = board [0][4];
		board[0][4] = board[0][3];
		board[0][3] = temp2;
		//System.out.println("Rotate");
		PuzzleGUI.updateBoard(board);
		return board;
	}
	
	//shifts the moves the entire puzzle one space in the clounterclockwise direction
	public static String [][] moveLeft(String board[][]){
		//System.out.println("Left");
		
		String temp1 = "";
		
		//top row moves
		String topLeftCorner = board[0][0];
		for(int i = 0; i < 7; i++){
			board[0][i] = board[0][i+1];
			temp1 = board[0][i+1];
		}
		board[0][7]=board[1][7];
		
		//bottom row moves
		String bottomRightCorner = board[3][7];
		for(int i = 7; i > 0; i--){
			board[3][i] = board[3][i-1];
			temp1 = board[0][i-1];
		}
		board[3][0]=board[2][0];
		
		//right side moves
		board[2][0]=board[1][0];
		board[1][0]=topLeftCorner;
		
		//left side moves
		board[1][7]=board[2][7];
		board[2][7]=bottomRightCorner;

		PuzzleGUI.updateBoard(board);
		return board;
	}
	
	//moves entire puzzle in clockwise direction
	public static String [][] moveRight(String board[][]){
		//System.out.println("Right");
		
		String temp = "";
		
		//top row moves
		String topRightCorner = board[0][7];
		for(int i = 7; i > 0; i--){
			board[0][i] = board[0][i-1];
			temp = board[0][i-1];
		}
		board[0][0]=board[1][0];
		
		//bottom row moves
		String bottomLeftCorner = board[3][0];
		for(int i = 0; i < 7; i++){
			board[3][i] = board[3][i+1];
			temp = board[0][i+1];
		}
		board[3][7]=board[2][7];
		
		//right side moves
		board[1][0]=board[2][0];
		board[2][0]=bottomLeftCorner;
		
		//left side moves
		board[2][7]=board[1][7];
		board[1][7]=topRightCorner;
		
		PuzzleGUI.updateBoard(board);
		return board;
	}
	
	public static String[][] reset(String[][] board){
		String[][] reset = new String[][] {
				{"18", "19", "20", "1", "2", "3", "4", "5"},
				{"17", null, null, null, null, null, null, "6"},
				{"16", null, null, null, null, null, null, "7"},
				{"15", "14", "13", "12", "11", "10", "9", "8"},
			};
		
		board = reset;
		PuzzleGUI.updateBoard(board);
		return board;
	}
	

}
