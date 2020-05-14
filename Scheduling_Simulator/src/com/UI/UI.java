package com.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.cont.Controller;
import com.pro.Process;
import com.func.*;
import com.mul.PrintThread;

public class UI extends JFrame {
	JLabel lblNewLabel = new JLabel(""); // process run부분
	UI instance = this;
	int rownum = 0;
	private JFrame frame = new JFrame();;
	private JTable processtable;
	Color[] color = new Color[15];
	JPanel[][] panels = new JPanel[4][100];
	JPanel printMain = new JPanel();
	private Scheduling sch;
	String[] algoList ={ "FCFS", "RR", "SPN", "SRTN", "HRRN", "COVID" };
	private enum algoType {
		FCFS(0), RR(1), SPN(2), SRTN(3), HRRN(4), COVID(5);
		private final int value;
		private algoType(int value) {
			this.value=value;
		}
		public int getVal() {
			return value;
		}
    }
	public int getProcessorNum() {
		return sch.getProcessorNum();
	}

	private String nameSch = "FCFS";

	// 초기화 버튼 객체 변수 선언
	private JButton btnClear = null;
	public UI() {
		System.out.println("생송");
		color[0] = new Color(220, 112, 126);
		color[1] = new Color(237, 170, 125);
		color[2] = new Color(239, 180, 193);
		color[3] = new Color(255, 255, 222);
		color[4] = new Color(168, 200, 121);
		color[5] = new Color(90, 160, 141);
		color[6] = new Color(76, 146, 177);
		color[7] = new Color(103, 143, 174);
		color[8] = new Color(150, 177, 208);
		color[9] = new Color(172, 153, 193);
		color[10] = new Color(240, 199, 171);
		color[11] = new Color(195, 226, 221);
		color[12] = new Color(212, 219, 181);
		color[13] = new Color(130, 171, 195);
		color[14] = new Color(252, 239, 204);
	}

	private Process[] pcs = new Process[15];
	private int processorNum = 0;
	// 무언가 기능이 실행중이면 true로 다른 입력 받지 않음
	boolean denay;

	public boolean isDenaied() {
		return denay;
	}
	public void printResultStart(Scheduling sch) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					printResult(sch);
					lblNewLabel.repaint();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void printResult(Scheduling sch) {

		JPanel pan = new JPanel();
		System.out.println("프로세서 개수!!!!"+sch.getProcessorNum());

		
		pan.setLayout(null);
		this.sch = sch;
		PrintThread t = new PrintThread(this);
		Thread th = new Thread(t);
		ArrayList<Process> pcs=sch.getProcessArray();
		for(int i=0;i<pcs.size();i++) {
			processtable.setValueAt(pcs.get(i).getWaitTime(), i, 2);
			processtable.setValueAt(pcs.get(i).getTurnTime(), i, 3);
			processtable.setValueAt(Math.round(pcs.get(i).getNormalizedTT()*100)/(float)100 , i, 4);
		}
		
		
		th.start();
	}

	public void printProcess(int time) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					for (int ii = 0; ii < sch.getProcessorNum(); ii++) {
						if (sch.runStatus[ii][time] != 0) {
							panels[ii][time] = new JPanel();
							panels[ii][time].setBounds(10 + time * 55, 20 + ii * 90, 50, 80);
							panels[ii][time].setBackground(color[sch.runStatus[ii][time] - 1]);
							panels[ii][time].add(new JLabel("p" + sch.runStatus[ii][time]));
							panels[ii][time].setVisible(true);
							lblNewLabel.add(panels[ii][time]);
							lblNewLabel.repaint();
						}
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

		frame.setVisible(true);
		frame.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(UI.class.getResource("/javax/swing/plaf/metal/icons/ocean/expanded.gif")));
		frame.setBackground(Color.WHITE);
		frame.getContentPane().setBackground(UIManager.getColor("Button.disabledShadow"));
		frame.getContentPane().setForeground(Color.ORANGE);
		frame.setBounds(60, 20, 1800, 1300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1189, 818);
		panel.setFont(new Font("굴림", Font.PLAIN, 19));
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.setBackground(new Color(0, 0, 0, 0));
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		//프로세스 뜨는 라벨임 --------------------------------
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBounds(249, 390, 900, 500);
		
		panel.add(lblNewLabel);

