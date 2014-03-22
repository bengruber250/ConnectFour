package gruber.ben.connectfour.gui;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

import static gruber.ben.connectfour.gui.ConnectFourGui.frame;
public class Square extends JButton {
	private int column;
	public Square(String arg0, int column) {
		super(arg0);
		this.column = column;
		addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e)
			{		                
				onClick();
			}
		});
	}
	public int getColumn(){
		return column;
	}
	private void onClick(){
		frame.makeMove(column, "R");
		frame.updateFromBoard(frame.getBoard());
	}
	public void setColor(Color c){
		setBackground(c);
	}

}
