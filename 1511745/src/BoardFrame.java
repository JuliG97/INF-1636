import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;

public class BoardFrame extends JFrame{
	
	private static BoardFrame bf = null;
	
	private static Board board;
	public static int boardDimension = 640;
	private static Tile[][] boardMatrix;
	private BoardPanel bp;
	static Tile selectedTile = null;
	static List<Tile> movementOptions = null;
	
	private BoardFrame(Board board){
		setSize(boardDimension+3, boardDimension+25);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		BoardFrame.board = board;
		BoardFrame.boardMatrix = board.getBoardMatrix();
		bp = BoardPanel.getBoardPanel(boardMatrix, boardDimension);
		getContentPane().add(bp);
		
		addMouseListener(boardClickHandler);
	}

	
	public static BoardFrame getBoardFrame(Board board){
		if(bf == null){
			bf = new BoardFrame(board);
		}
		return bf;
	}
	
	public MouseAdapter boardClickHandler = new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
	    	Point click = e.getPoint();
	    	System.out.println(click.getX() + "  " + click.getY());
	    	
	    	int column = (int) (click.getX()/(boardDimension/8));
	    	int row = (int) (click.getY()/(boardDimension/8));
	    	
	    	if(selectedTile == null){
	    		if(boardMatrix[row][column].getPiece() != null){
	    			selectedTile = boardMatrix[row][column];
	    			boardMatrix[row][column].setSelected(true);
	    			movementOptions = board.highlightMovemetOptions(row, column);
	    		}
	    	}else{
	    		if(selectedTile != boardMatrix[row][column]){
	    			
	    			if(movementOptions.contains(boardMatrix[row][column])){
			    		board.updatePieceLocation(selectedTile, boardMatrix[row][column]);
	    			}
	    			selectedTile.setSelected(false);
	    			selectedTile = null;
	    			movementOptions = null;
	    		}
	    	}
	    	bp.repaint();
        }
	};
}
