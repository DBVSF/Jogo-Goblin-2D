package com.vsfstudio.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import com.vsfstudio.entities.BulletShoot;
import com.vsfstudio.entities.Enemy;
import com.vsfstudio.entities.Entity;
import com.vsfstudio.entities.Player;
import com.vsfstudio.graficos.Spritesheet;
import com.vsfstudio.graficos.UI;
import com.vsfstudio.world.World;


public class Game extends Canvas implements Runnable, KeyListener {

	
	
	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private Thread thread;
	public static final int WIDTH = 240;
	public static final int HEIGHT = 160;
	private final int SCALE = 3;
	private BufferedImage image;
	public static Player player;
	public static World world;
	
	
	public static List <Entity> entities;
	public static List <BulletShoot> bullets;
	public static List <Enemy> enemies;
	public static Spritesheet spritesheet;
	public static Random rand = new Random();
	
	public UI ui;
	
	private boolean isRunning = true;
	
	public Game () {
		
	
		addKeyListener(this);
		setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		initFrame();
		
		
		ui = new UI();
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);				
		entities = new ArrayList<Entity>();
		enemies = new ArrayList<Enemy>();		
		bullets = new ArrayList<BulletShoot>();
		spritesheet = new Spritesheet("/spritesheet.png");
		player = new Player(0,0,16,16,spritesheet.getSprite(0, 0, 16, 16));
		entities.add(player);
		world = new World ("/map_0.png");

		
		
		
	}
	
	
	public void initFrame() {
		
		frame = new JFrame("GOBLIN WAR");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}
	
	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	public static void main (String args[]) {
		Game game = new Game();
		game.start();
	}
	
	public void tick () {
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.tick();
		}
		
		
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).tick();
		}
		
	}
	
	
	
	
	public void render () {
		
		BufferStrategy bs = this.getBufferStrategy();		
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		
		
		Graphics g = image.getGraphics();
		g.setColor(new Color(0,0,0));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		world.render(g);
		
		for(int i =0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.render(g);
		}
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).render(g);
		}
		
		
		ui.render(g);
		
		
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0,0, WIDTH*SCALE, HEIGHT*SCALE,null);
		
		g.setFont(new Font("arial",Font.BOLD,17));
	
		g.setColor(Color.red);
		g.fillRect(39,42,115,15);
		g.setColor(Color.white);
		g.drawString("Munição: " + player.ammo, 55, 55);
		
		
		bs.show();
		
	
		
		
	}
	
	
	public void run() {
		
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks ;
		double delta = 0;
		requestFocus();
		while (isRunning) {			
			
			long now = System.nanoTime();
			
			delta+= (now - lastTime) / ns ;
		
			if (delta >= 1 ) {
				tick();
				render();
				
			}
			
		}
		stop();
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		
			if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
				player.right = true;
			}
			else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
				player.left = true;
			} 
			if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
				player.up = true;
			}
			else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
				player.down = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_X){
				player.shoot=true;
			}
		
	}
	public void keyReleased(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			player.right = false;
		}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			player.left = false;
		} 
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			player.up = false;
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			player.down = false;
		}
	}


	

	
	
	
}
