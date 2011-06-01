
package application;

import java.awt.Color;
import java.awt.Graphics2D;

import compositor.Compositor;

public class Application extends Thread {
	private static final long serialVersionUID = -6461622400547388552L;
	
	private Color couleur= Color.black;
	private Integer x=0,y=0,w=0,h=0;
	
	public Application() {
		couleur = new Color((float)Math.random(),(float)Math.random(),(float)Math.random());
		
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
			couleur = new Color((float)Math.random(),(float)Math.random(),(float)Math.random());
			
			Compositor.getInstance().repaint(x,y,w,h);
			
			try {
				sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
