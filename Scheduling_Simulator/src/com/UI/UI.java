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
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.cont.Controller;
import com.pro.Process;
import com.func.FCFS;
import com.func.Scheduling;

public class UI extends JFrame {

	int rownum = 0;
	private JFrame frame;
	private JTable processtable;

	private Scheduling sch;
	private String nameSch = "";

	public UI() {
		System.out.println("생송");
	}

	private Process[] pcs = new Process[15];
	private int processorNum = 0;
	// 무언가 기능이 실행중이면 true로 다른 입력 받지 않음
	boolean denay;

	public boolean isDenaied() {
		return denay;
	}

	public void printResult(Scheduling sch) {
		System.out.println("출력출력 쨘");

		JFrame f = new JFrame();
		f.setSize(1200, 1200); // 창의 크기
		f.setTitle("Process Scheduling"); // 프레임 타이틀
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 닫힐 때 프로그램도 함께 종료
		f.setLocation(300, 50); // 프레임 여는 위치 지정
		f.setLayout(new BorderLayout());
		f.setVisible(true);
		// 일단 임시로 프레임 만들어 놨지만, 나중에 호환 되게 바꿔야함.
		JPanel pan = new JPanel();
		sch.setProcessorNum(4);
		JPanel[] processorPanel = new JPanel[sch.getProcessorNum()];
		JPanel printMain = new JPanel();
		// printMain.add(new JTextField(10));
		pan.setLayout(null);
		printMain.setLayout(null);
		printMain.setSize(10, 10);
		printMain.setBounds(10, 10, 1000, 500);
		printMain.setBackground(Color.YELLOW);
		printMain.setVisible(true);
		pan.add(printMain);
		f.add(pan);
//		int[] temp = { 1, 1, 2, 1, 0, 0 };
//		int[] temp2 = { 3, 3, 3, 4, 3, 3 };
//		for (int i = 0; i < 6; i++) {
//			sch.runStatus[0][i] = temp[i];
//			sch.runStatus[1][i] = temp2[i];
//		}

		for (int i = 0; i < 6; i++) {
			System.out.println("1번 프로세서 i = " + i + " Num = " + sch.runStatus[0][i]);
			System.out.println("2번 프로세서 i = " + i + " Num = " + sch.runStatus[1][i]);
			System.out.println();
		}
			
		for (int i = 0; i < sch.getProcessorNum(); i++) {
			processorPanel[i] = new JPanel();
			processorPanel[i].setBounds(20, 20 + i * 110, 100, 100);
			processorPanel[i].setBackground(Color.RED);
			processorPanel[i].setVisible(true);
			processorPanel[i].add(new JLabel("processor " + i));
			printMain.add(processorPanel[i]);
		}
		JPanel[][] panels = new JPanel[4][100];
		Color[] color = new Color[5];
		color[0] = Color.GRAY;
		color[1] = Color.GREEN;
		color[2] = Color.BLUE;
		color[3] = Color.CYAN;
		// 종료조건 추가 예정
		for (int i = 1; i < 7; i++) {
			for (int ii = 0; ii < sch.getProcessorNum(); ii++) {
				if (sch.runStatus[ii][i] != 0) {
					panels[ii][i] = new JPanel();
					panels[ii][i].setBounds(120 + i * 120, 20 + ii * 110, 100, 100);
					panels[ii][i].setBackground(color[sch.runStatus[ii][i] - 1]);
					panels[ii][i].add(new JLabel("process " + sch.runStatus[ii][i]));
					panels[ii][i].setVisible(true);
					printMain.add(panels[ii][i]);
				}
			}
		}
	}

