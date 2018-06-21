import java.awt.Image;
import java.util.List;
import java.util.Observer;

public class InterfaceFacade {
	
	private static InterfaceFacade interfaceFacade = null;
	private static BoardFrame boardFrame = null;
	
	private InterfaceFacade(){
		
	}
	
	public static InterfaceFacade getInterfaceFacade(){
		if(interfaceFacade == null){
			interfaceFacade = new InterfaceFacade();
		}
		return interfaceFacade;
	}
	
	public void initializeBoardFrame(Board b){
		boardFrame = BoardFrame.getBoardFrame(b);
	}
	
	public PieceColor getPlayerTurn(){
		return Board.getBoard().getPlayerTurn();
	}
	
	public void showPawnPromotionMenu(Tile t){
		PawnPromotionMenu.showMenu(t, boardFrame.getBoardDimension());
	}
	
	public Tile getTile(int row, int column){
		Tile[][] boardMatrix =  Board.getBoard().getBoardMatrix();
		return boardMatrix[row][column];
	}
	
	public Piece getTilePiece(Tile t){
		return t.getPiece(); 
	}
	
	public PieceColor getPieceColor(Piece p){
		if(p != null){
			return p.getColor();
		}
		return null;
	}
	
	public List<Tile> highlightMoveOptions(int row, int column){
		return Board.getBoard().highlightMovemetOptions(row, column);
	}
	
	public Tile[][] getBoardMatrix(){
		return Board.getBoard().getBoardMatrix();
	}
	
	public void updateBoardPieceLocation(Tile t1, Tile t2){
		Board.getBoard().updatePieceLocation(t1, t2);
	}
	
	public void Roque(Tile t1, Tile t2){
		Board.getBoard().Roque(t1, t2);
	}
	
	public void setTileSelection(Tile t, Boolean b){
		t.setSelected(b);
	}
	
	public Boolean getTileSelection(Tile t){
		return t.getSelected();
	}
	
	public void setTileHighlighted(Tile t, Boolean b){
		t.setHighlighted(b);
	}
	
	public Boolean getTileHighlighted(Tile t){
		return t.getHighlighted();
	}
	
	public void setRoqueState(Tile t, Boolean b){
		t.setRoque(b);
	}
	
	public Boolean getRoqueState(Tile t){
		return t.getRoque();
	}
	
	public Image getPieceImage(Piece p){
		return p.getImage();
	}
	
	public void addBoardObserver(Observer o){
		Board.getBoard().addObserver(o);
	}
	
	public void dataChanged(){
		Board.getBoard().dataChanged();
	}
	
	public int getGameOverState(){
		return Board.getBoard().getGameOverState();
	}
	
	public void resetGame(){
		Board.getBoard().resetGame();
	}

}
