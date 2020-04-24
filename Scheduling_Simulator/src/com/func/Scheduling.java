package com.func;

import java.util.ArrayList;
import java.util.LinkedList;

import com.pro.Process;

public class Scheduling {
	public Scheduling() {
		processorNum=1;
	}
	int processorNum;
	Process[] processor = new Process[4];
	ArrayList<Process> pcs = new ArrayList<Process>(); // 모든 프로세스
	LinkedList<Process> waitQ = new LinkedList<Process>(); // 시작 가능한 프로세스 대기큐
	
	// 프로세스 정보를 입력받아 저장
	public void insertPcs(int arrTime, int burTime) {
		Process pro = new Process(arrTime, burTime);
		pcs.add(pro);
	}
	// 모든 과정이 끝났는지 체크합니다.
		public boolean isEnd() {
			for (int i = 0; i < pcs.size(); i++)
				if (pcs.get(i).getRemainTime() != 0) 
					return false;
			return true;
		}
		// 프로세스 전체 큐에서 시간에 따라 프로세스 대기큐로 프로세스를 이동시킵니다. 매 시간단위마다 호출됩니다.
		public void setWaitQ(int time) {
			for (int i = 0; i < pcs.size(); i++) if (pcs.get(i).getArrTime() == time) waitQ.add(pcs.get(i));
		}
		
		public void doScheduling() {}
		
		
		
		public void checkRemain(int time, int processorIdx) {
				if(processor[processorIdx]!=null&&processor[processorIdx].getRemainTime() == 1) {
					processor[processorIdx].setTurnTime(time  - processor[processorIdx].getArrTime());
					processor[processorIdx].setEndTime(time );
					processor[processorIdx].setWaitTime(processor[processorIdx].getTurnTime() - processor[processorIdx].getBurTime());
					processor[processorIdx].reduRemainTime();
					System.out.println("   "+processorIdx+"번째 프로세서의 프로세스 종료  " + processor[processorIdx].toString());
					processor[processorIdx]=null;
				}
		}
		public void printProcessInfo() {
			for (int i = 0; i < pcs.size(); i++)
				System.out.println(pcs.get(i).toString());
		}
}