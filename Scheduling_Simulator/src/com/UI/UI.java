package com.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import com.cont.Controller;
import com.pro.Process;
import com.func.*;
import com.mul.PrintThread;
import java.awt.Rectangle;

public class UI extends JFrame {
	ProcessColor pcsColor = new ProcessColor();
	LabelBuilder lBuilder = new LabelBuilder();
	JLabel runLabel = new JLabel(""); // process run부분
	UI instance = this;
	int rownum = 0;
	private JFrame frame = new JFrame();;
	private JTable processtable;
	JPanel[][] panels = new JPanel[4][1000]; // 프로세스 출력용 패널
	JPanel printMain = new JPanel();
	private Scheduling sch;
	String[] algoList = { "FCFS", "RR", "SPN", "SRTN", "HRRN", "COVID" };

	private enum algoType {
		FCFS(0), RR(1), SPN(2), SRTN(3), HRRN(4), COVID(5);

		private final int value;

		private algoType(int value) {
			this.value = value;
		}

		public int getVal() {
			return value;
		}
	}

	public int getProcessorNum() {
		return sch.getProcessorNum();
	}

	private String nameSch = "FCFS";
	private Process[] pcs = new Process[15];
	private int processorNum = 0;

	public void printResultStart(Scheduling sch) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					printResult(sch);
					runLabel.repaint();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void printResult(Scheduling sch) {
		JPanel pan = new JPanel();
		pan.setLayout(null);
		this.sch = sch;
		PrintThread t = new PrintThread(this);
		Thread th = new Thread(t);
		ArrayList<Process> pcs = sch.getProcessArray();
		for (int i = 0; i < pcs.size(); i++) {
			processtable.setValueAt(pcs.get(i).getWaitTime(), i, 2);
			processtable.setValueAt(pcs.get(i).getTurnTime(), i, 3);
			processtable.setValueAt(Math.round(pcs.get(i).getNormalizedTT() * 100) / (float) 100, i, 4);
		}
		th.start();
	}

	public void printProcess(int time) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					for (int i = 0; i < sch.getProcessorNum(); i++)
						if (sch.runStatus[i][time] != 0) {
							System.out.println(time + " !!!");
							panels[i][time] = new JPanel();
							panels[i][time].setBorder(new LineBorder(pcsColor.getColor(sch.runStatus[i][time] - 1), 8));
							panels[i][time].setBounds(-20 + time * 48, 15 + i * 68, 42, 42);
							panels[i][time].setBackground(pcsColor.getColor(sch.runStatus[i][time] - 1));
							panels[i][time].add(new JLabel("P" + sch.runStatus[i][time]));
							panels[i][time].getComponent(0).setFont(new Font("Arial", Font.BOLD, 13));
							panels[i][time].setVisible(true);
							runLabel.add(panels[i][time]);
							runLabel.repaint();
						}
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initialize();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBackground(Color.WHITE);
		frame.getContentPane().setBackground(UIManager.getColor("Button.disabledShadow"));
		frame.setBounds(60, 20, 1297, 916);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		frame.setLayout(null);

