package p1;

public class Calculate {
	private final int m = 1; //理想距離の任意正定数
	private final double c = 0.1; //移動量の任意正定数
	private final int k = 2; //ばね定数（任意の正数）　本当は各キーワード間で別々にできるがとりあえず統一
	public Calculate() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public double calcIdealLength(double R) {
		double res = 0;
		res = this.m * (1 - R);
		return res;
	}

	public double calcMovementX(double x1, double x2, double y1, double y2, double l) {
		double res = 0;
		res = this.k * this.c * (x1 - x2) * (1 - (l / Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2))));
		return res;
	}

	public double calcMovementY(double y1, double y2, double x1, double x2, double l) {
		double res = 0;
		res = this.k * this.c * (y1 - y2) * (1 - (l / Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2))));
		return res;
	}

}
