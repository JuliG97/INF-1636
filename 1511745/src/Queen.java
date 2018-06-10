import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Queen extends Piece{
	
	PieceColor color;
	
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


	public List<Tile> getMovementOptions(int row, int column) {
		List<Tile> movementOptions = new ArrayList<Tile>();
		Tile[][] board = Board.getBoard().getBoardMatrix();
		
		int i = row-1; int j = column-1;
		while(i>=0 && j>=0){
			movementOptions.add(board[i][j]);
			i--; j--;
		}
		
		i = row-1; j = column+1;
		while(i>=0 && j<=7){
			movementOptions.add(board[i][j]);
			i--; j++;
		}
		
		i = row+1; j = column-1;
		while(i<=7 && j>=0){
			movementOptions.add(board[i][j]);
			i++; j--;
		}
		
		i = row+1; j = column+1;
		while(i<=7 && j<=7){
			movementOptions.add(board[i][j]);
			i++; j++;
		}
		
		i = row; j = column;
		while(i>=1) {
			movementOptions.add(board[i-1][j]);
			i--;
		}
		
		i = row; j = column;
		while(i <= 6) {
			movementOptions.add(board[i+1][j]);
			i++;
		}
		
		i = row; j = column;
		while(j>=1) {
			movementOptions.add(board[i][j-1]);
			j--;
		}
		
		i = row; j = column;
		while(j<=6) {
			movementOptions.add(board[i][j+1]);
			j++;
		}
			
		System.out.println("Queeen");
		return movementOptions;
	}
	}





