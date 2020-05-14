package com.func;
import java.util.ArrayList;
import java.util.LinkedList;
import com.pro.Process;
public abstract class Scheduling {
	int processorNum;
	
	public int getProcessorNum() {
		return processorNum;
	}
	public void setProcessorNum(int num) {
		processorNum=num;
	}
	Process[] rePro = new Process[15];
	//나중에 private 바꿀거임
	public int[][] runStatus = new int[4][150];
	
	public void setProcessNum(int processNum) {
		rePro = new Process[processNum];
	}
	public Scheduling(int processorNum) {
		this.processorNum = processorNum;
	}
	Process[] processor = new Process[4];
	
	ArrayList<Process> pcs = new ArrayList<Process>(); 	// 모든 프로세스가 저장되어있는 큐
	LinkedList<Process> waitQ = new LinkedList<Process>(); // 도착한 프로세스가 대기하는 큐

	public ArrayList<Process> getProcessArray(){
		return pcs;
	}
	// 새로운 프로세스 정보를 입력받아 저장
	public void insertPcs(int arrTime, int burTime) {
		Process pro = new Process(arrTime, burTime);
		pcs.add(pro);
	}
	public void insertPcs(int arrTime, int burTime, int ID) {
		Process pro = new Process(arrTime, burTime);
		pro.setID(ID);
		pcs.add(pro);
	}
	// 모든 과정이 끝났는지 체크합니다. pcs의 모든 프로세스의 remain time이 0일경우 true를 반환합니다.
	public boolean isEnd() {
		for (int i = 0; i < pcs.size(); i++)
			if (pcs.get(i).getRemainTime() != 0)
				return false;
		return true;
	}
	// 프로세스 전체 큐에서 시간에 따라 프로세스 대기큐로 프로세스를 이동시킵니다. 매 시간단위마다 호출됩니다.
	public void setWaitQ(int time) {
		for (int i = 0; i < pcs.size(); i++)
			if (pcs.get(i).getArrTime() == time)
				waitQ.add(pcs.get(i));
	}
	// 모든 스케줄링 클래스는 이 함수를 상속받아 구현합니다.
	public Process[] doScheduling() {return null;};

	// 특정 프로세서 내부의 프로세스가 종료되어야 하는지 검사하는 메소드입니다. 프로세스를 종료시키고 데이터를 입력해줍니다.
	public boolean checkRemain(int time, int processorIdx) {
		if (processor[processorIdx] != null && processor[processorIdx].getRemainTime() == 1) {
			processor[processorIdx].setTurnTime(time - processor[processorIdx].getArrTime());
			processor[processorIdx].setEndTime(time);
			processor[processorIdx]
					.setWaitTime(processor[processorIdx].getTurnTime() - processor[processorIdx].getBurTime());
			processor[processorIdx].reduRemainTime();
			processor[processorIdx].setNormalizedTT((float)processor[processorIdx].getTurnTime()/processor[processorIdx].getBurTime());
			System.out.println("   " + processorIdx + "번째 프로세서의 프로세스 종료  " + processor[processorIdx].toString());
			
			return true;
		}
		return false;
	}
	// 모든 프로세스의 정보를 출력합니다. 유지보수 및 디버깅용 입니다.
	public void printProcessInfo() {
		for (int i = 0; i < pcs.size(); i++)
			System.out.println(pcs.get(i).toString());
	}
}