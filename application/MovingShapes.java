package application;


import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import window.Window;

import compositor.Compositor;

public class MovingShapes extends Thread implements Application {
	
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

	
	
public void draw(Graphics2D context, int parX, int parY, int parW, int parH) {
	x=parX; y=parY; w=parW; h=parH;
	
	context.setColor(couleur);
	context.fillRect(x, y, w, h);
	
}

	


	
	
	
	
	@Override
	public void run() {
		while(true) {
			Compositor.getInstance().repaint(x,y,w,h);
			try {
				sleep(100);
			} catch (InterruptedException e) {e.printStackTrace(); }
		}
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
