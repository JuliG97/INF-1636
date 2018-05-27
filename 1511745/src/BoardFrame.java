import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class BoardFrame extends JFrame{
	
	public static int boardDimension = 640;
	private static Tile[][] board;
	static BoardPanel bp;
	static Tile selectedTile = null;

	
	public BoardFrame(Tile[][] board){
		setSize(boardDimension+3, boardDimension+25);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		bp = new BoardPanel(board, boardDimension);
		getContentPane().add(bp);
		
		addMouseListener(boardClickHandler);
		
		BoardFrame.board = board;
		
	}
	
	public static MouseAdapter boardClickHandler = new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
	    	Point click = e.getPoint();
	    	System.out.println(click.getX() + "  " + click.getY());
	    	
	    	int column = (int) (click.getX()/(boardDimension/8));
	    	int row = (int) (click.getY()/(boardDimension/8));
	    	
	    	if(selectedTile == null){
	    		if(board[row][column].getPiece() != null){
	    			selectedTile = board[row][column];
	    			board[row][column].setSelected(true);
	    		}
	    	}else{
	    		if(selectedTile != board[row][column]){
	    			selectedTile.setSelected(false);
		    		Board.updatePieceLocation(selectedTile, board[row][column]);
		    		selectedTile = null;
	    		}
	    	}
	    	bp.repaint();
        }
	};
}
