package f2.spw;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class EnemyYellow extends Enemy implements Scoring{
	public static final int Y_TO_FADE = 400;
	public static final int Y_TO_DIE = 600;
	public static final int score = 200;

	public EnemyYellow(int x, int y) {
		super(x, y);
		try {
			picture = ImageIO.read(new File("f2/spw/picture/y.png"));
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public void draw(Graphics2D g) {
		//if(y < Y_TO_FADE)
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		/*else{
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
					(float)(Y_TO_DIE - y)/(Y_TO_DIE - Y_TO_FADE)));
		}*/
		//g.setColor(Color.YELLOW);
		//g.fillRect(x, y, width, height);
		g.drawImage(picture,x,y,width,height,null);
	}
	public int getScore(){
		return score;
	}

}