package com.func.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import com.func.FCFS;
import com.pro.Process;
class FCFSTest {

	@Test
	void testFCFS() {
		fail("Not yet implemented");
	}

	@Test
	void testdoScheduling() {
		Process[] test = new Process[15];
		Process[] obj = new Process[15];
		FCFS f = new FCFS(1);
		f.insertPcs(0, 2);
		f.insertPcs(0, 2);
		f.insertPcs(1, 4);
		f.insertPcs(2, 3);
		test=f.doScheduling();
		
		obj[0]=new Process(0, 2, 2, 0, 2);
		obj[1]=new Process(0, 2, 4, 2, 4);
		obj[2]=new Process(1, 4, 8, 3, 7);
		obj[3]=new Process(2, 3, 11, 6, 9);
		for(int i=0;i<4;i++) {
			if(obj[i].equals(test[i])) {
				fail(obj[i].toString()+" Æ²·Á¾¸ "+test[i].toString());
			}
				
		}
		assertTrue(true);
	}
}
