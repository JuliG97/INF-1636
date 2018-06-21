import java.awt.Image;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;


public class Knight extends Piece {
	
	List<Tile> movementOptions;
	Tile[][] board;
	Piece pieceToMove;
	Piece temp;
	
	Knight(PieceColor c){
		color = c;
		String imagem = "";
		
		if(color == PieceColor.BLACK){
			imagem = "p_cavalo.gif";
		}else if(color == PieceColor.WHITE){
			imagem = "b_cavalo.gif";
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
		
		int i; int j;
		int a = 2; int b = 1;
		
		for(int w=0; w<=3; w++){
			i = row+a; j = column+b;
			addMovementOption(i, j, row, column);
			i = row+b; j = column+a;
			addMovementOption(i, j, row, column);
			
			if(w % 2 == 0){
				a=a*(-1); 
			}else{
				b=b*(-1);
			}
		}
				
		return movementOptions;
	}
	
	private void addMovementOption(int i, int j, int row, int column){
		
		if(i>=0 && i<=7 && j>=0 && j<=7){
			
			if(Board.getBoard().getPlayerTurn() == pieceToMove.getColor()){
				temp = board[i][j].getPiece();
				board[i][j].setPiece(pieceToMove);
				board[row][column].setPiece(null);
				
				for(Tile[] t: board){
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
			
			if(board[i][j].getPiece() != null){
				if(board[i][j].getPiece().getColor() != pieceToMove.getColor()){
					movementOptions.add(board[i][j]);
				}
			}else{
				movementOptions.add(board[i][j]);
			}
		}
	}
}