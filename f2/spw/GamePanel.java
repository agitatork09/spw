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
		big.drawString(String.format("%08d", reporter.getScore()), 300, 20);
		for(Sprite s : sprites){
			s.draw(big);
		}
		big.setColor(Color.BLACK);
		big.drawString(String.format("Lv %02d", reporter.getLv()), 50, 20);
		//hp
		big.setColor(Color.RED);
		//increse hp
		//big.fillRect(v.width,v.height-5,(v.width/v.hp),5);
		big.fillRect(0,590,v.hp*80,10);
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);
	}

}
