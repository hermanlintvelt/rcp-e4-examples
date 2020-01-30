package co.wildsoft.rcpapp;

import javax.inject.Inject;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.extensions.Preference;
import org.eclipse.e4.ui.workbench.lifecycle.PostContextCreate;
import org.eclipse.e4.ui.workbench.lifecycle.PreSave;
import org.eclipse.e4.ui.workbench.lifecycle.ProcessAdditions;
import org.eclipse.e4.ui.workbench.lifecycle.ProcessRemovals;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

/**
 * This is a stub implementation containing e4 LifeCycle annotated methods.<br />
 * There is a corresponding entry in <em>plugin.xml</em> (under the
 * <em>org.eclipse.core.runtime.products' extension point</em>) that references
 * this class.
 **/
@SuppressWarnings("restriction")
public class E4LifeCycle {
	
	@Inject
	@Preference(nodePath="co.wildosft.rcpapp.prefs")
	private IEclipsePreferences rootPrefs;
	
	@PostContextCreate
	void postContextCreate(IEclipseContext workbenchContext) {
		Preferences subNode = rootPrefs.node("usersettings");
		System.out.println("Loading initial pref for myKey: "+subNode.get("myKey", "default"));
	}

	@PreSave
	void preSave(IEclipseContext workbenchContext) {
		System.out.println("Storing prefs");
		Preferences subNode = rootPrefs.node("usersettings");
		subNode.put("myKey", "Some Value");
		try {
			rootPrefs.flush();
		} catch (BackingStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@ProcessAdditions
	void processAdditions(IEclipseContext workbenchContext) {
	}

	@ProcessRemovals
	void processRemovals(IEclipseContext workbenchContext) {
	}
}
