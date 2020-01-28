package co.wildsoft.swt.coffee.wizards;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

public class GrindPage extends WizardPage {
	public GrindPage() {
		super(".onegrind", "Grinding Preferences", null);
		setDescription("Set your grinding preferences");
	}

	@Override
	public void createControl(Composite parent) {
		final Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FillLayout());
		
	    Group group1 = new Group(composite, SWT.SHADOW_IN);
	    group1.setText("How must it be grinded?");
	    group1.setLayout(new RowLayout(SWT.HORIZONTAL));
	    new Button(group1, SWT.RADIO).setText("Finest (Espresso)");
	    new Button(group1, SWT.RADIO).setText("Finer (Drip Coffee)");
	    new Button(group1, SWT.RADIO).setText("Coarser (Plunger)");

		setControl(composite); //NB!
	}

}
