import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class BoardPanel extends JPanel{
	
	Tile[][] boardMatrix;
	int boardDimension;
	InterfaceFacade interfaceFacade;
	
	private static BoardPanel bp=null;
	
	private BoardPanel(Tile[][] boardMatrix, int boardDimension){
		this.boardMatrix = boardMatrix;
		this.boardDimension = boardDimension;
		
		this.setLayout(null);
		
		interfaceFacade = InterfaceFacade.getInterfaceFacade();
		
		PawnPromotionMenu.createPopUpMenu();
		
		JButton newGame = new JButton("NOVO JOGO");
		newGame.setBounds(95,280,200,80);
		
		JButton loadGame = new JButton("CONTINUAR JOGO");
		loadGame.setBounds(345,280,200,80);
		
		newGame.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				newGame.setVisible(false);
				loadGame.setVisible(false);
				interfaceFacade.resetGame();
			}
		});
		
		loadGame.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				newGame.setVisible(false);
				loadGame.setVisible(false);
				interfaceFacade.loadGame();
			}
		});
		
		this.add(newGame);
		this.add(loadGame);
		
	}
	
	public static BoardPanel getBoardPanel(Tile[][] boardMatrix, int boardDimension) { 
		if(bp == null){
			bp = new BoardPanel(boardMatrix, boardDimension);
		}
		return bp;
	}
	
	public static BoardPanel getBoardPanel() {
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
				if(interfaceFacade.getTileSelection(t) == true){ //the tile with the selected piece is painted yellow
					g2d.setPaint(Color.YELLOW);
				}else{
					if(interfaceFacade.getTileHighlighted(t) == true){
						g2d.setPaint(Color.GREEN);
						interfaceFacade.setTileHighlighted(t, false);
					}else{
						if((i+j) % 2 == 0){
							g2d.setPaint(Color.WHITE);
						}else{
							g2d.setPaint(Color.BLACK);
						}
					}
					
					if(interfaceFacade.getRoqueState(t) == true){
						g2d.setPaint(Color.BLUE);
					}
				}
				
				g2d.fill(tile);
				g2d.draw(tile);
				
				Piece p = interfaceFacade.getTilePiece(t);
				if(p != null){
					g.drawImage(interfaceFacade.getPieceImage(p),j*(boardDimension/8)+(boardDimension/32),i*(boardDimension/8)+(boardDimension/32),null);
				}
			}
		}
	}
}
