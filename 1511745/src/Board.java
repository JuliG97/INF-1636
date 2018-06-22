import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.swing.JOptionPane;

public class Board extends Observable{
	
	private static Board board = null;
	public static Tile[][] boardMatrix = new Tile [8][8];
	InterfaceFacade interfaceFacade;
	private PieceColor kingIsChecked = null;
	private PieceColor playerTurn = PieceColor.WHITE;
	private int gameOverState = 0;
	
	private Board(){
		interfaceFacade = InterfaceFacade.getInterfaceFacade();
	}
	
	public static Board getBoard(){
		if(board == null){
			board = new Board();
		}
		return board;
	}
	
	public void InitializeBoard(){  //initialize the pieces in the correct positons
		
		for(int i = 0; i<8; i++){
			for(int j = 0; j<8; j++){
				boardMatrix[i][j] = new Tile(i, j);
			}
		}
		
		boardMatrix[7][0].setPiece(new Rook(PieceColor.WHITE));
		boardMatrix[7][7].setPiece(new Rook(PieceColor.WHITE));
		boardMatrix[7][1].setPiece(new Knight(PieceColor.WHITE));
		boardMatrix[7][6].setPiece(new Knight(PieceColor.WHITE));
		boardMatrix[7][2].setPiece(new Bishop(PieceColor.WHITE));
		boardMatrix[7][5].setPiece(new Bishop(PieceColor.WHITE));
		boardMatrix[7][3].setPiece(new Queen(PieceColor.WHITE));
		boardMatrix[7][4].setPiece(new King(PieceColor.WHITE));
		for(int j = 0; j<8; j++) {
			boardMatrix[6][j].setPiece(new Pawn(PieceColor.WHITE));
			boardMatrix[1][j].setPiece(new Pawn(PieceColor.BLACK));
		}
		boardMatrix[0][0].setPiece(new Rook(PieceColor.BLACK));
		boardMatrix[0][7].setPiece(new Rook(PieceColor.BLACK));
		boardMatrix[0][1].setPiece(new Knight(PieceColor.BLACK));
		boardMatrix[0][6].setPiece(new Knight(PieceColor.BLACK));
		boardMatrix[0][2].setPiece(new Bishop(PieceColor.BLACK));
		boardMatrix[0][5].setPiece(new Bishop(PieceColor.BLACK));
		boardMatrix[0][3].setPiece(new Queen(PieceColor.BLACK));
		boardMatrix[0][4].setPiece(new King(PieceColor.BLACK));
	}
	
	public Tile[][] getBoardMatrix(){
		return boardMatrix;
	}
	
	public void updatePieceLocation(Tile t1, Tile t2){ //put the piece on tile t1 in tile t2
		
		Piece p = t1.getPiece();
		p.moved();
		
		if(p instanceof Pawn){ //check if a pawn got to the other side of the board
			for(int i = 0; i<8; i++){
				if(t2 == boardMatrix[0][i] ||t2 == boardMatrix[7][i]){
					interfaceFacade.showPawnPromotionMenu(t2);
				}
			}
		}
		
		t2.setPiece(p);
		t1.setPiece(null);
		
		if(pieceThreatensKing(t2) == true){ //check if the movement resulted in a check
			if(p.getColor() == PieceColor.WHITE){
				kingIsChecked = PieceColor.BLACK;
			}else{
				kingIsChecked = PieceColor.WHITE;
			}
		}
		
		nextPlayerTurn();
		
		if(checkGameOver() == true){
			if(kingIsChecked == null){
				gameOverState = 1;
			}else{
				if(kingIsChecked == PieceColor.BLACK){
					gameOverState = 2;
				}else{
					gameOverState = 3;
				}
			}
		}
		
		if(kingIsChecked != null){
			kingIsChecked = null;
		}
	}
	
	public void Roque(Tile t1, Tile t2){
		if(t2.getColumn() == 7){
			updatePieceLocation(t1, boardMatrix[t1.getRow()][6]);
			updatePieceLocation(t2, boardMatrix[t1.getRow()][5]);
		}
		
		if(t2.getColumn() == 0){
			updatePieceLocation(t1, boardMatrix[t1.getRow()][2]);
			updatePieceLocation(t2, boardMatrix[t1.getRow()][3]);
		}
		nextPlayerTurn();
	}

	public List<Tile> highlightMovemetOptions(int row, int column) {
		
		List<Tile> movementOptions;
		
		Piece p = boardMatrix[row][column].getPiece();
		movementOptions = p.getMovementOptions(boardMatrix[row][column]);
		
		for(Tile t: movementOptions){
			t.setHighlighted(true);
		}
		
		return movementOptions;
	}
	
	public Boolean pieceThreatensKing(Tile t){
		List<Tile> movementOptions;
		
		if(t.getPiece() != null){
			movementOptions = t.getPiece().getMovementOptions(t);
			for(Tile tile: movementOptions){
				if(tile.getPiece() != null){
					if(tile.getPiece() instanceof King){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public PieceColor getChekState(){
		return kingIsChecked;
	}
	
	public void setChekState(PieceColor pc){
		kingIsChecked = pc;
	}
	
	public PieceColor getPlayerTurn(){
		return playerTurn;
	}
	
	public void nextPlayerTurn(){
		if(playerTurn == PieceColor.WHITE){
			playerTurn = PieceColor.BLACK;
		}else{
			playerTurn = PieceColor.WHITE;
		}
	}
	
	public void dataChanged(){
		setChanged();
		notifyObservers();
	}
	
	private Boolean checkGameOver(){
		List<Tile> movementOptions;
			
		for(Tile[] t: boardMatrix){
			for(Tile tile: t){
				if(tile.getPiece() != null && !(tile.getPiece() instanceof King)){
					if(tile.getPiece().getColor() == playerTurn){
						movementOptions = tile.getPiece().getMovementOptions(tile);
						if( !(movementOptions.isEmpty()) ){
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	public int getGameOverState(){
		return gameOverState;
	}
	
	public void resetGame(){
		InitializeBoard();
		gameOverState = 0;
		playerTurn = PieceColor.WHITE;
		kingIsChecked = null;
		
		dataChanged();
	}
}
