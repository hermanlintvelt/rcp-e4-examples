package co.wildsoft.swt.coffee;

import java.awt.FlowLayout;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class CoffeeAppSwt3 {
	private final static String[] COFFEE_TYPES = {"Brazilian", "Columbian", "Ethiopian"};
	private final static String[] ROAST_TYPES = {"Light Roast", "Medium Roast", "Dark Roast"};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Hello world!");
		
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		shell.setLayout(layout);
		
		
		Combo coffee = new Combo (shell, SWT.READ_ONLY);
		coffee.setItems (COFFEE_TYPES);
		coffee.setText("Brazilian");

		Combo roast = new Combo (shell, SWT.READ_ONLY);
		roast.setItems (ROAST_TYPES);
		roast.setText("Medium Roast");

	    Group group1 = new Group(shell, SWT.SHADOW_IN);
	    group1.setText("How must it be grinded?");
	    group1.setLayout(new RowLayout(SWT.HORIZONTAL));
	    new Button(group1, SWT.RADIO).setText("Beans");
	    new Button(group1, SWT.RADIO).setText("Ground");
	    
	    Button purchase = new Button(shell, SWT.PUSH | SWT.FLAT);
	    purchase.setText("Purchase");
	    purchase.addSelectionListener(new SelectionListener() {

	    	@Override
	    	public void widgetSelected(SelectionEvent e) {
	    		System.out.println("Selected");

	    		MessageBox mbox = new MessageBox(shell, 
	    				SWT.ICON_QUESTION | SWT.YES | SWT.NO);
	    		mbox.setText("Purchase");
	    		String coffeeType = COFFEE_TYPES[coffee.getSelectionIndex()];
	    		String roastType = ROAST_TYPES[roast.getSelectionIndex()];
	    		mbox.setMessage(String.format("You are going to purchase %s coffee as %s. Go ahead? ", coffeeType, roastType));
	    		int result = mbox.open();

	    	}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		shell.open();
		while (!shell.isDisposed()) {
		if (!display.readAndDispatch()) display.sleep();
		}
		display.dispose();
	}

}
