package co.wildsoft.rcpapp.listeners;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Shell;

public class SampleListener {
	
	@Inject private ESelectionService selectionService;
    
	private void bindTreeViewerToSelectionService(TreeViewer tv)
	{
		tv.addSelectionChangedListener(new ISelectionChangedListener()
		{
			@Override
			public void selectionChanged(SelectionChangedEvent event)
			{
				IStructuredSelection isel = (IStructuredSelection) event.getSelection();
				selectionService.setSelection(
						(isel.size() == 1 ? isel.getFirstElement() : isel.toArray()));
			}
		});

	}	

	@Inject @Optional
	public void reactOnISelection(@Named(IServiceConstants.ACTIVE_SELECTION) ISelection selection)
	{
		// This method can be removed in a pure E4 application (useless)
		if (selection.isEmpty()) return;

		// Work indirectly with your selection instance -> must extract it from ISelection
		if (selection instanceof IStructuredSelection)
		{
			IStructuredSelection isel = (IStructuredSelection) selection;
			if (isel.size() == 1)
				reactOnSelection((Object) isel.getFirstElement());
			else
			{
				reactOnSelections(isel.toArray());
			}
		}
	}   

	@Inject @Optional
	public void reactOnSelection(@Named(IServiceConstants.ACTIVE_SELECTION) Object selection)
	{
		// Work directly with your unique selection instance
	}

	@Inject @Optional
	public void reactOnSelections(@Named(IServiceConstants.ACTIVE_SELECTION) Object[] selection)
	{
		// Work directly with your selection instances. Use them as an Object array
	}

	// tracks the active part
	@Inject
	@Optional
	public void receiveActivePart(
	    @Named(IServiceConstants.ACTIVE_PART) MPart activePart) {
	    if (activePart != null) {
	        System.out.println("Active part changed "
	                + activePart.getLabel());
	    }
	}

	// tracks the active shell
	@Inject
	@Optional
	public void receiveActiveShell(
	    @Named(IServiceConstants.ACTIVE_SHELL) Shell shell) {
	    if (shell != null) {
	        System.out.println("Active shell (Window) changed");
	    }
	}

	
	
}
