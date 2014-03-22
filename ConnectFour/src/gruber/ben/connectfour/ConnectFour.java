package gruber.ben.connectfour;

import java.util.Scanner;

public class ConnectFour {
	public static final int ROWS = 6;
	public static final int COLUMNS = 7;
	public static void main(String[] args) {
		// TODO Create player class
		Scanner scanner = new Scanner(System.in);
		Board gameBoard = new Board(COLUMNS,ROWS);
		boolean playerRed = true;
		while(true){
			System.out.print(gameBoard);
			System.out.println("0123456");
			if(gameBoard.checkWin("R")){
				System.out.println("Red wins");
				break;
			}
			else if(gameBoard.checkWin("B")){
				System.out.println("Blue wins");
				break;
			}
			if(playerRed){
				System.out.print("Red move(0-6): ");
				int move = scanner.nextInt();
				gameBoard.setPos(move,"R");
			}
			else{
				System.out.print("Blue move(0-6): ");
				int move = scanner.nextInt();
				gameBoard.setPos(move,"B");
			}
			playerRed = !playerRed;
		}
		scanner.close();
	}


}
