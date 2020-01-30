package co.wildsoft.rcpapp;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.e4.ui.workbench.lifecycle.PostContextCreate;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.swt.widgets.Display;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

public class SamplePrefs {
	
	@PostContextCreate
	public void postContextCreate(IApplicationContext applicationContext, Display display) {
		System.out.println("App Context created!");		
	}
	
	public void saveSamplePreferences() {
		Preferences prefs = InstanceScope.INSTANCE.getNode("co.wildsoft.rcpapp.sample");
		Preferences subNode = prefs.node("subnode");
		subNode.put("myKey", "someValue");
		subNode.putInt("myIntKey", 1000);
		try {
			prefs.flush();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}
	
	public void readSamplePreferences() {
		Preferences prefs = InstanceScope.INSTANCE.getNode("co.wildsoft.rcpapp.sample");
		Preferences subNode = prefs.node("subnode");
		String value = subNode.get("myKey", "defaultValue");
		int ivalue = subNode.getInt("myIntKey", 0);
	}

}
