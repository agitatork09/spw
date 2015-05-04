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
	private ArrayList<Item> items = new ArrayList<Item>();
	private SpaceShip v;	
	
	private Timer timer;
	
	private long score = 0;
	private int lvOld = 0;
	private int lvNew = 0;
	private double difficulty = 0.15;
	private double difficultyItem = 0.01;
	private int timeitem;
	
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
	
	//itemPink
	private void generateItemPink(){
		ItemPink it = new ItemPink((int)(Math.random()*390), 30);
		gp.sprites.add(it);
		items.add(it);
	}
	//itemBrown
	private void generateItemBrown(){
		ItemBrown it = new ItemBrown((int)(Math.random()*390), 30);
		gp.sprites.add(it);
		items.add(it);
	}

	private void process(){
		//enemyRed random
		if(Math.random() < difficulty){
			generateEnemyRed();
		}
		//enemyYellow random
		if(Math.random() < difficultyItem){
			generateEnemyYellow();	
		}
		//enemyBlue random
		if(Math.random() < 0.08){
			generateEnemyBlue();	
		}
		//enemyGreen random
		if(Math.random() < difficulty){
			generateEnemyGreen();	
		}
		//itemPink random
		if(Math.random() < difficultyItem){
			generateItemPink();	
		}
		//itemBrown random
		if(Math.random() < 0.005){
			generateItemBrown();	
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
		//Item
		Iterator<Item> it_iter = items.iterator();
		while(it_iter.hasNext()){
			Item it = it_iter.next();
			it.proceed();
			
			if(!it.isAlive()){
				it_iter.remove();
				gp.sprites.remove(it);
			}
		}
		
		gp.updateGameUI(this,v);
		//Enemy
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er;
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr)){
				if(!(e instanceof Scoring)){
					if(v.getSpaceShip()==true){
						v.changeHp(-1);
					}
					e.setAlive();
					if(v.getHp()==0){
						die();
						//gp.updateGameUI(this,v);
						return;
					}
				}
				else {
					score += ((Scoring)(e)).getScore();
					lvNew = (int)(score/2000);
					e.setAlive();
				}
			}
		}
		processItem();
		if(lvOld!=lvNew){
			v.increaseSize();
			lvOld = lvNew;
		}
		//Item
		Rectangle2D.Double ir;
		for(Item i : items){
			ir = i.getRectangle();
			if(ir.intersects(vr)){
				if(i instanceof ItemBrown){
					v.setSpaceShip(false);
					timeitem = 10000;
				}
				else if(i instanceof ItemPink){
					v.changeHp(1);
				}
				i.setAlive();
			}
		}
	}
	private void processItem(){
		if(timeitem>0){
			timeitem-=50;
			if(timeitem==0){
				v.setSpaceShip(true);
			}
		}
	}
	public void die(){
		
		gp.gameOver(this);
		new ShowScore(this);
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
		return lvOld;
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
