package application;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import window.Window;

public class BouncingPoint extends Thread implements Application {
	
	private Integer XSIZE = 10;
	private Integer YSIZE = 10;
	private Integer posX = -1;
	private Integer posY = -1;
	private Integer w = 0;
	private Integer h = 0;
	private Integer dx = 0;
	private Integer dy = 0;
	private Window padre;
	private Color couleurfond = Color.white;
	private Color couleurball = Color.black;
	private Double H, S, B;
	
	
	public BouncingPoint() {
		dx = (int)((Math.random()*10)+1);
		dy = (int)((Math.random()*10)+1);
		
		H = Math.random();
		S = Math.random();
		B = 0.8;
		
		couleurfond = new Color(Color.HSBtoRGB(H.floatValue(), S.floatValue(), B.floatValue()));
		
		H = Math.random();
		S = Math.random();
		
		couleurball = new Color(Color.HSBtoRGB(H.floatValue(), S.floatValue(), B.floatValue()));
		
	}
	
	public BouncingPoint(Window papa) {
		this();
		setPadre(papa);
	}
	
	@Override
	public void setPadre(Window papa) {
		padre = papa;
		start();
	}

	public void draw(Graphics2D context, int parW, int parH) {
		w = parW; h = parH;
		
		if(posX == -1) {
			posX = (int)Math.random()*w;
			posY = (int)Math.random()*h;
		}
		context.setColor(couleurfond);
		context.fillRect(0, 0, w, h);
		context.setColor(couleurball);
		context.fillOval(posX, posY, XSIZE, YSIZE);
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
			padre.maj();
			try {
				sleep(30);
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
