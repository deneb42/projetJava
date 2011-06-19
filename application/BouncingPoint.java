package application;


import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import compositor.Compositor;

public class BouncingPoint extends Thread implements Application {
	
	private Integer XSIZE = 10;
	private Integer YSIZE = 10;
	private Integer posX = -1;
	private Integer posY = -1;
	private Integer x = 0;
	private Integer y = 0;
	private Integer w = 0;
	private Integer h = 0;
	private Integer dx = 0;
	private Integer dy = 0;
	
	
	public BouncingPoint() {
		
		dx = (int)((Math.random()*10)+1);
		dy = (int)((Math.random()*10)+1);
		
		start();
	}

	public void draw(Graphics2D context, int parX, int parY, int parW, int parH) {

		x = parX; y = parY; w = parW; h = parH;
		
		if(posX == -1) {
			posX = (int)Math.random()*parX;
			posY = (int)Math.random()*parY;
		}
		
		context.fillOval(x+posX, y+posY, XSIZE, YSIZE);
	}
	
	public void move() {
	    posX += dx;
	    posY += dy;

	    if (posX < 0) {
	      posX = 0;
	      dx = -dx;
	    }
	    if (posX + XSIZE >= w) {
	      posX = w - XSIZE;
	      dx = -dx;
	    }
	    if (posY < 0) {
	      posY = 0;
	      dy = -dy;
	    }
	    if (posY + YSIZE >= h) {
	      posY = h - YSIZE;
	      dy = -dy;
	    }
	}
	
	
	
	@Override
	public void run() {
		while(true) {
			move();
			Compositor.getInstance().repaint(x,y,w,h); //il prend draw
			try {
				sleep(50);
			} catch (InterruptedException e) {e.printStackTrace(); }
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		this.interrupt();
		super.finalize();
	}
	
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseWheel(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
}
