import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class Piece {
	
	protected Image i;
	
	public Image getImage(){
		return i;
	}

	public List<Tile> getMovementOptions(int row, int column) {
		List<Tile> movementOptions = new ArrayList<Tile>();
		
		return movementOptions;
	}
	
	public void moved(){
		
	}

}
