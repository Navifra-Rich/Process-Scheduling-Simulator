package com.mul;

import javax.swing.SwingUtilities;

import com.UI.UI;
import com.func.Scheduling;

public class PrintThread implements Runnable {
	UI ui = new UI();
	public Scheduling sch;

	public PrintThread(UI ui) {
		this.ui = ui;
	}
	@Override
	public void run() {
		int i;
		// 종료조건 추가 예정 i의 값 말고 runStatus 마지막 값 출력될 때
		for (i = 1; i < sch.endTime; i++) {
			ui.printProcess(i);
			try {
				if (!Thread.currentThread().isInterrupted())
					Thread.sleep(1);
			} catch (InterruptedException  e) {
				e.printStackTrace();
				ui.denay = false;
				return;
			}
		}
		ui.denay = false;
	}
}