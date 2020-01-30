package co.wildsoft.rcpapp.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

public class ShowDetailsHandler {
	
	@Execute
	public void execute(Shell shell, ESelectionService selectionService){
		Object selection = selectionService.getSelection();
		String details = getDetails(selection); 
		
		MessageDialog.openInformation(shell, "Selected Supplier Details", details);
	}
	
	private String getDetails(Object selection) {
		if (selection == null) {
			return "No supplier selected";
		}
		if (selection instanceof Object[]) {
			Object[] selections = (Object[])selection;
			if (selections.length == 0) return "No supplier selected";
			else return "Multiple suppliers selected";
		}
		if (selection instanceof Object) {
			return "Details: "+selection.toString();
		}
		return "Cannot deal with this selection";
	}

}
