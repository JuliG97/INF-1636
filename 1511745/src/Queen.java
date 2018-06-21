import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Queen extends Piece{
	
	List<Tile> movementOptions;
	Tile[][] board;
	Piece pieceToMove;
	Piece temp;
	
	Queen(PieceColor c){
		
		color = c;
		String imagem = "";
		
		if(color == PieceColor.BLACK){
			imagem = "p_dama.gif";
		}else if(color == PieceColor.WHITE){
			imagem = "b_dama.gif";
		}
		
		try{
			i=ImageIO.read(new File("Pecas/Pecas_1/"+imagem));
		}catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
	
	public List<Tile> getMovementOptions(Tile tile) {
		movementOptions = new ArrayList<Tile>();
		board = Board.getBoard().getBoardMatrix();
		
		int row = tile.getRow();
		int column = tile.getColumn();
		
		pieceToMove = board[row][column].getPiece();
		
		int i = row-1; int j = column-1;
		while(i>=0 && j>=0){
			if(addMovementOption(i, j, row, column) == 0){break;}
			i--; j--;
		}
		
		i = row-1; j = column+1;
		while(i>=0 && j<=7){
			if(addMovementOption(i, j, row, column) == 0){break;}
			i--; j++;
		}
		
		i = row+1; j = column-1;
		while(i<=7 && j>=0){
			if(addMovementOption(i, j, row, column) == 0){break;}
			i++; j--;
		}
		
		i = row+1; j = column+1;
		while(i<=7 && j<=7){
			if(addMovementOption(i, j, row, column) == 0){break;}
			i++; j++;
		}
		
		i = row-1; j = column;
		while(i>=0) {
			if(addMovementOption(i, j, row, column) == 0){break;}
			i--;
		}
		
		i = row+1; j = column;
		while(i <= 7) {
			if(addMovementOption(i, j, row, column) == 0){break;}
			i++;
		}
		
		i = row; j = column-1;
		while(j>=0) {
			if(addMovementOption(i, j, row, column) == 0){break;}
			j--;
		}
		
		i = row; j = column+1;
		while(j<=7) {
			if(addMovementOption(i, j, row, column) == 0){break;}
			j++;
		}
		
		return movementOptions;
	}
	
	private int addMovementOption(int i, int j, int row, int column){
		
		if(Board.getBoard().getPlayerTurn() == pieceToMove.getColor()){
			temp = board[i][j].getPiece();
			board[i][j].setPiece(pieceToMove);
			board[row][column].setPiece(null);
			
			for(Tile[] t: board){ //if the movement will result in the king beeing in check then the movement is not possible
				for(Tile tile: t){
					if(tile.getPiece() != null && !(tile.getPiece() instanceof King)){
						if(tile.getPiece().getColor() != pieceToMove.getColor()){
							if(Board.getBoard().pieceThreatensKing(tile)){
								board[i][j].setPiece(temp);
								board[row][column].setPiece(pieceToMove);
								if(temp != null){
									return 0; //break here
								}
								return 1;
							}
						}
					}
				}
			}
			
			board[i][j].setPiece(temp);
			board[row][column].setPiece(pieceToMove);
		}
		
		if(board[i][j].getPiece() != null){
			if(board[i][j].getPiece().getColor() != pieceToMove.getColor()){
				this.movementOptions.add(board[i][j]);
			}
			return 0; //break here
		}
		this.movementOptions.add(board[i][j]);
		return 1;
		
	}
}




