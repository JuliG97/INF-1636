import java.awt.Image;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;


public class Pawn extends Piece {
	
	List<Tile> movementOptions;
	Tile[][] board;
	Piece pieceToMove;
	Piece temp;
	
	Pawn(PieceColor c){
		color = c;
		String imagem = "";
		
		if(color == PieceColor.BLACK){
			imagem = "p_peao.gif";
		}else if(color == PieceColor.WHITE){
			imagem = "b_peao.gif";
		}
		
		try {
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
		int i = row + 1; 
		int j = column;
		
		if(color == PieceColor.WHITE){
			i = row - 1;
			if(hasMoved == false){
				if(board[i-1][j].getPiece() == null){
					addMovementOption(i-1, j, row, column);
				}
			}
		}else{
			if(hasMoved == false){
				if(board[i+1][j].getPiece() == null){
					addMovementOption(i+1, j, row, column);
				}
			}
		}
		
		
		if(row>=0 && row<=7){
			if(board[i][j].getPiece() == null){
				addMovementOption(i, j, row, column);
			}
			if(column<7){
				j = column + 1;
				if(board[i][j].getPiece() != null && (board[i][j].getPiece().getColor() != pieceToMove.getColor() )){
					addMovementOption(i, j, row, column);
				}
			}
			if(column>0){
				j = column - 1;
				if(board[i][j].getPiece() != null && (board[i][j].getPiece().getColor() != pieceToMove.getColor() )){
					addMovementOption(i, j, row, column);
				}
			}
		}

		return movementOptions;
	}
	
	private void addMovementOption(int i, int j, int row, int column){
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
								return;
							}
						}
					}
				}
			}
			
			board[i][j].setPiece(temp);
			board[row][column].setPiece(pieceToMove);
		}
		
		movementOptions.add(board[i][j]);
	}
}