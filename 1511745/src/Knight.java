import java.awt.Image;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Knight extends Piece {
	
	PieceColor color;
	
	Knight(PieceColor c){
		color = c;
		String imagem = "";
		
		if(color == PieceColor.BLACK){
			imagem = "p_cavalo.gif";
		}else if(color == PieceColor.WHITE){
			imagem = "b_cavalo.gif";
		}
		
		try {
			i=ImageIO.read(new File("Pecas/Pecas_1/"+imagem));
		}catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
}