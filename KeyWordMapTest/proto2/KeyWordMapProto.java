package proto2;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class KeyWordMapProto extends Application{

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Test");
		stage.setWidth(600);
		stage.setHeight(600);

		Group root = new Group();

		final Canvas canvas = new Canvas(1000,1000);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		//初期配置の描画
		Keyword[] key = setKeyword();
		/*for(int i=0;i<4;i++) {
				this.setCircle(gc,key[i].getX()+100,key[i].getY()+100);
		}*/
		key = compute(key);
		//更新後の描画
		//gc.strokeOval(101,101,15,15);
		Color[] color = new Color[6];
		color[0] = Color.RED;
		color[1] = Color.BLUE;
		color[2] = Color.YELLOW;
		color[3] = Color.GREEN;
		color[4] = Color.PURPLE;
		color[5] = Color.BURLYWOOD;
		for(int i=0;i<6;i++) {
			gc.setFill(color[i]);
			this.setCircle(gc,key[i].getX()+canvas.getWidth()/2,key[i].getY()+canvas.getHeight()/2);
	    }

		/*try {
		Thread.sleep(5000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}*/


		root.getChildren().add(canvas);
		/*gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
		key = compute(key);
		//更新後の描画
		for(int i=0;i<4;i++) {
			this.setCircle(gc,key[i].getX()*10,key[i].getY()*10);
	    }*/
		//canvasクリア用　これを次のグラフを新たに作成する際に使えるか？
		//gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());

		//または、アニメーションでどんどん描画していく

		stage.setScene(new Scene(root));
		stage.show();
	}

	public void setCircle(GraphicsContext gc, double x, double y) {
		gc.fillRect(x,y,20,20);
	}
	public int x() {
		return 10;
	}

	public Keyword[] setKeyword() {
		//キーワードの設定
		Keyword[] keyArray = new Keyword[6];
		keyArray[0] = new Keyword(1.0, 1.0);
		keyArray[1] = new Keyword(3.0, -1.5);
		keyArray[2] = new Keyword(-5.0, 4.0);
		keyArray[3] = new Keyword(-11.0, -10.0);
		keyArray[4] = new Keyword(125.0,125.0);
		keyArray[5] = new Keyword(125.0,-125.0);
		//どうやら初期値は下の方が良さそう
		 /*keyArray[0].setXY(0, 100);
		 keyArray[1].setXY(100, 0);
		 keyArray[2].setXY(0, -100);
		 keyArray[3].setXY(-100, 0);*/
		keyArray[0].setXY(0, 250);
		 keyArray[1].setXY(250, 0);
		 keyArray[2].setXY(0, -250);
		 keyArray[3].setXY(-250, 0);
		 keyArray[4].setXY(125,125);

		return keyArray;
	}

	public Keyword[] compute(Keyword[] keyArray) {
		ComputeMap cm = new ComputeMap(6);
		cm.setEachValue(keyArray);
		return cm.computeMap(keyArray);
	}

}
