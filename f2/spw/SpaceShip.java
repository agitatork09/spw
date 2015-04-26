package f2.spw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpaceShip extends Sprite{

	int step = 8;
	Image picspace;
	Image picGuard;
	int maxHp = 5;
	int hp;
	boolean ss;
	public SpaceShip(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.ss = true;
		try {
			picspace = ImageIO.read(new File("f2/spw/picture/yel.png"));
			picGuard = ImageIO.read(new File("f2/spw/picture/blue.png"));
		}
		catch(IOException e){
			e.printStackTrace();
		}
		hp = maxHp;
	}
	public void decreaseHp(){
		hp--;
	}
	public int getHp(){
		return hp;
	}
	public void setSpaceShip(boolean ss){
		this.ss = ss;
	}
	public boolean getSpaceShip(){
		return ss;
	}
	@Override
	public void draw(Graphics2D g) {
		//g.setColor(Color.PINK);
		//g.fillRect(x, y, width, height);
		if(ss==true){
			g.drawImage(picspace,x,y,width,height,null);
		}
		else	
			g.drawImage(picGuard,x,y,width,height,null);
	}

	public void move(int directionX,int directionY){
		x += (step * directionX);
		if(x < 0)
			x = 0;
		if(x > 400 - width)
			x = 400 - width;

		y += (step * directionY);
		if(y < 0)
			y = 0;
		if(y > 650 - width)
			y = 650 - width;

	}

}
