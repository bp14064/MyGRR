package proto2;

public class ComputeMap {
	private final double M = 25; //これを変えると各頂点間の距離が変わる
	private final int TRIALS = 5500000; //ここは経験的にやるしかない（いまのところ） 追加（11/21）　画面の大きさが大きくなるとその分回数を増やさないといけない
	private final double cK = 15;
	private final double C = 0.05;//これ以上さげると、さらに回数を回さないといけなくなる　これ半分→TRIALS二倍

	private int node;
	private double[][] K;
	private double[][] L;
	private double[][] D;
	/*private double[][] R = {{0.5, 0.6, 0.6, 0.6},
            				  {0.6, 0.5, 0.1, 0.1},
            				  {0.6, 0.1, 0.5, 0.1},
            				  {0.6, 0.1, 0.1, 0.5}};*/
	/*private double[][] R = {{0.5, 0.4, 0.4, 0.4, 0.4},
			  {0.4, 0.5, 0.1, 0.1, 0.1},
			  {0.4, 0.1, 0.5, 0.1, 0.1},
			  {0.4, 0.1, 0.1, 0.5, 0.8},
			  {0.1, 0.1, 0.8 ,0.1, 0.5}};*/
	private double[][] R = {{0.5, 0.1, 0.1, 0.1, 0.1, 0.1},
							{0.1, 0.5, 0.1, 0.1, 0.1, 0.1},
							{0.1, 0.1, 0.5, 0.1, 0.1, 0.1},
							{0.1, 0.1, 0.1, 0.5, 0.8, 0.8},
							{0.1, 0.1, 0.1 ,0.8, 0.5, 0.1},
							{0.1, 0.1, 0.1, 0.8, 0.1, 0.1}};

	private double[] E = new double[4];

	public ComputeMap(int node) {
		this.node = node;
		this.D = new double[node][node];
		this.L = new double[node][node];
		this.K = new double[node][node];
		//this.R = new double[node][node];

	}

	public Keyword[] computeMap(Keyword[] keyArray) {
		double energy = 0;
		double deltaX = 0;
		double deltaY = 0;
		double preDx=0;
		double preDy=0;
		boolean first=true;
		double preE = 0;


		for(int t = 0; t < this.TRIALS; t++) {
			//エネルギーの計算
			//全体のエネルギーの算出
			for(int i=0;i<this.node;i++) {
				for(int j=0;j<this.node;j++) {
					if(i!=j) {
					energy += computeESub(keyArray[i].getX(), keyArray[j].getX(), keyArray[i].getY(), keyArray[j].getY(), this.L[i][j], this.K[i][j]);
					}
				}
			}
			//このエネルギーがInfinityになったらやめてその一個前を取る
			//System.out.println("エネルギー："+energy);
			preE = energy;
			//System.out.println(energy);

			if(energy==Double.POSITIVE_INFINITY)
				break;
			//System.out.println("=========================");

			//新しい座標の計算
			this.E[0] = this.computeE(keyArray, true, true);
			this.E[1] = this.computeE(keyArray, false, true);
			this.E[2] = this.computeE(keyArray, true, false);
			this.E[3] = this.computeE(keyArray, false, false);
			int minNum = 0;
			double min = E[0];
			if(first) {
			for(int i=1;i<4;i++) {
				if(E[i]<min) {
					min = E[i];
					minNum = i;
				}
			}
			first = false;
			}else {
				for(int i=0;i<4;i++) {
					if(E[i]<preE&&E[i]<min) {
						min = E[i];
						minNum=i;
					}
				}
			}

			if(minNum == 0) {
				for(int p = 0; p < this.node; p++) {
					for(int q = 0; q < this.node; q++) {
						if(p!=q) {
						     deltaX += computeDx(keyArray[p].getX(), keyArray[q].getX(), keyArray[p].getY(), keyArray[q].getY(), this.L[p][q], this.K[p][q]);
						     deltaY += computeDy(keyArray[p].getX(), keyArray[q].getX(), keyArray[p].getY(), keyArray[q].getY(), this.L[p][q], this.K[p][q]);
						}
					}
					//if(p!=0) {//あるものを動かしたくないときはここで設定しないことで行う

					preDx=deltaX;
					preDy=deltaY;
					keyArray[p].setX( keyArray[p].getX() + this.C * deltaX );
					keyArray[p].setY( keyArray[p].getY() + this.C * deltaY );
					//}
					deltaX=0;
					deltaY=0;
				}
			}else if(minNum == 1) {
				for(int p = 0; p < this.node; p++) {
					for(int q = 0; q < this.node; q++) {
						if(p!=q) {
						     deltaX += computeDx(keyArray[p].getX(), keyArray[q].getX(), keyArray[p].getY(), keyArray[q].getY(), this.L[p][q], this.K[p][q]);
						     deltaY += computeDy(keyArray[p].getX(), keyArray[q].getX(), keyArray[p].getY(), keyArray[q].getY(), this.L[p][q], this.K[p][q]);
						}
					}
					//if(p!=0) {//あるものを動かしたくないときはここで設定しないことで行う
					//System.out.println(preDx - deltaX);
					preDx=deltaX;
					preDy=deltaY;
					keyArray[p].setX( keyArray[p].getX() + this.C * (-1 * deltaX) );
					keyArray[p].setY( keyArray[p].getY() + this.C * deltaY );
					//}
					deltaX=0;
					deltaY=0;
				}
			}else if(minNum == 2) {
				for(int p = 0; p < this.node; p++) {
					for(int q = 0; q < this.node; q++) {
						if(p!=q) {
						     deltaX += computeDx(keyArray[p].getX(), keyArray[q].getX(), keyArray[p].getY(), keyArray[q].getY(), this.L[p][q], this.K[p][q]);
						     deltaY += computeDy(keyArray[p].getX(), keyArray[q].getX(), keyArray[p].getY(), keyArray[q].getY(), this.L[p][q], this.K[p][q]);
						}
					}
					//if(p!=0) {//あるものを動かしたくないときはここで設定しないことで行う

					preDx=deltaX;
					preDy=deltaY;
					keyArray[p].setX( keyArray[p].getX() + this.C * deltaX );
					keyArray[p].setY( keyArray[p].getY() + this.C * (-1 * deltaY) );
					//}
					deltaX=0;
					deltaY=0;
				}
			}else if(minNum == 3) {
				for(int p = 0; p < this.node; p++) {
					for(int q = 0; q < this.node; q++) {
						if(p!=q) {
						     deltaX += computeDx(keyArray[p].getX(), keyArray[q].getX(), keyArray[p].getY(), keyArray[q].getY(), this.L[p][q], this.K[p][q]);
						     deltaY += computeDy(keyArray[p].getX(), keyArray[q].getX(), keyArray[p].getY(), keyArray[q].getY(), this.L[p][q], this.K[p][q]);
						}
					}
					//if(p!=0) {//あるものを動かしたくないときはここで設定しないことで行う
					preDx=deltaX;
					preDy=deltaY;
					keyArray[p].setX( keyArray[p].getX() + this.C * (-1 * deltaX) );
					keyArray[p].setY( keyArray[p].getY() + this.C * (-1 * deltaY) );
					//}

					deltaX=0;
					deltaY=0;
				}
			}

		}

		System.out.println("最終結果");
		for(int y=0;y<node;y++) {
			keyArray[y].showKeywordInfo();
		}

		return keyArray;
	}

