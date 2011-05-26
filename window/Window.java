package window;

import java.awt.Color;
import java.awt.Graphics2D;

import application.Application;

public class Window {
	
	public static Integer marginTop=20, margin=4, iconSize=10;
	
	private Integer posiX, posiY, width, height;
	private Boolean iconified=false; //deprecated
	
	private Graphics2D context;

	private Application app;
	
	
	public Window(Graphics2D parContext, int x, int y, int w, int h) {
		
		posiX = x; posiY = y;
		width=w; height=h;
		context = parContext;
		
		app = new Application();
	}

	
	public void draw() {
		context.setColor(Color.black);
		context.fillRect(posiX, posiY, width, height);
		
		//croix
		context.setColor(Color.white);
		context.drawRect(posiX+width-marginTop+margin, posiY+margin, marginTop-2*margin, marginTop-2*margin);
		context.drawLine(posiX+width-marginTop+margin, posiY+margin, 
							posiX+width-margin, posiY+marginTop-margin);
		context.drawLine(posiX+width-marginTop+margin, posiY+marginTop-margin, 
							posiX+width-margin, posiY+margin);
		
		// minimalisation
		context.drawRect(posiX+width-2*(marginTop-margin), posiY+margin, marginTop-2*margin, marginTop-2*margin);
		context.fillRect(posiX+width-2*(marginTop-margin)+margin/2, posiY+marginTop-2*margin, marginTop-3*margin, margin/2);
		
		app.draw(context, posiX+margin, posiY+marginTop, 
				width-2*margin, height-marginTop-margin);
	}
	
	public void drawIcon(Integer x, Integer y) {
		context.setColor(app.getCouleur());
		context.fillRect(x, y, iconSize, iconSize);
	}
	
	
	public Integer getPosiX() {
		return posiX;
	}

	public void setPosiX(Integer posiX) {
		this.posiX = posiX;
	}

	public Integer getPosiY() {
		return posiY;
	}

	public void setPosiY(Integer posiY) {
		this.posiY = posiY;
	}
	
	public void translate(Integer x, Integer y) {
		posiX+=x;
		posiY+=y;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}
	
	public void addWidth(Integer parWidth) {
		if(width+parWidth<2*margin)
			width=2*margin;
		else
			width+=parWidth;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}
	
	public void addHeight(Integer parHeight) {
		if(height+parHeight<2*margin)
			height=2*margin;
		else
			height+=parHeight;
	}

	public Boolean isIconified() {
		return iconified;
	}

	public void setIconified(Boolean iconified) {
		this.iconified = iconified;
	}
}
