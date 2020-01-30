package co.wildsoft.rcpapp.parts;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.di.PersistState;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import co.wildsoft.rcpapp.events.EventConstants;

public class SamplePart {

	private TableViewer tableViewer;

	@Inject
	private MPart part;
		
	@Inject
	private ESelectionService selectionService;
	
	@Inject
	private IEventBroker eventBroker;
	
	@PostConstruct
	public void createComposite(Composite parent) {
		Map<String, String> state = part.getPersistedState();
		System.out.println("Loading persisted state: "+state);
				
		parent.setLayout(new GridLayout(1, false));

		Text txtInput = new Text(parent, SWT.BORDER);
		txtInput.setMessage("Enter text to mark part as dirty");
		txtInput.addModifyListener(e -> part.setDirty(true));
		txtInput.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		tableViewer = new TableViewer(parent);

		tableViewer.setContentProvider(ArrayContentProvider.getInstance());
		tableViewer.setInput(createInitialDataModel());
		tableViewer.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));
		
		tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				System.out.println("Table Selection changed");
				IStructuredSelection isel = event.getStructuredSelection();
				selectionService.setSelection(isel.size() == 1?isel.getFirstElement():isel.toArray());
//				if (isel.size() == 1) {
//					eventBroker.post("...", isel.getFirstElement());
//				} else {
//					eventBroker.post("...", isel.toList());
//				}
			}
		});
		
	}
	
	@PersistState
	public void persistState(MPart part) {
		Map<String, String> state = part.getPersistedState();
		state.put("myKey", "Hello World");
		System.out.println("Persisted state: "+state);
	}
	
	private void refreshTable() {
		System.out.println("Refreshing tableViewer");
		tableViewer.setInput(addElement((List<String>)tableViewer.getInput()));
	}

	@Inject
	@Optional
	private void subscribeTopicTodoUpdated
	    (@UIEventTopic(EventConstants.REFRESH_SUPPLIERS)
	        String filter) {
		refreshTable();
	}
	
	@Inject
	@Optional
	private void subscribeTopicTodoUpdated
	    (@UIEventTopic(EventConstants.REFRESH_SUPPLIERS)
	        Map<String, Object> eventmap) {
		System.out.println("Map version");
		refreshTable();
	}
	
	@Focus
	public void setFocus() {
		tableViewer.getTable().setFocus();
	}

	@Persist
	public void save() {
		part.setDirty(false);
	}
	
	private List<String> createInitialDataModel() {
		return Arrays.asList("Sample item 1", "Sample item 2", "Sample item 3", "Sample item 4", "Sample item 5");
	}
	
	private List<String> addElement(List<String> currentElements){
		List<String> elements = new ArrayList(currentElements);
		elements.add("Item "+LocalDateTime.now());
		return elements;
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