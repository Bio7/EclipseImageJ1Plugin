/*******************************************************************************
 * Copyright (c) 2005-2017 M. Austenfeld
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     M. Austenfeld
 *******************************************************************************/

package com.eco.bio7.image;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.part.ViewPart;

import ij.IJ;

public class IJtoolbar extends ViewPart {

	private IJtoolbarSwtAwt toolBar;
	private Composite topIJtoolbarSwtAwt;
	public static IJtoolbar instance;

	public IJtoolbar() {
		instance = this;
	}

	public void createPartControl(Composite parent) {

		getViewSite().getPage().addPartListener(new IPartListener2() {

			public void partActivated(IWorkbenchPartReference partRef) {
				if (partRef.getId().equals("com.eco.bio7.ijtoolbar")) {
					if (Util.getOS().equals("Mac")) {
						if (topIJtoolbarSwtAwt != null && topIJtoolbarSwtAwt.isDisposed() == false) {

							Display dis = Util.getDisplay();
							dis.asyncExec(new Runnable() {

								public void run() {

									toolBar.top.setVisible(false);
									toolBar.top.setVisible(true);
								}
							});
						}

					}

					/*
					 * SwingUtilities.invokeLater(new Runnable() { // !! public void run() { if (jpp
					 * != null) { jpp.doLayout(); jpp.repaint(); } } });
					 */

				}

			}

			public void partBroughtToTop(IWorkbenchPartReference partRef) {
				if (partRef.getId().equals("com.eco.bio7.ijtoolbar")) {
					if (Util.getOS().equals("Mac")) {
						if (topIJtoolbarSwtAwt != null && topIJtoolbarSwtAwt.isDisposed() == false) {
							toolBar.top.setVisible(false);
							toolBar.top.setVisible(true);
						}

					}

					/*
					 * SwingUtilities.invokeLater(new Runnable() { // !! public void run() { if (jpp
					 * != null) { jpp.doLayout(); jpp.repaint(); } } });
					 */
				}

			}

			public void partClosed(IWorkbenchPartReference partRef) {
				
				Display dis = Util.getDisplay();
				dis.syncExec(new Runnable() {

					public void run() {
						/*
						 * Composite top = toolBar.getTop(); Shell shell = toolBar.getParent();
						 * top.setParent(shell);
						 */
					}
				});
			}

			public void partDeactivated(IWorkbenchPartReference partRef) {

			}

			public void partHidden(IWorkbenchPartReference partRef) {

			}

			public void partInputChanged(IWorkbenchPartReference partRef) {

			}

			public void partOpened(IWorkbenchPartReference partRef) {

			}

			public void partVisible(IWorkbenchPartReference partRef) {

			}
		});
		/*
		 * On MacOSX the javaFX integration works fine and avoids some errors but has lower resolution!
		 */
		boolean enable = false;
		if (enable) { //
			SwingUtilities.invokeLater(new Runnable() {
				// !!
				public void run() {
					// jpp.repaint();

					JPanel jpp = new JPanel();
					jpp.setLayout(new GridLayout(2, 1));
					jpp.add(IJ.getInstance().toolbar);
					jpp.add(IJ.getInstance().statusBar);
					Display display = Util.getDisplay();
					display.asyncExec(new Runnable() {
						public void run() {
							SwingFxSwtView view = new SwingFxSwtView();
							view.embedd(parent, jpp);
						}
					});
				}
			});
		}

		/*
		 * On Windows and Linux we use the SWT_AWT bridge!
		 */
		else {
			Display dis = Util.getDisplay();
			dis.syncExec(new Runnable() {

				public void run() {
					Color col=parent.getBackground();
					toolBar = new IJtoolbarSwtAwt(col);
					topIJtoolbarSwtAwt = toolBar.getTop();
					topIJtoolbarSwtAwt.setParent(parent);
				}
			});
		}

	}

	public void setFocus() {

	}

	public void dispose() {
		toolBar.getParent().dispose();
		super.dispose();
	}

}
