package co.wildsoft.rcpapp.handlers;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Shell;
public class OpenHandler {

	boolean failure = false;
	@Execute
	public void execute(Shell shell){
//		FileDialog dialog = new FileDialog(shell);
//		dialog.open();
		
		IRunnableWithProgress commitJob = new IRunnableWithProgress(){
			public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
				System.out.println("Doing longrunning job...");
				monitor.beginTask("Doing some long operation", 10);
				try {
					for (int i = 0; i < 10; i++) {
						if (monitor.isCanceled()) {
							//cancelled
							System.out.println("Cancelled job");
							return;
						}
						try {
							System.out.println("sleeping 1 second..");
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							//cancelled
						}
						monitor.worked(1);
					}
				} finally {
					monitor.done();
				};		    	
			}
		};

		try {
			new ProgressMonitorDialog(shell).run(true, true, commitJob);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