	public double computeE(Keyword[] keyArray, boolean plusDx, boolean plusDy) {
		Keyword[] tmp = keyArray;
		double energy = 0;
		double deltaX = 0;
		double deltaY = 0;
		if((plusDx==true)&&(plusDy==true)) {
			for(int p = 0; p < this.node; p++) {
				for(int q = 0; q < this.node; q++) {
					if(p!=q) {
					     deltaX += computeDx(tmp[p].getX(), tmp[q].getX(), tmp[p].getY(), tmp[q].getY(), this.L[p][q], this.K[p][q]);
					     deltaY += computeDy(tmp[p].getX(), tmp[q].getX(), tmp[p].getY(), tmp[q].getY(), this.L[p][q], this.K[p][q]);
					}
				}
				//if(p!=0) {//あるものを動かしたくないときはここで設定しないことで行う
				tmp[p].setX( tmp[p].getX() + this.C * deltaX );
				tmp[p].setY( tmp[p].getY() + this.C * deltaY );
				//}
				deltaX=0;
				deltaY=0;
			}
			for(int i=0;i<this.node;i++) {
				for(int j=0;j<this.node;j++) {
					if(i!=j) {
					energy += computeESub(tmp[i].getX(), tmp[j].getX(), tmp[i].getY(), tmp[j].getY(), this.L[i][j], this.K[i][j]);
					}
				}
			}
			return energy;
		}else if((plusDx==false)&&(plusDy==true)) {
			for(int p = 0; p < this.node; p++) {
				for(int q = 0; q < this.node; q++) {
					if(p!=q) {
					     deltaX += computeDx(tmp[p].getX(), tmp[q].getX(), tmp[p].getY(), tmp[q].getY(), this.L[p][q], this.K[p][q]);
					     deltaY += computeDy(tmp[p].getX(), tmp[q].getX(), tmp[p].getY(), tmp[q].getY(), this.L[p][q], this.K[p][q]);
					}
				}
				//if(p!=0) {//あるものを動かしたくないときはここで設定しないことで行う
				tmp[p].setX( tmp[p].getX() + this.C * (-1 * deltaX) );
				tmp[p].setY( tmp[p].getY() + this.C * deltaY );
				//}
				deltaX=0;
				deltaY=0;
			}
			for(int i=0;i<this.node;i++) {
				for(int j=0;j<this.node;j++) {
					if(i!=j) {
					energy += computeESub(tmp[i].getX(), tmp[j].getX(), tmp[i].getY(), tmp[j].getY(), this.L[i][j], this.K[i][j]);
					}
				}
			}
			return energy;
		}else if((plusDx==true)&&(plusDy==false)) {
			for(int p = 0; p < this.node; p++) {
				for(int q = 0; q < this.node; q++) {
					if(p!=q) {
					     deltaX += computeDx(tmp[p].getX(), tmp[q].getX(), tmp[p].getY(), tmp[q].getY(), this.L[p][q], this.K[p][q]);
					     deltaY += computeDy(tmp[p].getX(), tmp[q].getX(), tmp[p].getY(), tmp[q].getY(), this.L[p][q], this.K[p][q]);
					}
				}
				//if(p!=0) {//あるものを動かしたくないときはここで設定しないことで行う
				tmp[p].setX( tmp[p].getX() + this.C * deltaX );
				tmp[p].setY( tmp[p].getY() + this.C * (-1 * deltaY) );
				//}
				deltaX=0;
				deltaY=0;
			}
			for(int i=0;i<this.node;i++) {
				for(int j=0;j<this.node;j++) {
					if(i!=j) {
					energy += computeESub(tmp[i].getX(), tmp[j].getX(), tmp[i].getY(), tmp[j].getY(), this.L[i][j], this.K[i][j]);
					}
				}
			}
			return energy;
		}else if((plusDx==false)&&(plusDy==false)) {
			for(int p = 0; p < this.node; p++) {
				for(int q = 0; q < this.node; q++) {
					if(p!=q) {
					     deltaX += computeDx(tmp[p].getX(), tmp[q].getX(), tmp[p].getY(), tmp[q].getY(), this.L[p][q], this.K[p][q]);
					     deltaY += computeDy(tmp[p].getX(), tmp[q].getX(), tmp[p].getY(), tmp[q].getY(), this.L[p][q], this.K[p][q]);
					}
				}
				//if(p!=0) {//あるものを動かしたくないときはここで設定しないことで行う
				tmp[p].setX( tmp[p].getX() + this.C * (-1 * deltaX) );
				tmp[p].setY( tmp[p].getY() + this.C * (-1 * deltaY) );
				//}
				deltaX=0;
				deltaY=0;
			}
			for(int i=0;i<this.node;i++) {
				for(int j=0;j<this.node;j++) {
					if(i!=j) {
					energy += computeESub(tmp[i].getX(), tmp[j].getX(), tmp[i].getY(), tmp[j].getY(), this.L[i][j], this.K[i][j]);
					}
				}
			}
			return energy;
		}
		return 0;
	}

