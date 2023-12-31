package com.vsfstudio.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.vsfstudio.main.Game;

public class Tile {

	public static BufferedImage TILE_FLOOR = Game.spritesheet.getSprite(159, 223, 16, 16);
	public static BufferedImage TILE_FLOOR1 = Game.spritesheet.getSprite(175, 223, 16, 16);
	public static BufferedImage TILE_FLOOR2= Game.spritesheet.getSprite(159, 239, 16, 16);
	public static BufferedImage TILE_FLOOR3 = Game.spritesheet.getSprite(175, 239, 16, 16);
	public static BufferedImage TILE_WALL = Game.spritesheet.getSprite(273, 241, 16, 16);
	
	
	private BufferedImage sprite;
	private int x,y;
	
	public Tile(int x, int y, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void render (Graphics g){
		g.drawImage(sprite,x- Camera.x,y- Camera.y,null);
	}
	
}
