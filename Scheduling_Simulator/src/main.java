
import com.func.*;
public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		FCFS f = new FCFS();
//		f.insertPcs(0, 2);
//		f.insertPcs(0, 2);
//		f.insertPcs(1, 4);
//		f.insertPcs(2, 3);
//		f.doScheduling();
//		
//		HRRN h = new HRRN();
//		h.insertPcs(0, 3);
//		h.insertPcs(1, 7);
//		h.insertPcs(3, 2);
//		h.insertPcs(5, 5);
//		h.insertPcs(6, 3);
//		h.doScheduling();
		
		RR r =new RR();
		r.insertPcs(0, 3);
		r.insertPcs(1, 7);
		r.insertPcs(3, 2);
		r.insertPcs(5, 5);
		r.insertPcs(6, 3);
		r.doScheduling();
	}

}