	public void setEachValue(Keyword[] keyArray) {
		//Rの設定
		//this.setRValue();
		//Dの設定
		this.setDValue(keyArray);
		//Lの設定
		this.setLValue(keyArray);
		//Kの設定
		this.setKValue(keyArray);
	}

	public int getNode() {
		return node;
	}

	//---------------------------------------------setEachValue用-----------------------------------------------------//
	private void setRValue() {
		for(int i = 0;i < this.node; i++) {
			for(int j = 0; j < this.node; j++) {
				//実際にはここで、それぞれのノード同士で合わせた関連度を設定する
				this.R[i][j] = -1;
			}
		}
	}

	private void setDValue(Keyword[] keyArray) {
		for(int i = 0; i < this.node; i++) {
			for(int i2 = 0; i2 < this.node; i2++) {
				if(i!=i2)
				this.D[i][i2] =  Math.sqrt( Math.pow((keyArray[i].getX() - keyArray[i2].getX()), 2) + Math.pow((keyArray[i].getY() - keyArray[i2].getY()), 2));
				else
				this.D[i][i2] = 0;
			}
		}
	}

	private void setLValue(Keyword[] keyArray) {
		for(int i = 0; i < node; i++) {
			for(int j = 0; j < node; j++) {
				this.L[i][j] = this.M * (1 / this.R[i][j]);
			}
		}
	}

	private void setKValue(Keyword[] keyArray) {
		for(int i = 0; i < node; i++) {
			for(int j = 0; j < node; j++) {
			  if(i!=j)
				this.K[i][j] = computeK(keyArray[i].getX(), keyArray[j].getX(), keyArray[i].getY(), keyArray[j].getY());
			  else
			    this.K[i][j] = 1;
			}
		}
	}
	private double computeK(double x1, double x2, double y1, double y2) {
		return this.cK / (Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
	}
	//---------------------------------------------setEachValue用-----------------------------------------------------//

	//---------------------------------------------computeMap用------------------------------------------------------//
	private double computeDx(double x1, double x2, double y1, double y2, double l, double k) {
		double res = 0;
		res = k * ( (x1-x2) - ( (l * (x1 -x2)) / ( Math.sqrt( (Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2) ) ) ) ));
		return res;
	}

	private double computeDy(double x1, double x2, double y1, double y2, double l, double k) {
		double res = 0;
		res = k * ( (y1-y2) - ( (l * (y1 -y2)) / ( Math.sqrt( (Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2) ) ) ) ));
		return res;
	}

	private double computeESub(double x1, double x2, double y1, double y2, double l, double k) {
		double res = 0;
		res = 0.5 * k * Math.pow( (  Math.sqrt( (Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2))) - l  ) , 2);
		return res;
	}
	//---------------------------------------------computeMap用------------------------------------------------------//
}
