package com.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class LabelBuilder {

	public LabelBuilder() {
		// TODO Auto-generated constructor stub
	}
	public JLabel build(String name, int x, int y, int width, int height) {
		JLabel re = new JLabel(name);
		re.setBorder(null);
		re.setForeground(SystemColor.activeCaptionText);
		re.setOpaque(true);
		re.setHorizontalAlignment(SwingConstants.CENTER);
		re.setFont(new Font("Arial", Font.BOLD, 19));
		re.setBackground(SystemColor.text);
		re.setBounds(x, y, width, height);
		return re;
	}
	public JLabel build(String name, int x, int y, int width, int height, int fontSize) {
		JLabel re = new JLabel(name);
		re.setBorder(null);
		re.setForeground(SystemColor.activeCaptionText);
		re.setOpaque(true);
		re.setHorizontalAlignment(SwingConstants.CENTER);
		re.setFont(new Font("Arial", Font.BOLD, fontSize));
		re.setBackground(SystemColor.text);
		re.setBounds(x, y, width, height);
		return re;
	}
	public JSpinner spinnerBuild( int x, int y, int width, int height, int fontSize) {
		JSpinner re = new JSpinner();
		re.setBorder(new LineBorder(SystemColor.textHighlight));
		re.setBounds(x, y, width, height);
		re.setFont(new Font("Arial", Font.BOLD, fontSize));
		//lBuilder.buttonBuild("Simulation Start", 90, 429, 300, 50, 23);
		return re;
	}
	public JButton buttonBuild(String name, int x, int y, int width, int height, int fontSize) {
		JButton re = new JButton(name);
		re.setBorder(new LineBorder(new Color(153, 180, 209)));
		re.setBounds(x, y, width, height);
		re.setFont(new Font("Arial", Font.BOLD, fontSize));
		re.setBackground(SystemColor.activeCaption);
		return re;
	}
}
