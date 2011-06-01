package compositor;

/*TODO :
 *  - pas moyen d'améliorer la fluidité de l'affichage ?
 * 
 *  - changer methode de redimentionnement
 */

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import window.Window;


public class Compositor extends JFrame implements MouseListener, MouseMotionListener, KeyListener {

	private static final long serialVersionUID = 1493982699619655700L;
	private static Compositor instance = null;
	
	
	//variables pour les event
	private Integer mouseClickX=0, mouseClickY=0;
	private Character mode =' ';
	private Boolean nPressed = false;
	
	//applications
	private ArrayList<Window> windows = new ArrayList<Window>();
	private ArrayList<Window> icons = new ArrayList<Window>();
	
	//constantes
	private Integer origIconX = 50, origIconY=40, padding=10;
	private Integer iMax;
	
	// Fin variables ---------------------------------------------------------------------
	
	public static Compositor getInstance() {
		if (instance == null)
			instance = new Compositor("Compositor");
		
		return instance;
	}
	
	private Compositor(String name) {
		super(name);
		Compositor.instance = this;
		setSize((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
				(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()-30);
		//pour prendre a peu pres la taille de l'ecran
		setLocation(0,0);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setUndecorated(true); // ne pas afficher le bandeau et les bords
		setVisible(true);
		createBufferStrategy(2);
		
		iMax = (getHeight()-origIconY)/(padding+Window.iconSize); 
		// le nombre max d'icones sur une hauteur de fenetre
		
		for(int i=0;i<10;i++) {
			windows.add(new Window(110*(1+(i%5)), 110*(1+(i/5)), 100, 100));
		} // on ajoute 10 applications
		
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D context = (Graphics2D)g;

		context.setColor(Color.white);
		context.fillRect(0, 0, getWidth(), getHeight());
		
		if(windows.size()>0 && !windows.get(windows.size()-1).isMaximised()) {
			for(int i=0;i<icons.size();i++) {
				if(icons.get(i)!=null)
					icons.get(i).drawIcon(context, origIconX+(i/iMax)*(padding+Window.iconSize), origIconY+(i%iMax)*(padding+Window.iconSize));
			} // dessin des icones en premier (en dessous du coup)
			
			for(int i=0;i<windows.size();i++) // puis dessin des fenetres
				windows.get(i).draw(context);
		}
		else if(windows.size()>0)
			windows.get(windows.size()-1).draw(context);
	}
	
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		for(int i=windows.size()-1;i>=0;i--) {
			Window w = windows.get(i);
			
			//fermeture
			if(collision(e.getX(), e.getY(), w.getPosiX()+w.getWidth()-Window.marginTop+Window.margin, 
					w.getPosiY()+Window.margin, Window.marginTop-2*Window.margin, Window.marginTop-2*Window.margin)) {
				windows.remove(w);
				repaint(w.getPosiX(),w.getPosiY(), w.getWidth(), w.getHeight());
				return;
			}
			//Maximize
			else if(collision(e.getX(), e.getY(), w.getPosiX()+w.getWidth()-2*(Window.marginTop-Window.margin), 
					w.getPosiY()+Window.margin, Window.marginTop-2*Window.margin, Window.marginTop-2*Window.margin)) {
				if(w.isMaximised()) {
					w.restore();
					w.setMaximised(false);
				}
				else {
					w.save();
					w.setMaximised(true);
					w.setPosiX(0); w.setPosiY(0);
					w.setWidth(getWidth()); w.setHeight(getHeight());
					
				}
				windows.remove(w);
				windows.add(w);
				repaint();
				return;
			}
			//iconify
			else if(collision(e.getX(), e.getY(), w.getPosiX()+w.getWidth()-3*(Window.marginTop-Window.margin), 
					w.getPosiY()+Window.margin, Window.marginTop-2*Window.margin, Window.marginTop-2*Window.margin)) {
				windows.remove(w);
				if(icons.indexOf(null)!=-1)
					icons.set(icons.indexOf(null), w); // si on trouve une place libre
				else
					icons.add(w);
				
				repaint();
				return;
			}
			
		}
		//desiconification
		for(int i=0;i<icons.size();i++) {
			if(collision(e.getX(), e.getY(), origIconX+(i/iMax)*(padding+Window.iconSize), 
					origIconY+(i%iMax)*(padding+Window.iconSize), Window.iconSize, Window.iconSize)) {
				windows.add(icons.get(i));
				icons.set(i, null);
				repaint();
				return;
			}
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		mouseClickX = e.getX(); mouseClickY = e.getY();
		
		if(!nPressed) {
			for(int i=windows.size()-1;i>=0;i--) {
				Window w = windows.get(i);
				
				//collision avec le bandeau
				if(collision(e.getX(), e.getY(), w.getPosiX()+Window.margin, w.getPosiY()+Window.margin, 
						w.getWidth()-2*Window.marginTop, Window.marginTop-Window.margin)) {
					mode = 'd';
				}
				else {
					//collision avec le margin d'en bas
					if(collision(e.getX(), e.getY(), w.getPosiX(), w.getPosiY()+w.getHeight()-Window.margin, w.getWidth(), Window.margin)) {
						mode = 'h';
						setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
					}
					/*else if(collision(e.getX(), e.getY(), w.getPosiX(), w.getPosiY(), w.getWidth(), Window.margin))
						mode = 'H';*/
							
							
					//collision avec le margin à droite
					if(collision(e.getX(), e.getY(), w.getPosiX()+w.getWidth()-Window.margin, w.getPosiY(), Window.margin, w.getHeight())) {
						if(mode=='h') {
							mode='a';
							setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
						}
						else {
							mode='w';
							setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
						}
					}
				}
				
				if(mode!=' ') {
					windows.remove(w);
					windows.add(w);
					w.setMaximised(false);
					return;
				}
			}
		}
		else {
			mode='n';
			windows.add(new Window(mouseClickX, mouseClickY, 0, 0));
		}
	}
	

	@Override
	public void mouseReleased(MouseEvent e) {
/*
		switch(mode) {
			case 'd':
				windows.get(windows.size()-1).translate(e.getX() - mouseClickX, e.getY() - mouseClickY);
				break;
			case 'h':
				windows.get(windows.size()-1).addHeight(e.getY() - mouseClickY);
				break;
			/*case 'H':
				windows.get(windows.size()-1).translate(0, e.getY() - mouseClickY);
				windows.get(windows.size()-1).addHeight(mouseClickY - e.getY());
				break;*
			case 'w':
				windows.get(windows.size()-1).addWidth(e.getX() - mouseClickX);
				break;
			case 'a':
				windows.get(windows.size()-1).addHeight(e.getY() - mouseClickY);
				windows.get(windows.size()-1).addWidth(e.getX() - mouseClickX);
				break;
		}
		
		if(mode!=' ')
			repaint();
*/
		mode = ' ';
		mouseClickX = mouseClickY = 0;
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		switch(mode) {
			case 'd':
				windows.get(windows.size()-1).translate(e.getX() - mouseClickX, e.getY() - mouseClickY);
				break;
			case 'h':
				windows.get(windows.size()-1).addHeight(e.getY() - mouseClickY);
				break;
			case 'w':
				windows.get(windows.size()-1).addWidth(e.getX() - mouseClickX);
				break;
			case 'a': case 'n':
				windows.get(windows.size()-1).addHeight(e.getY() - mouseClickY);
				windows.get(windows.size()-1).addWidth(e.getX() - mouseClickX);
				break;
		}
		
		if(mode != ' ') {
			mouseClickX=e.getX();
			mouseClickY=e.getY();
			repaint();
		}
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyChar()=='n')
			nPressed=true;	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyChar()=='n')
			nPressed=false;
	}
	
	@Override // but useless
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void keyTyped(KeyEvent arg0) {}

	
	private boolean collision(int mouseX, int mouseY, int x, int y, int w, int h) {
    	
    	if(mouseX < x)
    		return false;
    	if(mouseY < y)
    		return false;
		if(mouseX > x + w)
        	return false;
    	if(mouseY > y + h)
        	return false;
    		
    	return true;
    }
}
