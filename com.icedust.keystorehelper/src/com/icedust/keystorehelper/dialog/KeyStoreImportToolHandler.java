package com.icedust.keystorehelper.dialog;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.handlers.HandlerUtil;

public class KeyStoreImportToolHandler extends AbstractHandler {
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		KeyStoreImportToolUI dialog = new KeyStoreImportToolUI(HandlerUtil.getActiveShell(event));
		if (dialog.open() == Window.OK) {
		}
		return null;
	}
}
