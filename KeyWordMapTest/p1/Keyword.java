package p1;

public class Keyword {
	private double x;
	private double y;
	private double R;

	public Keyword(double x, double y, double R) {
		this.x = x;
		this.y = y;
		this.R = R;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getR() {
		return R;
	}

	public void setR(double r) {
		R = r;
	}

	public void showKeywordInfo() {
		System.out.println("X:"+this.x+" Y:"+this.y+" R:"+this.R);
	}
}
