import java.util.ArrayList;
import java.util.List;

public class Board {
	
	private static Board board = null;
	public static Tile[][] boardMatrix = new Tile [8][8];
	
	private Board(){
		
	}
	
	public static Board getBoard(){
		if(board == null){
			board = new Board();
		}
		return board;
	}
	
	public void InitializeBoard(){
		
		for(int i = 0; i<8; i++){
			for(int j = 0; j<8; j++){
				boardMatrix[i][j] = new Tile();
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
	
	public void updatePieceLocation(Tile t1, Tile t2){
		
		Piece p = t1.getPiece();
		
		if(p instanceof Pawn){
			p.moved();
		}
		
		t2.setPiece(p);
		t1.setPiece(null);
	}

	public List<Tile> highlightMovemetOptions(int row, int column) {
		
		List<Tile> movementOptions;
		
		Piece p = boardMatrix[row][column].getPiece();
		movementOptions = p.getMovementOptions(row, column);
		
		for(Tile t: movementOptions){
			t.setHighlighted(true);
		}
		
		return movementOptions;
	}
}
