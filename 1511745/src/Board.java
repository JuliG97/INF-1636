
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
		board[0][0].setPiece(new Rook_Blk());
		board[0][7].setPiece(new Rook_Blk());
		board[0][1].setPiece(new Knight_Blk());
		board[0][6].setPiece(new Knight_Blk());
		board[0][2].setPiece(new Bishop_Blk());
		board[0][5].setPiece(new Bishop_Blk());
		board[0][3].setPiece(new Queen_Blk());
		board[0][4].setPiece(new King_Blk());
		for(int j = 0; j<8; j++) {
			board[1][j].setPiece(new Pawn_Blk());
		}
	}
	
	public static Tile[][] getBoard(){
		return board;
	}

}
