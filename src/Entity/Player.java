package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;

import TileMap.TileMap;

public class Player extends MapObject{
	
	//Animâcijas
	private ArrayList<BufferedImage[]> sprites;
	private final int[] animFrames = {
			1, 1, 1, 1, 1,
	};
	
	private static final int IDLE = 0;
	private static final int RIGHT = 1;
	private static final int LEFT = 2;
	private static final int UP = 3;
	private static final int DOWN = 4;
	public Player(TileMap TM){
		super(TM);
		
		width = 20;
		height = 20;
		collisionWidth = 10;
		collisionHeight = 10;
		
		
		moveSpeed = maxSpeed  = 0.3;
		stopSpeed = 0;
		
		System.out.println(tileSize);
		//speletaja spraitu ielade
		try{
			BufferedImage spriteSheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/Player/spriteSheet2.png"));
			sprites = new ArrayList<BufferedImage[]>();
			for(int i = 0; i<5; i++){
				BufferedImage[] BI = new BufferedImage[animFrames[i]];
				for(int j = 0; j < animFrames[i]; j++){
					
					BI[j] = spriteSheet.getSubimage(j * width, i * height, width, height);
				}
				
				sprites.add(BI);
			}
			
		}catch(Exception E){
			E.printStackTrace();
		}
		
		Anim = new Animation();
		curAnim = IDLE;
		Anim.setFrames(sprites.get(IDLE));
		Anim.setDelay(50);
	}
	
	public void getNextPosition(){
		//Kustîba
		if(left){
			deltaX -= moveSpeed;
		}
		else if(right){
			deltaX += moveSpeed;

		}
		else if(up){
			deltaY -= moveSpeed;

		}
		else if(down){
			deltaY +=moveSpeed;

		}
		else{
			if(deltaX > 0){
				deltaX = 0;
			}
			else if(deltaX < 0){
				deltaX = 0;
			}
			else if(deltaY > 0){
				deltaY = 0;
			}
			else if(deltaY < 0){
				deltaY = 0;
			}
		}
	}
	
	public void update(){
		
		//pozicijas atjauninashana
		getNextPosition();
		checkTileMapCollision();
		setPosition(getTempX(), getTempY());
		
		//animaciju uzstadisana pec novietojuma
		if(right){
			if(curAnim != RIGHT){
				curAnim = RIGHT;
				Anim.setFrames(sprites.get(RIGHT));
				Anim.setDelay(20);
				width = 20;
			}
		}
		else if(left){
			if(curAnim != LEFT){
				curAnim = LEFT;
				Anim.setFrames(sprites.get(LEFT));
				Anim.setDelay(20);
				width = 20;
			}
		}
		else if(up){
			if(curAnim != UP){
				curAnim = UP;
				Anim.setFrames(sprites.get(UP));
				Anim.setDelay(20);
				width = 20;
			}
		}
		else if(down){
			if(curAnim != DOWN){
				curAnim = DOWN;
				Anim.setFrames(sprites.get(DOWN));
				Anim.setDelay(20);
				width = 20;
			}
		}
		else{
			if(curAnim != IDLE){
				curAnim = IDLE;
				Anim.setFrames(sprites.get(IDLE));
				Anim.setDelay(20);
				width = 20;
			}
		}
		Anim.update();
	}
	
	public void draw(Graphics2D G){
		setMapPosition();
		
		//Uzzîmçt spçlçtâju
		G.drawImage(Anim.getImage(),(int)(X + mapX), (int)(Y + mapY) , null);
	}
}
