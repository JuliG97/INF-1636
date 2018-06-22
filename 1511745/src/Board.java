import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Board extends Observable{
	
	private static Board board = null;
	public static Tile[][] boardMatrix = new Tile [8][8];
	InterfaceFacade interfaceFacade;
	private PieceColor kingIsChecked = null;
	private PieceColor playerTurn = PieceColor.WHITE;
	private int gameOverState = -1;
	
	private Board(){
		interfaceFacade = InterfaceFacade.getInterfaceFacade();
	}
	
	public static Board getBoard(){
		if(board == null){
			board = new Board();
		}
		return board;
	}
	
	public void InitializeBoard(){  //initialize the pieces in the correct positons
		
		for(int i = 0; i<8; i++){
			for(int j = 0; j<8; j++){
				boardMatrix[i][j] = new Tile(i, j);
			}
		}
		
		boardMatrix[7][0].setPiece(new Rook(PieceColor.WHITE));
		boardMatrix[7][7].setPiece(new Rook(PieceColor.WHITE));
		boardMatrix[7][1].setPiece(new Knight(PieceColor.WHITE));
		boardMatrix[7][6].setPiece(new Knight(PieceColor.WHITE));
		boardMatrix[7][2].setPiece(new Bishop(PieceColor.WHITE));
		boardMatrix[7][5].setPiece(new Bishop(PieceColor.WHITE));
		boardMatrix[7][3].setPiece(new Queen(PieceColor.WHITE));
		boardMatrix[7][4].setPiece(new King(PieceColor.WHITE));
		for(int j = 0; j<8; j++) {
			boardMatrix[6][j].setPiece(new Pawn(PieceColor.WHITE));
			boardMatrix[1][j].setPiece(new Pawn(PieceColor.BLACK));
		}
		boardMatrix[0][0].setPiece(new Rook(PieceColor.BLACK));
		boardMatrix[0][7].setPiece(new Rook(PieceColor.BLACK));
		boardMatrix[0][1].setPiece(new Knight(PieceColor.BLACK));
		boardMatrix[0][6].setPiece(new Knight(PieceColor.BLACK));
		boardMatrix[0][2].setPiece(new Bishop(PieceColor.BLACK));
		boardMatrix[0][5].setPiece(new Bishop(PieceColor.BLACK));
		boardMatrix[0][3].setPiece(new Queen(PieceColor.BLACK));
		boardMatrix[0][4].setPiece(new King(PieceColor.BLACK));
	}
	
	public Tile[][] getBoardMatrix(){
		return boardMatrix;
	}
	
	public void updatePieceLocation(Tile t1, Tile t2){ //put the piece on tile t1 in tile t2
		
		Piece p = t1.getPiece();
		p.moved();
		
		if(p instanceof Pawn){ //check if a pawn got to the other side of the board
			for(int i = 0; i<8; i++){
				if(t2 == boardMatrix[0][i] ||t2 == boardMatrix[7][i]){
					interfaceFacade.showPawnPromotionMenu(t2);
				}
			}
		}
		
		t2.setPiece(p);
		t1.setPiece(null);
		
		if(pieceThreatensKing(t2) == true){ //check if the movement resulted in a check
			if(p.getColor() == PieceColor.WHITE){
				kingIsChecked = PieceColor.BLACK;
			}else{
				kingIsChecked = PieceColor.WHITE;
			}
		}
		
		nextPlayerTurn();
		
		if(checkGameOver() == true){
			if(kingIsChecked == null){
				gameOverState = 1;
			}else{
				if(kingIsChecked == PieceColor.BLACK){
					gameOverState = 2;
				}else{
					gameOverState = 3;
				}
			}
		}
		
		if(kingIsChecked != null){
			kingIsChecked = null;
		}
	}
	
	public void Roque(Tile t1, Tile t2){
		if(t2.getColumn() == 7){
			updatePieceLocation(t1, boardMatrix[t1.getRow()][6]);
			updatePieceLocation(t2, boardMatrix[t1.getRow()][5]);
		}
		
		if(t2.getColumn() == 0){
			updatePieceLocation(t1, boardMatrix[t1.getRow()][2]);
			updatePieceLocation(t2, boardMatrix[t1.getRow()][3]);
		}
		nextPlayerTurn();
	}

	public List<Tile> highlightMovemetOptions(int row, int column) {
		
		List<Tile> movementOptions;
		
		Piece p = boardMatrix[row][column].getPiece();
		movementOptions = p.getMovementOptions(boardMatrix[row][column]);
		
		for(Tile t: movementOptions){
			t.setHighlighted(true);
		}
		
		return movementOptions;
	}
	
	public Boolean pieceThreatensKing(Tile t){
		List<Tile> movementOptions;
		
		if(t.getPiece() != null){
			movementOptions = t.getPiece().getMovementOptions(t);
			for(Tile tile: movementOptions){
				if(tile.getPiece() != null){
					if(tile.getPiece() instanceof King){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public PieceColor getChekState(){
		return kingIsChecked;
	}
	
	public void setChekState(PieceColor pc){
		kingIsChecked = pc;
	}
	
	public PieceColor getPlayerTurn(){
		return playerTurn;
	}
	
	public void nextPlayerTurn(){
		if(playerTurn == PieceColor.WHITE){
			playerTurn = PieceColor.BLACK;
		}else{
			playerTurn = PieceColor.WHITE;
		}
	}
	
	public void dataChanged(){
		setChanged();
		notifyObservers();
	}
	
	private Boolean checkGameOver(){
		List<Tile> movementOptions;
			
		for(Tile[] t: boardMatrix){
			for(Tile tile: t){
				if(tile.getPiece() != null && !(tile.getPiece() instanceof King)){
					if(tile.getPiece().getColor() == playerTurn){
						movementOptions = tile.getPiece().getMovementOptions(tile);
						if( !(movementOptions.isEmpty()) ){
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	public int getGameOverState(){
		return gameOverState;
	}
	
	public void resetGame(){
		InitializeBoard();
		gameOverState = 0;
		playerTurn = PieceColor.WHITE;
		kingIsChecked = null;
		
		dataChanged();
	}
	
	public void saveGame(){
		try {
    		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
    		System.out.println(timeStamp);
    		
    		String file_path = "SavedData/" + timeStamp + ".txt";
    		
    		//File arquivo = new File(file_path);
    		FileWriter file = new FileWriter(file_path);
            BufferedWriter buffer = new BufferedWriter(file);
            
            Piece piece;
            for(int i = 0; i<=7; i++){
            	for(int j = 0; j<=7; j++){
            		piece = boardMatrix[i][j].getPiece();
            		
            		if(piece != null){
            			if(piece.getColor() == PieceColor.WHITE){
            				buffer.write("W");
            			}else{
            				buffer.write("B");
            			}
            			
            			if(piece instanceof King){
            				buffer.write("K");
            			}else if(piece instanceof Queen){
            				buffer.write("Q");
            			}else if(piece instanceof Bishop){
            				buffer.write("B");
            			}else if(piece instanceof Pawn){
            				buffer.write("P");
            			}else if(piece instanceof Rook){
            				buffer.write("R");
            			}else if(piece instanceof Knight){
            				buffer.write("H");
            			}
            			
            			if(piece.getMovedState() == true){
            				buffer.write("1");
            			}else{
            				buffer.write("0");
            			}
            		}else{
            			buffer.write("00");
            		}
            		buffer.newLine();
            	}
            }
            
            if(playerTurn == PieceColor.WHITE){
            	buffer.write("0");
            }else{
            	buffer.write("1");
            }
            
            buffer.close();
            
            System.out.println("Game saved!!! :)");
        
		}catch(IOException ex) {
            ex.printStackTrace();
        }
	}
	
	public void loadGame(){
		JFileChooser chooser = new JFileChooser();
		String fileName = "";
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
	    chooser.setFileFilter(filter);
	    chooser.setCurrentDirectory(new java.io.File("./SavedData"));
	    int returnVal = chooser.showOpenDialog(null);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	      fileName = chooser.getSelectedFile().getName();
	    }
	    
	    try {
	    	BufferedReader br = new BufferedReader(new FileReader("SavedData/" + fileName));
	    	String piece = "";
	    	PieceColor color = null;
	    	
	    	for(int i = 0; i<=7; i++){
            	for(int j = 0; j<=7; j++){
            		piece = br.readLine();
            		if(piece.charAt(0) != '0'){
            			if(piece.charAt(0) == 'W'){
            				color = PieceColor.WHITE;
            			}else if(piece.charAt(0) == 'B'){
            				color = PieceColor.BLACK;
            			}
            			
            			if(piece.charAt(1) == 'K'){
            				boardMatrix[i][j].setPiece(new King(color));
            			}else if(piece.charAt(1) == 'Q'){
            				boardMatrix[i][j].setPiece(new Queen(color));
            			}else if(piece.charAt(1) == 'B'){
            				boardMatrix[i][j].setPiece(new Bishop(color));
            			}else if(piece.charAt(1) == 'P'){
            				boardMatrix[i][j].setPiece(new Pawn(color));
            			}else if(piece.charAt(1) == 'R'){
            				boardMatrix[i][j].setPiece(new Rook(color));
            			}else if(piece.charAt(1) == 'H'){
            				boardMatrix[i][j].setPiece(new Knight(color));
            			}
            			
            			if(piece.charAt(2) == '1'){
            				boardMatrix[i][j].getPiece().moved();
            			}
            		}else{
            			boardMatrix[i][j].setPiece(null);
            		}
            	}
	    	}
	    	
	    	if(br.readLine().charAt(0) == '0'){
	    		playerTurn = PieceColor.WHITE;
	    	}else{
	    		playerTurn = PieceColor.BLACK;
	    	}
	    	
	    	br.close();
	    	
	    	gameOverState = 0;
			dataChanged();
			
	    } catch (IOException e) {
    		e.printStackTrace();
    	}
	}
}
