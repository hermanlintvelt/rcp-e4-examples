package co.wildsoft.swt.coffee.suppliers;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * Template for custom dialog.
 * To open:
 * 		final SuppliersDialog dialog = new SuppliersDialog(shell);
 *		dialog.open();
 */
public class SuppliersDialogTemplate extends TitleAreaDialog {

	public SuppliersDialogTemplate(Shell parentShell) {
		super(parentShell);
		setBlockOnOpen(false);
	}

	protected Control createDialogArea(Composite parent) {
		Composite composite =  (Composite)super.createDialogArea(parent);
		//TODO: add your tableViewer here
		
		//TODO: do make your table more fashionable:
//		viewer.getTable().setHeaderVisible(true);
//		viewer.getTable().setLinesVisible(true);
		return composite;
	}
	
	public int open() {
		int result =  super.open();
		setTitle("Coffee Suppliers");
		setMessage("Coffee Suppliers information");
		return result;
	}
	
	protected void cancelPressed() {
		super.cancelPressed();
		System.out.println("Suppliers NOT Updated");
	}

	protected void okPressed() {
		super.okPressed();
		System.out.println("Suppliers Updated");
	}
}
