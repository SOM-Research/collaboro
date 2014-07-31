package fr.inria.atlanmod.collaboro.web.servlets;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;

import sun.misc.BASE64Encoder;

public abstract class AbstractRendererServlet extends AbstractCollaboroServlet {
	private static final long serialVersionUID = 18L;

	// The main path to the working dir (needed for generating the pictures)
	public static File workingDir = null;

	// The path to the Graphviz DOT execitable (needed for generating the pictures)
	public static String dotExePath = null;

	@Override
	public void init() throws ServletException {
		super.init();
		String workingDirString = properties.getProperty("workingDir");
		dotExePath = properties.getProperty("dotExePath");

		// We need a File (not a String)
		workingDir = new File(workingDirString);
		if(!workingDir.isDirectory()) throw new ServletException("The working dir does not exist");
	}
	
	/**
	 * Encodes a JPG picture into the BASE64 format
	 * 
	 * @param imagePath
	 * @return
	 * @throws IOException
	 */
	String encodeToString(File imagePath) throws IOException {
		BufferedImage image = ImageIO.read(imagePath);

		String imageString = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		try {
			ImageIO.write(image, "JPG", bos);
			byte[] imageBytes = bos.toByteArray();

			BASE64Encoder encoder = new BASE64Encoder();
			imageString = encoder.encode(imageBytes);

			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imageString;
	}
}
