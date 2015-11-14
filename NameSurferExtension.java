/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurferExtension extends Program implements NameSurferConstants {

	/**
	 * instant constants
	 */
	
	private JTextField nameField;
	private NameSurferGraph graph;
	
/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the top of the window.
 */
	public void init() {
		nameField=new JTextField(20);
		graph=new NameSurferGraph();
		
		add(graph);
		add(new JLabel("Name"), NORTH);
		add(nameField,NORTH);
		nameField.setActionCommand("Graph");
		nameField.addActionListener(this);
		add(new JButton("Graph"),NORTH);
		add(new JButton("Clear"),NORTH);
		
		addActionListeners();
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand();
		if(cmd.equals("Graph")) {
			NameSurferDataBase db=new NameSurferDataBase("names-data.txt");
			NameSurferEntry entry=db.findEntry(nameField.getText());
			graph.addEntry(entry);
			graph.update();
		} else if(cmd.equals("Clear")) {
			graph.clear();
			graph.update();
		}
	}
}
