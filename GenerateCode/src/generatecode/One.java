package generatecode;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

public class One extends Wizard implements INewWizard{
	
	private ISelection selection;
	private Two page;

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
	}
	
	

	@Override
	public void addPages() {
		try {
            page = new Two(selection, this);
            addPage(page);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}



	@Override
	public boolean performFinish() {
		IRunnableWithProgress op = new IRunnableWithProgress() {
            public void run(IProgressMonitor monitor) throws InvocationTargetException {
                try {
                    doFinish(monitor);
                } catch (CoreException e) {
                    e.printStackTrace();
                    throw new InvocationTargetException(e);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    monitor.done();
                }
            }
        };
        try {
            getContainer().run(true, false, op);
        } catch (InterruptedException e) {
            return false;
        } catch (InvocationTargetException e) {
            Throwable realException = e.getTargetException();
            MessageDialog.openError(getShell(), "Error", realException.getMessage());
            return false;
        }
        return true;
	}
	
	private void doFinish(IProgressMonitor monitor) throws CoreException {
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		CodegenEngine.generatecode();
	}

}
