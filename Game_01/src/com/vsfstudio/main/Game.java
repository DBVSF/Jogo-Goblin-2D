package com.vsfstudio.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

	
	public static JFrame frame;
	private Thread thread;
	private final int WIDTH = 160;
	private final int HEIGHT = 120;
	private final int SCALE = 4;
	private BufferedImage image;
	
	private boolean isRunning = true;
	
	public Game () {
		
		setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		initFrame();
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		
	}
	
	
	public void initFrame() {
		
		frame = new JFrame("GOBLIN DO AMOR");
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
		
	}
	
	
	
	
	public static void main (String args[]) {
		Game game = new Game();
		game.start();
	}
	
	public void tick () {
		
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
		g= bs.getDrawGraphics();
		g.drawImage(image, 0,0, WIDTH*SCALE, HEIGHT*SCALE,null);
		bs.show();
	}
	
	public void run() {
		
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks ;
		double delta = 0;
		while (isRunning) {			
			
			long now = System.nanoTime();
			
			delta+= (now - lastTime) / ns ;
		
			if (delta >= 1 ) {
				tick();
				render();
			}
			
		}
		
		
	}

	
	
	
}
