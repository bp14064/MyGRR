package p2;

public class Main {
	static final int m = 2;
	static final int K = 5;

	public static void main(String[] args) {
		int node = 4;
		double[][] R = {{0.3, 0.3, 0.3, 0.3},
				         {0.3, 0.5, -1, -1},
				         {0.3, -1, 0.5, -1},
				         {0.3, -1, -1, 0.5}};
		double[][] k = new double[4][4];

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
		for(int i = 0; i < node; i++) {
			for(int j = 0; j < node; j++) {
			  if(i!=j)
				k[i][j] = computeK(keyArray[i].getX(), keyArray[j].getX(), keyArray[i].getY(), keyArray[j].getY());
			  else
			    k[i][j] = 1;
			}
		}
		Keyword[] tmp = new Keyword[4];
		/*for(int i=0;i<node;i++) {
			tmp[i] = new Keyword(0,0);
		}*/

		int TRIALS = 200;
		double c = 3;
		double energy = 0;

		for(int t = 0; t < TRIALS; t++) {
			//座標の表示
			//System.out.println("TRIALS:"+(t+1));
			//for(int a=0;a<4;a++) {
			//	keyArray[a].showKeywordInfo();
			//}
			//エネルギーの計算
			//全体のエネルギーの算出
			for(int i=0;i<node;i++) {
				for(int j=0;j<node;j++) {
					energy += computeESub(keyArray[i].getX(), keyArray[j].getX(), keyArray[i].getY(), keyArray[j].getY(), l[i][j], k[i][j]);
				}
			}
			//このエネルギーがInfinityになったらやめてその一個前を取る
			//System.out.println("エネルギー："+energy);
			//System.out.println("=========================");
			if(energy == Double.POSITIVE_INFINITY) {
				break;
			}else {
				for(int v = 0;v<node;v++) {
				 tmp[v] = new Keyword(keyArray[v].getX(),keyArray[v].getY());
				}
			}
			energy=0;



			//新しい座標の計算
			for(int p = 0; p < node; p++) {
				for(int q = 0; q < node; q++) {
					if(p!=q) {
						if(p!=0) {
					    keyArray[p].setX( keyArray[p].getX() + (c * computeDx(keyArray[p].getX(), keyArray[q].getX(), keyArray[p].getY(), keyArray[q].getY(), l[p][q], k[p][q])) );
					    keyArray[p].setY( keyArray[p].getY() + (c * computeDx(keyArray[p].getX(), keyArray[q].getX(), keyArray[p].getY(), keyArray[q].getY(), l[p][q], k[p][q])) );
						}
					}

				}
			}

		}

		System.out.println("最終結果");
		for(int y=0;y<node;y++) {
			tmp[y].showKeywordInfo();
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

	static double computeK(double x1, double x2, double y1, double y2) {
		return K / (Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
	}

	static double computeESub(double x1, double x2, double y1, double y2, double l, double k) {
		double res = 0;
		res = 0.5 * k * Math.pow( (  Math.sqrt( (Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2))) - l  ) , 2);
		return res;
	}

}
