//import statements
import java.awt.EventQueue;

import javax.swing.JFrame;


import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;


import javax.swing.JTable;
import javax.swing.border.BevelBorder;

import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;

//GUI Class
public class PuzzleGUI {

	private JFrame frmAiPuzzle;
	private final JButton btnRandomize = new JButton("Randomize");
	private static JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PuzzleGUI window = new PuzzleGUI();
					window.frmAiPuzzle.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
	}

	/**
	 * Create the application.
	 */
	public PuzzleGUI() {
		
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAiPuzzle = new JFrame();
		frmAiPuzzle.setTitle("A.I. Puzzle");
		frmAiPuzzle.setBounds(100, 100, 600, 400);
		frmAiPuzzle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAiPuzzle.getContentPane().setLayout(null);
		btnRandomize.setBounds(477, 314, 107, 23);
		frmAiPuzzle.getContentPane().add(btnRandomize);
		PuzzleNodes Puzzle = new PuzzleNodes();
		
		
		//gives the randomizer button a function
		btnRandomize.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){ 
				String input = JOptionPane.showInputDialog("Random Moves:","");
				if(input != null){
					int totalMoves = Integer.parseInt(input);
					//PuzzleSolver.setMaxDepth(totalMoves);
					PuzzleOperations.randomizer(totalMoves,Puzzle.currentState);
				}
				
				
				
			}
		});
		
		//button that moves the entire puzzle 1 space int the counter clockwise direction
		JButton button = new JButton("<<Left");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PuzzleOperations.moveLeft(PuzzleOperations.getPuzzle());
				//updateBoard(PuzzleOperations.getPuzzle());
			}
		});
		button.setBounds(0, 338, 89, 23);
		frmAiPuzzle.getContentPane().add(button);
		
		//button that performs the same function as rotating the wheel 180 degrees on the real puzzle
		JButton btnRotate = new JButton("Rotate");
		btnRotate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PuzzleOperations.rotate(Puzzle.currentState);
				//updateBoard(PuzzleOperations.getPuzzle());
			}
		});
		btnRotate.setBounds(86, 338, 89, 23);
		frmAiPuzzle.getContentPane().add(btnRotate);
		
		//button to shift the entire puzzle clockwise 1 space
		JButton btnClockwise = new JButton("Right>>");
		btnClockwise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PuzzleOperations.moveRight(Puzzle.currentState);
				//updateBoard(PuzzleOperations.getPuzzle());
			}
		});
		btnClockwise.setBounds(172, 338, 89, 23);
		frmAiPuzzle.getContentPane().add(btnClockwise);
		
		//currently does nothing at all
		JButton btnNewButton = new JButton("Solve");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PuzzleSolver.idaSolve(Puzzle);
			}
		});
		btnNewButton.setBounds(477, 338, 107, 23);
		frmAiPuzzle.getContentPane().add(btnNewButton);
		
		//the table that represents the puzzle
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.BOLD, 20));
		table.setModel(new DefaultTableModel(PuzzleOperations.getPuzzle(),
			new String[] {
				"Position6", "Position7", "Position8", "Position1", "Position2", "Position3", "Position4", "Position5"
			}
		));
		
		//table formatting
		table.setRowHeight(73);
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(0).setMinWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setMinWidth(70);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.getColumnModel().getColumn(2).setMinWidth(70);
		table.getColumnModel().getColumn(3).setPreferredWidth(70);
		table.getColumnModel().getColumn(3).setMinWidth(70);
		table.getColumnModel().getColumn(4).setPreferredWidth(70);
		table.getColumnModel().getColumn(4).setMinWidth(70);
		table.getColumnModel().getColumn(5).setPreferredWidth(70);
		table.getColumnModel().getColumn(5).setMinWidth(70);
		table.getColumnModel().getColumn(6).setPreferredWidth(70);
		table.getColumnModel().getColumn(6).setMinWidth(70);
		table.getColumnModel().getColumn(7).setPreferredWidth(70);
		table.getColumnModel().getColumn(7).setMinWidth(70);
		table.setBackground(SystemColor.inactiveCaptionBorder);
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setBounds(10, 11, 564, 292);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		for(int i=0;i<8;i++){
	         table.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
	    }
		
		
		
		frmAiPuzzle.getContentPane().add(table);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			PuzzleOperations.reset(Puzzle.currentState);
				
			}
		});
		btnReset.setBounds(287, 338, 89, 23);
		frmAiPuzzle.getContentPane().add(btnReset);
		
		JButton btnSimulate = new JButton("Simulate");
		btnSimulate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			PuzzleSolver.simulate2();	
			}
		});
		btnSimulate.setBounds(378, 338, 89, 23);
		frmAiPuzzle.getContentPane().add(btnSimulate);
	}
	
	//updates the puzzle to its current setup
	public static void updateBoard(String display[][]){
		table.setModel(new DefaultTableModel(display,
				new String[] {
					"Position6", "Position7", "Position8", "Position1", "Position2", "Position3", "Position4", "Position5"
				}
			));
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		for(int i=0;i<8;i++){
	         table.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
	    }
		
	}
}




