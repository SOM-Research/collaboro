/*******************************************************************************
 * Copyright (c) 2008, 2012
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Javier Canovas (javier.canovas@inria.fr) 
 *******************************************************************************/

package fr.inria.atlanmod.collaboro.ui.views;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Panel;

import javax.swing.JRootPane;
import javax.swing.JScrollPane;

import org.apache.batik.swing.JSVGCanvas;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.w3c.dom.svg.SVGDocument;

import com.abstratt.content.ContentSupport;
import com.abstratt.content.IContentProviderRegistry.IProviderDescription;
import com.abstratt.imageviewer.Activator;
import com.abstratt.imageviewer.GraphicalViewer;
import com.abstratt.imageviewer.IGraphicalContentProvider;

public class NotationComposite extends Composite {

	private JSVGCanvas canvas;
	private Frame frame;
	
	public NotationComposite(Composite parent, SVGDocument initImage) {
		super(parent, SWT.EMBEDDED);

		canvas = new JSVGCanvas();
		canvas.setSVGDocument(initImage);
		canvas.setLayout(new BorderLayout());
        frame = SWT_AWT.new_Frame(this);
        
        Panel panel = new Panel();
		JRootPane rootPane = new JRootPane();
		panel.add(rootPane);
		java.awt.Container contentPane = rootPane.getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(BorderLayout.CENTER, new JScrollPane(canvas));
		
		frame.setLayout(new BorderLayout());
		frame.add(BorderLayout.CENTER, panel);
		frame.setEnabled(true);
	}
	
	public void setSVGDocument(SVGDocument svgImage) {
        canvas.setSVGDocument(svgImage);
        canvas.setLayout(new BorderLayout());

	}



}
