package f2.spw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;


public class GameEngine implements KeyListener, GameReporter{
	GamePanel gp;
		
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();	
	private SpaceShip v;	
	
	private Timer timer;
	
	private long score = 0;
	private int lv = 0;
	private double difficulty = 0.1;
	
	public GameEngine(GamePanel gp, SpaceShip v) {
		this.gp = gp;
		this.v = v;		
		
		gp.sprites.add(v);
		
		timer = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				process();
			}
		});
		timer.setRepeats(true);
		
	}
	
	public void start(){
		timer.start();
	}
	//enemy 
	/*private void generateEnemy(){
		Enemy e = new Enemy((int)(Math.random()*390), 30);
		gp.sprites.add(e);
		enemies.add(e);
	}*/

	//enemyRed
	private void generateEnemyRed(){
		EnemyRed e = new EnemyRed((int)(Math.random()*390), 30);
		gp.sprites.add(e);
		enemies.add(e);
	}
	//enemyYellow
	private void generateEnemyYellow(){	
		EnemyYellow e = new EnemyYellow((int)(Math.random()*390), 30);
		gp.sprites.add(e);
		enemies.add(e);
	}
	//enemyBlue
	private void generateEnemyBlue(){
		EnemyBlue e = new EnemyBlue((int)(Math.random()*390), 30);
		gp.sprites.add(e);
		enemies.add(e);
	}
	//enemyGreen
	private void generateEnemyGreen(){
		EnemyGreen e = new EnemyGreen((int)(Math.random()*390), 30);
		gp.sprites.add(e);
		enemies.add(e);
	}
	
	
	private void process(){
		//enemyRed random
		if(Math.random() < difficulty){
			generateEnemyRed();
		}
		//enemyYellow random
		if(Math.random() < difficulty){
			generateEnemyYellow();	
		}
		//enemyBlue random
		if(Math.random() < difficulty){
			generateEnemyBlue();	
		}
		//enemyGreen random
		if(Math.random() < difficulty){
			generateEnemyGreen();	
		}

		Iterator<Enemy> e_iter = enemies.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();
			
			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
				//score += 100;
			}
		}
		
		gp.updateGameUI(this,v);

		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er;
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr)){
				if(e.checkDamage()){
					v.decreaseHp();
					e.setAlive();
					if(v.getHp()==0){
						die();
						gp.updateGameUI(this,v);
						return;
					}
				}
				else {
					score += 100;
					if((score%1000)==0){
						lv++;
					}
					e.setAlive();
				}
			}
		}
	}
	
	public void die(){
		timer.stop();
	}
	
	void controlVehicle(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			v.move(-1,0);
			break;
		case KeyEvent.VK_RIGHT:
			v.move(1,0);
			break;
		case KeyEvent.VK_UP:
			v.move(0,-1);
			break;
		case KeyEvent.VK_DOWN:
			v.move(0,1);
			break;
		case KeyEvent.VK_D:
			difficulty += 0.1;
			break;
		}
	}

	public long getScore(){
		return score;
	}

	public int getLv(){
		return lv;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}
}
