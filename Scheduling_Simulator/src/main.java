import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.UI.UI;
import com.func.*;
import com.pro.Process;

public class main extends JFrame {

	public static void main(String[] args) {
		UI ui = new UI();
		Scheduling sch = new FCFS(1);

		ui.initialize();		
	}
}	