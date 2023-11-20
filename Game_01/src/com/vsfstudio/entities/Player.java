package com.vsfstudio.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.vsfstudio.main.Game;
import com.vsfstudio.world.Camera;
import com.vsfstudio.world.World;

public class Player extends Entity {
	
	public boolean right,left,up,down;
	public double speed = 0.03;
	public int right_dir = 0, left_dir = 1;
	public int dir = right_dir; 
	public int up_dir = 2, down_dir = 3;
	
	
	private int frames = 0, maxFrames = 200;
	private int  index = 0, maxIndex=3;
	private boolean moved = false;
	
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;
	private BufferedImage[] upPlayer;
	private BufferedImage[] downPlayer;
	
	
	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		
		rightPlayer = new BufferedImage[4];		
		leftPlayer = new BufferedImage[4];
		upPlayer = new BufferedImage[4];
		downPlayer = new BufferedImage[4];
		
		for(int i = 0; i < 4; i++) {
			rightPlayer[i] = Game.spritesheet.getSprite(0 + (i*16), 0, 16, 16);
			
		}
		for(int i = 0; i < 4; i++) {
			leftPlayer[i] = Game.spritesheet.getSprite(0 + (i*16), 16, 16, 16);
			
		}
		
		for(int i = 0; i < 4; i++) {
			downPlayer[i] = Game.spritesheet.getSprite(0 + (i*16), 32, 16, 16);
			
		}
		
		for(int i = 0; i<4; i++) {
			upPlayer[i] = Game.spritesheet.getSprite(0 + (i*16), 48, 16, 16);
		}
		
		
		
		
	}
	
	public void tick () {
		
		moved = false;
		
		if (right && World.isFree((int) (x+speed),this.getY())) {
			moved = true;
			x+=speed;
			dir = right_dir;
			
		}
		else if (left && World.isFree((int) (x-speed),this.getY())) {
			moved = true;
			x-=speed;
			dir = left_dir;
		}
		if (up && World.isFree(this.getX(),(int)(y-speed))) {
			moved = true;
			y-=speed;
			dir = up_dir;
		}
		else if (down && World.isFree(this.getX(),(int)(y+speed))) {
			moved = true;
			y+=speed;
			dir = down_dir;
		}
		
		if (moved) {
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex) 
					index = 0 ;
		}		
		}
		
		Camera.x = Camera.clamp(this.getX() - (Game.WIDTH/2),0,World.WIDTH*16 - Game.WIDTH);
		Camera.y = Camera.clamp(this.getY() - (Game.HEIGHT/2),0,World.HEIGHT*16 - Game.HEIGHT);
	}

	
	
	
	
	public void render(Graphics g) {
		
		if (dir == right_dir) {
			
			g.drawImage(rightPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		
		else if (dir == left_dir) {
			
			g.drawImage(leftPlayer[index], this.getX()- Camera.x, this.getY()- Camera.y, null);
		}
		
		if(dir == up_dir) {
			g.drawImage(upPlayer[index], this.getX()- Camera.x, this.getY()- Camera.y, null);
		}
		
		else if   (dir == down_dir) {
			g.drawImage(downPlayer[index], this.getX()- Camera.x, this.getY()- Camera.y, null);
		}
			
	}
}

