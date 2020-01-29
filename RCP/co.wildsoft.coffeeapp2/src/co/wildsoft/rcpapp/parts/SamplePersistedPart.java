package co.wildsoft.rcpapp.parts;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.di.PersistState;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class SamplePersistedPart {

	private TableViewer tableViewer;

	@Inject
	private MPart part;

	@PostConstruct
	public void createComposite(Composite parent) {
		
		Map<String, String> state = part.getPersistedState();
		String myStateValue = state.getOrDefault("myStateValue", "Nothing");
				
		parent.setLayout(new GridLayout(1, false));

		Text txtInput = new Text(parent, SWT.BORDER);
		txtInput.setMessage("Enter text to mark part as dirty");
		txtInput.addModifyListener(e -> part.setDirty(true));
		txtInput.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		tableViewer = new TableViewer(parent);

		tableViewer.setContentProvider(ArrayContentProvider.getInstance());
		tableViewer.setInput(createInitialDataModel());
		tableViewer.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));
	}
	
	@PersistState
	public void persistState(MPart part) {
		Map<String, String> state = part.getPersistedState();
		state.put("myStateValue", "Hello world");
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