import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class PawnPromotionMenu {
	
	static JPopupMenu pop;
	static Tile pawnToPromoteTile;
	Piece piecePromotion = null;
	
	public static void createPopUpMenu(){
		pop = new JPopupMenu();
		pop.addPopupMenuListener(new PopupMenuListener(){
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {}
			public void popupMenuCanceled(PopupMenuEvent e) {
				pawnToPromoteTile.setPiece(new Queen(pawnToPromoteTile.getPiece().getColor()));
				BoardPanel.getBoardPanel().repaint();
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {}
		});
		
		JMenuItem queenItem = new JMenuItem("Queen");
		queenItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				pawnToPromoteTile.setPiece(new Queen(pawnToPromoteTile.getPiece().getColor()));
				BoardPanel.getBoardPanel().repaint();
			}
		});
		JMenuItem rookItem = new JMenuItem("Rook");
		rookItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				pawnToPromoteTile.setPiece(new Rook(pawnToPromoteTile.getPiece().getColor()));
				BoardPanel.getBoardPanel().repaint();
			}
		});
		JMenuItem bishopItem = new JMenuItem("Bishop");
		bishopItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				pawnToPromoteTile.setPiece(new Bishop(pawnToPromoteTile.getPiece().getColor()));
				BoardPanel.getBoardPanel().repaint();
			}
		});
		JMenuItem knightItem = new JMenuItem("Knight");
		knightItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				pawnToPromoteTile.setPiece(new Knight(pawnToPromoteTile.getPiece().getColor()));
				BoardPanel.getBoardPanel().repaint();
			}
		});
		
		pop.add(queenItem);
		pop.add(rookItem);
		pop.add(bishopItem);
		pop.add(knightItem);
	}
	
	public static void showMenu(Tile t, int boardDimension){
		pawnToPromoteTile = t;
		pop.show(BoardPanel.getBoardPanel(), boardDimension/2 - 40, boardDimension/2 - 10);
	}

}
