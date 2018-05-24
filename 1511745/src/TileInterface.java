import java.awt.geom.Rectangle2D;

public class TileInterface {
	
	int x, y, l, h;
	
	public TileInterface(int x, int y, int l, int h){
		this.x = x;
		this.y = y;
		this.l = l;
		this.h = h;
	}
	
	public Rectangle2D getSquare(){
		return new Rectangle2D.Double(x,y,l,h);
	}

}
