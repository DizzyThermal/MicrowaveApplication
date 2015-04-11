package com.teamdc.stephendiniz.microwave;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI extends JFrame {

	public GUI(String windowTitle, int width, int height) {
		initialize(windowTitle, width, height);
	}
	
	private void initialize(String windowTitle, int width, int height) {
		BufferedImage microwave;
		JLabel microwaveLabel = null;
		try {
			microwave = ImageIO.read(getClass().getResource("/images/microwave.png"));
			microwaveLabel = new JLabel(new ImageIcon(microwave));
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}

		JPanel panel = new JPanel();

		panel.add(microwaveLabel);
		
		add(panel);
		
		this.setTitle(windowTitle);
		this.setSize(new Dimension(width, height));
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
