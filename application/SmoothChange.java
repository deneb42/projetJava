package application;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import window.Window;

public class SmoothChange extends Thread implements Application{

	private static final long serialVersionUID = -6461622400547388552L;
	private static final Double CONST_VAL_S=0.01;
	
	private Window padre;
	private Color couleur= Color.black;
	private Double H, S, B, valS;
	
	public SmoothChange(Window papa) {
		this();
		
		setPadre(papa);
	}
	public SmoothChange() {
		//padre = parP
		H = Math.random();
		S = Math.random();
		B = Math.random(); //0.8
		valS = CONST_VAL_S;
		
		couleur = new Color(Color.HSBtoRGB(H.floatValue(), S.floatValue(), B.floatValue()));
	}
	
	public void setPadre(Window papa) {
		padre = papa;
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
			
			couleur = new Color(Color.HSBtoRGB(H.floatValue(), S.floatValue(), B.floatValue()));
			
			padre.maj();
			
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

	public void mousePressed(MouseEvent e) {System.out.println("Mouse Pressed in " + B);}
	public void mouseReleased(MouseEvent e) {System.out.println("Mouse Released in " + B);}
	public void mouseMoved(MouseEvent e) {System.out.println("Mouse Moved in " + B);}
	public void mouseDragged(MouseEvent e) {System.out.println("Mouse Dragged in " + B);}
	public void mouseClicked(MouseEvent e) {System.out.println("Mouse Clicked in " + B);}
	public void mouseWheel(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void keyPressed(KeyEvent e) {System.out.println("Key " + e.getKeyChar() + " pressed in " + B);}
	public void keyReleased(KeyEvent e) {System.out.println("Key " + e.getKeyChar() + " released in " + B);}
	public void keyTyped(KeyEvent e) {System.out.println("Key " + e.getKeyChar() + " typed in " + B);}
}
