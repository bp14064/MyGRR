package p6;

import kk_method.Keyword;

public class Main {
	static final double m = 5;
	static final int K = -10;

	public static void main(String[] args) {
		int node = 4;
		double[][] k = new double[4][4];
		double[][] l = new double[4][4];
		double[][] d = new double[4][4];
		double[][] R = {{0.3, 0.3, 0.3, 0.3},
		                {0.3, 0.5, -1, -1},
		                {0.3, -1, 0.5, -1},
		                {0.3, -1, -1, 0.5}};
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
		for(int i = 0; i < node; i++) {
			for(int j = 0; j < node; j++) {
				l[i][j] = computeL(R[i][j]);
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
		 /*keyArray[0].setXY(0, 50);
		 keyArray[1].setXY(50, 0);
		 keyArray[2].setXY(0, -50);
		 keyArray[3].setXY(-50, 0);*/

		int TRIALS = 1000;
		double c = 0.5;
		double energy = 0;
		double deltaX = 0;
		double deltaY = 0;

		/*
		 * 終了条件は以下のようにすればいいのでは？
		 * preE - energy　< ε
		 * つまり、前回のエネルギーの値から今回のエネルギーの値を引いたものが　十分小さくなったら終了
		 * しかし、2.○○とかになったり、意外と大きいので、どのぐらいが十分小さくなったのかというのを判断するのが難しい
		 *
		 * とりあえず、TRIALSで決めて使うことにした
		 * 1000を超えると（さらに前でもかもしれないが）、ほとんど影響がでない
		 *
		 */

		for(int t = 0; t < TRIALS; t++) {
			//エネルギーの計算
			//全体のエネルギーの算出
			for(int i=0;i<node;i++) {
				for(int j=0;j<node;j++) {
					if(i!=j) {
					energy += computeESub(keyArray[i].getX(), keyArray[j].getX(), keyArray[i].getY(), keyArray[j].getY(), l[i][j], k[i][j]);
					}
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

	static double computeL(double R) {
		return m * (1 - R);
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
