package com.func;

public class RR extends Scheduling{
	
	int quantum;
	public RR() {
		super();
		quantum=3;
	}
	// 실제 스케쥴링
	@Override
	public void doScheduling() {
		printProcessInfo();
		for (int time = 0; !isEnd(); time++) {
			setWaitQ(time);
			for(int i=0;i<processorNum;i++) {
				if(processor[i]!=null) checkRemain(time,i);	
				if(processor[i]!=null&&processor[i].getQuantum()==quantum-1) {
					processor[i].setQuantum(0);
					processor[i].reduRemainTime();
					waitQ.offer(processor[i]);
					processor[i]=null;
				}
				if(processor[i]==null) {
					if(!waitQ.isEmpty()) processor[i] = waitQ.pop();
				}else {
					processor[i].reduRemainTime();
					processor[i].setQuantum(processor[i].getQuantum()+1);
				}
			}
		}
	}
}