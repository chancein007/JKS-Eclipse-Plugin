package com.icedust.keystorehelper.dialog;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.widgets.Shell;
import java.io.File;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import com.icedust.keystorehelper.utils.KeyStoreHelper;
import com.icedust.keystorehelper.utils.StringUtils;

public class KeyStoreImportToolUI extends TitleAreaDialog {
	private Text certificationFolderTxt;
	private Text jksStoreFileFolderTxt;
	private Text jksStoreFileNameTxt;
	private Text passwordTxt;
	private Button importKeyStoreBtn;
	private Button closeBtn;
	private Shell shell;

	public KeyStoreImportToolUI(Shell parentShell) {
		super(parentShell);
		this.shell = parentShell;
	}
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
	}
	@Override
	public void create() {
		super.create();
		// Set the title
		setTitle("Importing CA Certification files into the keystore!!!");
		// Set the message
		setMessage("This program is to be used to import CA Certification into the KeyStore file.",IMessageProvider.INFORMATION);
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		// center the dialog
		Monitor primary = newShell.getMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = newShell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		newShell.setLocation(x, y);
		newShell.setText("Generating BW6 ServiceNow plugin Shared Module");
	}

	@Override
	protected void setShellStyle(int newShellStyle) {
		int newstyle = newShellStyle & ~SWT.APPLICATION_MODAL; // turn off
		// Modeless
		newstyle |= SWT.MODELESS; /* turn on MODELESS */
		newstyle |= SWT.RESIZE;
		super.setShellStyle(newstyle);
	}

	@Override
	protected Control createDialogArea(final Composite parent) {
	
		Composite container = new Composite(parent, SWT.NONE);
		GridLayout topGridLayout = new GridLayout(3, false);
		topGridLayout.marginLeft = 10;
		topGridLayout.marginRight = 10;
		container.setLayout(topGridLayout);
		GridData gdcomp = new GridData(GridData.FILL_HORIZONTAL);
		// 1.Select CA certification folder
		Label certificationFolderLbl = new Label(container, SWT.None);
		certificationFolderLbl
				.setText("Please select CA Certification folder:");
		certificationFolderLbl.setLayoutData(gdcomp);

		gdcomp = new GridData(GridData.FILL_HORIZONTAL);
		gdcomp.widthHint = 150;
		gdcomp.heightHint = 15;
		certificationFolderTxt = new Text(container, SWT.BORDER);
		certificationFolderTxt.setLayoutData(gdcomp);

		Button browseCAFilesPath = new Button(container, SWT.PUSH);
		browseCAFilesPath.setText("Browse");
		gdcomp = new GridData();
		gdcomp.widthHint = 80;
		browseCAFilesPath.setLayoutData(gdcomp);
		browseCAFilesPath.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dialog = new DirectoryDialog(parent.getShell());
				String caDirectoryPath = dialog.open();
				if (caDirectoryPath != null) {
					certificationFolderTxt.setText(caDirectoryPath);
					if (StringUtils.IsNullOrEmpty(jksStoreFileFolderTxt
							.getText().trim())) {
						jksStoreFileFolderTxt.setText(caDirectoryPath);
					}
				}
			}

		});
		// 2.Select KeyStore file
		Label keyStorerLbl = new Label(container, SWT.None);
		keyStorerLbl.setText("Please select Key Store file folder:");
		gdcomp = new GridData(GridData.FILL_HORIZONTAL);
		certificationFolderLbl.setLayoutData(gdcomp);

		gdcomp = new GridData(GridData.FILL_HORIZONTAL);
		gdcomp.widthHint = 150;
		gdcomp.heightHint = 15;
		jksStoreFileFolderTxt = new Text(container, SWT.BORDER);
		jksStoreFileFolderTxt.setLayoutData(gdcomp);

		Button browseWSDLFilePath = new Button(container, SWT.PUSH);
		browseWSDLFilePath.setText("Browse");
		gdcomp = new GridData();
		gdcomp.widthHint = 80;
		browseWSDLFilePath.setLayoutData(gdcomp);
		browseWSDLFilePath.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dialog = new DirectoryDialog(parent.getShell());
				String jksStoreFileFolderPath = dialog.open();
				if (jksStoreFileFolderPath != null) {
					jksStoreFileFolderTxt.setText(jksStoreFileFolderPath);
				}
			}

		});

		// 3.KeyStore file name
		Label jksStoreFileNameLbl = new Label(container, SWT.None);
		jksStoreFileNameLbl.setText("Please enter JKS Store File Name:");
		gdcomp = new GridData();
		jksStoreFileNameLbl.setLayoutData(gdcomp);
		jksStoreFileNameTxt = new Text(container, SWT.BORDER);
		gdcomp = new GridData(GridData.FILL_HORIZONTAL);
		gdcomp.horizontalSpan = 2;
		jksStoreFileNameTxt.setLayoutData(gdcomp);

		// 4.Password Text
		Label jksPassWordLbl = new Label(container, SWT.None);
		jksPassWordLbl.setText("Please enter JKS Store Password:");
		gdcomp = new GridData();
		jksPassWordLbl.setLayoutData(gdcomp);
		passwordTxt = new Text(container, SWT.BORDER | SWT.PASSWORD);
		gdcomp = new GridData(GridData.FILL_HORIZONTAL);
		gdcomp.horizontalSpan = 2;
		passwordTxt.setLayoutData(gdcomp);
		// 5.Button
		Label placeHolderLbl = new Label(container, SWT.None);
		placeHolderLbl.setText("");
		placeHolderLbl = new Label(container, SWT.None);
		placeHolderLbl.setText("");

		gdcomp = new GridData();
		gdcomp.horizontalSpan = 2;
		Composite buttonBarComposite = new Composite(container, SWT.None);
		buttonBarComposite.setLayoutData(gdcomp);

		GridLayout buttonBarGridLayout = new GridLayout(3, false);
		buttonBarGridLayout.marginWidth = 15;
		buttonBarGridLayout.horizontalSpacing = 0;
		buttonBarComposite.setLayout(buttonBarGridLayout);

		importKeyStoreBtn = new Button(buttonBarComposite, SWT.PUSH);
		importKeyStoreBtn.setEnabled(true);
		importKeyStoreBtn.setText("Run");
		gdcomp = new GridData();
		gdcomp.widthHint = 110;
		importKeyStoreBtn.setLayoutData(gdcomp);
		importKeyStoreBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!validateSucc()) {
					return;
				}
				generateKeyStore();
			}
		});

		gdcomp = new GridData(GridData.HORIZONTAL_ALIGN_END
				| GridData.FILL_HORIZONTAL);
		buttonBarComposite.setLayoutData(gdcomp);
		closeBtn = new Button(buttonBarComposite, SWT.None);
		closeBtn.setText("Close");
		closeBtn.setLayoutData(gdcomp);
		closeBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				close();
			}
		});

		return parent;

	}
	private boolean validateSucc(){
		String certFolder=certificationFolderTxt.getText().trim();
		if(StringUtils.IsNullOrEmpty(certFolder)){
			MessageDialog.openError(shell,"Validation Error","Please select CA Certification folder.");
			return false;
		}
		String keyStoreFileFolder=jksStoreFileFolderTxt.getText().trim();
		if(StringUtils.IsNullOrEmpty(keyStoreFileFolder)){
			MessageDialog.openError(shell,"Validation Error","Please select output KeyStore file folder.");
			return false;
		}
		String keyStoreFileName=jksStoreFileNameTxt.getText().trim();
		if(StringUtils.IsNullOrEmpty(keyStoreFileName)){
			MessageDialog.openError(shell,"Validation Error","Please enter output KeyStore file name.");
			return false;
		}
		if(!keyStoreFileName.toLowerCase().endsWith(".jks")){
			MessageDialog.openError(shell,"Validation Error","Please enter KeyStore file ended with .jks.");
			return false;
		}
		String jksPassword=passwordTxt.getText().trim();
		if(StringUtils.IsNullOrEmpty(jksPassword)){
			MessageDialog.openError(shell,"Validation Error","Please input keystore password.");
			return false;
		}
		
		return true;
	}

	private void generateKeyStore() {
		try {
			importKeyStoreBtn.setText("Generating...");
			importKeyStoreBtn.setEnabled(false);
			String originalTrustFolder = certificationFolderTxt.getText().trim();
			String jksTrustStoreLocation = jksStoreFileFolderTxt.getText().trim()
					+ File.separator
					+ this.jksStoreFileNameTxt.getText().trim();
			String password = passwordTxt.getText().trim();
			KeyStoreHelper.createTrustJKSKeyStore(originalTrustFolder,jksTrustStoreLocation, password);
		} catch (Throwable e) {
			MessageDialog.openError(shell,"Exception from the process of importing",e.getLocalizedMessage());
			return;
		}
		
		MessageDialog.openInformation(shell, "Import KeyStore Succ","Import the all of the CA certification into the KeyStore file successfully!!!");
		importKeyStoreBtn.setEnabled(true);
	}

}
