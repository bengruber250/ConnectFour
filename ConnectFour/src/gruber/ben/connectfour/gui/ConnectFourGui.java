package gruber.ben.connectfour.gui;

import gruber.ben.connectfour.Board;
import gruber.ben.connectfour.ConnectFour;
import gruber.ben.connectfour.ai.AI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ConnectFourGui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4628709270545588638L;
	private JPanel contentPane;
	public JButton[][] buttons;
	public static ConnectFourGui frame;
	private Board board;
	public boolean playerRed;

	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ConnectFourGui(new Board(ConnectFour.COLUMNS, ConnectFour.ROWS));
					frame.setVisible(true);
					frame.setTitle("Connect Four");
					frame.setIconImage(new ImageIcon("Untitled.png").getImage());
					new AI().start();
					//Main Loop
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void updateFromBoard(Board board){
		setTitle("Connect Four" + ((playerRed)?"(Red Move)": "(Blue Move)"));
		for(int i =0;i<ConnectFour.COLUMNS;i++){
			for(int j=0;j<ConnectFour.ROWS;j++){
				buttons[i][j].setText(board.getBoardArray()[i][j]);
				if(board.getBoardArray()[i][j]!=null){
					switch(board.getBoardArray()[i][j]){
					case "R":
						((Square) buttons[i][j]).setColor(new Color(0xFF0000));
						break;
					case "B":
						((Square) buttons[i][j]).setColor(new Color(0x0000FF));
					}
				}
			}
		}
		if(this.board.checkWin("R")){
			JOptionPane.showMessageDialog(this, "Red wins");
			System.out.println("Red wins");
			dispose();
		}
		if(this.board.checkWin("B")){
			System.out.println("Blue wins");
			JOptionPane.showMessageDialog(this, "Blue wins");
			dispose();
		}
		//System.out.println(board.countRows(2, "R"));
	}
	public synchronized void makeMove(int column,String value){
		board.setPos(column, value);
		playerRed = !playerRed;
		updateFromBoard(this.board);
	}

	/**
	 * Create the frame.
	 */
	public ConnectFourGui(Board board) {
		playerRed = true;
		this.board = board;
		buttons = new JButton[ConnectFour.COLUMNS][ConnectFour.ROWS];
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 70*ConnectFour.COLUMNS, 70*ConnectFour.ROWS);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(ConnectFour.ROWS,ConnectFour.COLUMNS));


		for(int i =0;i<ConnectFour.ROWS;i++){
			for(int j=0;j<ConnectFour.COLUMNS;j++){
				Square button = new Square("-",j);
				contentPane.add(button);
				buttons[j][i] = button;
			}
		}
	}
}		





