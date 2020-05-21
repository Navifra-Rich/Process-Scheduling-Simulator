package com.UI;

import java.awt.Color;

public class ProcessColor {
	Color[] color = new Color[15];
	public ProcessColor() {
		color[0] = new Color(220, 112, 126);
		color[1] = new Color(237, 170, 125);
		color[2] = new Color(239, 180, 193);
		color[3] = new Color(255, 255, 222);
		color[4] = new Color(168, 200, 121);
		color[5] = new Color(90, 160, 141);
		color[6] = new Color(76, 146, 177);
		color[7] = new Color(103, 143, 174);
		color[8] = new Color(150, 177, 208);
		color[9] = new Color(172, 153, 193);
		color[10] = new Color(240, 199, 171);
		color[11] = new Color(195, 226, 221);
		color[12] = new Color(212, 219, 181);
		color[13] = new Color(130, 171, 195);
		color[14] = new Color(252, 239, 204);
	}
	public Color getColor(int idx) {
		return color[idx];
	}
}
