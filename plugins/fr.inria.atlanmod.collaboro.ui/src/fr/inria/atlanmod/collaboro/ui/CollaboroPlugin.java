package fr.inria.atlanmod.collaboro.ui;

import java.net.URL;

import org.apache.xpath.operations.Mod;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class CollaboroPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "fr.inria.atlanmod.collaboro.ui"; //$NON-NLS-1$

	// The shared instance
	private static CollaboroPlugin plugin;
	
	/**
	 * The constructor
	 */
	public CollaboroPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static CollaboroPlugin getDefault() {
		return plugin;
	}
	

	public static URL getInstallURL() {
		Bundle bundle = Platform.getBundle(CollaboroPlugin.PLUGIN_ID);
		if(bundle != null) {
			return bundle.getEntry("/");
		} else
			return null;
	}
	
	public static ImageDescriptor getImage(String relativePath) {
		URL url = CollaboroPlugin.getInstallURL();
		ImageDescriptor descriptor= null;
		try {
			descriptor = ImageDescriptor.createFromURL(new URL(url, relativePath));
		} catch (Exception e) {
			descriptor= ImageDescriptor.getMissingImageDescriptor();
		}
		return descriptor;
	}
	

}
