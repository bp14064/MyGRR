package p6;

public class Vector {
	private double X;
	private double Y;

	public Vector(double X, double Y) {
		this.X = X;
		this.Y = Y;
	}

	public static Vector operator(Vector v1, Vector v2) {
		return new Vector((v1.getX()+v2.getX()),(v1.getY()+v2.getY()));
	}


	public double getX() {
		return X;
	}

	public void setX(double x) {
		X = x;
	}

	public double getY() {
		return Y;
	}

	public void setY(double y) {
		Y = y;
	}

	public void setXY(double x, double y) {
		this.X = x;
		this.Y = y;
	}

}
