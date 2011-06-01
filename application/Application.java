package application;

import java.awt.Color;
import java.awt.Graphics2D;

import compositor.Compositor;

public class Application extends Thread {
	private static final long serialVersionUID = -6461622400547388552L;
	
	private Color couleur= Color.black;
	private Integer x=0,y=0,w=0,h=0;
	private Float H, S, B, valS, valB;
	
	public Application() {
		H = (float)Math.random();
		S = (float)Math.random();
		B = (float)Math.random();
		valS = valB = (float)0.01;
		
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
			
			H = (H+(float)0.01)%1;
			if(S>1) valS=(float)-0.5*valS;
			else if(S<0) valS=(float)-2*valS;
			S+=valS;

			if(B>1) valB=(float)-0.5*valB;
			else if(B<0) valB=(float)-2*valB;
			B+=valB;
			
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
}
