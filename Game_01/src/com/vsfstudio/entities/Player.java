package com.vsfstudio.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.vsfstudio.graficos.Spritesheet;
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
	private BufferedImage[] upPlayerWithGun;
	
	private BufferedImage playerDemage;
	
	public boolean isDemage = false;
	private int frameDemage = 0; 
	
	private boolean hasGun = false;
	
	public int ammo = 0;
	
	public double life = 100, maxLife = 100;
	
	
	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		
		rightPlayer = new BufferedImage[4];		
		leftPlayer = new BufferedImage[4];
		upPlayer = new BufferedImage[4];
		downPlayer = new BufferedImage[4];
		upPlayerWithGun = new BufferedImage[4];
		
		playerDemage = Game.spritesheet.getSprite(0, 64, 16, 16);
		
		for(int i = 0; i < 4; i++) {
			rightPlayer[i] = Game.spritesheet.getSprite(0 + (i*16), 0, 16, 16);
			
		}
		for(int i = 0; i < 4; i++) {
			leftPlayer[i] = Game.spritesheet.getSprite(0 + (i*16), 16, 16, 16);
			
		}
		
		for(int i = 0; i < 4; i++) {
			downPlayer[i] = Game.spritesheet.getSprite(0 + (i*16), 32, 16, 16);
			
		}
		
		 for(int i = 0; i < 4; i++) {
			upPlayerWithGun[i] = Game.spritesheet.getSprite(64 + (i*16), 32, 16, 16);
			
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
		
		this.checkCollisionLifePack();
		this.checkColissionAmmo();
		checkCollisionGun();
		
		
		if(isDemage) {
			frameDemage++;
			if(frameDemage ==1000) {
				frameDemage =0;
				isDemage = false;
			}
		}
		
		if (life <= 0 ) {
			Game.enemies.clear();
			Game.entities.clear();
			Game.entities = new ArrayList<Entity>();
			Game.enemies = new ArrayList<Enemy>();
			Game.spritesheet = new Spritesheet ("/spritesheet.png");
			Game.player = new Player(0,0,16,16,Game.spritesheet.getSprite(0, 0, 16, 16));
			Game.entities.add(Game.player);
			Game.world = new World ("/map_0.png");
			return;
			
		}
		
		Camera.x = Camera.clamp(this.getX() - (Game.WIDTH/2),0,World.WIDTH*16 - Game.WIDTH);
		Camera.y = Camera.clamp(this.getY() - (Game.HEIGHT/2),0,World.HEIGHT*16 - Game.HEIGHT);
	}

	
	public void checkColissionAmmo() {
		
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity atual = Game.entities.get(i);
			if (atual instanceof Ammo) {
				if(Entity.isColidding(this, atual)) {
					 ammo += 10;
					
					 Game.entities.remove(atual);
					}
					
					
				}
			}
			
		}
		
	
	
	
	public void checkCollisionLifePack() {
		
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity atual = Game.entities.get(i);
			if (atual instanceof LifePack) {
				if(Entity.isColidding(this, atual)) {
					 life += 10;
					if(life >100) {
						life =100;
						
					}
					Game.entities.remove(atual);
					
				}
			}
			
		}
		
	}
	
		public void checkCollisionGun() {
				
				for(int i = 0; i < Game.entities.size(); i++) {
					Entity atual = Game.entities.get(i);
					if (atual instanceof Weapon) {
						if(Entity.isColidding(this, atual)) {
							 hasGun = true;
							Game.entities.remove(atual);
							
						}
					}
					
				}
				
			}
	
	
	public void render(Graphics g) {
		
		if (!isDemage) {
			
		
		if (dir == right_dir) {
			
			g.drawImage(rightPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
			if (hasGun) {
				g.drawImage(Entity.SWORD_RIGHT, this.getX()+5 - Camera.x, this.getY()+3 - Camera.y, null);
			}
			
		}
		
		else if (dir == left_dir) {
			
			g.drawImage(leftPlayer[index], this.getX()- Camera.x, this.getY()- Camera.y, null);
			if (hasGun) {
				g.drawImage(Entity.SWORD_LEFT, this.getX()-7 - Camera.x, this.getY()+3 - Camera.y, null);
			}
		}
		
		if(dir == up_dir) {
			g.drawImage(upPlayer[index], this.getX()- Camera.x, this.getY()- Camera.y, null);
			if (hasGun == true) {
				
				//g.drawImage(Entity.SWORD_UP, this.getX() - Camera.x, this.getY() - Camera.y, null);
				g.drawImage(upPlayerWithGun[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
				
				
			}
		}
		
		else if   (dir == down_dir) {
			g.drawImage(downPlayer[index], this.getX()- Camera.x, this.getY()- Camera.y, null);
			if (hasGun) {
				g.drawImage(Entity.SWORD_DOWN, this.getX()+1 - Camera.x, this.getY()+8 - Camera.y, null);
			}
		}
		}else {
			g.drawImage(playerDemage, this.getX()- Camera.x, this.getY()- Camera.y, null);
			if (hasGun) {
				
			}
		}
	}
}

