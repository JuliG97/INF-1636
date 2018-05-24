
public class Main {
	
	public static void main(String[] args) {
		
		Board.InitializeBoard();
		BoardFrame board = new BoardFrame(Board.getBoard());
		board.setTitle("Chess Game");
		
		board.setVisible(true);
		
	}
}
