package application;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import compositor.Compositor;

public interface Application {
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
/*
public class Application extends Thread {
	private static final long serialVersionUID = -6461622400547388552L;
	
	private Color couleur= Color.black;
	private Integer x=0,y=0,w=0,h=0;
	private Float H, S, B, valS, valB;
	
	public Application() {
		H = (float)Math.random();
		S = (float)Math.random();
		B = (float)0.8;
		valS = (float)0.01;
		
		couleur = new Color(Color.HSBtoRGB(H, S, B));
		
		start();
	}
	
	public void draw(Graphics2D context, int parX, int parY, int parW, int parH) {
		x=parX; y=parY; w=parW; h=parH;
		
		context.setColor(couleur);
		context.fillRect(x, y, w, h);
	}

	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}
	
	@Override
	public void run() {
		while(true) {
			
			H = (H+(float)0.01);
			
			if(S>=1) valS=(float)-0.5*valS;
			else if(S<=0) valS=(float)-2*valS;
			S+=valS;
			
			couleur = new Color(Color.HSBtoRGB(H, S, B));
			
			Compositor.getInstance().repaint(x,y,w,h);
			
			try {
				sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}*/
