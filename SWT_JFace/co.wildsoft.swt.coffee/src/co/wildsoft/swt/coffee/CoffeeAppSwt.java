package co.wildsoft.swt.coffee;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import co.wildsoft.swt.coffee.suppliers.SuppliersDialog;
import co.wildsoft.swt.coffee.wizards.*;

public class CoffeeAppSwt {
	private final static String[] COFFEE_TYPES = {"Brazilian", "Columbian", "Ethiopian", "South African"};
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
		GridData layoutData = new GridData();
		layoutData.widthHint = 250;
		layoutData.horizontalAlignment = SWT.FILL;
		coffee.setLayoutData(layoutData);

		Combo roast = new Combo (shell, SWT.READ_ONLY);
		roast.setItems (ROAST_TYPES);
		roast.setText("Medium Roast");
		GridData layoutData2 = new GridData();
		layoutData2.widthHint = 250;
		layoutData2.horizontalAlignment = SWT.FILL;
		roast.setLayoutData(layoutData2);

	    Group group1 = new Group(shell, SWT.SHADOW_IN);
	    group1.setText("How must it be grinded?");
	    group1.setLayout(new RowLayout(SWT.HORIZONTAL));
	    new Button(group1, SWT.RADIO).setText("Beans");
	    new Button(group1, SWT.RADIO).setText("Ground");
		GridData layoutData3 = new GridData();
		layoutData3.widthHint = 250;
		layoutData3.horizontalAlignment = SWT.FILL;
		group1.setLayoutData(layoutData3);
	    
	    Button purchase = new Button(shell, SWT.PUSH | SWT.FLAT);
	    purchase.setText("Purchase");
		GridData layoutData4 = new GridData();
		layoutData4.widthHint = 150;
		layoutData4.horizontalAlignment = SWT.CENTER;
		purchase.setLayoutData(layoutData4);
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

		final Button suppliersButton = new Button(shell, SWT.PUSH | SWT.FLAT);
		suppliersButton.setText("Suppliers");
		GridData layoutData5 = new GridData();
		layoutData5.widthHint = 150;
		layoutData5.horizontalAlignment = SWT.CENTER;
		suppliersButton.setLayoutData(layoutData5);
		suppliersButton.addListener(SWT.Selection, event -> openSuppliersDialog(shell));

		final Button grindButton = new Button(shell, SWT.PUSH);
		grindButton.setText("Grind Beans");
		GridData layoutData6 = new GridData();
		layoutData6.widthHint = 150;
		layoutData6.horizontalAlignment = SWT.CENTER;
		grindButton.setLayoutData(layoutData6);
		grindButton.addListener(SWT.Selection, event -> doGrind(shell));

		final Button grindButton2 = new Button(shell, SWT.PUSH);
		grindButton2.setText("Grind Beans (Wizard)");
		GridData layoutData7 = new GridData();
		layoutData7.widthHint = 150;
		layoutData7.horizontalAlignment = SWT.CENTER;
		grindButton2.setLayoutData(layoutData7);
		grindButton2.addListener(SWT.Selection, event -> openGrindWizard(shell));

		shell.open();
		while (!shell.isDisposed()) {
		if (!display.readAndDispatch()) display.sleep();
		}
		display.dispose();
	}

	private static void openSuppliersDialog(Shell shell){
		final SuppliersDialog dialog = new SuppliersDialog(shell);
		dialog.open();
	}
	
	private static void doGrind(Shell shell){
		try{
			IRunnableWithProgress op = new IRunnableWithProgress(){
				public void run(IProgressMonitor monitor){
					monitor.beginTask("Grinding Beans...", 100);
					for(int i=0; i<100; i++){
						if (monitor.isCanceled()) {
							System.out.println("Cancelled!");
							return;
						}
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						monitor.worked(1);
					}
					monitor.done();
				}
			};
			new ProgressMonitorDialog(
						Display.getCurrent().getActiveShell()).run(true, true, op);
		} catch (InvocationTargetException e){
			//TODO: handle exception
		} catch (InterruptedException e){
			//TODO: handle cancellation
		}
	}

	private static void openGrindWizard(Shell shell) {
		final WizardDialog dialog = 
			new WizardDialog(Display.getCurrent().getActiveShell(),
						 new GrindWizard());
		dialog.open();
	}

}
