package co.wildsoft.rcpapp.handlers;

import java.time.LocalDateTime;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.extensions.Preference;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.osgi.service.prefs.BackingStoreException;

public class QuitHandler {
	@Execute
	public void execute(
			@Preference(nodePath="co.wildsoft.rcp.sample") IEclipsePreferences prefs,
			IWorkbench workbench, Shell shell){
		if (MessageDialog.openConfirm(shell, "Confirmation",
				"Do you want to exit?")) {
			
			prefs.put("lastUpdated", LocalDateTime.now().toString());
			try {
				prefs.flush();
			} catch (BackingStoreException e) {
				e.printStackTrace();
			}
			
			workbench.close();
		}
	}
}
