package application;


import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import compositor.Compositor;

public class BouncingPoint extends Thread implements Application {
	
	private Integer x=0,y=0,w=0,h=0;

	public void draw(Graphics2D context, int parX, int parY, int parW, int parH) {

		
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
