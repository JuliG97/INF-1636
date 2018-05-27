
public class Tile {
	
	private Piece piece;
	private Boolean selected = false;
	
	public void setPiece(Piece p){
		piece = p;
	}
	
	public Piece getPiece(){
		return piece;
	}
	
	public void setSelected(Boolean b){
		selected = b;
	}
	
	public Boolean getSelected(){
		return selected;
	}
	
	
}
