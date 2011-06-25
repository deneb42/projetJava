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

public class MovingShapes implements Application {
	
	private Integer posX = -1;
	private Integer posY = -1;
	private Integer Rx = 30;
	private Integer Ry = 30;
	private Integer Ex = 60;
	private Integer Ey = 60;
	private Integer Rw = 5;
	private Integer Rh = 5;
	private Integer Ew = 25;
	private Integer Eh = 25;
	private Integer index = 0;
	private int[] Tx = {21, 35, 15};
	private int[] Ty = {21, 35, 24};
	private Window padre;
	
	public ArrayList<Shape> shapes = new ArrayList<Shape>();


	public MovingShapes() {
		shapes.add(new Rectangle(Rx, Ry, Rw, Rh));
		shapes.add(new Ellipse2D.Double(Ex, Ey, Ew, Eh));
		shapes.add(new Polygon(Tx,Ty,3));
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
		
		context.setColor(Color.white);
		context.fillRect(0, 0, parW, parH);
		context.setColor(Color.ORANGE);
		for(Shape s: shapes) {//pour s qui prend toutes les valeurs qu'il y a dans le tableau
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
