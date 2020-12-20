package ij.gui;
import java.awt.*;

import javax.swing.JPanel;

import ij.*;

/** This class is used by GenericDialog to add images to dialogs. */
public class ImagePanel extends JPanel {
	private ImagePlus img;
	private int width, height;
	 
	ImagePanel(ImagePlus img) {
		this.img = img;
		width = img.getWidth();
		height = img.getHeight();
	}

	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

	public Dimension getMinimumSize() {
		return new Dimension(width, height);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img.getProcessor().createImage(), 0, 0, null);
	}

}
