package proto;

public class ComputeMap {
	private final double M = 50; //これを変えると各頂点間の距離が変わる
	private final int TRIALS = 1000;
	private final double cK = -10;
	private final double C = 0.5;

	private int node;
	private double[][] K;
	private double[][] L;
	private double[][] D;
	private double[][] R = {{0.3, 0.3, 0.3, 0.3},
            {0.3, 0.5, -1, -1},
            {0.3, -1, 0.5, -1},
            {0.3, -1, -1, 0.5}};
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
			System.out.println(energy);
			if(energy==Double.NEGATIVE_INFINITY)
				break;
			//System.out.println("=========================");

			//新しい座標の計算
			for(int p = 0; p < this.node; p++) {
				for(int q = 0; q < this.node; q++) {
					if(p!=q) {
					     deltaX += computeDx(keyArray[p].getX(), keyArray[q].getX(), keyArray[p].getY(), keyArray[q].getY(), this.L[p][q], this.K[p][q]);
					     deltaY += computeDy(keyArray[p].getX(), keyArray[q].getX(), keyArray[p].getY(), keyArray[q].getY(), this.L[p][q], this.K[p][q]);
					}
				}
				keyArray[p].setX( keyArray[p].getX() + this.C * deltaX );
				keyArray[p].setY( keyArray[p].getY() + this.C * deltaY );
				deltaX=0;
				deltaY=0;
			}

		}

		System.out.println("最終結果");
		for(int y=0;y<node;y++) {
			keyArray[y].showKeywordInfo();
		}

		return keyArray;
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
				this.L[i][j] = this.M * (1 - this.R[i][j]);
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
