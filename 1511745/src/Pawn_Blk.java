import java.awt.Image;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Pawn_Blk extends Piece {
	
	Pawn_Blk(){
		try {
			i=ImageIO.read(new File("Pecas/Pecas_1/p_peao.gif"));
		}catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
}