package com.UI;

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
import java.util.ArrayList;
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
	public int getProcessorNum() {
		return sch.getProcessorNum();
	}

	private String nameSch = "FCFS";

	// 초기화 버튼 객체 변수 선언
	private JButton btnClear = null;

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
		
		processtable.repaint();
		th.start();
	}


	
	
	public void printProcess(int time) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					for (int ii = 0; ii < sch.getProcessorNum(); ii++) {
						if (sch.runStatus[ii][time] != 0) {
							panels[ii][time] = new JPanel();
							panels[ii][time].setBorder(new LineBorder(color[sch.runStatus[ii][time] - 1] , 8));
							
							panels[ii][time].setBounds(-20 + time * 48, 15 + ii * 68, 42, 42);
							panels[ii][time].setBorder(new LineBorder(color[sch.runStatus[ii][time] - 1] , 8));
							panels[ii][time].setBackground(color[sch.runStatus[ii][time] - 1]);
							panels[ii][time].add(new JLabel("P" + sch.runStatus[ii][time]));
							panels[ii][time].getComponent(0).setFont(new Font("Arial", Font.BOLD, 13));
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
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBackground(Color.WHITE);
		frame.getContentPane().setBackground(UIManager.getColor("Button.disabledShadow"));
		frame.getContentPane().setForeground(Color.ORANGE);
		frame.setBounds(60, 20, 1297, 916);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1279, 869);
		panel.setFont(new Font("굴림", Font.PLAIN, 19));
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.setBackground(Color.WHITE);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblCore_3 = new JLabel("4 Core");
		lblCore_3.setOpaque(true);
		lblCore_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblCore_3.setForeground(Color.BLACK);
		lblCore_3.setFont(new Font("Arial", Font.BOLD, 27));
		lblCore_3.setBorder(null);
		lblCore_3.setBackground(Color.WHITE);
		lblCore_3.setBounds(106, 747, 100, 37);
		panel.add(lblCore_3);
		
		JLabel lblCore_2 = new JLabel("3 Core");
		lblCore_2.setOpaque(true);
		lblCore_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblCore_2.setForeground(Color.BLACK);
		lblCore_2.setFont(new Font("Arial", Font.BOLD, 27));
		lblCore_2.setBorder(null);
		lblCore_2.setBackground(Color.WHITE);
		lblCore_2.setBounds(106, 682, 100, 37);
		panel.add(lblCore_2);
		
		JLabel lblCore_1 = new JLabel("2 Core");
		lblCore_1.setOpaque(true);
		lblCore_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCore_1.setForeground(Color.BLACK);
		lblCore_1.setFont(new Font("Arial", Font.BOLD, 27));
		lblCore_1.setBorder(null);
		lblCore_1.setBackground(Color.WHITE);
		lblCore_1.setBounds(106, 612, 100, 37);
		panel.add(lblCore_1);

		//프로세스 뜨는 라벨임 --------------------------------
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBounds(232, 524, 950, 278);
		
		panel.add(lblNewLabel);

		JSpinner numProcess = new JSpinner(); // number of processor
		numProcess.setBounds(new Rectangle(0, 0, 0, 50));
		numProcess.setBorder(new LineBorder(SystemColor.textHighlight));
		numProcess.setBounds(414, 150, 100, 50);
		numProcess.setFont(new Font("Arial", Font.BOLD, 23));
		numProcess.setModel(new SpinnerNumberModel(1, 1, 4, 1));
		panel.add(numProcess);

		JLabel lbltimequntaum = new JLabel("Time quntaum");
		lbltimequntaum.setBounds(new Rectangle(0, 0, 0, 50));
		lbltimequntaum.setBorder(new LineBorder(SystemColor.activeCaption, 2));
		lbltimequntaum.setBounds(90, 243, 189, 50);
		lbltimequntaum.setHorizontalAlignment(SwingConstants.CENTER);
		lbltimequntaum.setOpaque(true);
		lbltimequntaum.setBackground(Color.WHITE);
		lbltimequntaum.setVisible(false);
		lbltimequntaum.setFont(new Font("Arial", Font.BOLD, 23));
		panel.add(lbltimequntaum);

		JSpinner timequntanm = new JSpinner();
		timequntanm.setBounds(new Rectangle(0, 0, 0, 50));
		timequntanm.setBorder(new LineBorder(SystemColor.activeCaption, 2));
		timequntanm.setModel(new SpinnerNumberModel(0, 0, 10, 1));
		timequntanm.setBounds(316, 243, 70, 50);
		timequntanm.setFont(new Font("Arial", Font.BOLD, 23));
		timequntanm.setVisible(false);
		panel.add(timequntanm);

		JLabel lblScheduler = new JLabel("Choose Scheduler");
		lblScheduler.setBorder(new LineBorder(SystemColor.textHighlight));
		lblScheduler.setBounds(90, 243, 296, 50);
		lblScheduler.setHorizontalAlignment(SwingConstants.CENTER);
		lblScheduler.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblScheduler.setBackground(Color.WHITE);
		lblScheduler.setOpaque(true);
		lblScheduler.setFont(new Font("Arial", Font.BOLD, 23));
		panel.add(lblScheduler);
		
		JLabel lblCovid = new JLabel("");
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
					// System.out.println(scheduler.getSelectedItem());
				} else {
					lbltimequntaum.setVisible(false);
					timequntanm.setVisible(false);
					lblScheduler.setVisible(true);
				}
				if (COVID.equals(scheduler.getSelectedItem())) {
					panel.setBackground(new Color(0,0,0,0));
					lblCovid.setVisible(true);
					lblNumberofProcessor.setText("Number of Doctor");
					
					
				} else {
					panel.setBackground(Color.WHITE);
					lblCovid.setVisible(false);
				}
				
				
				nameSch = scheduler.getSelectedItem().toString();
				System.out.println("name = " + nameSch);
			}
		});
		
		
		
		
		panel.add(scheduler);
		scheduler.setBackground(SystemColor.inactiveCaptionBorder);
		scheduler.setModel(new DefaultComboBoxModel(algoList));

		processtable = new JTable(); // 표

		processtable.setFont(new Font("Arial", Font.BOLD, 14));
		processtable.setBounds(623, 150, 559, 329);
		processtable.setRowHeight(22);
		processtable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		panel.add(processtable);
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
		// 정렬할 테이블의 ColumnModel을 가져옴
		TableColumnModel tcmSchedule = processtable.getColumnModel();

		lblNumberofProcessor.setBounds(new Rectangle(0, 0, 0, 50));
		lblNumberofProcessor.setBorder(new LineBorder(SystemColor.textHighlight));
		lblNumberofProcessor.setBounds(94, 150, 296, 50);
		lblNumberofProcessor.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumberofProcessor.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNumberofProcessor.setOpaque(true);
		lblNumberofProcessor.setBackground(Color.WHITE);
		lblNumberofProcessor.setForeground(Color.BLACK);
		lblNumberofProcessor.setFont(new Font("Arial", Font.BOLD, 23));
		panel.add(lblNumberofProcessor);

		

		JLabel lblArrivalTime = new JLabel("Arrival Time");
		lblArrivalTime.setBounds(new Rectangle(0, 0, 0, 50));
		lblArrivalTime.setBorder(new LineBorder(SystemColor.textHighlight));
		lblArrivalTime.setBounds(90, 336, 146, 27);
		lblArrivalTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblArrivalTime.setOpaque(true);
		lblArrivalTime.setBackground(Color.WHITE);
		lblArrivalTime.setFont(new Font("Arial", Font.BOLD, 19));
		panel.add(lblArrivalTime);

		JButton btnSimulationstart = new JButton("Simulation Start");
		btnSimulationstart.setForeground(SystemColor.window);
		btnSimulationstart.setBounds(new Rectangle(0, 0, 0, 50));
		btnSimulationstart.setBorder(new LineBorder(new Color(153, 180, 209)));
		btnSimulationstart.setBounds(90, 429, 300, 50);
		btnSimulationstart.setFont(new Font("Arial", Font.BOLD, 23));
		btnSimulationstart.setBackground(SystemColor.activeCaption);
		btnSimulationstart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for (int rNum=0; rNum >= 0; rNum--) {
					for(int i=2;i<5;i++)
					processtable.setValueAt(null, rNum, i);
				}
				lblNewLabel.removeAll();
				lblNewLabel.repaint();
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
					sch = new COVID(processorNum);
				}else {
					
				}
				processorNum = (int) numProcess.getValue();
				Controller cont = new Controller();
				cont.simulateAlgorithm(instance, sch, processorNum, pcs);
				processtable.repaint();
			}
		});
		panel.add(btnSimulationstart);

		JLabel lblBurstTime = new JLabel("Burst Time");
		lblBurstTime.setBounds(new Rectangle(0, 0, 0, 50));
		lblBurstTime.setBorder(new LineBorder(SystemColor.textHighlight));
		lblBurstTime.setBounds(250, 336, 140, 27);
		lblBurstTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblBurstTime.setOpaque(true);
		lblBurstTime.setBackground(Color.WHITE);
		lblBurstTime.setFont(new Font("Arial", Font.BOLD, 19));
		panel.add(lblBurstTime);

		JSpinner arrivaltime = new JSpinner();
		arrivaltime.setBorder(new LineBorder(SystemColor.textHighlight));
		arrivaltime.setBounds(new Rectangle(0, 0, 0, 50));
		arrivaltime.setBounds(90, 360, 146, 25);
		arrivaltime.setFont(new Font("Arial", Font.BOLD, 20));
		arrivaltime.setModel(new SpinnerNumberModel(0, 0, 10, 1));
		arrivaltime.setForeground(SystemColor.info);
		arrivaltime.setPreferredSize(new Dimension(17, 24));
		panel.add(arrivaltime);

		JSpinner bursttime = new JSpinner();
		bursttime.setBorder(new LineBorder(SystemColor.textHighlight));
		bursttime.setBounds(new Rectangle(0, 0, 0, 50));
		bursttime.setModel(new SpinnerNumberModel(new Integer(1), new Integer(0), null, new Integer(1)));
		bursttime.setBounds(250, 360, 140, 25);
		bursttime.setFont(new Font("Arial", Font.BOLD, 20));
		panel.add(bursttime);

		JButton btnTimeinsert = new JButton("Insert");
		btnTimeinsert.setBounds(new Rectangle(0, 0, 0, 50));
		btnTimeinsert.setBorder(new LineBorder(SystemColor.textHighlight));

		btnTimeinsert.setBounds(414, 336, 100, 50);
		btnTimeinsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (rownum < 15) {
					System.out.println("row num = "+rownum+" pcs size = "+pcs.length);
					pcs[rownum] = new Process((int) arrivaltime.getValue(), (int) bursttime.getValue());
					processtable.setValueAt(arrivaltime.getValue(), rownum, 0);
					processtable.setValueAt(bursttime.getValue(), rownum, 1);
					rownum++;
				}
			}
		});

		JButton btnClear = new JButton("Reset");
		btnClear.setBounds(new Rectangle(0, 0, 0, 50));
		btnClear.setBorder(new LineBorder(SystemColor.textHighlight));
		btnClear.setBounds(414, 429, 100, 50);
		btnClear.setFont(new Font("Arial", Font.BOLD, 23));
		btnClear.setBackground(Color.white);
		panel.add(btnClear);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (; rownum >= 0; rownum--) {
					pcs[rownum] = null;
					for(int i=0;i<5;i++)
					processtable.setValueAt(null, rownum, i);
				}
				lblNewLabel.removeAll();
				lblNewLabel.repaint();
				processtable.repaint();
				rownum=0;
				arrivaltime.requestFocus();
			}
		});
		btnTimeinsert.setFont(new Font("Arial", Font.BOLD, 23));
		btnTimeinsert.setBackground(SystemColor.inactiveCaptionBorder);
		panel.add(btnTimeinsert);

		JLabel lblCore = new JLabel("1 Core");
		lblCore.setBorder(null);
		lblCore.setForeground(SystemColor.activeCaptionText);
		lblCore.setOpaque(true);
		lblCore.setHorizontalAlignment(SwingConstants.CENTER);
		lblCore.setFont(new Font("Arial", Font.BOLD, 27));
		lblCore.setBackground(SystemColor.text);
		lblCore.setBounds(106, 542, 100, 37);
		panel.add(lblCore);

		JLabel lblAt = new JLabel("A.T");
		lblAt.setOpaque(true);
		lblAt.setHorizontalAlignment(SwingConstants.CENTER);
		lblAt.setFont(new Font("Arial", Font.BOLD, 16));
		lblAt.setBackground(Color.WHITE);
		lblAt.setBounds(661, 123, 32, 15);
		panel.add(lblAt);

		JLabel lblBt = new JLabel("B.T");
		lblBt.setOpaque(true);
		lblBt.setHorizontalAlignment(SwingConstants.CENTER);
		lblBt.setFont(new Font("Arial", Font.BOLD, 16));
		lblBt.setBackground(Color.WHITE);
		lblBt.setBounds(773, 123, 32, 15);
		panel.add(lblBt);

		JLabel lblWt = new JLabel("W.T");
		lblWt.setOpaque(true);
		lblWt.setHorizontalAlignment(SwingConstants.CENTER);
		lblWt.setFont(new Font("Arial", Font.BOLD, 16));
		lblWt.setBackground(Color.WHITE);
		lblWt.setBounds(888, 123, 32, 15);
		panel.add(lblWt);

		JLabel lblTt = new JLabel("T.T");
		lblTt.setOpaque(true);
		lblTt.setHorizontalAlignment(SwingConstants.CENTER);
		lblTt.setFont(new Font("Arial", Font.BOLD, 16));
		lblTt.setBackground(Color.WHITE);
		lblTt.setBounds(993, 123, 32, 15);
		panel.add(lblTt);

		JLabel lblNTt = new JLabel("NTT");
		lblNTt.setOpaque(true);
		lblNTt.setHorizontalAlignment(SwingConstants.CENTER);
		lblNTt.setFont(new Font("Arial", Font.BOLD, 16));
		lblNTt.setBackground(Color.WHITE);
		lblNTt.setBounds(1109, 123, 32, 15);
		panel.add(lblNTt);

		JLabel lblP = new JLabel("P01");
		lblP.setOpaque(true);
		lblP.setHorizontalAlignment(SwingConstants.CENTER);
		lblP.setFont(new Font("Arial", Font.BOLD, 15));
		lblP.setBackground(Color.WHITE);
		lblP.setBounds(566, 150, 40, 22);
		panel.add(lblP);

		JLabel lblP_1 = new JLabel("P02");
		lblP_1.setOpaque(true);
		lblP_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblP_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblP_1.setBackground(Color.WHITE);
		lblP_1.setBounds(566, 172, 40, 22);
		panel.add(lblP_1);

		JLabel lblP_2 = new JLabel("P03");
		lblP_2.setOpaque(true);
		lblP_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblP_2.setFont(new Font("Arial", Font.BOLD, 15));
		lblP_2.setBackground(Color.WHITE);
		lblP_2.setBounds(566, 194, 40, 22);
		panel.add(lblP_2);

		JLabel lblP_3 = new JLabel("P04");
		lblP_3.setOpaque(true);
		lblP_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblP_3.setFont(new Font("Arial", Font.BOLD, 15));
		lblP_3.setBackground(Color.WHITE);
		lblP_3.setBounds(566, 216, 40, 22);
		panel.add(lblP_3);

		JLabel lblP_4 = new JLabel("P05");
		lblP_4.setOpaque(true);
		lblP_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblP_4.setFont(new Font("Arial", Font.BOLD, 15));
		lblP_4.setBackground(Color.WHITE);
		lblP_4.setBounds(566, 238, 40, 22);
		panel.add(lblP_4);

		JLabel lblP_5 = new JLabel("P06");
		lblP_5.setOpaque(true);
		lblP_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblP_5.setFont(new Font("Arial", Font.BOLD, 15));
		lblP_5.setBackground(Color.WHITE);
		lblP_5.setBounds(566, 260, 40, 22);
		panel.add(lblP_5);

		JLabel lblP_6 = new JLabel("P07");
		lblP_6.setOpaque(true);
		lblP_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblP_6.setFont(new Font("Arial", Font.BOLD, 15));
		lblP_6.setBackground(Color.WHITE);
		lblP_6.setBounds(566, 282, 40, 22);
		panel.add(lblP_6);

		JLabel lblP_7 = new JLabel("P08");
		lblP_7.setOpaque(true);
		lblP_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblP_7.setFont(new Font("Arial", Font.BOLD, 15));
		lblP_7.setBackground(Color.WHITE);
		lblP_7.setBounds(566, 304, 40, 22);
		panel.add(lblP_7);

		JLabel lblP_8 = new JLabel("P09");
		lblP_8.setOpaque(true);
		lblP_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblP_8.setFont(new Font("Arial", Font.BOLD, 15));
		lblP_8.setBackground(Color.WHITE);
		lblP_8.setBounds(566, 326, 40, 22);
		panel.add(lblP_8);

		JLabel lblP_9 = new JLabel("P10");
		lblP_9.setOpaque(true);
		lblP_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblP_9.setFont(new Font("Arial", Font.BOLD, 15));
		lblP_9.setBackground(Color.WHITE);
		lblP_9.setBounds(566, 348, 40, 22);
		panel.add(lblP_9);

		JLabel lblP_10 = new JLabel("P11");
		lblP_10.setOpaque(true);
		lblP_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblP_10.setFont(new Font("Arial", Font.BOLD, 15));
		lblP_10.setBackground(Color.WHITE);
		lblP_10.setBounds(566, 370, 40, 22);
		panel.add(lblP_10);

		JLabel lblP_11 = new JLabel("P12");
		lblP_11.setOpaque(true);
		lblP_11.setHorizontalAlignment(SwingConstants.CENTER);
		lblP_11.setFont(new Font("Arial", Font.BOLD, 15));
		lblP_11.setBackground(Color.WHITE);
		lblP_11.setBounds(566, 392, 40, 22);
		panel.add(lblP_11);

		JLabel lblP_12 = new JLabel("P13");
		lblP_12.setOpaque(true);
		lblP_12.setHorizontalAlignment(SwingConstants.CENTER);
		lblP_12.setFont(new Font("Arial", Font.BOLD, 15));
		lblP_12.setBackground(Color.WHITE);
		lblP_12.setBounds(566, 414, 40, 22);
		panel.add(lblP_12);

		JLabel lblP_13 = new JLabel("P14");
		lblP_13.setOpaque(true);
		lblP_13.setHorizontalAlignment(SwingConstants.CENTER);
		lblP_13.setFont(new Font("Arial", Font.BOLD, 15));
		lblP_13.setBackground(Color.WHITE);
		lblP_13.setBounds(566, 436, 40, 22);
		panel.add(lblP_13);

		JLabel lblP_14 = new JLabel("P15");
		lblP_14.setOpaque(true);
		lblP_14.setHorizontalAlignment(SwingConstants.CENTER);
		lblP_14.setFont(new Font("Arial", Font.BOLD, 15));
		lblP_14.setBackground(Color.WHITE);
		lblP_14.setBounds(566, 458, 40, 22);
		panel.add(lblP_14);
		
		JLabel label = new JLabel("Scheduling Simulator");
		label.setBounds(394, 34, 491, 58);
		panel.add(label);
		label.setOpaque(true);
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 38));
		label.setBackground(SystemColor.activeCaption);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBorder(new LineBorder(SystemColor.activeCaption, 2));
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBackground(SystemColor.text);
		lblNewLabel_1.setBounds(80, 509, 1118, 308);
		panel.add(lblNewLabel_1);
		
		lblCovid.setVisible(false);
		lblCovid.setBounds(0, 0, 1279, 869);
		panel.add(lblCovid);

		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (int index = 0; index < tcmSchedule.getColumnCount(); index++) {
			tcmSchedule.getColumn(index).setCellRenderer(tScheduleCellRenderer);
		}

	}
}