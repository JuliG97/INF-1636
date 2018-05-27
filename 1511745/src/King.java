import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class King extends Piece{
	
	King(){
		try{
			i=ImageIO.read(new File("Pecas/Pecas_1/b_rei.gif"));
		}catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
}
