import java.awt.Image;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Knight extends Piece {
	
	Knight(){
		try {
			i=ImageIO.read(new File("Pecas/Pecas_1/b_cavalo.gif"));
		}catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
}