// Tick Tack Toe

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class tick_tack_toe implements ActionListener {
	// Properties
	JFrame theFrame = new JFrame("Tick Tack Toe");
	GamePanel thePanel = new GamePanel();
	
	// Methods
	public void actionPerformed(ActionEvent evt){
		
	}
	
	// Constructor
	public tick_tack_toe(){
		thePanel.setLayout(null);
		thePanel.setPreferredSize(new Dimension(800, 800));
		
		theFrame.setContentPane(thePanel);
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theFrame.pack();
		theFrame.setVisible(true);
	}
	
	// Main Program
	public static void main(String[] args){
		new tick_tack_toe();
	}
}

// Interface
class GamePanel extends JPanel implements MouseListener {
	// Properties
	int intScore1 = 0;
	int intScore2 = 0;

	// 0 = empty, 1 = Player 1/X, 2 = Player 2/O
	int[][] intBoard = new int[3][3];

	int intCurrentPlayer = 1;
	boolean blnGameOver = false;

	// Constructor
	public GamePanel() {
		addMouseListener(this);
	}

	// Methods
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Title
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 40));
		g.drawString("Tick Tack Toe", 270, 50);

		// Score line
		g.setFont(new Font("Arial", Font.PLAIN, 24));
		g.drawString("Player 1: " + intScore1, 50, 50);
		g.drawString("Player 2: " + intScore2, 600, 50);

		// Grid Lines
		g.setColor(Color.BLACK);
		g.fillRect(297, 100, 6, 600);
		g.fillRect(497, 100, 6, 600);
		g.fillRect(100, 297, 600, 6);
		g.fillRect(100, 497, 600, 6);

		// Draw X's and O's
		g.setFont(new Font("Arial", Font.BOLD, 100));

		for(int row = 0; row < 3; row++) {
			for(int col = 0; col < 3; col++) {
				int x = 100 + col * 200;
				int y = 100 + row * 200;

				if(intBoard[row][col] == 1) {
					g.setColor(Color.BLUE);
					g.drawString("X", x + 65, y + 135);
				}
				else if(intBoard[row][col] == 2) {
					g.setColor(Color.RED);
					g.drawString("O", x + 60, y + 135);
				}
			}
		}
	}

	public void mouseClicked(MouseEvent evt) {
		// Reset Board
		if(blnGameOver == true) {
			resetBoard();
			return;
		}

		// Mouse Position
		int mouseX = evt.getX();
		int mouseY = evt.getY();

		if(mouseX < 100 || mouseX > 700 || mouseY < 100 || mouseY > 700) {
			return;
		}

		int col = (mouseX - 100)/200;
		int row = (mouseY - 100)/200;
 
		if(intBoard[row][col] == 0) {
			intBoard[row][col] = intCurrentPlayer;

			if(checkWinner(intCurrentPlayer)) {
				if(intCurrentPlayer == 1) {
					intScore1++;
				}
				else {
					intScore2++;
				}

				blnGameOver = true;
			}
			else if(checkTie()) {
				blnGameOver = true;
			}
			else {
				if(intCurrentPlayer == 1) {
					intCurrentPlayer = 2;
				}
				else {
					intCurrentPlayer = 1;
				}
			}

			repaint();
		}
	}

	public boolean checkWinner(int player) {
		// Rows
		for(int row = 0; row < 3; row++) {
			if(intBoard[row][0] == player && intBoard[row][1] == player && intBoard[row][2] == player) {
				return true;
			}
		}

		// Columns
		for(int col = 0; col < 3; col++) {
			if(intBoard[0][col] == player && intBoard[1][col] == player && intBoard[2][col] == player) {
				return true;
			}
		}

		// Diagonals
		if(intBoard[0][0] == player && intBoard[1][1] == player && intBoard[2][2] == player) {
			return true;
		}

		if(intBoard[0][2] == player && intBoard[1][1] == player && intBoard[2][0] == player) {
			return true;
		}

		return false;
	}

	public boolean checkTie() {
		for(int row = 0; row < 3; row++) {
			for(int col = 0; col < 3; col++) {
				if(intBoard[row][col] == 0) {
					return false;
				}
			}
		}

		return true;
	}

	public void resetBoard() {
		for(int row = 0; row < 3; row++) {
			for(int col = 0; col < 3; col++) {
				intBoard[row][col] = 0;
			}
		}

		intCurrentPlayer = 1;
		blnGameOver = false;
		repaint();
	}

	public void mousePressed(MouseEvent evt) {}
	public void mouseReleased(MouseEvent evt) {}
	public void mouseEntered(MouseEvent evt) {}
	public void mouseExited(MouseEvent evt) {}
}
