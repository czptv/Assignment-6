
/*
* File: TextAndGraphics.java
* --------------------------
* This program shows an example of using text and two different
* graphics canvases in a ConsoleProgram
*/
import acm.program.*;
import acm.graphics.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ConsoleTest extends ConsoleProgram {
	public void init() {
		NameSurferEntry entry = new NameSurferEntry("Sam 58 69 99 131 168 236 278 380 467 408 466");
		println(entry.getName());
		println("The rank is: "+entry.getRank(3));
		println(entry.toString());
	}
}