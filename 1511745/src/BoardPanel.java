import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class BoardPanel extends JPanel{
	
	Tile[][] boardMatrix;
	int boardDimension;
	
	private static BoardPanel bp=null;
	JPopupMenu pop;
	
	private BoardPanel(Tile[][] boardMatrix, int boardDimension){
		this.boardMatrix = boardMatrix;
		this.boardDimension = boardDimension;
		
	}
	
	public static BoardPanel getBoardPanel(Tile[][] boardMatrix, int boardDimension) { 
		if(bp == null){
			bp = new BoardPanel(boardMatrix, boardDimension);
		}
		return bp;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D) g;
		
		TileInterface ti;
		for(int i = 0; i<8; i++){
			for(int j = 0; j<8; j++){
				ti = new TileInterface(j*(boardDimension/8), i*(boardDimension/8), (boardDimension/8), (boardDimension/8));
				Rectangle2D tile = ti.getSquare();
				Tile t = boardMatrix[i][j];
				if(boardMatrix[i][j].getSelected() == true){
					g2d.setPaint(Color.YELLOW);
				}else{
					if(boardMatrix[i][j].getHighlighted() == true){
						g2d.setPaint(Color.GREEN);
						boardMatrix[i][j].setHighlighted(false);
					}else{
						if((i+j) % 2 == 0){
							g2d.setPaint(Color.WHITE);
						}else{
							g2d.setPaint(Color.BLACK);
						}
					}
				}
				g2d.fill(tile);
				g2d.draw(tile);
				
				if(boardMatrix[i][j].getPiece() != null){
					g.drawImage(boardMatrix[i][j].getPiece().getImage(),j*(boardDimension/8)+(boardDimension/32),i*(boardDimension/8)+(boardDimension/32),null);
				}
			}
		}
	}
}
