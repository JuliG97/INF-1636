import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Rook extends Piece{
	
	Rook(){
		try{
			i=ImageIO.read(new File("Pecas/Pecas_1/b_torre.gif"));
		}catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
}
