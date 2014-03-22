package gruber.ben.connectfour.ai;

import static gruber.ben.connectfour.gui.ConnectFourGui.frame;

import java.io.IOException;

import gruber.ben.connectfour.Board;
import gruber.ben.connectfour.Player;
import gruber.ben.connectfour.gui.ConnectFourGui;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
public class AI extends Thread{
	private boolean ran;
	private String playerChar;
	private Player player;
	private static Runtime rt;
//	private 
	static{
		rt = Runtime.getRuntime();
	}
	public AI() {
		ran= true;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void run(){
		while(true){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(!ConnectFourGui.frame.playerRed && ran){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ran = false;

				Board startingBoard = ConnectFourGui.frame.getBoard();

				int columnToPlay = -1;
				double highest = -2.0;
				double score;
				for(int column:startingBoard.getPossibleMoves()){
					System.out.println(column);
					//System.out.println(startingBoard.monteCarlo(column, "B", 1000));
					score = startingBoard.monteCarlo(column, "B", 100);
					if(score>highest){
						highest = score;
						columnToPlay = column;
					}

				}
				frame.makeMove(columnToPlay, "B");
				try {
					rt.exec("cmd.exe /C echo " + new Character((char) 11));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				




			}
			else if(ConnectFourGui.frame.playerRed){
				ran = true;
			}
		}
	}

}
