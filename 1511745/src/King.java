import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class King extends Piece{
	
	List<Tile> movementOptions;
	Tile[][] board;
	Piece pieceToMove;
	Piece temp;
	
	King(PieceColor c){
		color = c;
		String imagem = "";
		
		if(color == PieceColor.BLACK){
			imagem = "p_rei.gif";
		}else if(color == PieceColor.WHITE){
			imagem = "b_rei.gif";
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
		
		int i; int j; int y; int z;
		
		for(y = -1; y <= 1; y++){
			z = 1;
			i = row + z; j = column + y;
			addMovementOption(i, j, row, column);
			i = row - z;
			addMovementOption(i, j, row, column);
		}
		y=1;
		for(z = 0; z<=1; z++){
			i = row; j = column + y;
			addMovementOption(i, j, row, column);
			y=y*(-1);
		}
		
		i = row; j = column + 3; /////checking for the possibility of Roque move
		if(Board.getBoard().getChekState() != pieceToMove.getColor()){
			if(Board.getBoard().getPlayerTurn() == pieceToMove.getColor()){
				if(i>=0 && i<=7 && j>=0 && j<=7){
					if(board[i][j].getPiece() != null){
						if(pieceToMove.getMovedState() == false && board[i][j].getPiece().getMovedState() == false){
							board[i][j].setRoque(true);
							for(int x = j-1; x>column; x--){
								if(board[i][x].getPiece() != null){
									board[i][j].setRoque(false);
								}
							}
						}else{
							board[i][j].setRoque(false);
						}
					}
				}
			}
		}
		
	
	i = row; j = column - 4 ; /////checking for the possibility of Roque move
	if(Board.getBoard().getChekState() != pieceToMove.getColor()){
		if(Board.getBoard().getPlayerTurn() == pieceToMove.getColor()){
			if(i>=0 && i<=7 && j>=0 && j<=7){
				if(board[i][j].getPiece() != null){
					if(pieceToMove.getMovedState() == false && board[i][j].getPiece().getMovedState() == false){
						board[i][j].setRoque(true);
						for(int x = j+1; x<column; x++){
							if(board[i][x].getPiece() != null){
								board[i][j].setRoque(false);
							}
						}
					}else{
						board[i][j].setRoque(false);
					}
				}
			}
		}
	}
	
	return movementOptions;
}
	
	private void addMovementOption(int i, int j, int row, int column){
		
		if(i>=0 && i<=7 && j>=0 && j<=7){
				temp = board[i][j].getPiece();
				board[i][j].setPiece(new Pawn(pieceToMove.getColor()));
				board[row][column].setPiece(null);
				for(Tile[] t: board){ //if the movement will result in the king beeing in check then the movement is not possible
					for(Tile tile: t){
						Piece p = tile.getPiece();
						if(p != null){
							if(p.getColor() != pieceToMove.getColor()){
								if(p instanceof King){
									if(tile.getRow()<=i+1 && tile.getRow()>=i-1 && tile.getColumn()<=j+1 && tile.getColumn()>=j-1){
										board[i][j].setPiece(temp);
										board[row][column].setPiece(pieceToMove);
										return;
									}
								}
								List<Tile> optionsTemp = p.getMovementOptions(tile);
								for(Tile option: optionsTemp){
									if(option == board[i][j]){
										board[i][j].setPiece(temp);
										board[row][column].setPiece(pieceToMove);
										return;
									}
								}
							}
						}
					}
				}
			
			if(temp != null){
				if(temp.getColor() != pieceToMove.getColor()){
					movementOptions.add(board[i][j]);
				}
			}else{
				movementOptions.add(board[i][j]);
			}
			board[i][j].setPiece(temp);
			board[row][column].setPiece(pieceToMove);
		}
	}
}







