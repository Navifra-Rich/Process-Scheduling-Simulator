package com.cont;

import com.func.Scheduling;
import com.pro.Process;
import com.UI.*;
public class Controller {
	
	public Scheduling simulateAlgorithm(UI ui, Scheduling sch, int processorNum, Process[] pcs) {
		System.out.println("---In Controller "+ pcs.length);
		sch.setProcessNum(pcs.length);
		sch.setProcessorNum(processorNum);
		for(int i = 0;i<pcs.length;i++) {
			if(pcs[i]!=null) {
				sch.insertPcs(pcs[i].getArrTime(), pcs[i].getBurTime(),i+1);
				System.out.println("ID = "+pcs[i].getID()+"arr = "+pcs[i].getArrTime()+"  bur = "+pcs[i].getBurTime());
			}
			else
				break;
		}
		sch.doScheduling();
		ui.printResultStart(sch);
		return sch;
	}
}