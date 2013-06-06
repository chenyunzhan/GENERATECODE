package generatecode;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

public class Two extends WizardPage implements Listener{
	
	public Two(ISelection selection, One currentWizard) {
		super("wizardPage");
		setTitle("generatecode wizard");
		setDescription("it will generate code");
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		setControl(container);

	}

	@Override
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
		
	}
}
