import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class BoardFrame extends JFrame implements Observer{
	
	private static BoardFrame bf = null;
	
	private static Board board;
	public static int boardDimension = 640;
	private static Tile[][] boardMatrix;
	private BoardPanel bp;
	static Tile selectedTile = null;
	static List<Tile> movementOptions = null;
	InterfaceFacade interfaceFacade;
	
	private BoardFrame(Board board){
		setSize(boardDimension+3, boardDimension+25);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		interfaceFacade = InterfaceFacade.getInterfaceFacade();
		
		interfaceFacade.addBoardObserver(this);
		
		BoardFrame.board = board;
		BoardFrame.boardMatrix = interfaceFacade.getBoardMatrix();
		
		bp = BoardPanel.getBoardPanel(boardMatrix, boardDimension);
		getContentPane().add(bp);
		
		addMouseListener(boardClickHandler);
	}

	
	public static BoardFrame getBoardFrame(Board board){
		if(bf == null){
			bf = new BoardFrame(board);
			bf.setTitle("Chess Game");
			bf.setVisible(true);
		}
		return bf;
	}
	
	public MouseAdapter boardClickHandler = new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
	    	
	    	if(SwingUtilities.isLeftMouseButton(e)){
		    	if(interfaceFacade.getGameOverState() != -1){
			    	Point click = e.getPoint();
			    	
			    	int column = (int) (click.getX()/(boardDimension/8));
			    	int row = (int) ((click.getY() - 30)/(boardDimension/8));
			    	
			    	Tile tileClicked = interfaceFacade.getTile(row, column);
			    	
			    	if(selectedTile == null){ //if there is no piece selected the player must select a piece to move
			    		if(interfaceFacade.getPieceColor(interfaceFacade.getTilePiece(tileClicked)) == interfaceFacade.getPlayerTurn()){
				    		if(interfaceFacade.getTilePiece(tileClicked) != null){
				    			selectedTile = tileClicked;
				    			interfaceFacade.getTile(row, column).setSelected(true);
				    			movementOptions = interfaceFacade.highlightMoveOptions(row, column);
				    		}
			    		}
			    	}else{ //otherwise if there is a piece selected the player must choose where to move the piece
			    			
			    		if(movementOptions.contains(tileClicked)){ //check if the selected piece can go to the tile clicked
					    	interfaceFacade.updateBoardPieceLocation(selectedTile, tileClicked);
			    		}
			    			
			    		if(interfaceFacade.getRoqueState(tileClicked) == true){
			    			interfaceFacade.Roque(selectedTile, tileClicked);
			    		}
			    			
			    		interfaceFacade.setRoqueState(interfaceFacade.getTile(0, 0), false);
			    		interfaceFacade.setRoqueState(interfaceFacade.getTile(7, 0), false);
			    		interfaceFacade.setRoqueState(interfaceFacade.getTile(0, 7), false);
			    		interfaceFacade.setRoqueState(interfaceFacade.getTile(7, 7), false);
			    			
			    		interfaceFacade.setTileSelection(selectedTile, false);
			    		selectedTile = null;
			    		movementOptions = null;
			    	}
			    	interfaceFacade.dataChanged();
		    	}
	    	}else if(SwingUtilities.isRightMouseButton(e)){
	    		interfaceFacade.saveGame();
	    	}
        }
	};
	
	public int getBoardDimension(){
		return boardDimension;
	}


	@Override
	public void update(Observable o, Object arg) {
		int gameOver  = interfaceFacade.getGameOverState();
		String message = "";
		
		bp.repaint();
		
		if(gameOver != 0 && gameOver != -1){
			if(gameOver == 1){
				message = "StaleMate!";
			}else if(gameOver == 2){
				message = "White player Wins!!!";
			}else if(gameOver == 3){
				message = "Black player Wins!!!";
			}
			
			JOptionPane.showMessageDialog(this, message, "Game Over", JOptionPane.PLAIN_MESSAGE);
			interfaceFacade.resetGame();
		}
	}
}