		// 232, 524, 950, 278

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1279, 869);
		panel.setFont(new Font("굴림", Font.PLAIN, 19));
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		frame.getContentPane().add(panel);
		for (int i = 0; i < 4; i++) {
			JLabel lblCore = lBuilder.build((i + 1) + " Core", 106, 542 + i * 70, 100, 37, 27);
			panel.add(lblCore);
		}

		runLabel.setOpaque(true);
		runLabel.setBounds(232, 524, 950, 278);
		panel.add(runLabel);
		JSpinner numProcess = lBuilder.spinnerBuild(414, 150, 100, 50, 23);
		numProcess.setModel(new SpinnerNumberModel(1, 1, 4, 1));
		panel.add(numProcess);

		JSpinner timequntanm = lBuilder.spinnerBuild(316, 243, 70, 50, 23);
		timequntanm.setModel(new SpinnerNumberModel(0, 0, 10, 1));
		timequntanm.setVisible(false);
		panel.add(timequntanm);

		JLabel title = lBuilder.build("Scheduling Simulator", 394, 34, 491, 58, 38);
		title.setBackground(SystemColor.activeCaption);
		title.setForeground(Color.WHITE);
		panel.add(title);

		JLabel lbltimequntaum = lBuilder.build("Time quntaum", 90, 243, 189, 50, 23);
		lbltimequntaum.setBorder(new LineBorder(SystemColor.activeCaption, 2));
		lbltimequntaum.setVisible(false);
		panel.add(lbltimequntaum);

		JLabel lblScheduler = lBuilder.build("Choose Scheduler", 90, 243, 296, 50, 23);
		lblScheduler.setBorder(new LineBorder(SystemColor.textHighlight));
		panel.add(lblScheduler);

		JLabel lblNumberofProcessor = new JLabel("Number of Processor");
		JComboBox scheduler = new JComboBox();
		scheduler.setBorder(new LineBorder(SystemColor.activeCaption));
		scheduler.setBounds(new Rectangle(0, 0, 0, 50));
		scheduler.setBounds(414, 243, 100, 50);
		scheduler.setFont(new Font("Arial", Font.BOLD, 21));
		scheduler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String RR = "RR";
				String COVID = "COVID";
				if (RR.equals(scheduler.getSelectedItem())) {
					lbltimequntaum.setVisible(true);
					timequntanm.setVisible(true);
					lblScheduler.setVisible(false);
				} else {
					lbltimequntaum.setVisible(false);
					timequntanm.setVisible(false);
					lblScheduler.setVisible(true);
				}
				if (COVID.equals(scheduler.getSelectedItem())) {
					lblNumberofProcessor.setText("Number of Doctor");
				} else {
					lblNumberofProcessor.setText("Number of Processor");
				}
				nameSch = scheduler.getSelectedItem().toString();
			}
		});

		panel.add(scheduler);
		scheduler.setBackground(SystemColor.inactiveCaptionBorder);
		scheduler.setModel(new DefaultComboBoxModel(algoList));

		processtable = new JTable();
		processtable.setFont(new Font("Arial", Font.BOLD, 14));
		processtable.setBounds(623, 150, 559, 329);
		processtable.setRowHeight(22);
		processtable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		processtable.setBackground(SystemColor.inactiveCaptionBorder);
		processtable.setGridColor(SystemColor.activeCaption);
		processtable.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		processtable.setBorder(null);
		processtable.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, },
				new String[] { "Arrival Time", "Burst Time", "waiting Time", "TurnArround Time", "NTT" }) {
			Class[] columnTypes = new Class[] { Integer.class, Integer.class, Integer.class, Integer.class,
					Float.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		panel.add(processtable);
		TableColumnModel tcmSchedule = processtable.getColumnModel();
		lblNumberofProcessor.setBorder(new LineBorder(SystemColor.textHighlight));
		lblNumberofProcessor.setBounds(94, 150, 296, 50);
		lblNumberofProcessor.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumberofProcessor.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNumberofProcessor.setOpaque(true);
		lblNumberofProcessor.setBackground(Color.WHITE);
		lblNumberofProcessor.setForeground(Color.BLACK);
		lblNumberofProcessor.setFont(new Font("Arial", Font.BOLD, 23));
		panel.add(lblNumberofProcessor);

		JLabel lblArrivalTime = lBuilder.build("Arrival Time", 90, 336, 146, 27, 19);
		lblArrivalTime.setBorder(new LineBorder(SystemColor.textHighlight));
		panel.add(lblArrivalTime);

		JLabel lblBurstTime = lBuilder.build("Burst Time", 250, 336, 140, 27, 19);
		lblBurstTime.setBorder(new LineBorder(SystemColor.textHighlight));
		panel.add(lblBurstTime);

		JButton btnSimulationstart = lBuilder.buttonBuild("Simulation Start", 90, 429, 300, 50, 23);
		btnSimulationstart.setForeground(SystemColor.window);
		btnSimulationstart.setBorder(new LineBorder(new Color(153, 180, 209)));
		btnSimulationstart.setBackground(SystemColor.activeCaption);
		btnSimulationstart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idx = rownum;
				for (; idx >= 0; idx--)
					for (int i = 2; i < 5; i++)
						processtable.setValueAt(null, idx, i);
				runLabel.removeAll();

				// 여기 좀 고쳐야됨 좀 아닌듯?
				int processNum = 0;
				while (processtable.getValueAt(processNum, 0) != null && processNum < 15) {
					int col = 0;
					while (col < 2) {
						processtable.getValueAt(processNum, col);
						col++;
					}
					processNum++;
				}

				if (nameSch.equals(algoList[algoType.FCFS.getVal()]))
					sch = new FCFS(processorNum);
				else if (nameSch.equals(algoList[algoType.RR.getVal()]))
					sch = new RR(processorNum, (int) timequntanm.getValue());
				else if (nameSch.equals(algoList[algoType.SPN.getVal()]))
					sch = new SPN(processorNum);
				else if (nameSch.equals(algoList[algoType.SRTN.getVal()]))
					sch = new SRTN(processorNum);
				else if (nameSch.equals(algoList[algoType.HRRN.getVal()]))
					sch = new HRRN(processorNum);
				else if (nameSch.equals(algoList[algoType.COVID.getVal()]))
					sch = new COVID(processorNum);
				processorNum = (int) numProcess.getValue();
				Controller cont = new Controller();
				cont.simulateAlgorithm(instance, sch, processorNum, pcs);
			}
		});
		panel.add(btnSimulationstart);

		JSpinner arrivaltime = lBuilder.spinnerBuild(90, 360, 146, 25, 20);
		arrivaltime.setModel(new SpinnerNumberModel(0, 0, 10, 1));
		panel.add(arrivaltime);

		JSpinner bursttime = lBuilder.spinnerBuild(250, 360, 140, 25, 20);
		bursttime.setModel(new SpinnerNumberModel(1, 1, 200, 1));
		panel.add(bursttime);

		JButton btnTimeinsert = lBuilder.buttonBuild("Insert", 414, 336, 100, 50, 23);
		btnTimeinsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (rownum < 15) {
					pcs[rownum] = new Process((int) arrivaltime.getValue(), (int) bursttime.getValue());
					processtable.setValueAt(arrivaltime.getValue(), rownum, 0);
					processtable.setValueAt(bursttime.getValue(), rownum, 1);
					rownum++;
				}
			}
		});

		JButton btnClear = lBuilder.buttonBuild("Reset", 414, 429, 100, 50, 23);
		btnClear.setBackground(Color.white);
		panel.add(btnClear);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (; rownum >= 0; rownum--) {
					pcs[rownum] = null;
					for (int i = 0; i < 5; i++)
						processtable.setValueAt(null, rownum, i);
				}
				runLabel.removeAll();
				runLabel.repaint();
				rownum = 0;
				arrivaltime.requestFocus();
			}
		});
		btnTimeinsert.setFont(new Font("Arial", Font.BOLD, 23));
		btnTimeinsert.setBackground(SystemColor.inactiveCaptionBorder);
		panel.add(btnTimeinsert);
		String[] tableRow = { "A.T", "B.T", "W.T", "T.T", "NTT" };
		for (int i = 0; i < tableRow.length; i++) {
			JLabel tableRowLabel = lBuilder.build(tableRow[i], 661 + i * 112, 123, 32, 15);
			panel.add(tableRowLabel);
		}
		JLabel[] prcNameTable = new JLabel[15];
		for (int i = 0; i < prcNameTable.length; i++) {
			prcNameTable[i] = lBuilder.build("P" + (i + 1), 566, 150 + 22 * i, 40, 22);
			panel.add(prcNameTable[i]);
		}

		JLabel runBackground = lBuilder.build("", 80, 509, 1118, 308);
		runBackground.setBorder(new LineBorder(SystemColor.activeCaption, 2));
		panel.add(runBackground);

		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (int index = 0; index < tcmSchedule.getColumnCount(); index++) {
			tcmSchedule.getColumn(index).setCellRenderer(tScheduleCellRenderer);
		}
	}
}