package p3;

import javafx.scene.paint.Color;

public class Control2 {
	private int index;
	private final int max;
	Color[] color = new Color[4];
	public Control2(int index, int max) {
		this.index = index;
		this.max = max;
		this.color[0] = Color.BLUEVIOLET;
		this.color[1] = Color.RED;
		this.color[2] = Color.YELLOW;
		this.color[3] = Color.GREEN;
		// TODO 自動生成されたコンストラクター・スタブ
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getMax() {
		return max;
	}

}
