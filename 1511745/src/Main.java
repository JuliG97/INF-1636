
public class Main {
	
	public static void main(String[] args) {
		
		Board board = Board.getBoard();
		board.InitializeBoard();
		
		BoardFrame bf = BoardFrame.getBoardFrame(board);
		bf.setTitle("Chess Game");
		bf.setVisible(true);
		
	}
}
