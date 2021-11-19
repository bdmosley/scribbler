package us.idlechatter.scribbler;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;

public class AppWindow {
	
	private Display display;
	private Shell shell;
	private Image image;
	private Canvas canvas;
	private Point position;
	private boolean drawing = false;
	
	public AppWindow(Display display) {
		this.display = display;
		initialize();
	}
	
	private void initialize() {
		shell = new Shell(display, SWT.SHELL_TRIM & ~SWT.RESIZE);
		shell.setText("Scribbler");
		shell.setSize(800, 600);
		shell.setLayout(new FillLayout());
		
		image = new Image(display, shell.getClientArea());
		
		canvas = new Canvas(shell, SWT.NONE);
		canvas.addMouseListener(MouseListener.mouseDownAdapter(e -> { drawing = true; position = new Point(e.x, e.y); }));
		canvas.addMouseListener(MouseListener.mouseUpAdapter(e -> { drawing = false; }));
		canvas.addMouseMoveListener(e -> {
			if (drawing) {
				var black = display.getSystemColor(SWT.COLOR_BLACK);
				var g = new GC(image);
				g.setForeground(black);
				g.setLineWidth(5);
				g.drawLine(position.x, position.y, e.x, e.y);
				g.dispose();
				position = new Point(e.x, e.y);
			}
		});
		canvas.addPaintListener(e -> { e.gc.drawImage(image, 0, 0); canvas.redraw(); });
		
		shell.open();
	}
}
