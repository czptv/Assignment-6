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
		double col=getWidth()/11;
		//vertical lines
		for (int i=1; i<11;i++) {
			add(new GLine(col*i,0,col*i,getHeight()));
		}
		//horizontal lines
		add(new GLine(0,space,getWidth(),space));
		add(new GLine(0,getHeight()-space,getWidth(),getHeight()-space));
		
		//draw labels
		add(new GLabel("1900",0,getHeight()));
		add(new GLabel("1910",col,getHeight()));
		add(new GLabel("1920",col*2,getHeight()));
		add(new GLabel("1930",col*3,getHeight()));
		add(new GLabel("1940",col*4,getHeight()));
		add(new GLabel("1950",col*5,getHeight()));
		add(new GLabel("1960",col*6,getHeight()));
		add(new GLabel("1970",col*7,getHeight()));
		add(new GLabel("1980",col*8,getHeight()));
		add(new GLabel("1990",col*9,getHeight()));
		add(new GLabel("2000",col*10,getHeight()));
	}
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		while(!names.isEmpty()) {
			names.remove(0);
		}
	}
	
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		if (entry!=null) {
			names.add(entry);
		}
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
		double col=getWidth()/11;
		double unit=(getHeight()-2*space)/1000.0;
		for (int i=0; i<names.size();i++) { //for names
			for (int j=1; j<11;j++) { //for decade
				drawLabelAndLine(i,j,unit,col);
			}
			//draw the last label
			double y=checkRank(i,11,unit);
			drawLabel(col*10,y,i,11);
		}
	}
	
	/**
	 * draw label and lines
	 */
	
	private void drawLabelAndLine(int nameOrder, int decadeOrder, double unit, double col) {
		//draw line
		double x1=col*(decadeOrder-1);
		double x2=col*decadeOrder;
		double y1=checkRank(nameOrder, decadeOrder, unit);
		double y2=checkRank(nameOrder, decadeOrder+1, unit);
		add(new GLine(x1,y1,x2,y2));
		//draw label
		drawLabel(x1,y1,nameOrder, decadeOrder);
	}
	
	/**
	 * check whether the rank is 0
	 * @param nameOrder: order of name in the ArrayList
	 * @param decadeOrder: the decade away from the starting decade
	 * @param unit: the height of one unit out of 1000 of the middle part of the columns
	 * @return value of y
	 */
	
	private double checkRank(int nameOrder, int decadeOrder, double unit){
		double rank=names.get(nameOrder).getRank(decadeOrder);
		double y;
		if (rank!=0) {
			y=space+unit*rank;
		} else {
			y=getHeight()-space;
		}
		return y;
	}
	
	/**
	 * draw Label
	 * @param x: x parameter of the label
	 * @param y: y parameter of the label
	 * @param nameOrder: order of name in the ArrayList
	 * @param decadeOrder: the decade away from the starting decade
	 */
	
	private void drawLabel(double x, double y, int nameOrder, int decadeOrder) {
		GLabel label;
		if (names.get(nameOrder).getRank(decadeOrder)==0) {
			label=new GLabel(names.get(nameOrder).getName()+" *");
		} else {
			label=new GLabel(names.get(nameOrder).getName()+" "+names.get(nameOrder).getRank(decadeOrder));
		}
		add(label,x,y);
	}
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
