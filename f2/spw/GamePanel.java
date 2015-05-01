
package f2.spw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	
	private BufferedImage bi;	
	Graphics2D big;
	Image background;
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();

	public GamePanel() {
		bi = new BufferedImage(400, 600, BufferedImage.TYPE_INT_ARGB);
		big = (Graphics2D) bi.getGraphics();
		//big.setBackground(Color.BLACK);
		try {
			background = ImageIO.read(new File("f2/spw/picture/bg4.jpg"));  
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public void updateGameUI(GameReporter reporter,SpaceShip v){
		big.clearRect(0, 0, 400, 600);
		
		big.drawImage(background,0,0,null);
		
		//score
		big.setColor(Color.BLACK);		
		big.drawString(String.format("%05d", reporter.getScore()), 300, 20);
		for(Sprite s : sprites){
			s.draw(big);
		}
		big.setColor(Color.BLACK);
		big.drawString(String.format("Lv %02d", reporter.getLv()), 50, 20);


		//hp
		big.setColor(Color.RED);
		big.drawString(String.format("hp : %d/%d", v.hp,v.maxHp), 50, 583);

		big.setColor(Color.BLACK);
		big.fillRect(0,584,400,16);

		big.setColor(Color.RED);
		big.fillRect(5,587,78*v.hp,10);
		repaint();

	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);
	}

}
