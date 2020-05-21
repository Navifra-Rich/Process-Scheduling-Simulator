package com.func;
import com.pro.Process;
public class HRRN extends Scheduling{
	
	public HRRN(int processorNum) {super(processorNum);}
	// 대기큐 내에 있는 모든 프로세스의 대기시간을 1씩 더합ㅎ니다.
	public void addWaitTime() {
		for (int i = 0; i < waitQ.size(); i++) waitQ.get(i).addWaitTime();
	}
	// HRRN알고리즘에 의해서 대기큐에 있는 프로세스중 가장 우선순위가 높은 프로세스의 인덱스를 반환합니다.
	public int pick() {
		int maxIdx=0;
		double maxVal=0;
		for(int i=0;i<waitQ.size();i++) {
			double ratio;
			ratio = (double)waitQ.get(i).getWaitTime()/waitQ.get(i).getBurTime();
			if(ratio>maxVal) {
				maxIdx=i;
				maxVal=ratio;
			}	
		}
		return maxIdx;
	}
	// 실제 스케쥴링
	@Override
	public Process[] doScheduling() {
		int index=0;
		Process[] rePro=new Process[15];
		printProcessInfo();
		for (int time = 0; !isEnd(); time++) {
			setWaitQ(time);
			for(int i=0;i<processorNum;i++) {
				if (processor[i] != null) {
					runStatus[i][time] = processor[i].getID();
				}else runStatus[i][time]=-1;
				if(checkRemain(time,i)) {
					rePro[index++]=processor[i];
					processor[i]=null;
				}
				if(processor[i]==null) {
					if(!waitQ.isEmpty()) {
						int idx=pick();
						processor[i] = waitQ.get(idx);
						waitQ.remove(idx);
					}	
				}else processor[i].reduRemainTime();
			}
			addWaitTime();
		}
		return rePro;
	}
}