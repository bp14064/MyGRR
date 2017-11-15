package p4;

public class Main {
	static final double m = 2;
	static final int K = 10;

	/**
	 * 移動量を求める式を変更
	 * @param args
	 */
	public static void main(String[] args) {
		int node = 4;
		double[][] R = {{0.3, 0.3, 0.3, 0.3},
				         {0.3, 0.5, -1, -1},
				         {0.3, -1, 0.5, -1},
				         {0.3, -1, -1, 0.5}};
		double[][] k = new double[4][4];

		double[][] l = new double[4][4];

		/*for(int i = 0; i < node; i++) {
			for(int j = 0; j < node; j++) {
				l[i][j] = computeL(R[i][j]);
			}
		}*/

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
		double d[] = new double[node];
		for(int g=0;g<node;g++) {
		  for(int g2=0;g2<0;g2++) {
			  if(g!=g2)
			d[g] = computeD(keyArray[g].getX(), keyArray[g2].getX(), keyArray[g].getY(), keyArray[g2].getY());
		  }
		}
		double max2=0;
		for(int l1 = 0; l1 < node; l1++)
            max2 = Math.max(max2,d[l1]);

		for(int h = 0; h < node; h++) {
			for(int j = 0; j < node; j++) {
				if(h!=j)
				l[h][j] = computeL2(d[j],max2);
			}
		}

		int LIMIT = 100;
		int LIMIT2 = 100;
		double c = 3;
		double energy = 0;

		double eps = 0;
		double delta[] = new double[node];
		double max1 =0;
		int maxNum = 0;
		double dx=0;
		double dy=0;

		for(int i = 0; i<LIMIT;i++) {

			for(int i1=0;i1<node;i1++) {
				for(int i2=0;i2<node;i2++) {
					energy += computeESub(keyArray[i1].getX(), keyArray[i2].getX(), keyArray[i1].getY(), keyArray[i2].getY(), l[i1][i2], k[i1][i2]);
				}
			}
			//このエネルギーがInfinityになったらやめてその一個前を取る
			//System.out.println("エネルギー："+energy);
			System.out.println(energy);
			//System.out.println("=========================");


			//移動させる頂点を決める
			 //Δiの計算
			 for(int j=0;j<node;j++) {
				for(int j2=0;j2<node;j2++) {
					if(j!=j2)
				     delta[j] =  computeDelta(keyArray[j].getX(), keyArray[j2].getX(), keyArray[j].getY(), keyArray[j2].getY(), l[j][j2], k[j][j2]);
				}
			 }
			 //Δiの中から最大値を求める → Δm
			 for(int l1 = 0; l1 < node; l1++)
                 max1 = Math.max(max1,delta[l1]);
			 for(int l2 = 0; l2<node; l2++) {
			      if(max1 == delta[l2]) {
			    	  maxNum = l2;
			    	  break;
			      }
			 }

			 //以下をΔmが十分小さくなるまで繰り返す
			 for(int s = 0;s<LIMIT2;s++) {
			 //ノード（m）と他のノード（i）間で移動量の計算
			 //mは固定,iだけ動かす
			 for(int n = 0; n<node;n++) {
				if(maxNum!=n) {
				 dx += computeDx(keyArray[maxNum].getX(),keyArray[n].getX(),keyArray[maxNum].getY(),keyArray[n].getY(),l[maxNum][n], k[maxNum][n]);
				 dy += computeDy(keyArray[maxNum].getX(),keyArray[n].getX(),keyArray[maxNum].getY(),keyArray[n].getY(),l[maxNum][n], k[maxNum][n]);
				}
			 }
			 keyArray[maxNum].setX(keyArray[maxNum].getX() + dx);
			 keyArray[maxNum].setY(keyArray[maxNum].getY() + dy);


			 }
			 max1 = 0;
			 maxNum=0;
			 dx=0;
			 dy=0;
		}


		System.out.println("最終結果");
		for(int y=0;y<node;y++) {
			keyArray[y].showKeywordInfo();
		}

	}

