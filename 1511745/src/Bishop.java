import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bishop extends Piece{
	
	Bishop(){
		try{
			i=ImageIO.read(new File("Pecas/Pecas_1/b_bispo.gif"));
		}catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
}

