package application;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;

import window.Window;

public class MovingShapes extends Thread implements Application {
	
	private Integer posX = -1;
	private Integer posY = -1;
	private Integer x = 0;
	private Integer y = 0;
	private Integer w = 0;
	private Integer h = 0;
	private Integer index = 0;
	private int[] tx = {1, 5, 10};
	private int[] ty = {1, 5, 10};
	private Window padre;
	
	public ArrayList<Shape> shapes = new ArrayList<Shape>();


	public MovingShapes() {
		shapes.add(new Rectangle(x, y, w, h));
		shapes.add(new Ellipse2D.Double(x, y, 10, 10));
		shapes.add(new Polygon(tx,ty,3));
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
	public void draw(Graphics2D context, int parX, int parY, int parW, int parH) {
		// TODO Auto-generated method stub
		
		context.setColor(Color.white);
		context.fillRect(parX, parY, parW, parH);
		context.setColor(Color.ORANGE);
		for(Shape s:shapes) {//pour s qui prend toutes les valeurs qu'il y a dans le tableau
			context.fill(s);
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
			((RectangularShape)shapes.get(index)).setFrame(x + e.getX() - posX, y + e.getY() - posY, w, h);
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
