package us.idlechatter.scribbler;

import org.eclipse.swt.widgets.*;

public class Main {
	public static void main(String... args) {
		var display = new Display();
		var window = new AppWindow(display);
		
		while(display.getShells().length > 0 && 
			  !display.getShells()[0].isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
}
