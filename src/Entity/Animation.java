package Entity;

import java.awt.image.*;

public class Animation {
	private BufferedImage[] animFrame;
	private int currentFrame;
	
	private long time;
	private long delay;
	
	private boolean animPlayed;
	
	public Animation(){
		animPlayed = false;
	}
	
	public void setFrames(BufferedImage[] animFrame){
		this.animFrame = animFrame;
		currentFrame = 0;
		time = System.nanoTime();
		animPlayed = false;
	}
	
	public void setDelay(long d){
		delay = d;
	}
	
	public void setFrame(int f){
		currentFrame = f;
	}
	
	public void update(){
		if(delay == -1){
			return;
		}
		long elapsed = (System.nanoTime() - time) / 10000000;
		if(elapsed > delay){
			currentFrame++;
			time = System.nanoTime();
		}
		if(currentFrame == animFrame.length){
			currentFrame = 0;
			animPlayed = true;
		}
	}
	
	public int getFrame(){
		return currentFrame;
	}
	
	public BufferedImage getImage(){
		return animFrame[currentFrame];
	}
	
	public boolean animationPlayedOnce(){
		return animPlayed;
	}
}
