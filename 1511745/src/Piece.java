import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class Piece {
	
	PieceColor color;
	protected Image i;
	Boolean hasMoved = false;
	
	public Image getImage(){
		return i;
	}

	public List<Tile> getMovementOptions(Tile tile) {
		List<Tile> movementOptions = new ArrayList<Tile>();
		
		return movementOptions;
	}
	
	public void moved(){
		hasMoved = true;
	}
	
	public Boolean getMovedState(){
		return hasMoved;
	}
	
	public PieceColor getColor(){
		return color;
	}

}
