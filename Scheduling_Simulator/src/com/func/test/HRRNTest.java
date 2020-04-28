package com.func.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.func.HRRN;
import com.pro.Process;

class HRRNTest {

	// 코어 1개, 프로세스 4개
	@Test
	void testDoScheduling() {
		Process[] test = new Process[15];
		Process[] obj = new Process[15];
		HRRN h = new HRRN(1);
		h.insertPcs(0, 3);
		h.insertPcs(1, 7);
		h.insertPcs(3, 2);
		h.insertPcs(5, 5);
		h.insertPcs(6, 3);
		test=h.doScheduling();
		
		obj[0]=new Process(0, 3, 3, 0, 3);
		obj[1]=new Process(1, 7, 10, 2, 9);
		obj[2]=new Process(3, 2, 12, 7, 9);
		obj[3]=new Process(6, 3, 15, 6, 9);
		obj[4]=new Process(5, 5, 20, 10, 15);
		for(int i=0;i<5;i++) {
			if(obj[i].equals(test[i])) {
				fail(obj[i].toString()+" Not Equal "+test[i].toString());
			}
				
		}
		assertTrue(true);
	}

}
