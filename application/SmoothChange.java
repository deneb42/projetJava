package application;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import window.Window;

import compositor.Compositor;

public class SmoothChange extends Thread implements Application{

	private static final long serialVersionUID = -6461622400547388552L;
	private static final Double CONST_VAL_S=0.01;
	
	private Window padre;
	private Color couleur= Color.black;
	private Double H, S, B, valS;
	
	public SmoothChange() {
		//padre = parP
		H = Math.random();
		S = Math.random();
		B = 0.8;
		valS = CONST_VAL_S;
		
		couleur = new Color(Color.HSBtoRGB(H.floatValue(), S.floatValue(), B.floatValue()));
		
		start();
	}
	
	public void draw(Graphics2D context, int x, int y, int w, int h) {
		context.setColor(couleur);
		context.fillRect(x, y, w, h);
	}

	
	@Override
	public void run() {
		while(true) {
			
			H = (H+(float)0.01)%360;
			
			if(S>=1-valS) valS=-0.5*valS;
			else if(S<=valS) valS=CONST_VAL_S;
			S+=valS;
			
			//couleur = new Color(Color.HSBtoRGB(H.floatValue(), S.floatValue(), B.floatValue()));
			
			//Compositor.getInstance().repaint();
			
			try {
				sleep(100);
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
