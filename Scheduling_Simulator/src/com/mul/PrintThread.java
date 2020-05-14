package com.mul;
import javax.swing.SwingUtilities;

import com.UI.UI;

public class PrintThread implements Runnable {
	UI ui = new UI();
	public PrintThread (UI ui) {
		this.ui=ui;
	}
	@Override
	public void run() {
		int i;
		// 종료조건 추가 예정 i의 값 말고 runStatus 마지막 값 출력될 때
		for(i=0;i<20;i++) {
			ui.printProcess(i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}

