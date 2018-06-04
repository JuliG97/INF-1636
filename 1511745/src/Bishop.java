import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Bishop extends Piece{
	
	PieceColor color;
	
	Bishop(PieceColor c){
		color = c;
		String imagem = "";
		
		if(color == PieceColor.BLACK){
			imagem = "p_bispo.gif";
		}else if(color == PieceColor.WHITE){
			imagem = "b_bispo.gif";
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
		while(i>=0 && j>=0 && board[i][j].getPiece() == null){
			movementOptions.add(board[i][j]);
			i--; j--;
		}
		
		i = row-1; j = column+1;
		while(i>=0 && j<=7 && board[i][j].getPiece() == null){
			movementOptions.add(board[i][j]);
			i--; j++;
		}
		
		i = row+1; j = column-1;
		while(i<=7 && j>=0 && board[i][j].getPiece() == null){
			movementOptions.add(board[i][j]);
			i++; j--;
		}
		
		i = row+1; j = column+1;
		while(i<=7 && j<=7 && board[i][j].getPiece() == null){
			movementOptions.add(board[i][j]);
			i++; j++;
		}
		
		
		System.out.println("bishooooop");
		return movementOptions;
	}
}

