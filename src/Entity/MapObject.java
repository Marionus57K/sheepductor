package Entity;

import java.awt.Rectangle;

import TileMap.Tile;
import TileMap.TileMap;

//Klase visiem objektiem (Players, Enemies)
public abstract class MapObject {
	protected int sheepsDestroyed;
	
	protected TileMap tileMap;
	protected int tileSize;
	protected double mapX;
	protected double mapY;
	
	
	protected double X;
	protected double Y;
	protected double deltaX;
	protected double deltaY;
	
	protected int width;
	protected int height;
	
	//Kolîzijas
	protected int collisionWidth;
	protected int collisionHeight;
	
	protected int currRow;
	protected int currColumn;
	
	
	
	//Koordinâtas, uz kurâm doties
	protected double destinationX;
	protected double destinationY;
	
	protected double tempX;
	protected double tempY;
	
	protected boolean topLeft, topRight, botLeft, botRight;
	
	//Animâcijas
	protected Animation Anim;
	protected int curAnim;
	protected int prevAnim;
	protected boolean faceLeft, faceRight, faceUp, faceDown;
	
	//Kustîba
	protected boolean left, right, up, down;
	protected double moveSpeed;
	protected double maxSpeed;
	protected double stopSpeed;
	
	
	public MapObject(TileMap TM){
		tileMap = TM;
		tileSize = TM.getTileSize();
	}
	
	public boolean checkCollision(MapObject MO){
		Rectangle R1 = getRectangle();
		Rectangle R2 = MO.getRectangle();
		return R1.intersects(R2);
	}
	
	public Rectangle getRectangle(){
		return new Rectangle((int)X - collisionWidth, (int)Y - collisionHeight, collisionWidth, collisionHeight);
	}
	
	//Koliiziju apreekinaasana
	public void calculateCorners(double X, double Y){
		int leftTile = (int)(X - collisionWidth / 2) / tileSize;
		int rightTile = (int)(X + collisionWidth / 2 - 1) / tileSize;
		int topTile = (int)(Y - collisionHeight / 2) / tileSize;
		int botTile = (int)(Y + collisionHeight / 2 - 1) / tileSize;
		  if(topTile < 0 || botTile >= tileMap.getNumRows() ||
	                leftTile < 0 || rightTile >= tileMap.getNumCols()) {
	                topLeft = topRight = botLeft = botRight = false;
	                return;
	        }

		
		int tl = tileMap.getType(topTile, leftTile);
		int tr = tileMap.getType(topTile, rightTile);
		int bl = tileMap.getType(botTile, leftTile);
		int br = tileMap.getType(botTile, rightTile);
		
		topLeft = tl == Tile.BLOCKED;
		topRight = tr == Tile.BLOCKED;
		botLeft = bl == Tile.BLOCKED;
		botRight = br == Tile.BLOCKED;
	}
	
	//Pârbauda, kâdâ tile`â esam iegâjuði (BLOCKED/NORMAL)
	public void checkTileMapCollision(){
		currColumn = (int)X / tileSize;
		currRow = (int)Y / tileSize;
		
		destinationX = X + deltaY;
		destinationY = Y + deltaY;
		
		tempX = X;
		tempY = Y;
		
		calculateCorners(X, destinationY);
		if(deltaY<0){
			if(topLeft || topRight){
				deltaY = 0;
				tempY = currRow * tileSize + collisionHeight / 2;
			}
			else{
				tempY += deltaY;
			}
		}
		if(deltaY>0){
			if(botLeft || botRight){
				deltaY = 0;
				tempY = (currRow + 1) * tileSize - collisionHeight / 2;
			}
			else{
				tempY += deltaY;;
			}
		}
		
		calculateCorners(destinationX, Y);
			if(deltaX < 0){
				if(topLeft || botLeft){
					deltaX = 0;
					tempX = currColumn * tileSize + collisionWidth / 2;
				}
				else{
					tempX += deltaX;
				}
			}
			if(deltaX > 0){
				if(topRight || botRight){
					deltaX = 0;
					tempX = (currColumn + 1) * tileSize - collisionWidth / 2;
				}
				else{
					tempX += deltaX;;
				}
			}
	}
	
	public int getX(){
		return (int)X;
	}
	
	public int getY(){
		return (int)Y;
	}
	
	public int getHeight(){
		return height;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getCollisionHeight(){
		return collisionHeight;
	}
	
	public int getCollisionWidth(){
		return collisionWidth;
	}
	
	public void setPosition (double X, double Y){
		this.X = X;
		this.Y = Y;
	}
	
	public void setVector(double deltaX, double deltaY){
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}
	
	public void setMapPosition(){
		mapX = tileMap.getx();
		mapY = tileMap.gety();
	}
	
	public double getMapX(){
		return (int)mapX;
	}
	
	public double getMapY(){
		return (int)mapY;
	}
	
	public void setLeft(boolean B){
		left = B;
	}
	
	public void setRight(boolean B){
		right = B;
	}
	
	public void setUp(boolean B){
		up = B;
	}
	
	public void setDown(boolean B){
		down = B;
	}

	public double getTempX() {
		return tempX;
	}

	public void setTempX(double tempX) {
		this.tempX = tempX;
	}

	public double getTempY() {
		return tempY;
	}

	public void setTempY(double tempY) {
		this.tempY = tempY;
	}
}
