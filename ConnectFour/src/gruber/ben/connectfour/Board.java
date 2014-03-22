package gruber.ben.connectfour;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Board{
	private String[][] boardArray;
	public String[][] getBoardArray() {
		return boardArray;
	}
	public void setBoardArray(String[][] boardArray) {
		this.boardArray = boardArray;
	}

	private int rows;
	private int columns;
	public Board(int columns, int rows) {
		this.rows = rows;
		this.columns = columns;
		boardArray = new String[columns][rows];
	}
	public Board(Board board){
		rows = board.rows;
		columns = board.columns;
		String[][] src = board.getBoardArray();
		this.boardArray = new String[columns][rows];
		for(int i=0; i<src.length; i++)
			  for(int j=0; j<src[i].length; j++)
				  boardArray[i][j]=src[i][j];
	}
	public int countRows(int numberInRow, String playerChar){

		Pattern pattern = Pattern.compile(playerChar + "{" + numberInRow + "}");
		Matcher matcher = pattern.matcher(toString());
		int count = 0;
		while(matcher.find()){
			count++;
		}
		matcher = pattern.matcher(toDiagonalString());
		while(matcher.find()){
			count++;
		}
		matcher = pattern.matcher(toSideString());
		while(matcher.find()){
			count++;
		}


		return count;
	}
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(int i =0; i<rows;i++){
			for(int j =0;j<columns;j++){
				sb.append(boardArray[j][i]==(null)?"-":boardArray[j][i]);
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	public String toSideString(){
		StringBuilder sb = new StringBuilder();
		for(int i =0; i<columns;i++){
			for(int j =0;j<rows;j++){
				sb.append(boardArray[i][j]==(null)?"-":boardArray[i][j]);
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public  void setPos(int column, String value){

		for(int i=rows-1;i>=0;i--){
			if(boardArray[column][i]==(null)){
				boardArray[column][i] = value;
				return;
			}
		}
	}
	public Boolean checkWin(String playerChar){
		Pattern pattern = Pattern.compile("(?s).*"+playerChar+"{4}.*");
		return (pattern.matcher(toString()).matches() || pattern.matcher(toSideString()).matches() || pattern.matcher(toDiagonalString()).matches());
	}
	public String betterCheckWin(String playerChar,String enemyChar){
		Pattern pattern = Pattern.compile("(?s).*"+playerChar+"{4}.*");
		Pattern patternEnemy = Pattern.compile("(?s).*"+enemyChar+"{4}.*");
		String horizontal = toString();
		String vertical = toSideString();
		String diagonal = toDiagonalString();
		if(pattern.matcher(horizontal).matches() || pattern.matcher(vertical).matches() || pattern.matcher(diagonal).matches()){
			return "win";
		}if(patternEnemy.matcher(horizontal).matches() || patternEnemy.matcher(vertical).matches() || patternEnemy.matcher(diagonal).matches()){
			return "loss";
		}
		return null;
	}
	public  double monteCarlo(int column,String playerChar,int iterations){
		int reds, blues;
		Board carloBoard = null;

		int wins = 0,losses = 0,draws = 0;
		boolean redTurn = false;
		for(int i=0;i<iterations;i++){
			carloBoard = new Board(this);
			carloBoard.setPos(column, "B");
//			reds = carloBoard.countRows(1, "R"); //1
//			blues = carloBoard.countRows(1,"B"); //1
			//red is first
//			if(reds-blues==4){
//				redTurn = false;
//			}
//
//			else if(reds==blues){
//				redTurn = true;
//			}


			while (true){
				ArrayList<Integer> possibleMoves = carloBoard.getPossibleMoves();
				String outcome = carloBoard.betterCheckWin("B", "R");
				if(possibleMoves.size()==0){
					break;
				}
				if (outcome=="loss"){
					losses++;//for B
					break;
				}
				if(outcome=="win"){
					wins++; //for B
					break;
				}
				carloBoard.setPos(possibleMoves.get((int)(Math.random() * possibleMoves.size())), (redTurn ? "R" : "B"));
				redTurn = !redTurn;
			}
		}
		return (wins-losses)/(double)iterations;
	}
	public String toDiagonalString(){
		StringBuilder sb = new StringBuilder();
		int m = columns;
		int n = rows;
		for (int slice = 0; slice < m + n - 1; slice++) {
			int z1 = slice < n ? 0 : slice - n + 1;
			int z2 = slice < m ? 0 : slice - m + 1;
			for (int j = slice - z2; j >= z1; --j) {
				sb.append(boardArray[j][slice - j]);
			}
			sb.append("\n");
		}
		m = columns;
		n = rows;
		for (int slice = 0; slice < m + n - 1; slice++) {
			int z1 = slice < n ? 0 : slice - n + 1;
			int z2 = slice < m ? 0 : slice - m + 1;
			for (int j = slice - z2; j >= z1; --j) {
				sb.append(boardArray[columns-1-j][slice - j]);
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public ArrayList<Integer> getPossibleMoves(){
		ArrayList<Integer> possibleMoves = new ArrayList<Integer>();
		for(int i = 0; i<columns;i++){
			if(boardArray[i][0] == null){
				possibleMoves.add(i);
			}
		}
		return possibleMoves;
	}
}


