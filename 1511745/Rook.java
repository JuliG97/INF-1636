import java.awt.Image;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Rook extends Piece{
	
	PieceColor color;
	
	Rook(PieceColor c){
		color = c;
		String imagem = "";
		
		if(color == PieceColor.BLACK){
			imagem = "p_torre.gif";
		}else if(color == PieceColor.WHITE){
			imagem = "b_torre.gif";
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
		
				
		int i = row; int j = column;
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
					
		System.out.println("Roook");
		return movementOptions;
	}
	}

		
		
		
		


