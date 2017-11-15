package kk_method;

public class Main {
	//static final double m = 2;
	static final int K = 10;

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

		Keyword[] tmp = new Keyword[4];
		/*for(int i=0;i<node;i++) {
			tmp[i] = new Keyword(0,0);
		}*/

		int TRIALS = 300;
		int TRIALS2 = 200;
		double c = 3;
		double energy = 0;

		double[] delta = new double[node];
		double deltaMX=0;
		double deltaMY=0;
		double deltaMax =0;
		int M=0;
		double moveX=0;
		double moveY=0;
		double preDeltaM=0;
		double preDeltaMax=0;

		//for(int t = 0; t < TRIALS; t++) {
			 for(; ;) {

			//Δiを計算
			for(int k1=0;k1<node;k1++) {
			  for(int k2=0;k2<node;k2++) {
				if(k1!=k2) {
				deltaMX += computeEqC(keyArray[k1].getX(), keyArray[k2].getX(), keyArray[k1].getY(), keyArray[k2].getY(), l[k1][k2], k[k1][k2]);
				deltaMY += computeEqF(keyArray[k1].getX(), keyArray[k2].getX(), keyArray[k1].getY(), keyArray[k2].getY(), l[k1][k2], k[k1][k2]);
				}
			  }
			  delta[k1] = Math.sqrt(Math.pow(deltaMX, 2) + Math.pow(deltaMY, 2));
			  deltaMX=0;
			  deltaMY=0;
			}
		    //Δm（Δiの最大値を）決定
			 //まずは最大値を
			deltaMax=0;
			 for(int k3=0;k3<node;k3++) {
				 if(deltaMax<delta[k3]) {
					 deltaMax=delta[k3];
				 }
			 }
			 //最大のiを決定
			 for(int k4=0;k4<node;k4++) {
				 if(deltaMax==delta[k4])
					 M=k4;
			 }
			 //また、実際にはこのdeltaMaxがεより小さい場合（十分に小さい）は終了となる
			 /*
			  * if(deltaMax < eps)
			  *    break;
			  */
			 if(preDeltaMax < deltaMax) {
				 preDeltaMax = deltaMax;
			 }else {
				 break;
			 }

			 //次に、移動の計算に入る
			  //実際にはここにwhile（Δm>ε）が入る
			 //for(int t1 = 0; t1 < TRIALS2; t1++) {
			 for(; ;) {
			 //δx、δyの計算
			 for(int l1=0;l1<node;l1++) {
				 if(l1!=M) {
					 moveX += computeDx(keyArray[M].getX(), keyArray[l1].getX(), keyArray[M].getY(), keyArray[l1].getY(), l[M][l1], k[M][l1]);
					 moveY += computeDy(keyArray[M].getX(), keyArray[l1].getX(), keyArray[M].getY(), keyArray[l1].getY(), l[M][l1], k[M][l1]);
				 }
			 }
			 //移動
			 keyArray[M].setX( keyArray[M].getX() + moveX);
			 keyArray[M].setY( keyArray[M].getY() + moveY);
			 moveX=0;
			 moveY=0;
			 //終了条件のためにΔを計算
			 for(int l2=0;l2<node;l2++) {
				 if(l2!=M) {
					 deltaMX += computeEqC(keyArray[M].getX(), keyArray[l2].getX(), keyArray[M].getY(), keyArray[l2].getY(), l[M][l2], k[M][l2]);
				     deltaMY += computeEqF(keyArray[M].getX(), keyArray[l2].getX(), keyArray[M].getY(), keyArray[l2].getY(), l[M][l2], k[M][l2]);
				 }
			 }
			 delta[M] = Math.sqrt(Math.pow(deltaMX, 2) + Math.pow(deltaMY, 2));
			 deltaMX=0;
			 deltaMY=0;
			 //Δm > ε　つまり、十分小さいなら終了
			 if(preDeltaM < delta[M]) {
				 preDeltaM = delta[M];
			 }else {
				 break;
			 }

			 }
			 //System.out.println(delta[M]);
			 M=0;
			 deltaMX=0;
			 deltaMY=0;
			 deltaMax =0;
			 moveX=0;
			 moveY=0;
		}

		System.out.println("最終結果");
		for(int y=0;y<node;y++) {
			keyArray[y].showKeywordInfo();
		}

	}

	static double computeL(double L0, double maxd, double d) {
		return (L0 / maxd) * d;
	}


	static double computeK(double x1, double x2, double y1, double y2) {
		return K / (Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
	}

	static double computeESub(double x1, double x2, double y1, double y2, double l, double k) {
		double res = 0;
		res = 0.5 * k * Math.pow( (  Math.sqrt( (Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2))) - l  ) , 2);
		return res;
	}

	//------------------------------------------------------------------------------------------------------------//
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

	static double computeDelta(double x1, double x2, double y1, double y2, double l, double k) {
		return Math.sqrt( Math.pow(computeEqC(x1,x2,y1,y2,l,k), 2) + Math.pow(computeEqF(x1,x2,y1,y2,l,k), 2) );
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

}
