
public class Board {
	
	public static Tile[][] board = new Tile [8][8];
	
	public static void InitializeBoard(){
		
		for(int i = 0; i<8; i++){
			for(int j = 0; j<8; j++){
				board[i][j] = new Tile();
			}
		}
	}
	
	public static Tile[][] getBoard(){
		return board;
	}

}
