 
package co.wildsoft.coffeeapp.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.CanExecute;

public class MyAction {
	@Execute
	public void execute() {
		
	}
	
	
	@CanExecute
	public boolean canExecute() {
		
		return true;
	}
		
}