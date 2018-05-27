
public class Board {
	
	public static Tile[][] board = new Tile [8][8];
	
	public static void InitializeBoard(){
		
		for(int i = 0; i<8; i++){
			for(int j = 0; j<8; j++){
				board[i][j] = new Tile();
			}
		}
		
		board[7][0].setPiece(new Rook());
		board[7][7].setPiece(new Rook());
		board[7][1].setPiece(new Knight());
		board[7][6].setPiece(new Knight());
		board[7][2].setPiece(new Bishop());
		board[7][5].setPiece(new Bishop());
		board[7][3].setPiece(new Queen());
		board[7][4].setPiece(new King());
		for(int j = 0; j<8; j++) {
			board[6][j].setPiece(new Pawn());
		}
		
	}
	
	public static Tile[][] getBoard(){
		return board;
	}

}
