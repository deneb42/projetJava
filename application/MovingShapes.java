package application;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;

import window.Window;

public class MovingShapes implements Application {
	
	private Integer posX = -1;
	private Integer posY = -1;
	private Integer index = 0;
	private int[] Tx = new int[3];
	private int[] Ty = new int[3];
	private Window padre;
	
	public ArrayList<Shape> shapes = new ArrayList<Shape>();
	public ArrayList<Color> colors = new ArrayList<Color>();


	public MovingShapes() {
		for(int i=0;i<3;i++)
			colors.add(new Color((float)Math.random(), (float)Math.random(), (float)Math.random()));
	}
	
	public MovingShapes(Window papa) {
		this();
		setPadre(papa);
	}
	
	public void setPadre(Window papa) {
		padre = papa;
		padre.maj();
	}
	
	@Override
	public void draw(Graphics2D context, int parW, int parH) {
		if(shapes.size()==0) {
			shapes.add(new Rectangle2D.Double((Math.random()*2*(parW-15)/3)+15, (Math.random()*2*(parH-15)/3)+15, 
					(Math.random()*(parW-15)/2)+15,(Math.random()*(parH-15)/2)+15));
			shapes.add(new Ellipse2D.Double((Math.random()*2*(parW-15)/3)+15, (Math.random()*2*(parH-15)/3)+15, 
					(Math.random()*(parW-15)/2)+15,(Math.random()*(parH-15)/2)+15));
			for(int i=0;i<3;i++) {
				Tx[i] = (int)(Math.random()*parW);
				Ty[i] = (int)(Math.random()*parH);
			}
			shapes.add(new Polygon(Tx,Ty,3));
		}
		context.setColor(Color.white);
		context.fillRect(0, 0, parW, parH);

		for(int i=0;i<shapes.size();i++) {//pour s qui prend toutes les valeurs qu'il y a dans le tableau
			context.setColor(colors.get(i));
			context.fill(shapes.get(i));
		}
	}
	

	//enregistrer les coordonnées d'origine de la shape
	public void mousePressed(MouseEvent e)  {
		//collision	
		for(int i = 0; i < shapes.size(); i++) {
			if(shapes.get(i).contains(e.getX(), e.getY())) {
				posX = e.getX();
				posY = e.getY();
				index = i;	
				return;
			}	
		}				
	}

	//Mettre à jour les coordonnées de la shape (Ellipse et rectangle se comportent en RectangularShape, et Polygon est un cas à part)
	public void mouseDragged(MouseEvent e) {
		//sortie si pas de shape sélectionnée
		if (index==-1) {
			return;
		}
		
		if(shapes.get(index) instanceof RectangularShape) {
			RectangularShape s = (RectangularShape)shapes.get(index);
			s.setFrame(s.getX() + e.getX() - posX, s.getY() + e.getY() - posY, s.getWidth(), s.getHeight());
			padre.maj();
		}
		else if(shapes.get(index) instanceof Polygon) {
			((Polygon)shapes.get(index)).translate(e.getX()-posX, e.getY()-posY);
			padre.maj();
		}
		posX = e.getX();
		posY = e.getY();
	}
	
	//Réintinialiser les origines et l'index
	public void mouseReleased(MouseEvent e) {
		posX = -1;
		posY = -1;
		index = -1;
	}
	
	public void mouseClicked(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseWheel(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
}
