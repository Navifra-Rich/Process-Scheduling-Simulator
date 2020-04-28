import javax.swing.JFrame;

import com.func.*;
import com.pro.Process;
public class main extends JFrame{

	public static void main(String[] args) {
		SRTN f = new SRTN(1);
		f.insertPcs(0, 3);
		f.insertPcs(1, 1);
		f.insertPcs(1, 4);
		f.insertPcs(2, 3);
		f.insertPcs(6, 2);
		Process[] tt =f.doScheduling();
		for(int i=0;i<5;i++) {
			System.out.println(tt[i].toString());
		}
		
//		SPN f = new SPN(1);
//		f.insertPcs(0, 2);
//		f.insertPcs(0, 2);
//		f.insertPcs(1, 4);
//		f.insertPcs(2, 3);
//		f.insertPcs(6, 3);
//		f.doScheduling();
	}
}