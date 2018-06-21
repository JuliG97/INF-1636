import java.awt.Image;

public class Tile {
	
	private Piece piece;
	private Boolean selected = false;
	private Boolean highlighted = false;
	private int row;
	private int column;
	private Boolean roque = false;
	
	Tile(int row, int column){
		this.row = row;
		this.column = column;
		
	}
	
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
	
	public int getRow(){
		return row;
	}
	
	public int getColumn(){
		return column;
	}
	
	public void setRoque(Boolean b){
		roque = b;
	}
	
	public Boolean getRoque(){
		return roque;
	}
}
