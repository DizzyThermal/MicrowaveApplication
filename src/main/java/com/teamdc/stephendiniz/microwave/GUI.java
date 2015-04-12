package com.teamdc.stephendiniz.microwave;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI extends JFrame {

	private final int X_DISPLAY = 1045;
	private final int Y_DISPLAY = 115;
	private final int W_DISPLAY = 52;
	private final int H_DISPLAY = 34;
	
	private final int X_BUTTONS = 1049;
	private final int Y_BUTTONS = 186;
	private final int W_BUTTONS = 107;
	private final int H_BUTTONS = 218;

	private final int DISPLAY_OFFSET = 62;
	
	private final int PAD_DISPLAY = 4;
	private final int PAD_BUTTON = 1;
	
	private final int W_MICROWAVE = 1205;
	private final int H_MICROWAVE = 516;
	
	private final int W_DIS_NUMBER = 24;
	private final int H_DIS_NUMBER = 34;
	
	private final int W_BUTTON = 35;
	private final int H_BUTTON = 37;
	
	private final int W_BUTTON_ZERO = 107;
	private final int H_BUTTON_ZERO = 31;
	
	private final int W_BUTTON_BOTTOM = 107;
	private final int H_BUTTON_BOTTOM = 18;
	
	private JPanel panel;

	private JPanel displayPanelLeft;
	private JPanel displayPanelRight;
	
	private JPanel buttonPanel;

	private BufferedImage[] displayNumbers;
	private ImageIcon[] displayIcons = new ImageIcon[10];
	private JLabel[] displayLabels = new JLabel[4];

	private BufferedImage[] buttonNumbers = new BufferedImage[10];
	private JLabel[] buttonLabels = new JLabel[10];
	
	private BufferedImage start;
	private JLabel startLabel;
	
	private BufferedImage stopClear;
	private JLabel stopClearLabel;
	
	private int numbersPresent = 0;
	private int[] numbers = new int[4];
	
	private boolean running = false;
	
	public GUI(String windowTitle, int width, int height) {
		initialize(windowTitle, width, height);
	}

	private void initialize(String windowTitle, int width, int height) {
		// Initialize Panels
		panel = new JPanel(null);

		// Create Main Microwave Image
		BufferedImage microwave;
		JLabel microwaveLabel = null;
		try {
			microwave = ImageIO.read(getClass().getResource("/images/microwave.png"));
			microwaveLabel = new JLabel(new ImageIcon(microwave));
			microwaveLabel.setBounds(0, 0, W_MICROWAVE, H_MICROWAVE);
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}

		// Add Display Labels to appropriate places
		initializeDisplayPanel();
		
		// Add Button Panel
		initializeButtonPanel();

		// Add Items to Main Panel
		panel.add(displayPanelLeft);
		panel.add(displayPanelRight);
		panel.add(buttonPanel);
		panel.add(microwaveLabel);
		
		// Initialize JFrame and make Visible
		this.setTitle(windowTitle);
		this.setContentPane(panel);
		this.setSize(new Dimension(width, height));
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initializeDisplayPanel() {
		// Initialize Display Numbers
		BufferedImage displayNumbers_BI = null;
		try {
			displayNumbers_BI = ImageIO.read(getClass().getResource("/images/display_numbers.png"));
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		displayNumbers = new BufferedImage[10];
		int rows = 2;
		int columns = 5;
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				displayNumbers[i * columns + j] = displayNumbers_BI.getSubimage(j * W_DIS_NUMBER, i * H_DIS_NUMBER, W_DIS_NUMBER, H_DIS_NUMBER);
				displayIcons[i * columns + j] = new ImageIcon(displayNumbers[i * columns + j]);
			}
		}

		// Initialize Panels
		displayPanelLeft = new JPanel(null);
		displayPanelRight = new JPanel(null);

		// Set Background Color
		displayPanelLeft.setBackground(new Color(0));
		displayPanelRight.setBackground(new Color(0));
		
		// Set Positions
		displayPanelLeft.setBounds(X_DISPLAY, Y_DISPLAY, W_DISPLAY, H_DISPLAY);
		displayPanelRight.setBounds((X_DISPLAY + DISPLAY_OFFSET), Y_DISPLAY, W_DISPLAY, H_DISPLAY);
		
		// First Number
		displayLabels[0] = new JLabel(displayIcons[0]);
		displayLabels[0].setBounds((0 * W_DIS_NUMBER), 0, W_DIS_NUMBER, H_DIS_NUMBER);
		displayPanelLeft.add(displayLabels[0]);
		
		// Second Number
		displayLabels[1] = new JLabel(displayIcons[0]);
		displayLabels[1].setBounds((1 * W_DIS_NUMBER + PAD_DISPLAY), 0, W_DIS_NUMBER, H_DIS_NUMBER);
		displayPanelLeft.add(displayLabels[1]);
		
		// Third Number
		displayLabels[2] = new JLabel(displayIcons[0]);
		displayLabels[2].setBounds((0 * W_DIS_NUMBER), 0, W_DIS_NUMBER, H_DIS_NUMBER);
		displayPanelRight.add(displayLabels[2]);
		
		// Fourth Number
		displayLabels[3] = new JLabel(displayIcons[0]);
		displayLabels[3].setBounds((1 * W_DIS_NUMBER + PAD_DISPLAY), 0, W_DIS_NUMBER, H_DIS_NUMBER);
		displayPanelRight.add(displayLabels[3]);
		
		// Initialize Numbers
		for(int i = 0 ; i < numbers.length; i++) {
			numbers[i] = 0;
		}
	}
	
	private void initializeButtonPanel() {
		// Initialize Buttons
		BufferedImage buttons_BI = null;
		try {
			buttons_BI = ImageIO.read(getClass().getResource("/images/buttons.png"));
			buttonNumbers[9] = ImageIO.read(getClass().getResource("/images/button_zero.png"));
			start = ImageIO.read(getClass().getResource("/images/start.png"));
			stopClear = ImageIO.read(getClass().getResource("/images/stop_clear.png"));
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		int rows = 3;
		int columns = 3;
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				buttonNumbers[i * columns + j] = buttons_BI.getSubimage(j * W_BUTTON, i * H_BUTTON, W_BUTTON, H_BUTTON);
			}
		}

		// Initialize Panel
		buttonPanel = new JPanel(null);
		
		// Set Background Color
		buttonPanel.setBackground(new Color(0));

		// Set Positions
		buttonPanel.setBounds(X_BUTTONS, Y_BUTTONS, W_BUTTONS, H_BUTTONS);
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				buttonLabels[i * columns + j] = new JLabel(new ImageIcon(buttonNumbers[i * columns + j]));
				buttonLabels[i * columns + j].setBounds((j * PAD_BUTTON) + (j * W_BUTTON), (i * 2 * PAD_BUTTON) + (i * H_BUTTON), W_BUTTON, H_BUTTON);
				buttonPanel.add(buttonLabels[i * columns + j]);
			}
		}
		buttonLabels[9] = new JLabel(new ImageIcon(buttonNumbers[9]));
		buttonLabels[9].setBounds(0, (6 * PAD_BUTTON) + (3 * H_BUTTON), W_BUTTON_ZERO, H_BUTTON_ZERO);
		buttonPanel.add(buttonLabels[9]);
		
		startLabel = new JLabel(new ImageIcon(start));
		startLabel.setBounds(0, (30 * PAD_BUTTON) + (4 * H_BUTTON), W_BUTTON_BOTTOM, H_BUTTON_BOTTOM);
		buttonPanel.add(startLabel);
		
		stopClearLabel = new JLabel(new ImageIcon(stopClear));
		stopClearLabel.setBounds(0, (15 * PAD_BUTTON) + (5 * H_BUTTON), W_BUTTON_BOTTOM, H_BUTTON_BOTTOM);
		buttonPanel.add(stopClearLabel);
		
		// Add Click Listeners
		for(int i = 0; i < (buttonLabels.length - 1); i++) {
			final int index = i;
			buttonLabels[i].addMouseListener(new MouseListener() {
				public void mousePressed(MouseEvent e) {
					playSound(getClass().getResource("/sound/beep.wav"));
					if(canShift()) {
						shiftNumbers();
						addToDisplay((index + 1), 3);
						numbersPresent++;
					}
					else {
						return;
					}
				}
				
				// Unimplemented Methods
				public void mouseClicked(MouseEvent arg0) {}
				public void mouseEntered(MouseEvent e) {}
				public void mouseExited(MouseEvent e) {}
				public void mouseReleased(MouseEvent e) {}
			});
		}
		buttonLabels[9].addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent e) {
				playSound(getClass().getResource("/sound/beep.wav"));
				if(canShift()) {
					shiftNumbers();
					addToDisplay(0, 3);
				}
				else {
					return;
				}
			}
			
			// Unimplemented Methods
			public void mouseClicked(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});
		startLabel.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent e) {
				playSound(getClass().getResource("/sound/beep.wav"));
				// START
			}
			
			// Unimplemented Methods
			public void mouseClicked(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});
		stopClearLabel.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent e) {
				playSound(getClass().getResource("/sound/beep.wav"));
				if(isRunning()) {
					// PAUSE
				}
				else {
					clearDisplay();
				}
			}
			
			// Unimplemented Methods
			public void mouseClicked(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});
	}
	
	private void addToDisplay(int number, int index) {
		displayLabels[index].setIcon(displayIcons[number]);
		numbers[index] = number;
	}
	
	private void clearDisplay() {
		for(int i = 0; i < 4; i++) {
			addToDisplay(0, i);
		}
		
		numbersPresent = 0;
	}
	
	private void shiftNumbers() {
		for(int i = 1; i < numbers.length; i++) {
			addToDisplay(numbers[i], (i-1));
		}
	}
	
	private boolean canShift() {
		if(numbersPresent < 4) {
			return true;
		}
		
		return false;
	}
	
	private void playSound(URL fileURL) {
		AudioClip clip = Applet.newAudioClip(fileURL);
		clip.play();
	}
	
	private void setRunning(boolean running) {
		this.running = running;
	}
	
	private boolean isRunning() {
		return running;
	}
}