package com.pro;

public class Process {
	int arrTime;	//arrival time
	int burTime;	//burst time
	int endTime;	//end time
	int ID;			//process ID
	int waitTime;	//waiting time
	int turnTime;	//turnaround time
	int remainTime;	//remain time
	int quantum;	//quantum (Round Robin)
	float normalizedTT;
	
	public Process() {}
	public Process(Process p) {
		this.arrTime = p.arrTime;
		this.burTime = p.burTime;
		this.endTime = p.endTime;
		this.waitTime = p.waitTime;
		this.turnTime = p.turnTime;
		this.remainTime = p.remainTime;
		this.ID = p.ID;
	}
	public Process(int arrTime, int burTime) {
		this.arrTime=arrTime;
		this.burTime=burTime;
		this.remainTime=burTime;
		waitTime=0;  
		turnTime=0;
		quantum=0;
	}
	// 테스트용
	public Process(int arrTime, int burTime, int endTime, int waitTime, int turnTime) {
		super();
		this.arrTime = arrTime;
		this.burTime = burTime;
		this.endTime = endTime;
		this.waitTime = waitTime;
		this.turnTime = turnTime;
	}
	public void addWaitTime() {
		waitTime++;
	}
	public void reduRemainTime() {
		remainTime--;
	}
	public int getRemainTime() {
		return remainTime;
	}
	public void setRemainTime(int remainTime) {
		this.remainTime = remainTime;
	}
	public int getArrTime() {
		return arrTime;
	}
	public void setArrTime(int arrTime) {
		this.arrTime = arrTime;
	}
	public int getBurTime() {
		return burTime;
	}
	public void setBurTime(int burTime) {
		this.burTime = burTime;
	}
	public int getEndTime() {
		return endTime;
	}
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		this.ID = iD;
	}
	public int getWaitTime() {
		return waitTime;
	}
	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}
	public int getTurnTime() {
		return turnTime;
	}
	public void setTurnTime(int turnTime) {
		this.turnTime = turnTime;
	}
	public int getQuantum() {
		return quantum;
	}
	public void setQuantum(int quantum) {
		this.quantum = quantum;
	}
	public float getNormalizedTT() {
		return normalizedTT;
	}
	public void setNormalizedTT(float normalizedTT) {
		this.normalizedTT = normalizedTT;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	@Override
	public String toString() {
		return "Process [ ID="+ID+", arrTime=" + arrTime + ", burTime=" + burTime + ", endTime=" + endTime + ", waitTime="
				+ waitTime + ", turnTime=" + turnTime +", remainTime="+remainTime + ", NTT = "+normalizedTT+"]";
	}
}
