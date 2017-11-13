package p1;

public class KeyWordMap {

	public KeyWordMap() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public static void main(String[] args) {
		int TRIALS = 100;
		double R = 0.5;
		//まずは、データの生成[
		Keyword[] keyArray = new Keyword[5];
		keyArray[0] = new Keyword(1.0, 1.0, R);
		keyArray[1] = new Keyword(2.0, 1.5, R);
		keyArray[2] = new Keyword(3.0, 4.0, R);
		keyArray[3] = new Keyword(6.0, 5.0, R);
		keyArray[4] = new Keyword(4.0, 1.0, R);
		//データをcalculateに渡す
		Calculate calc = new Calculate();

		for(int i = 0; i<TRIALS; i++) {
			System.out.println("TRIALS:"+(i+1));
			for(int k=0;k<5;k++) {
				keyArray[k].showKeywordInfo();
			}
			System.out.println("=========================");
			for(int j=0; j<4; j++) {
			 for(int l=1; l<4; l++) {
				keyArray[j].setX( keyArray[j].getX() + calc.calcMovementX(keyArray[j].getX(), keyArray[l].getX(), keyArray[j].getY(), keyArray[l].getY(), calc.calcIdealLength(keyArray[j].getR()) ));
				keyArray[j].setY( keyArray[j].getY() + calc.calcMovementY(keyArray[j].getY(), keyArray[l].getY(), keyArray[j].getX(), keyArray[l].getX(), calc.calcIdealLength(keyArray[j].getR()) ));
			 }
			 }
			keyArray[4].setX( keyArray[4].getX() + calc.calcMovementX(keyArray[4].getX(), keyArray[0].getX(), keyArray[4].getY(), keyArray[0].getY(), calc.calcIdealLength(keyArray[4].getR()) ));
			keyArray[4].setY( keyArray[4].getY() + calc.calcMovementY(keyArray[4].getY(), keyArray[0].getY(), keyArray[4].getX(), keyArray[0].getX(), calc.calcIdealLength(keyArray[4].getR()) ));
		}
	}



}
