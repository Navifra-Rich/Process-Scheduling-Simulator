package com.func;

import com.pro.Process;

public class COVID extends Scheduling {
	int isolation = 0;
	int INFECTED = 5;
	public COVID(int processorNum) {
		super(processorNum);
	}
	@Override
	public Process[] doScheduling() {
		isolation = processorNum / 2;
		int index = 0;
		Process[] rePro = new Process[15];
		for (int time = 0; !isEnd(); time++) {
			setWaitQ(time); // 매 시간단위마다 도착한 프로세스를 대기큐에 넣어줍니다.
			for (int i = 0; i < processorNum; i++) { // 프로세서의 개수만큼 반복
				if (processor[i] != null) runStatus[i][time] = processor[i].getID();
				if (checkRemain(time, i)) { // 프로세스가 끝났는지 검사합니다.
					rePro[index++] = processor[i];
					processor[i] = null;
				}
				if (processor[i] == null) { // 프로세서가 비어있을경우
					if (!waitQ.isEmpty()) {// 그리고 대기큐에 프로세스가 있는경우
						int idx = getIndex(i<isolation);	//burstTime을 측정해서 격리 여부를 결정합니다.
						if(idx!=-1) {
							processor[i] = waitQ.get(idx); // 대기큐에서 프로세스를 가져옵니다.
							waitQ.remove(idx);
						}
					}	
				} else processor[i].reduRemainTime();
				// 프로세서가 사용되고있으면 remain time을 감소시킵니다.
			}
		}
		return rePro;
	}
	public int getIndex(boolean isIsolated) {
		if(isolation==0)
			return 0;
		int re=-1;
		for(int i=0;i<waitQ.size()&&re==-1;i++) {
			if(isIsolated) {
				if(waitQ.get(i).getBurTime()>=INFECTED) re=i;
			}else {
				if(waitQ.get(i).getBurTime()<INFECTED)re=i;
			}
		}
		return re;
	}
}
