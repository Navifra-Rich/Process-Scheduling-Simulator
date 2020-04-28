package com.func;

import com.pro.Process;

public class SRTN extends Scheduling {
	public SRTN(int processorNum) {
		super(processorNum);
	}
	public int pick(int remainTime) {
		int idx = -1, min = remainTime;
		for (int i = 0; i < waitQ.size(); i++) {
			int tempInt=waitQ.get(i).getRemainTime();
			System.out.println(" temp = "+tempInt);
			if(tempInt<min) {
				min=tempInt;
				idx=i;
			}
		}
		System.out.println("idx = "+idx);
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
				int remainTime = Integer.MAX_VALUE;	
				if(!waitQ.isEmpty()) {
					int idx=0;
					if (processor[i] != null) {
						processor[i].reduRemainTime(); // 프로세서가 사용되고있으면 remain time을 감소시킵니다.
						remainTime = processor[i].getRemainTime();		
					}
					idx = pick(remainTime);	
					if(idx!=-1) {
						if(processor[i]!=null) {
							Process add = processor[i];
							waitQ.offer(add);
						}
						processor[i] = waitQ.get(idx);
						waitQ.remove(idx);
					}
				}else if(processor[i]!=null)
					processor[i].reduRemainTime(); // 프로세서가 사용되고있으면 remain time을 감소시킵니다.
			}	
		}
		return rePro;
	}
}