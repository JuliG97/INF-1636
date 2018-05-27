import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bishop_Blk extends Piece{
	
	Bishop_Blk(){
		try{
			i=ImageIO.read(new File("Pecas/Pecas_1/p_bispo.gif"));
		}catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
}

