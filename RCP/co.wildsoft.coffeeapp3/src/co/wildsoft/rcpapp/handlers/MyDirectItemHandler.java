package co.wildsoft.rcpapp.handlers;

import java.util.Date;
import java.util.List;

import org.eclipse.e4.ui.di.AboutToHide;
import org.eclipse.e4.ui.di.AboutToShow;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.commands.MCommand;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.menu.MDirectMenuItem;
import org.eclipse.e4.ui.model.application.ui.menu.MHandledMenuItem;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuElement;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuFactory;
import org.eclipse.e4.ui.workbench.modeling.EModelService;

public class MyDirectItemHandler {
	@AboutToShow
	public void aboutToShow(List<MMenuElement> items, MApplication application, EModelService modelService) {
	    MDirectMenuItem dynamicItem = MMenuFactory.INSTANCE
	            .createDirectMenuItem();
	    dynamicItem.setLabel("Dynamic Menu Item (" + new Date() + ")");
	    dynamicItem.setContributorURI("platform:/plugin/co.wildsoft.rcpapp");
	    dynamicItem
	            .setContributionURI("bundleclass://co.wildsoft.rcpapp/co.wildsoft.rcpapp.handlers.SaveHandler");    
	        items.add(dynamicItem);
	        
	   
	   //lookup openCommand
	   
	   MCommand openCommand = application.getCommand("org.eclipse.ui.file.open");
	   if (openCommand != null) {
		   MHandledMenuItem handledMenu = MMenuFactory.INSTANCE.createHandledMenuItem();
		   handledMenu.setLabel("Handled menu Item (" + new Date() + ")");
		   handledMenu.setCommand(openCommand);
		   items.add(handledMenu);
	   }	 
	   
	   //modelService.findElements(application, MPart.class, "some.id", null);
	   
	   //MMenuFactory.INSTANCE.cre
	}
	
	@AboutToHide
	public void aboutToHide() {
		System.out.println("hiding dynamic menu");
	}
}
