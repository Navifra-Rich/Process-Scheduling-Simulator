package com.func;

import com.pro.Process;

public class SPN extends Scheduling {
	public SPN(int processorNum) {
		super(processorNum);
	}
	public int pick() {
		int idx = 0;
		int minRemainTime=Integer.MAX_VALUE,tempInt=0;
		for (int i = 0; i < waitQ.size(); i++) {
			tempInt=waitQ.get(i).getRemainTime();
			if(tempInt<minRemainTime) {
				minRemainTime=tempInt;
				idx=i;
			}
		}
		return idx;
	}
	@Override
	public Process[] doScheduling() {
		int index = 0;
		Process[] rePro = new Process[15];
		printProcessInfo(); // 프로세스 정보 출력. 디버깅용
		for (int time = 0; !isEnd(); time++) {
			System.out.println("time "+time);
			setWaitQ(time); // 매 시간단위마다 도착한 프로세스를 대기큐에 넣어줍니다.
			for (int i = 0; i < processorNum; i++) { // 프로세서의 개수만큼 반복
				if (checkRemain(time, i)) { // 프로세스가 끝났는지 검사합니다.
					rePro[index++] = processor[i];
					processor[i] = null;
				}
				if (processor[i] == null) { // 프로세서가 비어있을경우
					if (!waitQ.isEmpty()) // 그리고 대기큐에 프로세스가 있는경우
					{
						int idx=pick();
						processor[i] = waitQ.get(idx); // 대기큐에서 프로세스를 가져옵니다.
						waitQ.remove(idx);
					}
				} else
					processor[i].reduRemainTime(); // 프로세서가 사용되고있으면 remain time을 감소시킵니다.
			}
		}
		return rePro;
	}
}
