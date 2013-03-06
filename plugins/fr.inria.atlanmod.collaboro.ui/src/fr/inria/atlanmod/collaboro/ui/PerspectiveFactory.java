/*******************************************************************************
 * Copyright (c) 2008, 2012
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Guillaume Doux (guillaume.doux @ inria.fr) 
 *******************************************************************************/
package fr.inria.atlanmod.collaboro.ui;


import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import fr.inria.atlanmod.collaboro.ui.views.CollaborationView;
import fr.inria.atlanmod.collaboro.ui.views.NotationView;
import fr.inria.atlanmod.collaboro.ui.views.VersionView;

public class PerspectiveFactory implements IPerspectiveFactory {

	private static final String ORG_ECLIPSE_EMF_CDO_UI_CDO_SESSIONS_VIEW = "org.eclipse.emf.cdo.ui.CDOSessionsView";

	@Override
	public void createInitialLayout(IPageLayout layout) {

		// Get the editor area.
		String editorArea = layout.getEditorArea();
		// Top left: Resource Project Explorer view, CDO Session view and Bookmarks view placeholder
		IFolderLayout topLeft = layout.createFolder("topLeft", IPageLayout.LEFT, 0.25f,
				editorArea);
		topLeft.addView(IPageLayout.ID_PROJECT_EXPLORER);
		topLeft.addView(ORG_ECLIPSE_EMF_CDO_UI_CDO_SESSIONS_VIEW);
		topLeft.addPlaceholder(IPageLayout.ID_BOOKMARKS);

		// Top Right: version view
		IFolderLayout topRight = layout.createFolder("topRight", IPageLayout.RIGHT, 0.66f,
				editorArea);
		topRight.addView(VersionView.ID);

		// middle Right: Collaboration view
		IFolderLayout middleRight = layout.createFolder("middleRight", IPageLayout.BOTTOM, 0.66f,
				"topRight");
		middleRight.addView(CollaborationView.ID);
		
		// Bottom left: Notation view
		IFolderLayout bottomLeft = layout.createFolder("bottomLeft", IPageLayout.BOTTOM, 0.50f,
				"topLeft");
		bottomLeft.addView(NotationView.ID);
		bottomLeft.addPlaceholder(IPageLayout.ID_OUTLINE);
		bottomLeft.addPlaceholder(IPageLayout.ID_TASK_LIST);


		// Bottom center: Property sheet view
		IFolderLayout bottomCenter = layout.createFolder("bottomCenterAndRight", IPageLayout.BOTTOM, 0.66f,
				editorArea);
		bottomCenter.addView(IPageLayout.ID_PROP_SHEET);
		


	}

}
