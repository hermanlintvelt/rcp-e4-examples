package co.wildsoft.swt.coffee;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class CoffeeAppSwt2 {
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Hello world!");
		
		Combo combo = new Combo (shell, SWT.READ_ONLY);
		combo.setItems ("Brazilian", "Columbian", "Ethiopian");
		Rectangle clientArea = shell.getClientArea ();
		combo.setBounds (clientArea.x, clientArea.y, 200, 200);
		combo.setText("Brazilian");
		
		shell.open();
		while (!shell.isDisposed()) {
		if (!display.readAndDispatch()) display.sleep();
		}
		display.dispose();
	}

}
