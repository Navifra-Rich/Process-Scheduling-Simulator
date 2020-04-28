
import com.func.*;
import com.pro.Process;
public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Process[] p = new Process[15];
//		FCFS f = new FCFS(1);
//		f.insertPcs(0, 2);
//		f.insertPcs(0, 2);
//		f.insertPcs(1, 4);
//		f.insertPcs(2, 3);
//		p=f.doScheduling();
//		for(int i=0;i<4;i++) {
//			System.out.println(p[i].toString());
//		}
		HRRN h = new HRRN(1);
		h.insertPcs(0, 3);
		h.insertPcs(1, 7);
		h.insertPcs(3, 2);
		h.insertPcs(5, 5);
		h.insertPcs(6, 3);
		h.doScheduling();
		

//		Process[] obj = new Process[15];
//		RR r =new RR(1,2);
//		r.insertPcs(0, 3);
//		r.insertPcs(1, 7);
//		r.insertPcs(3, 2);
//		r.insertPcs(5, 5);
//		r.insertPcs(6, 3);
//		r.doScheduling();
	}

}
