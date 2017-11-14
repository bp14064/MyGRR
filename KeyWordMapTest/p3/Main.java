package p3;

public class Main {
	static final int m = 2;

	public static void main(String[] args) {
		int node = 4;
		double[][] R = {{0.5, 0.5, 0.5, 0.5},
				       {0.5, 0.5, 0.5, 0.5},
				       {0.5, 0.5, 0.5, 0.5},
				       {0.5, 0.5, 0.5, 0.5}};
		double[][] k = {{1.5, 1.5, 1.5, 1.5},
			           {1.5, 1.5, 1.5, 1.5},
			           {1.5, 1.5, 1.5, 1.5},
			           {1.5, 1.5, 1.5, 1.5}};

		double[][] l = new double[4][4];

		for(int i = 0; i < node; i++) {
			for(int j = 0; j < node; j++) {
				l[i][j] = computeL(R[i][j]);
			}
		}

		//キーワード
		Keyword[] keyArray = new Keyword[4];
		keyArray[0] = new Keyword(1.0, 1.0);
		keyArray[1] = new Keyword(2.0, 1.5);
		keyArray[2] = new Keyword(3.0, 4.0);
		keyArray[3] = new Keyword(6.0, 5.0);

		int TRIALS = 50;
		int c = 2;

		for(int t = 0; t < TRIALS; t++) {
			System.out.println("TRIALS:"+(t+1));
			for(int a=0;a<4;a++) {
				keyArray[a].showKeywordInfo();
			}
			System.out.println("=========================");

			for(int p = 1; p < node; p++) {
			keyArray[0].setX( keyArray[0].getX() + (c * computeDx(keyArray[0].getX(), keyArray[p].getX(), keyArray[0].getY(), keyArray[p].getY(), l[0][p], k[0][p])) );
			keyArray[0].setY( keyArray[0].getY() + (c * computeDx(keyArray[0].getX(), keyArray[p].getX(), keyArray[0].getY(), keyArray[p].getY(), l[0][p], k[0][p])) );
			}
			keyArray[1].setX( keyArray[1].getX() + (c * computeDx(keyArray[1].getX(), keyArray[0].getX(), keyArray[1].getY(), keyArray[0].getY(), l[1][0], k[1][0])) );
			keyArray[1].setY( keyArray[1].getY() + (c * computeDx(keyArray[1].getX(), keyArray[0].getX(), keyArray[1].getY(), keyArray[0].getY(), l[1][0], k[1][0])) );
			keyArray[2].setX( keyArray[2].getX() + (c * computeDx(keyArray[2].getX(), keyArray[0].getX(), keyArray[2].getY(), keyArray[0].getY(), l[2][0], k[2][0])) );
			keyArray[2].setY( keyArray[2].getY() + (c * computeDx(keyArray[2].getX(), keyArray[0].getX(), keyArray[2].getY(), keyArray[0].getY(), l[2][0], k[2][0])) );
			keyArray[3].setX( keyArray[3].getX() + (c * computeDx(keyArray[3].getX(), keyArray[0].getX(), keyArray[3].getY(), keyArray[0].getY(), l[3][0], k[3][0])) );
			keyArray[3].setY( keyArray[3].getY() + (c * computeDx(keyArray[3].getX(), keyArray[0].getX(), keyArray[3].getY(), keyArray[0].getY(), l[3][0], k[3][0])) );
		}

	}

	static double computeL(double R) {
		return m * (1 - R);
	}

	static double computeDx(double x1, double x2, double y1, double y2, double l, double k) {
		double res = 0;
		res = k * ( (x1-x2) - ( l / ( Math.sqrt( (Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2) ) ) ) ));
		return res;
	}

	static double computeDy(double x1, double x2, double y1, double y2, double l, double k) {
		double res = 0;
		res = k * ( (y1-y2) - ( l / ( Math.sqrt( (Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2) ) ) ) ));
		return res;
	}

}
