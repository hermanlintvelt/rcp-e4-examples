package co.wildsoft.rcpapp.handlers;

import java.util.HashMap;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;

import co.wildsoft.rcpapp.events.EventConstants;
import co.wildsoft.rcpapp.parts.SamplePart;

public class RefreshSuppliersHandler {
	
	@Execute
	public void execute(IEventBroker eventBroker) {
		
		eventBroker.post(EventConstants.REFRESH_SUPPLIERS, "something");
		eventBroker.post(EventConstants.REFRESH_SUPPLIERS, new HashMap<String, Object>());
		
//		if (part.getObject()!=null && part.getObject() instanceof SamplePart) {
//			System.out.println("Refreshing Suppliers in "+part);
//			((SamplePart)part.getObject()).refreshTable();
//		} else {
//			System.out.println("Incorrect Part, cannot refresh!");
//		}
	}

}
