package com.cont;

import com.func.Scheduling;
import com.pro.Process;
import com.UI.*;
public class Controller {
	
	public Scheduling simulateAlgorithm(Scheduling sch, int processorNum, Process[] pcs) {
		System.out.println("---In Controller "+ pcs.length);
		int processNum = pcs.length;
		Process[] result = new Process[pcs.length];
		sch.setProcessNum(pcs.length);
		sch.setProcessorNum(processorNum);
		for(int i = 0;i<pcs.length;i++) {
			if(pcs[i]!=null) {
				//나중에 알아서 고치자
				sch.insertPcs(pcs[i].getArrTime(), pcs[i].getBurTime(),i+1);
				pcs[i].setID(i);
				System.out.println("ID = "+pcs[i].getID()+"arr = "+pcs[i].getArrTime()+"  bur = "+pcs[i].getBurTime());
			}
			else
				i=999;
		}
		UI ui = new UI();
		result = sch.doScheduling();
		ui.printResult(sch);
		return sch;
	}
}
