package com.func;

import com.pro.Process;

public class FCFS extends Scheduling {
	public FCFS(int processorNum) {
		super(processorNum);
	}

	@Override
	public Process[] doScheduling() {
		System.out.println("---In FCFS processorNum = "+processorNum);
		int index = 0;
		Process[] rePro = new Process[15];
		printProcessInfo(); // 프로세스 정보 출력. 디버깅용
		for (int time = 0; !isEnd(); time++) {
			setWaitQ(time); // 매 시간단위마다 도착한 프로세스를 대기큐에 넣어줍니다.
			for (int i = 0; i < processorNum; i++) { // 프로세서의 개수만큼 반복
				if (processor[i] != null) {
					runStatus[i][time] = processor[i].getID();
					System.out.println(i + "번 프로세서의   " + time + " 시간에  = " + processor[i].getID());
				}
				if (checkRemain(time, i)) { // 프로세스가 끝났는지 검사합니다.
					rePro[index++] = processor[i];
					processor[i] = null;
				}
				if (processor[i] == null) { // 프로세서가 비어있을경우
					if (!waitQ.isEmpty()) // 그리고 대기큐에 프로세스가 있는경우
						processor[i] = waitQ.pop(); // 대기큐에서 프로세스를 가져옵니다.
				} else {
					processor[i].reduRemainTime();

				} // 프로세서가 사용되고있으면 remain time을 감소시킵니다.
			}
		}
		//this.rePro = rePro;
		System.out.println("---Out FCFS ");
		return rePro;
	}
}