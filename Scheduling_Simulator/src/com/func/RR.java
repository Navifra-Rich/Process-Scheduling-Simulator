package com.func;

import com.pro.Process;

public class RR extends Scheduling {
	int quantum;

	public RR(int processorNum, int quantum) {
		super(processorNum);
		this.quantum = quantum;
	}

	@Override
	public Process[] doScheduling() {
		int index=0;
		Process[] rePro=new Process[15];
		printProcessInfo();
		for (int time = 0; !isEnd(); time++) {
			setWaitQ(time);
			for (int i = 0; i < processorNum; i++) {
				if (processor[i] != null) {
					if(checkRemain(time, i)) {
						rePro[index++]=processor[i];
						processor[i] = null;
					}else if (processor[i].getQuantum() == quantum - 1) {
					//if (processor[i] != null && processor[i].getQuantum() == quantum - 1) {
						processor[i].setQuantum(0);
						processor[i].reduRemainTime();
						waitQ.offer(processor[i]);
						processor[i] = null;
					}
				}
				if (processor[i] == null) {
					if (!waitQ.isEmpty())
						processor[i] = waitQ.pop();
				} else {
					processor[i].reduRemainTime();
					processor[i].setQuantum(processor[i].getQuantum() + 1);
				}
			}
		}
		System.out.println("Á¾·á");
		return rePro;
	}
}