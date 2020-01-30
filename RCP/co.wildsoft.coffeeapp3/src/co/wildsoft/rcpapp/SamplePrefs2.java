package co.wildsoft.rcpapp;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.workbench.lifecycle.PostContextCreate;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.swt.widgets.Display;
import org.eclipse.e4.core.di.extensions.Preference;

public class SamplePrefs2 {
	
	@Inject
	@Optional
	public void trackDarkThemeSetting(
			@Preference(nodePath="co.wildsoft.rcp.sample", value="darkTheme") boolean useDarkTheme) {
		System.out.println("pref.darkTheme = "+useDarkTheme);
		
	}
	
}

//@PostContextCreate
//public void postContextCreate(IApplicationContext applicationContext, Display display) {
//	System.out.println("App Context created!");		
//}
