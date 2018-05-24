import javax.swing.JFrame;

public class BoardFrame extends JFrame{
	
	public int boardDimension = 640;
	
	public BoardFrame(Tile[][] board){
		setSize(boardDimension+3, boardDimension+25);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		getContentPane().add(new BoardPanel(board, boardDimension));
		
	}
}
