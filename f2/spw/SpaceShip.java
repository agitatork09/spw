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
	public void changeHp(int chp){
		hp += chp;
		if(hp>maxHp){
			hp = maxHp;
		}
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
	public void increaseSize(){
		width +=5;
		height +=5;
		step+=2;
	}
	public void resetSize(int width,int height){
		super.width = width;
		super.height = height;
		//System.out.println("super"+super.width+super.height);
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
		if(y < 30)
			y = 30;
		if(y > 584 - width)
			y = 584 - width;

	}

}
