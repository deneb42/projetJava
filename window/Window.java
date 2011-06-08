package window;

import java.awt.Color;
import java.awt.Graphics2D;

import application.Application;

public class Window {
	
	public static final Integer marginTop=20, margin=4, iconSize=10;
	
	private Integer posiX, posiY, width, height;
	private Integer oldPosiX, oldPosiY, oldWidth, oldHeight;
	private Boolean maximised=false;

	private Application app;
	
	
	public Window(Application parApp, int x, int y, int w, int h) {
		
		posiX = x; posiY = y;
		width=w; height=h;
		
		app = parApp;
	}
	
	@Override
	protected void finalize() throws Throwable {
		app = null;
		super.finalize();
	}
	
	public void draw(Graphics2D context) {
		context.setClip(posiX, posiY, width, height);
		
		context.setColor(Color.black);
		context.fillRect(posiX, posiY, width, height);
		
		//croix
		context.setColor(Color.white);
		context.drawRect(posiX+width-marginTop+margin, posiY+margin, 
				marginTop-2*margin, marginTop-2*margin);
		context.drawLine(posiX+width-marginTop+margin, posiY+margin, 
				posiX+width-margin, posiY+marginTop-margin);
		context.drawLine(posiX+width-marginTop+margin, posiY+marginTop-margin, 
				posiX+width-margin, posiY+margin);
		
		//maximisation
		context.drawRect(posiX+width-2*(marginTop-margin), posiY+margin, 
				marginTop-2*margin, marginTop-2*margin);
		context.drawRect(posiX+width-2*(marginTop-margin)+margin/2, 
				posiY+margin+margin/2, marginTop-3*margin, marginTop-3*margin-margin/2);
		
		// minimalisation
		context.drawRect(posiX+width-3*(marginTop-margin), posiY+margin, marginTop-2*margin, marginTop-2*margin);
		context.fillRect(posiX+width-3*(marginTop-margin)+margin/2, posiY+marginTop-2*margin, marginTop-3*margin, margin/2);
		
		context.setClip(posiX+margin, posiY+marginTop, 
				width-2*margin, height-marginTop-margin);
		app.draw(context, posiX+margin, posiY+marginTop, 
				width-2*margin, height-marginTop-margin);
	}
	
	public void drawIcon(Graphics2D context, Integer x, Integer y) {
		app.draw(context, x, y, iconSize, iconSize);
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

	public void setWidth(Integer parWidth) {
		if(parWidth>3*marginTop)
			width=parWidth;
	}
	
	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer parHeight) {
		if(parHeight>=2*marginTop)
			height=parHeight;
	}

	public Boolean isMaximised() {
		return maximised;
	}

	public void setMaximised(Boolean maximised) {
		this.maximised = maximised;
	}
	
	public void save() {
		oldPosiX=posiX; 
		oldPosiY=posiY; 
		oldWidth=width; 
		oldHeight=height;
	}
	
	public void restore() {
		posiX=oldPosiX; 
		posiY=oldPosiY;
		width=oldWidth;
		height=oldHeight;
	}
}
