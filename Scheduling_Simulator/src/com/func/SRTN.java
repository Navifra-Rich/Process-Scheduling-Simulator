package com.func;

import com.pro.Process;

public class SRTN extends Scheduling {
	public SRTN(int processorNum) {
		super(processorNum);
	}

	@Override
	public Process[] doScheduling() {
		int index = 0;
		Process[] rePro = new Process[15];
		printProcessInfo(); // 프로세스 정보 출력. 디버깅용
		for (int time = 0; !isEnd(); time++) {
			setWaitQ(time); // 매 시간단위마다 도착한 프로세스를 대기큐에 넣어줍니다.
			for (int i = 0; i < processorNum; i++) { // 프로세서의 개수만큼 반복
				if (processor[i] != null) {
					runStatus[i][time] = processor[i].getID();
				}else runStatus[i][time]=-1;
				if (checkRemain(time, i)) { // 프로세스가 끝났는지 검사합니다.
					rePro[index++] = processor[i];
					processor[i] = null;
				}
				if (processor[i] == null) {
					if (!waitQ.isEmpty()) {
						processor[i] = waitQ.get(findNext());
						waitQ.remove(findNext());
					}
				} else if (!waitQ.isEmpty() && (processor[i].getRemainTime() > waitQ.get(findNext()).getRemainTime())) {
					processor[i].reduRemainTime();
					if (processor[i].getRemainTime() > 1) {
						waitQ.add(processor[i]);
					}
					processor[i] = waitQ.get(findNext());
					waitQ.remove(findNext());
				} else {
					processor[i].reduRemainTime();
				}
			}
		}
		return rePro;
	}

	public int findNext() {
		int min = Integer.MAX_VALUE;
		int index = 0;
		for (int i = 0; i < waitQ.size(); i++) {
			if (waitQ.get(i).getRemainTime() < min) {
				min = waitQ.get(i).getRemainTime();
				index = i;
			}
		}
		return index;
	}
}