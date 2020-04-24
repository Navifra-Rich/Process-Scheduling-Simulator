package com.pro;

public class Process {
	int arrTime;
	int burTime;
	int endTime;
	int ID;
	int waitTime;
	int turnTime;
	int remainTime;
	int quantum;
	
	public Process() {}
	public Process(int arrTime, int burTime) {
		this.arrTime=arrTime;
		this.burTime=burTime;
		this.remainTime=burTime;
		waitTime=0;  
		turnTime=0;
		quantum=0;
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
		ID = iD;
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
	@Override
	public String toString() {
		return "Process [arrTime=" + arrTime + ", burTime=" + burTime + ", endTime=" + endTime + ", waitTime="
				+ waitTime + ", turnTime=" + turnTime +", remainTime="+remainTime + "]";
	}
}
