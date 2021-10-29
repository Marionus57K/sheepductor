package GameState;

import TileMap.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

import Entity.*;
import Entity.PickUps.Sheep;
import Main.GamePanel;

public class WorldMapState extends GameState {
	Timer k = new Timer(0, null);
	private TileMap tileMap;
	private Player player;
	private ArrayList<PickUp> sheep;
	private int HighScore;
	private Random r;
	
	Graphics2D g;
	
	public WorldMapState(GameStateManager gsm) {
		this.gsm = gsm;	
		init();
	}
	
	public void init() {
		tileMap = new TileMap(20);
		tileMap.loadTiles("/Tilesets/tileSet.png");
		tileMap.loadMap("/Maps/tileMap.map");
		tileMap.setPosition(0, 0);
		
		player = new Player(tileMap);
		player.setPosition(100, 100);
		
		Sheep s1;
		sheep = new ArrayList<PickUp>();
		s1 = new Sheep(tileMap);
		s1.setPosition(500, 325);
		sheep.add(s1);
		
		Sheep s2;
		s2 = new Sheep(tileMap);
		s2.setPosition(100, 200);
		sheep.add(s2);
		
		Sheep s3;
		s3 = new Sheep(tileMap);
		s3.setPosition(100, 325);
		sheep.add(s3);
		
		Sheep s4;
		s4 = new Sheep(tileMap);
		s4.setPosition(320, 400);
		sheep.add(s4);
		
		Sheep s5;
		s5 = new Sheep(tileMap);
		s5.setPosition(100, 500);
		sheep.add(s5);
		
		Sheep s6;
		s6 = new Sheep(tileMap);
		s6.setPosition(350, 475);
		sheep.add(s6);
		
		Sheep s7;
		s7 = new Sheep(tileMap);
		s7.setPosition(420, 250);
		sheep.add(s7);
		
		Sheep s8;
		s8 = new Sheep(tileMap);
		s8.setPosition(356, 150);
		sheep.add(s8);
		
		Sheep s9;
		s9 = new Sheep(tileMap);
		s9.setPosition(140, 300);
		sheep.add(s9);
		
		Sheep s10;
		s10 = new Sheep(tileMap);
		s10.setPosition(400, 100);
		sheep.add(s10);

	}
	
	
	public void update() {
		player.update();
		tileMap.setPosition(GamePanel.WIDTH / 2 - player.getX(), GamePanel.HEIGHT / 2 - player.getY());
		for(int i = 0; i < sheep.size(); i++){
			sheep.get(i).update();
			if((player.getX() >= sheep.get(i).getX() -10 && player.getX() <= sheep.get(i).getX() +10) && (player.getY() >= sheep.get(i).getY() - 10 && player.getY() <= sheep.get(i).getY() + 10)){
				sheep.remove(i);
				HighScore++;
				String drawHS = "Atlikuðas: " + HighScore + " aitas";
				System.out.println(HighScore);
				i = 0;
			}
			}

	}
	
	public void draw(Graphics2D g) {
	
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		//Uzzîmçt ielâdçtos kartes laukus
		tileMap.draw(g);
		
		//Uzzîmet spçlçtâja attçlu
		player.draw(g);
		g.drawString("Atlikuðas: " + sheep.size() + " aitas", 550, 30);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		//uzzîmçt aitas
		for(int i = 0; i < sheep.size(); i++){
			sheep.get(i).draw(g);
		}
		
		if(sheep.size() == 0){
			g.clearRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
			g.setBackground(Color.WHITE);
			g.drawString("DEMO beidzies!", 250, 250);
			g.drawString("Visas aitas ir savâktas!", 250, 300);
			
			g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

			
		}
			
	}
	
	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_LEFT){
			player.setLeft(true);
		}
		if(k == KeyEvent.VK_RIGHT){
			player.setRight(true);
		}
		if(k == KeyEvent.VK_UP){
			player.setUp(true);
		}
		if(k == KeyEvent.VK_DOWN){
			player.setDown(true);
		}
	}
	
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_LEFT){
			player.setLeft(false);
		}
		if(k == KeyEvent.VK_RIGHT){
			player.setRight(false);
		}
		if(k == KeyEvent.VK_UP){
			player.setUp(false);
		}
		if(k == KeyEvent.VK_DOWN){
			player.setDown(false);
		}

	}
	
}












