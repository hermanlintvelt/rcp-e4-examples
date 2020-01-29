package co.wildsoft.rcpapp.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;

import co.wildsoft.rcpapp.parts.SamplePart;

public class RefreshSuppliersHandler {
	
	@Execute
	public void execute(MPart part) {
		
		if (part.getObject()!=null && part.getObject() instanceof SamplePart) {
			System.out.println("Refreshing Suppliers in "+part);
			((SamplePart)part.getObject()).refreshTable();
		} else {
			System.out.println("Incorrect Part, cannot refresh!");
		}
	}

}
