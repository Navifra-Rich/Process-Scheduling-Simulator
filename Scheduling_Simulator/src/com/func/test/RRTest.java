package com.func.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import com.func.RR;
import com.pro.Process;
class RRTest {

	//코어 1개, 프로세스 5개, quantum 2 
	@Test
	void testDoScheduling() {
		
		Process[] test = new Process[15];
		Process[] obj = new Process[15];
		RR r =new RR(1,2);
		r.insertPcs(0, 3);
		r.insertPcs(1, 7);
		r.insertPcs(3, 2);
		r.insertPcs(5, 5);
		r.insertPcs(6, 3);
		test=r.doScheduling();
		
		obj[0]=new Process(0, 3, 5, 2, 5);
		obj[1]=new Process(3, 2, 7, 2, 4);
		obj[2]=new Process(6, 3, 18, 9, 12);
		obj[3]=new Process(1, 7, 19, 11, 18);
		obj[4]=new Process(5, 5, 20, 10, 15);
		for(int i=0;i<5;i++) {
			if(obj[i].equals(test[i])) {
				fail(obj[i].toString()+" Not Equal "+test[i].toString());
			}
				
		}
		assertTrue(true);
	}
	//코어 1개, 프로세스 5개, quantum 3 
		@Test
		void testDoScheduling2() {
			
			Process[] test = new Process[15];
			Process[] obj = new Process[15];
			RR r =new RR(1,3);
			r.insertPcs(0, 3);
			r.insertPcs(1, 7);
			r.insertPcs(3, 2);
			r.insertPcs(5, 5);
			r.insertPcs(6, 3);
			test=r.doScheduling();
			
			obj[0]=new Process(0, 3, 3, 0, 3);
			obj[1]=new Process(3, 2, 8, 3, 5);
			obj[2]=new Process(6, 3, 14, 5, 8);
			obj[3]=new Process(5, 5, 19, 9, 14);
			obj[4]=new Process(6, 3, 20, 5, 8);
			for(int i=0;i<5;i++) {
				if(obj[i].equals(test[i])) {
					fail(obj[i].toString()+" Not Equal "+test[i].toString());
				}
					
			}
			assertTrue(true);
		}

}
