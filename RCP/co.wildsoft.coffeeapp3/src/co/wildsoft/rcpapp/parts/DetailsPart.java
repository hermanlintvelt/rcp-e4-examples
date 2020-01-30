
package co.wildsoft.rcpapp.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class DetailsPart {

	@Inject
	private ESelectionService service;
	
	private Label detailsLabel;

	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new FillLayout());
		
		detailsLabel = new Label(parent, SWT.HORIZONTAL | SWT.WRAP);
		detailsLabel.setText("No supplier selected");
	}
	
	private void updateDetails(String details) {
		if (detailsLabel!=null) detailsLabel.setText("Details: "+details);
	}
	
	private void clearDetails() {
		if (detailsLabel!=null)  detailsLabel.setText("No supplier selected");
	}

	@Inject @Optional
	public void reactOnSelection(@Named(IServiceConstants.ACTIVE_SELECTION) Object selection)
	{
		if (selection != null && !(selection instanceof Object[])) {
			System.out.println("Single selection: "+selection);
			updateDetails(selection.toString());
		} else {
			System.out.println("Empty selection");
			clearDetails();
		}
		
	}

	@Inject @Optional
	public void reactOnSelections(@Named(IServiceConstants.ACTIVE_SELECTION) Object[] selection)
	{
		if (selection != null && selection.length > 0){
			updateDetails("Multiple suppliers selected");
		} else {
			System.out.println("Empty selection");
			clearDetails();
		}
	}



}