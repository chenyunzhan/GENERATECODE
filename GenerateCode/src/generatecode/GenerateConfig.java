package generatecode;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;
import org.eclipse.swt.layout.FillLayout;

public class GenerateConfig extends WizardPage implements Listener{
	
	private Map<String, Control> mContainer = null;
	public String selectPath = "";
	
	public GenerateConfig(ISelection selection, GenerateWizard currentWizard) {
		super("wizardPage");
		setTitle("generatecode wizard");
		setDescription("it will generate code");
		mContainer = new HashMap<String, Control>();
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		new Label(container, SWT.NULL).setText("File Selection:");
		final Text baseProjectPath = new Text(container, SWT.BORDER | SWT.SINGLE);
		mContainer.put("baseProjectPath", baseProjectPath);
		Button button_baseProjectPath = new Button(container, SWT.PUSH);
        button_baseProjectPath.setText("Browse...");
        button_baseProjectPath.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                handleBrowse("baseProjectPath");
            }
        });
        
        setControl(container);
	}

	private void handleBrowse(String name) {
		Text text = (Text) mContainer.get(name);
		ContainerSelectionDialog dialog = new ContainerSelectionDialog(getShell(), ResourcesPlugin.getWorkspace().getRoot(), false, "Please Select File...");
		dialog.open();
		dialog.getResult();
		for (int i = 0; i < dialog.getResult().length; i++) {
			text.setText(dialog.getResult()[i].toString());
			selectPath = Platform.getLocation().toString() + dialog.getResult()[i];
			System.out.println(Platform.getLocation().toString() + dialog.getResult()[i]);
		}
		
	}

	@Override
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
		
	}
}
