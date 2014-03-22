package gruber.ben.connectfour.ai;

import java.util.ArrayList;

import gruber.ben.connectfour.Board;

public class Negamax {

	public Negamax() {

	}

	/**
	 *@param board
	 *@return column 
	 */
	public int move(Board board){
		ArrayList<Integer> possibleMoves = board.getPossibleMoves();
		for(int move: possibleMoves){
			getNegamaxValue(move);
		}
		return 0;
	}
	protected int getNegamaxValue(int movee){

		return 0;

	}

	/**
	 *@param board
	 *@return mini 
	 */


}
