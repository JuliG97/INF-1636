import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class BoardPanel extends JPanel{
	
	Tile[][] board;
	int boardDimension;
	
	public BoardPanel(Tile[][] board, int boardDimension) { 
		this.board = board;
		this.boardDimension = boardDimension;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D) g;
		
		TileInterface ti;
		for(int i = 0; i<8; i++){
			for(int j = 0; j<8; j++){
				ti = new TileInterface(j*(boardDimension/8), i*(boardDimension/8), (boardDimension/8), (boardDimension/8));
				Rectangle2D tile = ti.getSquare();
				if((i+j) % 2 == 0){
					g2d.setPaint(Color.WHITE);
				}else{
					g2d.setPaint(Color.BLACK);
				}
				g2d.fill(tile);
				g2d.draw(tile);
			}
		}
	}
}