	public void initialize() {

		System.out.println("11");
		frame = new JFrame();
		frame.setVisible(true);
		frame.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(UI.class.getResource("/javax/swing/plaf/metal/icons/ocean/expanded.gif")));
		frame.setBackground(Color.WHITE);
		frame.getContentPane().setBackground(UIManager.getColor("Button.disabledShadow"));
		frame.getContentPane().setForeground(Color.ORANGE);
		frame.setBounds(100, 100, 1207, 865);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1189, 818);
		panel.setFont(new Font("굴림", Font.PLAIN, 19));
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.setBackground(new Color(0, 0, 0, 0));
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setIcon(new ImageIcon(
				"C:\\Users\\sprtm\\OneDrive\\\uBC14\uD0D5 \uD654\uBA74\\\uC8FC\uC11D 2020-05-08 214234.jpg"));
		lblNewLabel.setBounds(249, 505, 852, 213);
		panel.add(lblNewLabel);

		JLabel lblProcessrun = new JLabel("Process Run");
		lblProcessrun.setBounds(130, 415, 233, 31);
		panel.add(lblProcessrun);
		lblProcessrun.setHorizontalAlignment(SwingConstants.CENTER);
		lblProcessrun.setOpaque(true);
		lblProcessrun.setBackground(Color.WHITE);
		lblProcessrun.setFont(new Font("Arial", Font.BOLD, 19));

		JLabel label = new JLabel("Scheduling Simulator");
		label.setBounds(399, 12, 372, 58);
		label.setOpaque(true);
		label.setForeground(new Color(0, 0, 0));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Arial", Font.BOLD, 34));
		label.setBackground(Color.WHITE);
		panel.add(label);

		JSpinner numProcess = new JSpinner();
		numProcess.setBounds(274, 117, 77, 24);
		numProcess.setFont(new Font("Arial", Font.BOLD, 20));
		numProcess.setModel(new SpinnerNumberModel(1, 1, 4, 1));
		panel.add(numProcess);

		JLabel lbltimequntaum = new JLabel("time quntaum");
		lbltimequntaum.setBounds(54, 211, 169, 18);
		lbltimequntaum.setHorizontalAlignment(SwingConstants.CENTER);
		lbltimequntaum.setOpaque(true);
		lbltimequntaum.setBackground(Color.WHITE);
		lbltimequntaum.setVisible(false);
		lbltimequntaum.setFont(new Font("Arial", Font.BOLD, 19));
		panel.add(lbltimequntaum);

		JSpinner timequntanm = new JSpinner();
		timequntanm.setModel(new SpinnerNumberModel(0, 0, 10, 1));
		timequntanm.setBounds(274, 211, 77, 24);
		timequntanm.setFont(new Font("Arial", Font.BOLD, 20));
		timequntanm.setVisible(false);
		panel.add(timequntanm);

		JComboBox scheduler = new JComboBox();
		scheduler.setBounds(274, 162, 77, 24);
		scheduler.setFont(new Font("Arial", Font.PLAIN, 20));
		scheduler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String RR = "RR";
				String COVID = "COVID";
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
		scheduler.setModel(new DefaultComboBoxModel(new String[] { "FCBS", "RR", "SPN", "SRTN", "HHRN", "COVID" }));

		JLabel lblNumberofProcessor = new JLabel("Number of Processor");
		lblNumberofProcessor.setBounds(54, 117, 238, 32);
		lblNumberofProcessor.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumberofProcessor.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNumberofProcessor.setOpaque(true);
		lblNumberofProcessor.setBackground(Color.WHITE);
		lblNumberofProcessor.setForeground(Color.BLACK);
		lblNumberofProcessor.setFont(new Font("Arial", Font.BOLD, 19));
		panel.add(lblNumberofProcessor);

		processtable = new JTable();

		processtable.setFont(new Font("Arial", Font.BOLD, 18));
		processtable.setBounds(533, 120, 593, 330);
		processtable.setRowHeight(22);
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

		JLabel lblScheduler = new JLabel("Scheduler");
		lblScheduler.setBounds(54, 162, 112, 18);
		lblScheduler.setHorizontalAlignment(SwingConstants.CENTER);
		lblScheduler.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblScheduler.setBackground(Color.WHITE);
		lblScheduler.setOpaque(true);
		lblScheduler.setFont(new Font("Arial", Font.BOLD, 19));
		panel.add(lblScheduler);

		JLabel lblArrivalTime = new JLabel("Arrival Time");
		lblArrivalTime.setBounds(54, 269, 112, 18);
		lblArrivalTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblArrivalTime.setOpaque(true);
		lblArrivalTime.setBackground(Color.WHITE);
		lblArrivalTime.setFont(new Font("Arial", Font.BOLD, 19));
		panel.add(lblArrivalTime);

		JButton btnSimulationstart = new JButton("Simulation Start");
		btnSimulationstart.setBounds(57, 352, 306, 51);
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
				System.out.println("시뮬시작버튼 누름" + numProcess.getValue());

				// 이거 무조건 리펙토링 해야됨. 열거형쓰던가 좀 깔끔하게 해야됨 이대로는 ㄴㄴ
				if (nameSch.equals("FCFS")) {
					sch = new FCFS(processorNum);
				}
				sch = new FCFS(processorNum);
				processorNum = (int) numProcess.getValue();
				System.out.println("여긴 잘되나? " + pcs[0].getArrTime());
				Controller cont = new Controller();
				cont.simulateAlgorithm(sch, processorNum, pcs);
			}
		});
		panel.add(btnSimulationstart);

		JLabel lblBurstTime = new JLabel("Burst Time");
		lblBurstTime.setBounds(180, 269, 112, 18);
		lblBurstTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblBurstTime.setOpaque(true);
		lblBurstTime.setBackground(Color.WHITE);
		lblBurstTime.setFont(new Font("Arial", Font.BOLD, 19));
		panel.add(lblBurstTime);

		JSpinner arrivaltime = new JSpinner();
		arrivaltime.setBounds(55, 300, 77, 27);
		arrivaltime.setFont(new Font("Arial", Font.BOLD, 20));
		arrivaltime.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		arrivaltime.setModel(new SpinnerNumberModel(0, 0, 10, 1));
		arrivaltime.setForeground(SystemColor.info);
		arrivaltime.setPreferredSize(new Dimension(17, 24));
		panel.add(arrivaltime);

		JSpinner bursttime = new JSpinner();
		bursttime.setModel(new SpinnerNumberModel(new Integer(1), new Integer(0), null, new Integer(1)));
		bursttime.setBounds(183, 299, 77, 27);
		bursttime.setFont(new Font("Arial", Font.BOLD, 20));
		panel.add(bursttime);

		JButton btnTimeinsert = new JButton("Insert");

		btnTimeinsert.setBounds(278, 300, 85, 27);
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

		// System.out.println();
		// System.out.println(arrivaltime.getValue());

		btnTimeinsert.setFont(new Font("Arial", Font.BOLD, 19));
		btnTimeinsert.setBackground(SystemColor.inactiveCaptionBorder);
		panel.add(btnTimeinsert);

		JLabel lblCore = new JLabel("1 Core");
		lblCore.setOpaque(true);
		lblCore.setHorizontalAlignment(SwingConstants.CENTER);
		lblCore.setFont(new Font("Arial", Font.BOLD, 19));
		lblCore.setBackground(Color.WHITE);
		lblCore.setBounds(14, 556, 233, 31);
		panel.add(lblCore);

		JLabel lblCore_1 = new JLabel("2 Core");
		lblCore_1.setOpaque(true);
		lblCore_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCore_1.setFont(new Font("Arial", Font.BOLD, 19));
		lblCore_1.setBackground(Color.WHITE);
		lblCore_1.setBounds(14, 596, 233, 31);
		panel.add(lblCore_1);

		JLabel lblCore_2 = new JLabel("3 Core");
		lblCore_2.setOpaque(true);
		lblCore_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblCore_2.setFont(new Font("Arial", Font.BOLD, 19));
		lblCore_2.setBackground(Color.WHITE);
		lblCore_2.setBounds(14, 639, 233, 31);
		panel.add(lblCore_2);

		JLabel lblCore_3 = new JLabel("4 Core");
		lblCore_3.setOpaque(true);
		lblCore_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblCore_3.setFont(new Font("Arial", Font.BOLD, 19));
		lblCore_3.setBackground(Color.WHITE);
		lblCore_3.setBounds(14, 682, 233, 31);
		panel.add(lblCore_3);

		JLabel lblAt = new JLabel("A.T");
		lblAt.setOpaque(true);
		lblAt.setHorizontalAlignment(SwingConstants.CENTER);
		lblAt.setFont(new Font("Arial", Font.BOLD, 19));
		lblAt.setBackground(Color.WHITE);
		lblAt.setBounds(527, 82, 119, 31);
		panel.add(lblAt);

		JLabel lblBt = new JLabel("B.T");
		lblBt.setOpaque(true);
		lblBt.setHorizontalAlignment(SwingConstants.CENTER);
		lblBt.setFont(new Font("Arial", Font.BOLD, 19));
		lblBt.setBackground(Color.WHITE);
		lblBt.setBounds(652, 77, 119, 31);
		panel.add(lblBt);

		JLabel lblP = new JLabel("P1");
		lblP.setOpaque(true);
		lblP.setHorizontalAlignment(SwingConstants.CENTER);
		lblP.setFont(new Font("Arial", Font.BOLD, 17));
		lblP.setBackground(Color.WHITE);
		lblP.setBounds(479, 121, 40, 24);
		panel.add(lblP);

		JLabel lblP_1 = new JLabel("P2");
		lblP_1.setOpaque(true);
		lblP_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblP_1.setFont(new Font("Arial", Font.BOLD, 17));
		lblP_1.setBackground(Color.WHITE);
		lblP_1.setBounds(479, 143, 40, 24);
		panel.add(lblP_1);

		JLabel lblP_2 = new JLabel("P3");
		lblP_2.setOpaque(true);
		lblP_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblP_2.setFont(new Font("Arial", Font.BOLD, 17));
		lblP_2.setBackground(Color.WHITE);
		lblP_2.setBounds(479, 167, 40, 24);
		panel.add(lblP_2);

		JLabel lblP_3 = new JLabel("P4");
		lblP_3.setOpaque(true);
		lblP_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblP_3.setFont(new Font("Arial", Font.BOLD, 17));
		lblP_3.setBackground(Color.WHITE);
		lblP_3.setBounds(479, 188, 40, 24);
		panel.add(lblP_3);

		JLabel lblP_4 = new JLabel("P5");
		lblP_4.setOpaque(true);
		lblP_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblP_4.setFont(new Font("Arial", Font.BOLD, 17));
		lblP_4.setBackground(Color.WHITE);
		lblP_4.setBounds(479, 207, 40, 24);
		panel.add(lblP_4);

		JLabel lblP_5 = new JLabel("P6");
		lblP_5.setOpaque(true);
		lblP_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblP_5.setFont(new Font("Arial", Font.BOLD, 17));
		lblP_5.setBackground(Color.WHITE);
		lblP_5.setBounds(479, 229, 40, 24);
		panel.add(lblP_5);

		JLabel lblP_6 = new JLabel("P7");
		lblP_6.setOpaque(true);
		lblP_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblP_6.setFont(new Font("Arial", Font.BOLD, 17));
		lblP_6.setBackground(Color.WHITE);
		lblP_6.setBounds(479, 253, 40, 24);
		panel.add(lblP_6);

		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
		// DefaultTableCellHeaderRenderer의 정렬을 가운데 정렬로 지정
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		// 반복문을 이용하여 테이블을 가운데 정렬로 지정
		for (int index = 0; index < tcmSchedule.getColumnCount(); index++) {

			tcmSchedule.getColumn(index).setCellRenderer(tScheduleCellRenderer);

		}

	}
}
