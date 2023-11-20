package com.vsfstudio.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.vsfstudio.main.Game;
import com.vsfstudio.world.Camera;
import com.vsfstudio.world.World;






public class Enemy extends Entity{

	private double speed = 0.02;
	
	private int frames = 0, maxFrames = 5000;
	private int  index = 0, maxIndex=1;
	private boolean moved = false;
	
	
	private BufferedImage[] sprites;
	
	Player p = Game.player;
	
	public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, null);
		sprites = new BufferedImage[2];
		sprites[0] = Game.spritesheet.getSprite(0, 208, 16, 16);
		sprites[1] = Game.spritesheet.getSprite(16, 208, 16, 16);
	}
	
	
	
	public void tick () {
		
		if(new Random().nextInt(100) < 50) {
			if((int) x < p.getX() && World.isFree((int)(x+speed), this.getY()) && !isColidding((int)(x+speed), this.getY())) {
				
				x+=speed;
			}
			else if ((int) x > p.getX() && World.isFree((int)(x-speed), this.getY()) && !isColidding((int)(x-speed), this.getY())) {
				
				x-=speed;
			} if ((int) y > p.getY() && World.isFree(this.getX(), (int)(y-speed)) && !isColidding(this.getX(), (int)(y-speed))) {
				
				y-=speed;
			}else if ((int) y < p.getY() && World.isFree(this.getX(),(int)(y+speed)) && !isColidding(this.getX(), (int)(y+speed)) ) {
				
				y+=speed;
			}
		}
		
		
		//if(moved) {			
		
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex) 
					index = 0 ;
		}		
		
		
	}
	

	public boolean isColidding (int xNext, int yNext) {
		Rectangle enemyCurrent = new Rectangle(xNext,yNext,World.TILE_SIZE,World.TILE_SIZE);{
			
			for(int i = 0; i< Game.enemies.size(); i++) {
				Enemy e = Game.enemies.get(i);
				if(e == this) 
					continue;	
				Rectangle targetEnemy = new Rectangle(e.getX(),e.getY(),World.TILE_SIZE,World.TILE_SIZE);
				if(enemyCurrent.intersects(targetEnemy)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void render(Graphics g) {
		g.drawImage(sprites[index], this.getX() - Camera.x,this.getY() - Camera.y,null);
	}
	
	
}