package com.vsfstudio.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.vsfstudio.main.Game;
import com.vsfstudio.world.Camera;

public class Entity {

				
	public static BufferedImage LIFEPACK_EN = Game.spritesheet.getSprite(299, 185, 16, 16);
	public static BufferedImage WEAPON_EN = Game.spritesheet.getSprite(64, 0, 16, 16);
	public static BufferedImage AMMO_EN = Game.spritesheet.getSprite(129, 185, 8, 16);
	public static BufferedImage ENEMY_EN = Game.spritesheet.getSprite(0, 208, 16, 16);
	public static BufferedImage SWORD_LEFT = Game.spritesheet.getSprite(96, 0, 16, 16);
	public static BufferedImage SWORD_RIGHT = Game.spritesheet.getSprite(80, 0, 16, 16);
	public static BufferedImage SWORD_DOWN = Game.spritesheet.getSprite(120, 0, 8, 16);
	public static BufferedImage SWORD_UP = Game.spritesheet.getSprite(112, 0, 8, 16);
	public static BufferedImage BULLETSHOOT_LEFT = Game.spritesheet.getSprite(257, 154, 16, 16);
	public static BufferedImage BULLETSHOOT_RIGHT = Game.spritesheet.getSprite(273, 154, 16, 16);
	public static BufferedImage BULLETSHOOT_UP = Game.spritesheet.getSprite(205, 154, 16, 16);
	public static BufferedImage BULLETSHOOT_DOWN = Game.spritesheet.getSprite(205, 169, 16, 16);
	
	
	protected double x;
	protected double y;
	protected int width;
	protected int height;
	private int maskX,maskY,mWidth,mHeight;
	
	private BufferedImage sprite;
	
	
	public Entity (int x, int y, int width, int height, BufferedImage sprite) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
		
		this.maskX = 0;
		this.maskY = 0;
		this.mHeight = height;
		this.mWidth = width;
		
	}
	
	public void setMask(int maskX, int maskY, int mHeight, int mWidth) {
		this.maskX = maskX;
		this.maskY = maskY;
		this.mHeight = mHeight;
		this.mWidth = mWidth;
	}
	
	
	
	public void setX(int newX) {
		this.x = newX;
	}
	public void setY(int newY) {
		this.y = newY;
	}
	
	public int getX() {
		return (int)this.x;
	}
	
	public int getY() {
		return (int)this.y;
	}
	
	public int getWidht() {
		return this.width;
	}
	public int getHeight() {
		return this.height;
	}
	
	public void tick () {
		
	}
	
	
	public static boolean isColidding(Entity e1, Entity e2) {
		Rectangle e1Mask = new Rectangle(e1.getX() + e1.maskX, e1.getY() + e1.maskY,e1.mWidth,e1.mHeight);
		Rectangle e2Mask = new Rectangle(e2.getX() + e2.maskX, e2.getY() + e2.maskY,e2.mWidth,e2.mHeight);
		
		return e1Mask.intersects(e2Mask);
	}
	
	
	public void render (Graphics g) {
		g.drawImage(sprite, this.getX() -Camera.x, this.getY() - Camera.y, null);
	
	}
	
	
	
	
	
}
