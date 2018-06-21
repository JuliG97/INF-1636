
public class Main {
	
	public static void main(String[] args) {
		
		Board board = Board.getBoard();
		board.InitializeBoard();
		
		InterfaceFacade.getInterfaceFacade().initializeBoardFrame(board);
	}
}
