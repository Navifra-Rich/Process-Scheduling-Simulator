package com.func;

public class FCFS extends Scheduling{
	
	public FCFS() {
		super();
	}
	// 실제 스케쥴링
	@Override
	public void doScheduling() {
		printProcessInfo();
		for (int time = 0; !isEnd(); time++) {
			setWaitQ(time);
			for(int i=0;i<processorNum;i++) {
				checkRemain(time,i);	
				if(processor[i]==null) {
					if(!waitQ.isEmpty())
						processor[i] = waitQ.pop();
				}else processor[i].reduRemainTime();		
			}
		}
	}
}