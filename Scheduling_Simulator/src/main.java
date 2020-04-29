import javax.swing.JFrame;

import com.func.*;
import com.pro.Process;
public class main extends JFrame{

	public static void main(String[] args) {
		Temp f = new Temp(1);
		f.insertPcs(0, 3);
		f.insertPcs(1, 10);
		f.insertPcs(0, 1);
		f.insertPcs(1, 4);
		f.insertPcs(2, 3);
		f.insertPcs(6, 3);
		Process[] tt =f.doScheduling();
		System.out.println(" RMx");

		for(int i=0;i<6;i++) {
			System.out.println(tt[i].toString()+" "+i);
		}
//		SRTN f = new SRTN(1);
//		f.insertPcs(0, 3);
//		f.insertPcs(1, 1);
//		f.insertPcs(1, 4);
//		f.insertPcs(2, 3);
//		f.insertPcs(6, 2);
//		Process[] tt =f.doScheduling();
//		for(int i=0;i<5;i++) {
//			System.out.println(tt[i].toString());
//		}
////		
//		SPN f = new SPN(1);
//		f.insertPcs(0, 3);
//		f.insertPcs(1, 10);
//		f.insertPcs(0, 1);
//		f.insertPcs(1, 4);
//		f.insertPcs(2, 3);
//		f.insertPcs(6, 3);
//		Process[] tt =f.doScheduling();
//		System.out.println(" RMx");
//		for(int i=0;i<6;i++) {
//			System.out.println(tt[i].toString()+" 11");
//		}
	}
}