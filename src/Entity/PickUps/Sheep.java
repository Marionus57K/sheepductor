package Entity.PickUps;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import Entity.Animation;
import Entity.PickUp;
import TileMap.TileMap;

public class Sheep extends PickUp {
	
	private BufferedImage[] sheeps;
	
	public Sheep(TileMap TM){
		super(TM);	
		
	
	width = 20;
	height = 20;
	collisionWidth = 60;
	collisionHeight = 60;
	
	//spraita ielâde
	try{
		BufferedImage spriteSheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/Player/spriteSheep.png"));
		sheeps = new BufferedImage[1];
		for(int i = 0; i<sheeps.length; i++){
			sheeps[i] = spriteSheet.getSubimage(i * width, 0, width, height);

		}
	}catch(Exception E){
		E.printStackTrace();
	}
	
	Anim = new Animation();
	Anim.setFrames(sheeps);
	Anim.setDelay(100);
	}
	
	private void getNextPosition(){
		
	}
	//poziicijas uzstaadiishana
	public void update(){
		getNextPosition();
		checkTileMapCollision();
		setPosition(tempX, tempY);
	}
	
	
	public void draw(Graphics2D g){
		setMapPosition();
		g.drawImage(Anim.getImage(),(int)(X + mapX), (int)(Y + mapY), width, height, null);
		
	}
}



