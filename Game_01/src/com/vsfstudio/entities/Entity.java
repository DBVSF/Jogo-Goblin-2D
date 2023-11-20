package com.vsfstudio.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.vsfstudio.main.Game;
import com.vsfstudio.world.Camera;

public class Entity {

				
	public static BufferedImage LIFEPACK_EN = Game.spritesheet.getSprite(299, 185, 16, 16);
	public static BufferedImage WEAPON_EN = Game.spritesheet.getSprite(10, 154, 16, 16);
	public static BufferedImage AMMO_EN = Game.spritesheet.getSprite(129, 185, 8, 16);
	public static BufferedImage ENEMY_EN = Game.spritesheet.getSprite(0, 208, 16, 16);
	
	
	protected double x;
	protected double y;
	protected int width;
	protected int height;
	
	
	private BufferedImage sprite;
	
	
	public Entity (int x, int y, int width, int height, BufferedImage sprite) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
		
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
	
	public void render (Graphics g) {
		g.drawImage(sprite, this.getX() -Camera.x, this.getY() - Camera.y, null);
	}
	
	
	
	
	
}
