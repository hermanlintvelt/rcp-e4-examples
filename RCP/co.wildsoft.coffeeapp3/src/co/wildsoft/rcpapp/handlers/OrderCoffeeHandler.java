package co.wildsoft.rcpapp.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

public class OrderCoffeeHandler {
	@Execute
	public void execute(Shell shell){
		MessageDialog.openInformation(shell, "Order Coffee", "Eventually this will order coffee for you.");
	}
}
