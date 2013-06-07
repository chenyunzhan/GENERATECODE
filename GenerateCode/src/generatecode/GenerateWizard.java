package generatecode;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.xml.transform.TransformerException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

public class GenerateWizard extends Wizard implements INewWizard{
	
	private ISelection selection;
	private GenerateConfig page;

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
	}
	
	

	@Override
	public void addPages() {
		try {
            page = new GenerateConfig(selection, this);
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
		try {
			CodegenEngine.generatecode();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
