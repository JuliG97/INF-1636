import java.awt.Image;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;


public class Pawn extends Piece {
	
	PieceColor color;
	Boolean hasMoved = false;
	
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
	
	public List<Tile> getMovementOptions(int row, int column) {
		List<Tile> movementOptions = new ArrayList<Tile>();
		Tile[][] board = Board.getBoard().getBoardMatrix();
		
		int i = row+1; 
		int j = column;
		
		if(color == PieceColor.WHITE){
			i = row-1;
			if(hasMoved == false){
				if(board[i-1][j].getPiece() == null){
					movementOptions.add(board[i-1][j]);
				}
			}
		}else{
			if(hasMoved == false){
				if(board[i+1][j].getPiece() == null){
					movementOptions.add(board[i+1][j]);
				}
			}
		}
		
		
		if(i>=0 && i<=7){
			if(board[i][j].getPiece() == null){
				movementOptions.add(board[i][j]);
			}
			if(j<7){
				if(board[i][j+1].getPiece() != null){
					movementOptions.add(board[i][j+1]);
				}
			}
			if(j>0){
				if(board[i][j-1].getPiece() != null){
					movementOptions.add(board[i][j-1]);
				}
			}
		}

		return movementOptions;
	}
	
	public void moved(){
		hasMoved = true;
	}
}