package application;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import window.Window;

public interface Application {
	
	public void draw(Graphics2D context, int parX, int parY, int parW, int parH);
	public void setPadre(Window papa);
	
	//for an implementation of mouse listener, mouse motion listener and key listener
	public void mousePressed(MouseEvent e);
	public void mouseReleased(MouseEvent e);
	public void mouseMoved(MouseEvent e);
	public void mouseDragged(MouseEvent e);
	public void mouseClicked(MouseEvent e);
	public void mouseWheel(MouseEvent e);
	public void mouseEntered(MouseEvent e);
	public void mouseExited(MouseEvent e);
	public void keyPressed(KeyEvent e);
	public void keyReleased(KeyEvent e);
	public void keyTyped(KeyEvent e);
}