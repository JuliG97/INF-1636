import java.awt.Image;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;


public class Knight extends Piece {
	
	PieceColor color;
	Boolean hasMoved = false;
	
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

	public List<Tile> getMovementOptions(int row, int column) {
		List<Tile> movementOptions = new ArrayList<Tile>();
		Tile[][] board = Board.getBoard().getBoardMatrix();
		
		int i = row; 
		int j = column;
		
		if(color == PieceColor.WHITE){
			//i = row-1;
			//if(board[i-1][j+1].getPiece() == null){
				movementOptions.add(board[i-2][j+1]);
			
			//if(board[i-1][j-1].getPiece() == null){
				movementOptions.add(board[i-2][j-1]);
				
			if(j <= 5) {
				movementOptions.add(board[i-1][j+2]);
			}
			
			if(j >= 2) {
				movementOptions.add(board[i-1][j-2]);
			}
			
			//if(i>0 && i<7) {
				//movementOptions.add(board[i-1][j-2]);
				//movementOptions.add(board[i-1][j+2]);
			//}
		}
			
		else{
			//if(board[i+1][j].getPiece() == null){
				movementOptions.add(board[i+2][j+1]);
				movementOptions.add(board[i+2][j-1]);
				
			if(j <= 5) {
				movementOptions.add(board[i+1][j+2]);
			}
			if(j >= 2) {
				movementOptions.add(board[i+1][j-2]);
			}
						
		}
		
		if(i>0 && i<7) {
			movementOptions.add(board[i-1][j-2]);
			movementOptions.add(board[i-1][j+2]);
			movementOptions.add(board[i+1][j-2]);
			movementOptions.add(board[i+1][j+2]);
		}
				
		return movementOptions;
	}

	public void moved(){
		hasMoved = true;
	}
	}