		JLabel lblProcessrun = new JLabel("<Process Run>");
		lblProcessrun.setBounds(13, 363, 233, 31);
		panel.add(lblProcessrun);
		lblProcessrun.setHorizontalAlignment(SwingConstants.CENTER);
		lblProcessrun.setOpaque(true);
		lblProcessrun.setBackground(Color.WHITE);
//      lblProcessrun.setBackground(SystemColor.inactiveCaptionBorder);
//      lblProcessrun.setGridColor(SystemColor.activeCaption);
		lblProcessrun.setFont(new Font("Arial", Font.BOLD, 15));

		JLabel label = new JLabel("Scheduling Simulator");
//      label.setBounds(399, 12, 372, 58);
		label.setBounds(365, 10, 370, 58);
		label.setOpaque(true);
		label.setForeground(new Color(0, 0, 0));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Arial", Font.BOLD, 34));
		label.setBackground(Color.WHITE);
		panel.add(label);

		JSpinner numProcess = new JSpinner(); // number of processor
		numProcess.setBounds(274, 100, 70, 24);
		numProcess.setFont(new Font("Arial", Font.BOLD, 16));
		numProcess.setModel(new SpinnerNumberModel(1, 1, 4, 1));
		panel.add(numProcess);

		JLabel lbltimequntaum = new JLabel("time quntaum");
		lbltimequntaum.setBounds(40, 140, 169, 18);
		lbltimequntaum.setHorizontalAlignment(SwingConstants.CENTER);
		lbltimequntaum.setOpaque(true);
		lbltimequntaum.setBackground(Color.WHITE);
		lbltimequntaum.setVisible(false);
		lbltimequntaum.setFont(new Font("Arial", Font.BOLD, 16));
		panel.add(lbltimequntaum);

		JSpinner timequntanm = new JSpinner();
		timequntanm.setModel(new SpinnerNumberModel(0, 0, 10, 1));
		timequntanm.setBounds(274, 180, 70, 24);
		timequntanm.setFont(new Font("Arial", Font.BOLD, 16));
		timequntanm.setVisible(false);
		panel.add(timequntanm);

