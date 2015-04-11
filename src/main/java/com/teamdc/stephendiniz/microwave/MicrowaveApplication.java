package com.teamdc.stephendiniz.microwave;

import java.awt.Dimension;

import javax.swing.JFrame;

public class MicrowaveApplication {

	private static final int GUI_WIDTH = 1220;
	private static final int GUI_HEIGHT = 555;
	
	public static void main(String[] args) {
		GUI gui = new GUI("Microwave", GUI_WIDTH, GUI_HEIGHT);
	}
}