	static double computeL(double R) {
		return m * (1 - R);
	}

	static double computeL2(double d, double maxd) {
		return (100 / maxd)*d;
	}

	static double computeK(double x1, double x2, double y1, double y2) {
		return K / (Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
	}

	static double computeD(double x1, double x2, double y1, double y2) {
		return  Math.sqrt( (Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2)));
	}

	static double computeESub(double x1, double x2, double y1, double y2, double l, double k) {
		double res = 0;
		res = 0.5 * k * Math.pow( (  Math.sqrt( (Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2))) - l  ) , 2);
		return res;
	}

	static double computeEqA(double x1, double x2, double y1, double y2, double l, double k) {
		double res = 0;
		res = k * ( 1 - ( ( l * Math.pow( ( y1 - y2 ) , 2) ) / ( Math.pow( Math.pow( (x1 -x2), 2) + Math.pow((y1-y2), 2) , 3/2) ) ) );
		return res;
	}
	static double computeEqE(double x1, double x2, double y1, double y2, double l, double k) {
		double res=0;
		res =  k * ( 1 - ( ( l * Math.pow( ( x1 - x2 ) , 2) ) / ( Math.pow( Math.pow( (x1 -x2), 2) + Math.pow((y1-y2), 2) , 3/2) ) ) );
		return res;
	}
	static double computeEqB(double x1, double x2, double y1, double y2, double l, double k) {
		double res = 0;
		res = k * ( ( l * (x1 - x2) * (y1 - y2) ) / (  Math.pow( Math.pow( (x1 -x2), 2) + Math.pow((y1-y2), 2) , 3/2) ) );
		return res;
	}
	static double computeEqD(double x1, double x2, double y1, double y2, double l, double k) {
		double res = 0;
		res = k * ( ( l * (x1 - x2) * (y1 - y2) ) / (  Math.pow( Math.pow( (x1 -x2), 2) + Math.pow((y1-y2), 2) , 3/2) ) );
		return res;
	}
	static double computeEqC(double x1, double x2, double y1, double y2, double l, double k) {
		double res = 0;
		res = k * ( (x1-x2) - ( (l * (x1 -x2)) / ( Math.sqrt( (Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2) ) ) ) ));
		return res;
	}
	static double computeEqF(double x1, double x2, double y1, double y2, double l, double k) {
		double res = 0;
		res = k * ( (y1-y2) - ( (l * (y1 -y2)) / ( Math.sqrt( (Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2) ) ) ) ));
		return res;
	}
	static double computeDx(double x1, double x2, double y1, double y2, double l, double k) {
		double res = 0;
		res = ((computeEqB(x1,x2,y1,y2,l,k) * computeEqF(x1,x2,y1,y2,l,k))/computeEqE(x1,x2,y1,y2,l,k) - computeEqC(x1,x2,y1,y2,l,k)) / (computeEqA(x1,x2,y1,y2,l,k) - (computeEqB(x1,x2,y1,y2,l,k) * computeEqD(x1,x2,y1,y2,l,k))/computeEqE(x1,x2,y1,y2,l,k));
		return res;
	}
	static double computeDy(double x1, double x2, double y1, double y2, double l, double k) {
		double res = 0;
		res =  ((computeEqD(x1,x2,y1,y2,l,k) * computeEqC(x1,x2,y1,y2,l,k))/computeEqA(x1,x2,y1,y2,l,k) - computeEqF(x1,x2,y1,y2,l,k)) / (computeEqE(x1,x2,y1,y2,l,k) - (computeEqD(x1,x2,y1,y2,l,k) * computeEqB(x1,x2,y1,y2,l,k))/computeEqA(x1,x2,y1,y2,l,k));
		return res;
	}

	static double computeDelta(double x1, double x2, double y1, double y2, double l, double k) {
		return Math.sqrt( Math.pow(computeEqC(x1,x2,y1,y2,l,k), 2) + Math.pow(computeEqF(x1,x2,y1,y2,l,k), 2) );
	}

}
