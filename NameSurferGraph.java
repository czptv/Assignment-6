/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;
import acm.gui.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	 * constants
	 */
	
	private static final int space=20;
	
	/**
	 * instance variables
	 */
	
	private ArrayList<NameSurferEntry> names;
	
	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
		names = new ArrayList<NameSurferEntry>();
		initialize();
	}
	
	/**
	 * initialize the grids and labels of years in the graph
	 */
	private void initialize() {
		//draw lines
		int col=getWidth()/11;
		//vertical lines
		for (int i=1; i<11;i++) {
			add(new GLine(col*i,0,col*i,getHeight()));
		}
		//horizontal lines
		add(new GLine(0,space,getWidth(),space));
		add(new GLine(0,getHeight()-space,getWidth(),getHeight()-space));
		
		//draw labels
		int y=getHeight()-space;
		add(new GLabel("1900",0,y));
		add(new GLabel("1910",col,y));
		add(new GLabel("1920",col*2,y));
		add(new GLabel("1930",col*3,y));
		add(new GLabel("1940",col*4,y));
		add(new GLabel("1950",col*5,y));
		add(new GLabel("1960",col*6,y));
		add(new GLabel("1970",col*7,y));
		add(new GLabel("1980",col*8,y));
		add(new GLabel("1990",col*9,y));
		add(new GLabel("2000",col*10,y));		
	}
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		while(names.isEmpty()) {
			names.remove(0);
		}
		/* int col=getWidth()/11;
		 * for (int i=0; i<names.size();i++) { //for names
			for (int j=1; j<11;i++) { //for years
				int x1=col*(j-1);
				int y1=space+unit*names.get(i).getRank(j-1);
				for (int k=0; k<2;k++) { //check and remove both line and label
					GObject obj=getElementAt(x1,y1);
					if(obj!=null) {
						remove(obj);
					}
				}
			}
			//remove the last label
			GObject obj=getElementAt(col*10,space+unit*names.get(i).getRank(10));
			if(obj!=null) {
				remove(obj);
			}
		}*/
	}
	
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		names.add(entry);
	}
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		removeAll();
		initialize();
		int col=getWidth()/11;
		int unit=(getHeight()-2*space)/1000;
		for (int i=0; i<names.size();i++) { //for names
			for (int j=1; j<11;i++) { //for years
				//draw line
				int x1=col*(j-1);
				int y1=space+unit*names.get(i).getRank(j);
				int x2=col*j;
				int y2=space+unit*names.get(i).getRank(j+1);
				add(new GLine(x1,y1,x2,y2));
				//draw label
				GLabel label=new GLabel(names.get(i).getName()+" "+names.get(i).getRank(j));
				add(label,x1,y1-label.getHeight());
			}
			//draw the last label
			GLabel label=new GLabel(names.get(1).getName()+" "+names.get(i).getRank(11));
			add(label,col*10,space+unit*names.get(i).getRank(11)-label.getHeight());
		}
	}
	
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
