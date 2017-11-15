package p5;

import kk_method.Keyword;

public class Main {
	static final double m = 2;
	static final int K = -10;

	public static void main(String[] args) {
		int node = 4;
		double[][] k = new double[4][4];
		double[][] l = new double[4][4];
		double[][] d = new double[4][4];

		//ノードの設定
		Keyword[] keyArray = new Keyword[4];
		keyArray[0] = new Keyword(1.0, 1.0);
		keyArray[1] = new Keyword(2.0, -1.5);
		keyArray[2] = new Keyword(-3.0, 4.0);
		keyArray[3] = new Keyword(-6.0, -5.0);

		//dの計算
		for(int i = 0; i < node; i++) {
			for(int i2 = 0; i2 < node; i2++) {
				if(i!=i2)
				d[i][i2] =  Math.sqrt( Math.pow((keyArray[i].getX() - keyArray[i2].getX()), 2) + Math.pow((keyArray[i].getY() - keyArray[i2].getY()), 2));
				else
				d[i][i2] = 0;
			}
		}
		//lの計算
			//dの中で最大値のものを取る
		   double maxD = 0;
		   for(int j=0;j<node;j++) {
			  for(int j2=0;j2<node;j2++) {
				  if(j!=j2) {
					  if(maxD<d[j][j2])
						  maxD=d[j][j2];
				  }
			  }
		   }
		  //計算
		   for(int j=0;j<node;j++) {
				  for(int j2=0;j2<node;j2++) {
					  if(j!=j2)
					  l[j][j2] = computeL(100, maxD, d[j][j2]);
				  }
		   }

		   //kの計算
		for(int i = 0; i < node; i++) {
			for(int j = 0; j < node; j++) {
			  if(i!=j)
				k[i][j] = computeK(keyArray[i].getX(), keyArray[j].getX(), keyArray[i].getY(), keyArray[j].getY());
			  else
			    k[i][j] = 1;
			}
		}

		//ノードの座標の初期化
		//直径L0の円周上に等間隔に配置
		 keyArray[0].setXY(0, 50);
		 keyArray[1].setXY(50, 0);
		 keyArray[2].setXY(0, -50);
		 keyArray[3].setXY(-50, 0);

		int TRIALS = 500;
		double c = 0.5;
		double energy = 0;
		double deltaX = 0;
		double deltaY = 0;

		for(int t = 0; t < TRIALS; t++) {
			//エネルギーの計算
			//全体のエネルギーの算出
			for(int i=0;i<node;i++) {
				for(int j=0;j<node;j++) {
					if(i!=j)
					energy += computeESub(keyArray[i].getX(), keyArray[j].getX(), keyArray[i].getY(), keyArray[j].getY(), l[i][j], k[i][j]);
				}
			}
			//このエネルギーがInfinityになったらやめてその一個前を取る
			//System.out.println("エネルギー："+energy);
			System.out.println(energy);
			//System.out.println("=========================");

			//新しい座標の計算
			for(int p = 0; p < node; p++) {
				for(int q = 0; q < node; q++) {
					if(p!=q) {
					     deltaX += computeDx(keyArray[p].getX(), keyArray[q].getX(), keyArray[p].getY(), keyArray[q].getY(), l[p][q], k[p][q]);
					     deltaY += computeDy(keyArray[p].getX(), keyArray[q].getX(), keyArray[p].getY(), keyArray[q].getY(), l[p][q], k[p][q]);
					}
				}
				keyArray[p].setX( keyArray[p].getX() + c * deltaX );
				keyArray[p].setY( keyArray[p].getY() + c * deltaY );
				deltaX=0;
				deltaY=0;
			}

		}

		System.out.println("最終結果");
		for(int y=0;y<node;y++) {
			keyArray[y].showKeywordInfo();
		}

	}

	static double computeL(double L0, double maxd, double d) {
		return (L0 / maxd) * d;
	}

	static double computeDx(double x1, double x2, double y1, double y2, double l, double k) {
		double res = 0;
		res = k * ( (x1-x2) - ( (l * (x1 -x2)) / ( Math.sqrt( (Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2) ) ) ) ));
		return res;
	}

	static double computeDy(double x1, double x2, double y1, double y2, double l, double k) {
		double res = 0;
		res = k * ( (y1-y2) - ( (l * (y1 -y2)) / ( Math.sqrt( (Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2) ) ) ) ));
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
