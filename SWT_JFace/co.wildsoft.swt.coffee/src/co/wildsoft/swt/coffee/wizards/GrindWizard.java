package co.wildsoft.swt.coffee.wizards;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.Wizard;

public class GrindWizard extends Wizard {
	
	public GrindWizard() {
		setWindowTitle("Grinding is an art");
		addPage(new GrindPage());
		setNeedsProgressMonitor(true);
		
		
		//IProgressMonitor source: org.eclipse.equinox.common.source....jar
	}

	@Override
	public boolean performFinish() {
		//setNeedsProgressMonitor(true);
		IRunnableWithProgress op = new IRunnableWithProgress(){
			public void run(IProgressMonitor monitor){
				monitor.beginTask("Grinding Beans...", 100);
				for(int i=0; i<100; i++){
					if (monitor.isCanceled()) {
						System.out.println("Cancelled!");
						return;
					}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					monitor.worked(1);
				}
				monitor.done();
			}
		};
		try {
			getContainer().run(true, true, op);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

}
