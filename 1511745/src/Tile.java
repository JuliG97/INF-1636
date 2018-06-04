
public class Tile {
	
	private Piece piece;
	private Boolean selected = false;
	private Boolean highlighted = false;
	
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
	
	public void setHighlighted(Boolean b){
		highlighted = b;
	}
	
	public Boolean getHighlighted(){
		return highlighted;
	}
}
