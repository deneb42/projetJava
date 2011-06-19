package application;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Color;


public MovingShapes() {
	start();
}

public void draw(Graphics2D context, int parX, int parY, int parW, int parH) {
	x=parX; y=parY; w=parW; h=parH;
	
	context.setColor(couleur);
	context.fillRect(x, y, w, h);
}

	

	 


	  public void updateLocation(MouseEvent e) {
	    rect.setLocation(preX + e.getX(), preY + e.getY());

	    if (checkRect()) {
	      ShapeMover.label.setText(rect.getX() + ", " + rect.getY());
	    } else {
	      ShapeMover.label.setText("drag inside the area.");
	    }

	    repaint();
	  }

	  public void paint(Graphics g) {
	    update(g);
	  }

	  public void update(Graphics g) {
	    Graphics2D g2 = (Graphics2D) g;
	    Dimension dim = getSize();
	    int w = (int) dim.getWidth();
	    int h = (int) dim.getHeight();
	    g2.setStroke(new BasicStroke(8.0f));

	    if (isFirstTime) {
	      area = new Rectangle(dim);
	      rect.setLocation(w / 2 - 50, h / 2 - 25);
	      isFirstTime = false;
	    }

	    // Clears the rectangle that was previously drawn.
	    g2.setPaint(Color.white);
	    g2.fillRect(0, 0, w, h);

	    g2.setColor(Color.red);
	    g2.draw(rect);
	    g2.setColor(Color.black);
	    g2.fill(rect);
	  }

	  boolean checkRect() {
	    if (area == null) {
	      return false;
	    }

	    if (area.contains(rect.x, rect.y, 100, 50)) {
	      return true;
	    }
	    int new_x = rect.x;
	    int new_y = rect.y;

	    if ((rect.x + 100) > area.getWidth()) {
	      new_x = (int) area.getWidth() - 99;
	    }
	    if (rect.x < 0) {
	      new_x = -1;
	    }
	    if ((rect.y + 50) > area.getHeight()) {
	      new_y = (int) area.getHeight() - 49;
	    }
	    if (rect.y < 0) {
	      new_y = -1;
	    }
	    rect.setLocation(new_x, new_y);
	    return false;
	  }
	}
	           
	         
	    
	
	
	
	
	
	
	
	
	
	@Override
	public void run() {
		while(true) {
			Compositor.getInstance().repaint(x,y,w,h);
			try {
				sleep(100);
			} catch (InterruptedException e) {e.printStackTrace(); }
		}
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
