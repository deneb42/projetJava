package application;

import java.awt.Color;
import java.awt.Graphics2D;

public class Application {
	private Color couleur;
	
	public Application() {
		couleur = new Color((float)Math.random(),(float)Math.random(),(float)Math.random());
	}
	
	public void draw(Graphics2D context, int x, int y, int w, int h) {
		context.setColor(couleur);
		context.fillRect(x, y, w, h);
	}

	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}
}