		JComboBox scheduler = new JComboBox();
		scheduler.setBounds(274, 140, 70, 24);
		scheduler.setFont(new Font("Arial", Font.PLAIN, 16));
		scheduler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String RR = "RR";
				String COVID = "COVID";
				System.out.println("이거 되야되는거 아뇽? " + nameSch);
				if (RR.equals(scheduler.getSelectedItem())) {
					lbltimequntaum.setVisible(true);
					timequntanm.setVisible(true);
					// System.out.println(scheduler.getSelectedItem());
				} else {
					lbltimequntaum.setVisible(false);
					timequntanm.setVisible(false);
				}
				nameSch = scheduler.getSelectedItem().toString();
				System.out.println("name = " + nameSch);
			}
		});
		panel.add(scheduler);
		scheduler.setBackground(SystemColor.inactiveCaptionBorder);
		scheduler.setModel(new DefaultComboBoxModel(algoList));

		processtable = new JTable(); // 표

		processtable.setFont(new Font("Arial", Font.BOLD, 18));
		processtable.setBounds(533, 100, 500, 260);
		processtable.setRowHeight(17);
		processtable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		panel.add(processtable);
		processtable.setBackground(SystemColor.inactiveCaptionBorder);
		processtable.setGridColor(SystemColor.activeCaption);
		processtable.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		processtable.setBorder(new LineBorder(Color.WHITE, 3));
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
		// 정렬할 테이블의 ColumnModel을 가져옴
		TableColumnModel tcmSchedule = processtable.getColumnModel();

		JLabel lblNumberofProcessor = new JLabel("Number of Processor");
		lblNumberofProcessor.setBounds(54, 90, 200, 32);
		lblNumberofProcessor.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumberofProcessor.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNumberofProcessor.setOpaque(true);
		lblNumberofProcessor.setBackground(Color.WHITE);
		lblNumberofProcessor.setForeground(Color.BLACK);
		lblNumberofProcessor.setFont(new Font("Arial", Font.BOLD, 16));
		panel.add(lblNumberofProcessor);

		JLabel lblScheduler = new JLabel("Scheduler");
		lblScheduler.setBounds(54, 140, 112, 18);
		lblScheduler.setHorizontalAlignment(SwingConstants.CENTER);
		lblScheduler.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblScheduler.setBackground(Color.WHITE);
		lblScheduler.setOpaque(true);
		lblScheduler.setFont(new Font("Arial", Font.BOLD, 16));
		panel.add(lblScheduler);

		JLabel lblArrivalTime = new JLabel("Arrival Time");
		lblArrivalTime.setBounds(54, 220, 112, 18);
		lblArrivalTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblArrivalTime.setOpaque(true);
		lblArrivalTime.setBackground(Color.WHITE);
		lblArrivalTime.setFont(new Font("Arial", Font.BOLD, 16));
		panel.add(lblArrivalTime);

		JButton btnSimulationstart = new JButton("Simulation Start");
		btnSimulationstart.setBounds(70, 300, 200, 45);
		btnSimulationstart.setFont(new Font("Arial", Font.BOLD, 19));
		btnSimulationstart.setBackground(SystemColor.inactiveCaptionBorder);
		btnSimulationstart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// System.out.println(processtable.getValue(0,0));
				int processNum = 0;
				while (processtable.getValueAt(processNum, 0) != null && processNum < 15) {
					int col = 0;
					while (col < 2) {
						System.out.print(processtable.getValueAt(processNum, col) + "  ");
						col++;
					}
					System.out.println();
					processNum++;
				}

				if (nameSch.equals(algoList[algoType.FCFS.getVal()])) {
					sch = new FCFS(processorNum);
				}else if(nameSch.equals(algoList[algoType.RR.getVal()])) {
					sch = new RR(processorNum,(int)timequntanm.getValue());
				}else if(nameSch.equals(algoList[algoType.SPN.getVal()])) {
					sch = new SPN(processorNum);
				}else if(nameSch.equals(algoList[algoType.SRTN.getVal()])) {
					sch = new SRTN(processorNum);
				}else if(nameSch.equals(algoList[algoType.HRRN.getVal()])) {
					sch = new HRRN(processorNum);
				}else if(nameSch.equals(algoList[algoType.COVID.getVal()])) {
					//sch = new COVID(processorNum);
				}else {
					//System.out.println(nameSch+"띠용"+algoList[algoType.FCFS.getVal()]);
				}
				
				processorNum = (int) numProcess.getValue();
				Controller cont = new Controller();
				cont.simulateAlgorithm(instance, sch, processorNum, pcs);
			}
		});
		panel.add(btnSimulationstart);

		JLabel lblBurstTime = new JLabel("Burst Time");
		lblBurstTime.setBounds(165, 220, 112, 18);
		lblBurstTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblBurstTime.setOpaque(true);
		lblBurstTime.setBackground(Color.WHITE);
		lblBurstTime.setFont(new Font("Arial", Font.BOLD, 16));
		panel.add(lblBurstTime);

		JSpinner arrivaltime = new JSpinner();
		arrivaltime.setBounds(70, 250, 77, 27);
		arrivaltime.setFont(new Font("Arial", Font.BOLD, 17));
		arrivaltime.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		arrivaltime.setModel(new SpinnerNumberModel(0, 0, 10, 1));
		arrivaltime.setForeground(SystemColor.info);
		arrivaltime.setPreferredSize(new Dimension(17, 24));
		panel.add(arrivaltime);

		JSpinner bursttime = new JSpinner();
		bursttime.setModel(new SpinnerNumberModel(new Integer(1), new Integer(0), null, new Integer(1)));
		bursttime.setBounds(185, 250, 77, 27);
		bursttime.setFont(new Font("Arial", Font.BOLD, 17));
		panel.add(bursttime);

		JButton btnTimeinsert = new JButton("Insert");

		btnTimeinsert.setBounds(290, 250, 100, 27);
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

		JButton btnClear = new JButton("Reset");
		btnClear.setBounds(290, 300, 100, 45);
		btnClear.setFont(new Font("Arial", Font.BOLD, 19));
		btnClear.setBackground(Color.white);
		panel.add(btnClear);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (; rownum >= 0; rownum--) {
					pcs[rownum] = null;
					processtable.setValueAt(null, rownum, 0);
					processtable.setValueAt(null, rownum, 1);
				}
				rownum++;
				arrivaltime.requestFocus();
			}
		});
		btnTimeinsert.setFont(new Font("Arial", Font.BOLD, 19));
		btnTimeinsert.setBackground(SystemColor.inactiveCaptionBorder);
		panel.add(btnTimeinsert);

		JLabel lblCore = new JLabel("1 Core");
		lblCore.setOpaque(true);
		lblCore.setHorizontalAlignment(SwingConstants.CENTER);
		lblCore.setFont(new Font("Arial", Font.BOLD, 19));
		lblCore.setBackground(Color.WHITE);
		lblCore.setBounds(10, 430, 230, 31);
		panel.add(lblCore);

		JLabel lblCore_1 = new JLabel("2 Core");
		lblCore_1.setOpaque(true);
		lblCore_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCore_1.setFont(new Font("Arial", Font.BOLD, 19));
		lblCore_1.setBackground(Color.WHITE);
		lblCore_1.setBounds(10, 520, 230, 31);
		panel.add(lblCore_1);

		JLabel lblCore_2 = new JLabel("3 Core");
		lblCore_2.setOpaque(true);
		lblCore_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblCore_2.setFont(new Font("Arial", Font.BOLD, 19));
		lblCore_2.setBackground(Color.WHITE);
		lblCore_2.setBounds(10, 610, 230, 31);
		panel.add(lblCore_2);

		JLabel lblCore_3 = new JLabel("4 Core");
		lblCore_3.setOpaque(true);
		lblCore_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblCore_3.setFont(new Font("Arial", Font.BOLD, 19));
		lblCore_3.setBackground(Color.WHITE);
		lblCore_3.setBounds(10, 700, 230, 31);
		panel.add(lblCore_3);

		JLabel lblAt = new JLabel("A.T");
		lblAt.setOpaque(true);
		lblAt.setHorizontalAlignment(SwingConstants.CENTER);
		lblAt.setFont(new Font("Arial", Font.BOLD, 16));
		lblAt.setBackground(Color.WHITE);
		lblAt.setBounds(570, 80, 32, 15);
		panel.add(lblAt);

		JLabel lblBt = new JLabel("B.T");
		lblBt.setOpaque(true);
		lblBt.setHorizontalAlignment(SwingConstants.CENTER);
		lblBt.setFont(new Font("Arial", Font.BOLD, 16));
		lblBt.setBackground(Color.WHITE);
		lblBt.setBounds(670, 80, 32, 15);
		panel.add(lblBt);

		JLabel lblWt = new JLabel("W.T");
		lblWt.setOpaque(true);
		lblWt.setHorizontalAlignment(SwingConstants.CENTER);
		lblWt.setFont(new Font("Arial", Font.BOLD, 16));
		lblWt.setBackground(Color.WHITE);
		lblWt.setBounds(770, 80, 32, 15);
		panel.add(lblWt);

		JLabel lblTt = new JLabel("T.T");
		lblTt.setOpaque(true);
		lblTt.setHorizontalAlignment(SwingConstants.CENTER);
		lblTt.setFont(new Font("Arial", Font.BOLD, 16));
		lblTt.setBackground(Color.WHITE);
		lblTt.setBounds(870, 80, 32, 15);
		panel.add(lblTt);

		JLabel lblNTt = new JLabel("NTT");
		lblNTt.setOpaque(true);
		lblNTt.setHorizontalAlignment(SwingConstants.CENTER);
		lblNTt.setFont(new Font("Arial", Font.BOLD, 16));
		lblNTt.setBackground(Color.WHITE);
		lblNTt.setBounds(970, 80, 32, 15);
		panel.add(lblNTt);

		JLabel lblP = new JLabel("P1");
		lblP.setOpaque(true);
		lblP.setHorizontalAlignment(SwingConstants.CENTER);
		lblP.setFont(new Font("Arial", Font.BOLD, 15));
		lblP.setBackground(Color.WHITE);
		lblP.setBounds(488, 100, 40, 15);
		panel.add(lblP);

		JLabel lblP_1 = new JLabel("P2");
		lblP_1.setOpaque(true);
		lblP_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblP_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblP_1.setBackground(Color.WHITE);
		lblP_1.setBounds(488, 118, 40, 15);
		panel.add(lblP_1);

		JLabel lblP_2 = new JLabel("P3");
		lblP_2.setOpaque(true);
		lblP_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblP_2.setFont(new Font("Arial", Font.BOLD, 15));
		lblP_2.setBackground(Color.WHITE);
		lblP_2.setBounds(488, 135, 40, 15);
		panel.add(lblP_2);

		JLabel lblP_3 = new JLabel("P4");
		lblP_3.setOpaque(true);
		lblP_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblP_3.setFont(new Font("Arial", Font.BOLD, 15));
		lblP_3.setBackground(Color.WHITE);
		lblP_3.setBounds(488, 153, 40, 15);
		panel.add(lblP_3);

		JLabel lblP_4 = new JLabel("P5");
		lblP_4.setOpaque(true);
		lblP_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblP_4.setFont(new Font("Arial", Font.BOLD, 15));
		lblP_4.setBackground(Color.WHITE);
		lblP_4.setBounds(488, 170, 40, 15);
		panel.add(lblP_4);

		JLabel lblP_5 = new JLabel("P6");
		lblP_5.setOpaque(true);
		lblP_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblP_5.setFont(new Font("Arial", Font.BOLD, 15));
		lblP_5.setBackground(Color.WHITE);
		lblP_5.setBounds(488, 185, 40, 15);
		panel.add(lblP_5);

		JLabel lblP_6 = new JLabel("P7");
		lblP_6.setOpaque(true);
		lblP_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblP_6.setFont(new Font("Arial", Font.BOLD, 15));
		lblP_6.setBackground(Color.WHITE);
		lblP_6.setBounds(488, 203, 40, 15);
		panel.add(lblP_6);

		JLabel lblP_7 = new JLabel("P8");
		lblP_7.setOpaque(true);
		lblP_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblP_7.setFont(new Font("Arial", Font.BOLD, 15));
		lblP_7.setBackground(Color.WHITE);
		lblP_7.setBounds(488, 220, 40, 15);
		panel.add(lblP_7);

		JLabel lblP_8 = new JLabel("P9");
		lblP_8.setOpaque(true);
		lblP_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblP_8.setFont(new Font("Arial", Font.BOLD, 15));
		lblP_8.setBackground(Color.WHITE);
		lblP_8.setBounds(488, 237, 40, 15);
		panel.add(lblP_8);

		JLabel lblP_9 = new JLabel("P10");
		lblP_9.setOpaque(true);
		lblP_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblP_9.setFont(new Font("Arial", Font.BOLD, 15));
		lblP_9.setBackground(Color.WHITE);
		lblP_9.setBounds(488, 254, 40, 15);
		panel.add(lblP_9);

		JLabel lblP_10 = new JLabel("P11");
		lblP_10.setOpaque(true);
		lblP_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblP_10.setFont(new Font("Arial", Font.BOLD, 15));
		lblP_10.setBackground(Color.WHITE);
		lblP_10.setBounds(488, 271, 40, 15);
		panel.add(lblP_10);

		JLabel lblP_11 = new JLabel("P12");
		lblP_11.setOpaque(true);
		lblP_11.setHorizontalAlignment(SwingConstants.CENTER);
		lblP_11.setFont(new Font("Arial", Font.BOLD, 15));
		lblP_11.setBackground(Color.WHITE);
		lblP_11.setBounds(488, 288, 40, 15);
		panel.add(lblP_11);

		JLabel lblP_12 = new JLabel("P13");
		lblP_12.setOpaque(true);
		lblP_12.setHorizontalAlignment(SwingConstants.CENTER);
		lblP_12.setFont(new Font("Arial", Font.BOLD, 15));
		lblP_12.setBackground(Color.WHITE);
		lblP_12.setBounds(488, 305, 40, 15);
		panel.add(lblP_12);

		JLabel lblP_13 = new JLabel("P14");
		lblP_13.setOpaque(true);
		lblP_13.setHorizontalAlignment(SwingConstants.CENTER);
		lblP_13.setFont(new Font("Arial", Font.BOLD, 15));
		lblP_13.setBackground(Color.WHITE);
		lblP_13.setBounds(488, 322, 40, 15);
		panel.add(lblP_13);

		JLabel lblP_14 = new JLabel();
		lblP_14 = labelBuilder("P15",488,340,40,15);
		panel.add(lblP_14);

		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
		// DefaultTableCellHeaderRenderer의 정렬을 가운데 정렬로 지정
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		// 반복문을 이용하여 테이블을 가운데 정렬로 지정
		for (int index = 0; index < tcmSchedule.getColumnCount(); index++) {

			tcmSchedule.getColumn(index).setCellRenderer(tScheduleCellRenderer);

		}

	}
	public JLabel labelBuilder(String name,int x, int y, int width, int height) {
		JLabel temp = new JLabel(name);
		temp.setOpaque(true);
		temp.setHorizontalAlignment(SwingConstants.CENTER);
		temp.setFont(new Font("Arial", Font.BOLD, 16));
		temp.setBackground(Color.WHITE);
		temp.setBounds(x, y, width, height);
		return temp;
	}
}